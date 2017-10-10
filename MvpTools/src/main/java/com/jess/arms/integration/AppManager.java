package com.jess.arms.integration;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.jess.arms.R;
import com.jess.arms.widget.dialog.alertview.AlertView;
import com.jess.arms.widget.dialog.alertview.OnClickListener;
import com.jess.arms.widget.dialog.alertview.OnItemClickListener;
import com.jess.arms.widget.dialog.loading.LoadingView;
import com.jess.arms.widget.dialog.loading.OnCancelListener;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * 用于管理所有activity,和在前台的 activity
 * 可以通过直接持有AppManager对象执行对应方法
 * 也可以通过eventbus post事件,远程遥控执行对应方法
 * Created by jess on 14/12/2016 13:50
 * Contact with jess.yan.effort@gmail.com
 */
@Singleton
public final class AppManager {
    protected final String TAG = this.getClass().getSimpleName();
    public static final String APPMANAGER_MESSAGE = "appmanager_message";
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_not_add_activity_list";//true 为不需要加入到 Activity 容器进行统一管理,反之亦然
    public static final int START_ACTIVITY = 0x00;
    public static final int SHOW_SNACKBAR = 0x01;
    public static final int KILL_ALL = 0x02;
    public static final int APP_EXIT = 0x03;
    public static final int SHOW_ALERTVIEW_CENTER = 0x04;
    public static final int SHOW_ALERTVIEW_BOTTOM = 0x05;
    public static final int SHOW_LOADING = 0x06;
    public static final int DISSMISSLOADING = 0x07;
    public static final int SHOW_TOAST = 0x08;


    private Application mApplication;

    //管理所有activity
    public List<Activity> mActivityList;
    //当前在前台的activity
    private Activity mCurrentActivity;

    private Toast mToast;
    private Toast mToastBottom;
    //    private Dialog mDialog = null;
    private LoadingView mLoadingView;
    private View toastView;

    @Inject
    public AppManager(Application application) {
        this.mApplication = application;
        EventBus.getDefault().register(this);
    }


    /**
     * 通过eventbus post事件,远程遥控执行对应方法
     */
    @Subscriber(tag = APPMANAGER_MESSAGE, mode = ThreadMode.MAIN)
    public void onReceive(Message message) {
        switch (message.what) {
            case START_ACTIVITY:
                if (message.obj == null)
                    break;
                dispatchStart(message);
                break;
            case SHOW_SNACKBAR:
                if (message.obj == null)
                    break;
                showSnackbar((String) message.obj, message.arg1 == 0 ? false : true);
                break;
            case KILL_ALL:
                killAll();
                break;
            case APP_EXIT:
                appExit();
                break;
            case SHOW_ALERTVIEW_CENTER:
                Bundle bundle_c = message.getData();
                if (bundle_c == null) return;
                showAlertCenterDialog(bundle_c.getString("title"), bundle_c.getString("msg"), bundle_c.getBoolean("isAccentSureButton"), ((OnClickListener) message.obj));
                break;
            case SHOW_ALERTVIEW_BOTTOM:
                Bundle bundle_b = message.getData();
                if (bundle_b == null) return;
                showAlertBottomDialog(bundle_b.getString("title"), bundle_b.getString("msg")
                        , bundle_b.getStringArray("accentStrs"), bundle_b.getStringArray("strs"), ((OnItemClickListener) message.obj));
                break;
            case SHOW_LOADING:
                Bundle bundle_l = message.getData();
                if (bundle_l == null) return;
                showLoading(bundle_l.getString("msg"),
                        bundle_l.getBoolean("cancleable"), message.obj == null ? null : (OnCancelListener) message.obj);
                break;
            case DISSMISSLOADING:
                dissMissLoadingDialog();
                break;
            case SHOW_TOAST:
                if (message.obj == null)
                    break;
                showToast((String) message.obj, message.arg1 == 2 ? true : false);
                break;
            default:
                Timber.tag(TAG).w("The message.what not match");
                break;
        }
    }


