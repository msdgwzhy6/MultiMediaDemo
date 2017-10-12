package com.hyty.cordova.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.util.ScreenUtils;
import com.hyty.cordova.MultiMediaConfig;
import com.hyty.cordova.R;
import com.hyty.cordova.bean.Key;
import com.hyty.cordova.camera.JCameraView;
import com.hyty.cordova.camera.listener.ErrorListener;
import com.hyty.cordova.camera.listener.JCameraListener;
import com.hyty.cordova.di.component.DaggerTakeCameraComponent;
import com.hyty.cordova.di.module.TakeCameraModule;
import com.hyty.cordova.mvp.contract.TakeCameraContract;
import com.hyty.cordova.mvp.impl.CopyFilesListener;
import com.hyty.cordova.mvp.presenter.TakeCameraPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.Calendar;

import timber.log.Timber;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * ================================================================
 * 创建时间:2017-10-10 19:07:25
 * 创建人:赵文贇
 * 文件描述：拍照页面
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class TakeCameraActivity extends BaseActivity<TakeCameraPresenter> implements TakeCameraContract.View {


    JCameraView mCameraView;
    private MultiMediaConfig mMultiMediaConfig;//全局单例配置类
    private int resultcode = -1;//响应码

    private SensorManager mSensorManager;
    private Sensor mSensor;//加速度传感器，用来控制自动对焦
    boolean isFocusing = false;
    boolean canFocusIn = false;  //内部是否能够对焦控制机制
    boolean canFocus = false;
    public static final int DELEY_DURATION = 500;

    public static final int STATUS_NONE = 0;
    public static final int STATUS_STATIC = 1;
    public static final int STATUS_MOVE = 2;
    private int STATUE = STATUS_NONE;

    private int foucsing = 1;
    private int mX, mY, mZ;
    private long lastStaticStamp = 0;
    private Calendar mCalendar;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTakeCameraComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .takeCameraModule(new TakeCameraModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return R.layout.activity_take_camera; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        findId();
        mMultiMediaConfig = MultiMediaConfig.getInstance();
        resultcode = getIntent().getIntExtra(Key.REQUEST_CODE, -1);
        //设置参数
        int mCameraFeatures = 0;
        switch (mMultiMediaConfig.getCameraFeature()) {
            case BUTTON_STATE_BOTH:
                mCameraFeatures = JCameraView.BUTTON_STATE_BOTH;
                break;
            case BUTTON_STATE_ONLY_CAPTURE:
                mCameraFeatures = JCameraView.BUTTON_STATE_ONLY_CAPTURE;//仅可拍照
                break;
            case BUTTON_STATE_ONLY_RECORDER:
                mCameraFeatures = JCameraView.BUTTON_STATE_ONLY_RECORDER;//仅可录像
                break;
        }
        mCameraView.setFeatures(mCameraFeatures);
        mCameraView.setContinuousCapture(true);
        mCameraView.setTip("点击拍照");
        mCameraView.setErrorLisenter(mErrorListener);
        mCameraView.setJCameraLisenter(mCameraListener);
        mCameraView.setLeftClickListener(() -> finishPage(null));
        mCameraView.setRightClickListener(() -> {
            showLoading("正在处理图片，请稍后...");
            mPresenter.getResultData(new CopyFilesListener() {
                @Override
                public void onSucc(ArrayList<String> toFilesPath) {
                    Intent mIntent = new Intent();
                    mIntent.putStringArrayListExtra(Key.RESULT_INTENT, toFilesPath);
                    hideLoading();
                    finishPage(mIntent);
                }

                @Override
                public void onError(String errorMsg) {
                    hideLoading();
                    if (!TextUtils.isEmpty(errorMsg)) showMessage(errorMsg);
                    finishPage(null);
                }
            });
        });
        //加速度传感器
        mSensorManager = (SensorManager) getSystemService(Activity.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);// TYPE_GRAVITY

    }

    private void finishPage(Intent mIntent) {
        setResult(resultcode, mIntent);
        finish();
    }

    /**
     * 录像、拍照成功的回调
     */
    private JCameraListener mCameraListener = new JCameraListener() {
        @Override
        public void captureSuccess(Bitmap bitmap) {
            //将图片保存到相册目录下
            mPresenter.saveBitmapToCameraFile(bitmap);
        }

        @Override
        public void recordSuccess(String url, Bitmap firstFrame, long time) {

        }
    };

    /**
     * 错误监听
     */
    private ErrorListener mErrorListener = new ErrorListener() {
        @Override
        public void onError() {
            Timber.e("拍照页面发生错误");
        }

        @Override
        public void AudioPermissionError() {
            Timber.e("没有授予录音权限");
        }
    };

    private void findId() {
        mCameraView = (JCameraView) findViewById(R.id.camera_view);
    }


    @Override
    public void showLoading() {
        ArmsUtils.showLoading("加载中...", true, null);
    }

    @Override
    public void hideLoading() {
        ArmsUtils.dissMissLoading();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.showToast(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
        restParams();
        canFocus = true;
        mSensorManager.registerListener(mSensorEventListener, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        Timber.d("加速度传感器注册成功");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(mSensorEventListener, mSensor);
        canFocus = false;
        Timber.d("加速度传感器注销成功");
    }

    /**
     * 展示一个指定msg的进度条
     *
     * @param msg
     */
    @Override
    public void showLoading(String msg) {
        ArmsUtils.showLoading(msg, false, null);
    }


    /**
     * 加速度控制器  用来控制对焦
     */
    private SensorEventListener mSensorEventListener = new SensorEventListener2() {
        @Override
        public void onFlushCompleted(Sensor mSensor) {

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor == null) {
                return;
            }

            if (isFocusing) {
                restParams();
                return;
            }

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                int x = (int) event.values[0];
                int y = (int) event.values[1];
                int z = (int) event.values[2];
                mCalendar = Calendar.getInstance();
                long stamp = mCalendar.getTimeInMillis();// 1393844912

                int second = mCalendar.get(Calendar.SECOND);// 53

                if (STATUE != STATUS_NONE) {
                    int px = Math.abs(mX - x);
                    int py = Math.abs(mY - y);
                    int pz = Math.abs(mZ - z);
//                Log.d(TAG, "pX:" + px + "  pY:" + py + "  pZ:" + pz + "    stamp:"
//                        + stamp + "  second:" + second);
                    double value = Math.sqrt(px * px + py * py + pz * pz);
                    if (value > 1.4) {
//                    textviewF.setText("检测手机在移动..");
//                    Log.i(TAG,"mobile moving");
                        STATUE = STATUS_MOVE;
                    } else {
//                    textviewF.setText("检测手机静止..");
//                    Log.i(TAG,"mobile static");
                        //上一次状态是move，记录静态时间点
                        if (STATUE == STATUS_MOVE) {
                            lastStaticStamp = stamp;
                            canFocusIn = true;
                        }

                        if (canFocusIn) {
                            if (stamp - lastStaticStamp > DELEY_DURATION) {
                                //移动后静止一段时间，可以发生对焦行为
                                if (!isFocusing) {
                                    canFocusIn = false;
                                    mCameraView.setFocusViewWidthAnimation(ScreenUtils.getScreenWidth()/2,ScreenUtils.getScreenHeight()/2);
//                                onCameraFocus();
//                                    if (mCameraFocusListener != null) {
//                                        mCameraFocusListener.onFocus();
//                                    }
//                                Log.i(TAG,"mobile focusing");
                                }
                            }
                        }

                        STATUE = STATUS_STATIC;
                    }
                } else {
                    lastStaticStamp = stamp;
                    STATUE = STATUS_STATIC;
                }

                mX = x;
                mY = y;
                mZ = z;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor mSensor, int mI) {

        }
    };


    private void restParams() {
        STATUE = STATUS_NONE;
        canFocusIn = false;
        mX = 0;
        mY = 0;
        mZ = 0;
    }

    /**
     * 对焦是否被锁定
     *
     * @return
     */
    public boolean isFocusLocked() {
        if (canFocus) {
            return foucsing <= 0;
        }
        return false;
    }

    /**
     * 锁定对焦
     */
    public void lockFocus() {
        isFocusing = true;
        foucsing--;
    }

    /**
     * 解锁对焦
     */
    public void unlockFocus() {
        isFocusing = false;
        foucsing++;
    }

    public void restFoucs() {
        foucsing = 1;
    }

    public interface CameraFocusListener {
        void onFocus();
    }
}