    private void showToast(String obj, boolean b) {
        if (mApplication == null) {
            Timber.tag(TAG).w("mApplication was null");
            return;
        }
        showT(obj, b);
    }

    private void showT(String str, boolean isLong) {
        if (mToastBottom == null) {
            mToastBottom = Toast.makeText(mApplication.getApplicationContext(), str, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
            LayoutInflater inflate = (LayoutInflater)
                    mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            toastView = inflate.inflate(R.layout.dialogui_toast, null);
            mToastBottom.setView(toastView);
            mToastBottom.setGravity(Gravity.BOTTOM, 0, mApplication.getResources().getDimensionPixelSize(R.dimen.dialogui_toast_margin));
        }
        mToast = mToastBottom;
        mToast.setText(str);
        mToast.show();
    }

    private void dispatchStart(Message message) {
        if (message.obj instanceof Intent)
            startActivity((Intent) message.obj);
        else if (message.obj instanceof Class)
            startActivity((Class) message.obj);
    }


    /**
     * 使用snackbar显示内容
     *
     * @param message
     * @param isLong
     */
    public void showSnackbar(String message, boolean isLong) {
        if (getCurrentActivity() == null) {
            Timber.tag(TAG).w("mCurrentActivity == null when showSnackbar(String,boolean)");
            return;
        }
        View view = getCurrentActivity().getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, message, isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT).show();
    }


    /**
     * 展示一个中间显示的对话框
     *
     * @param title              标题
     * @param contentMsg         内容
     * @param isAccentSureButton 是否对确定按钮的文字高亮显示
     * @param onClickListener    监听
     */
    private void showAlertCenterDialog(@NonNull String title, @NonNull String contentMsg, boolean isAccentSureButton, OnClickListener onClickListener) {
        AlertView.Builder builder = new AlertView.Builder();
        builder.setContext(mCurrentActivity);
        builder.setTitle(TextUtils.isEmpty(title) ? "提示" : title);
        builder.setCancelText("取消");
        builder.setMessage(TextUtils.isEmpty(contentMsg) ? "" : contentMsg);
        if (isAccentSureButton) {
            builder.setDestructive("确定");//红色
        } else {
            builder.setOthers(new String[]{"确定"});//蓝色
        }
        builder.setStyle(AlertView.Style.Alert);
        if (onClickListener != null) {
            builder.setOnItemClickListener((o, position) -> {
                switch (position) {
                    case -1:
                        onClickListener.onCancle();
                        break;
                    case 0:
                        onClickListener.onSubmit();
                        break;
                }
            });
        }
        builder.build().show();
    }

    /**
     * 展示一个底部显示的对话框
     *
     * @param title               标题 可为空
     * @param contentMsg          内容 可为空
     * @param accentStrs          高亮显示的文本item 可为空
     * @param strs                普通item  可为空
     * @param onItemClickListener 点击事件
     */
    private void showAlertBottomDialog(@NonNull String title, @NonNull String contentMsg, String[] accentStrs, String[] strs, OnItemClickListener onItemClickListener) {
        AlertView.Builder builder = new AlertView.Builder();
        builder.setContext(mCurrentActivity);
        builder.setTitle(TextUtils.isEmpty(title) ? "提示" : title);
        builder.setCancelText("取消");
        builder.setMessage(TextUtils.isEmpty(contentMsg) ? "" : contentMsg);
        builder.setStyle(AlertView.Style.ActionSheet);
        builder.setDestructive(accentStrs);
        builder.setOthers(strs);
        if (onItemClickListener != null) builder.setOnItemClickListener(onItemClickListener);
        builder.build().show();
    }

    /**
     * 展示一个简易进度条
     *
     * @param msg              显示的文本内容
     * @param cancleable       是否可手动关闭
     * @param onCancelListener 取消的监听
     */
    private void showLoading(CharSequence msg, boolean cancleable, OnCancelListener onCancelListener) {
        mLoadingView = LoadingView.init(new WeakReference<Activity>(mCurrentActivity).get()).showLoading(msg, cancleable);
        if (onCancelListener != null) {
            mLoadingView.setOnCancelListener(() -> onCancelListener.onCancel());
        }
    }

    private void dissMissLoadingDialog() {
        try {
            mLoadingView.dissMissDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 让在前台的activity,打开下一个activity
     *
     * @param intent
     */
    public void startActivity(Intent intent) {
        if (getCurrentActivity() == null) {
            Timber.tag(TAG).w("mCurrentActivity == null when startActivity(Intent)");
            //如果没有前台的activity就使用new_task模式启动activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplication.startActivity(intent);
            return;
        }
        getCurrentActivity().startActivity(intent);
    }

    /**
     * 让在前台的activity,打开下一个activity
     *
     * @param activityClass
     */
    public void startActivity(Class activityClass) {
        startActivity(new Intent(mApplication, activityClass));
    }


    /**
     * 将在前台的activity保存
     *
     * @param currentActivity
     */
    public void setCurrentActivity(Activity currentActivity) {
        this.mCurrentActivity = currentActivity;
    }

    /**
     * 获得当前在前台的activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        return mCurrentActivity != null ?
                mCurrentActivity : mActivityList != null && mActivityList.size() > 0 ?
                mActivityList.get(mActivityList.size() - 1) : null;
    }

    /**
     * 返回一个存储所有未销毁的activity的集合
     *
     * @return
     */
    public List<Activity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }


    /**
     * 添加Activity到集合
     */
    public void addActivity(Activity activity) {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        synchronized (AppManager.class) {
            if (!mActivityList.contains(activity)) {
                mActivityList.add(activity);
            }
        }
    }

    /**
     * 删除集合里的指定activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when removeActivity(Activity)");
            return;
        }
        synchronized (AppManager.class) {
            if (mActivityList.contains(activity)) {
                mActivityList.remove(activity);
            }
        }
    }

    /**
     * 删除集合里的指定位置的activity
     *
     * @param location
     */
    public Activity removeActivity(int location) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when removeActivity(int)");
            return null;
        }
        synchronized (AppManager.class) {
            if (location > 0 && location < mActivityList.size()) {
                return mActivityList.remove(location);
            }
        }
        return null;
    }

    /**
     * 关闭指定activity
     *
     * @param activityClass
     */
    public void killActivity(Class<?> activityClass) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when killActivity");
            return;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                activity.finish();
            }
        }
    }


    /**
     * 指定的activity实例是否存活
     *
     * @param activity
     * @return
     */
    public boolean activityInstanceIsLive(Activity activity) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when activityInstanceIsLive");
            return false;
        }
        return mActivityList.contains(activity);
    }


    /**
     * 指定的activity class是否存活(一个activity可能有多个实例)
     *
     * @param activityClass
     * @return
     */
    public boolean activityClassIsLive(Class<?> activityClass) {
        if (mActivityList == null) {
            Timber.tag(TAG).w("mActivityList == null when activityClassIsLive");
            return false;
        }
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(activityClass)) {
                return true;
            }
        }

        return false;
    }


    /**
     * 关闭所有activity
     */
    public void killAll() {
        Iterator<Activity> iterator = getActivityList().iterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            iterator.remove();
            next.finish();
        }

    }

    /**
     * 释放资源
     */
    public void release() {
        EventBus.getDefault().unregister(this);
//        PgyCrashManager.unregister();
        mActivityList.clear();
        mActivityList = null;
        mCurrentActivity = null;
        toastView = null;
//        mDialog = null;
        this.mLoadingView = null;
        mToast = null;
        mToastBottom = null;
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            killAll();
            release();
            if (mActivityList != null)
                mActivityList = null;
            ActivityManager activityMgr =
                    (ActivityManager) mApplication.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(mApplication.getPackageName());
            mApplication = null;
            activityMgr = null;
            System.exit(0);
            for (int i = 0; i < 100; i++) {
                System.gc();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
