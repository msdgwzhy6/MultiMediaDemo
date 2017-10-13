package com.hyty.cordova.plugins;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hyty.cordova.MultiMediaConfig;
import com.hyty.cordova.bean.CameraFeature;
import com.hyty.cordova.bean.ConfigParams;
import com.hyty.cordova.bean.Key;
import com.hyty.cordova.camera.util.FileUtil;
import com.hyty.cordova.camera.util.ViewUtils;
import com.hyty.cordova.imagepicker.ImagePicker;
import com.hyty.cordova.imagepicker.loader.GlideImageLoader;
import com.hyty.cordova.imagepicker.ui.ImageGridActivity;
import com.hyty.cordova.mvp.ui.activity.TakeCameraActivity;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.logger.AndroidLogAdapter;
import com.jess.arms.utils.logger.Logger;
import com.jess.arms.utils.logger.PrettyFormatStrategy;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;

import butterknife.ButterKnife;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import timber.log.Timber;

/**
 * ================================================================
 * 创建时间：2017/10/10 16:06
 * 创建人：赵文贇
 * 文件描述：插件入口
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class MultiMediaPlugin extends CordovaPlugin {
//    private static MultiMediaPlugin mPlugin;// TODO: 2017/10/10 记得删除单利测试模式
    private MultiMediaConfig mMediaConfig;//全局配置类
    private static boolean isInitLog = false;//日志初始化标识符
    private Activity mActivity;//super.cordova.getActivity();
    private ImagePicker mImagePicker;//图片加载器

//    public static MultiMediaPlugin getInstance(Activity mActivity) {
//        mPlugin = mPlugin == null ? new MultiMediaPlugin(mActivity) : mPlugin;
//        return mPlugin;
//    }

//    private MultiMediaPlugin(Activity mActivity) {
//        Utils.init(mActivity.getApplication());
//        mMediaConfig = MultiMediaConfig.getInstance();
//        mImagePicker = ImagePicker.getInstance();
//        mImagePicker.setImageLoader(new GlideImageLoader());
//        this.mActivity = mActivity;
//    }

    @Override
    public boolean execute(String action, String rawArgs, CallbackContext callbackContext) throws JSONException {
        if (TextUtils.isEmpty(rawArgs)) {
            Timber.e("传入参数不能为空");
            return false;
        }
        mMediaConfig = MultiMediaConfig.getInstance();
        mImagePicker = ImagePicker.getInstance();
        mImagePicker.setImageLoader(new GlideImageLoader());
        ConfigParams mConfigParams = null;
        try {
            mConfigParams = new Gson().fromJson(rawArgs, ConfigParams.class);
        } catch (JsonSyntaxException mE) {
            mE.printStackTrace();
            Timber.e("传入参数解析出错");
        }
        if (mConfigParams == null || mConfigParams.getType() == 0 || TextUtils.isEmpty(mConfigParams.getFolderName())) {
            Timber.e("传入参数不合法,检测 Type 、FolderName是否传入");
            return false;
        }
        Timber.d("传入参数:" + rawArgs);
        mActivity = super.cordova.getActivity();// TODO: 2017/10/10 正式模式记得打开此处注释
        requestPermissions(mConfigParams);//请求权限
        return true;
    }

    /**
     * 权限申请
     *
     * @param mConfigParams
     */
    private void requestPermissions(ConfigParams mConfigParams) {
        PermissionUtil.requestPermission(new PermissionUtil.RequestPermission() {
                                             @Override
                                             public void onRequestPermissionSuccess() {
                                                 doSomething(mConfigParams);
                                             }

                                             @Override
                                             public void onRequestPermissionFailure() {
                                                 ViewUtils.showDialog(mActivity, "授权失败，是否重新授权？", new ViewUtils.DialogClickListenr() {
                                                     @Override
                                                     public void onClickSubmit() {
                                                         requestPermissions(mConfigParams);
                                                     }

                                                     @Override
                                                     public void onClickCancel() {
                                                         mActivity.finish();
                                                     }
                                                 });
                                             }
                                         }, new RxPermissions(mActivity), mMediaConfig.getRxErrorHandler(mActivity.getApplication()), Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
//                Manifest.permission.WAKE_LOCK,
//                Manifest.permission.WRITE_SETTINGS
        );
    }

    /**
     * 业务操作在这里
     *
     * @param mConfigParams
     */
    private void doSomething(ConfigParams mConfigParams) {
        mMediaConfig.setFileSavedPath(mConfigParams.getFolderName());//设置全局存储文件的目录名称
        mMediaConfig.setFlagText(mConfigParams.getFlagText());
        init();//初始化参数
        initFile();//初始化文件夹
        Intent mIntent = null;
        int requestCode = 0;
        switch (mConfigParams.getType()) {
            case 1:
                // TODO: 2017/10/10 直接打开拍照页面，需要最大可拍照数量，拍照结束后点击返回按钮关闭所有页面不返回任何参数，点击保存按钮对图片进行copy、压缩(只压缩copy进指定文件夹的图片) ,并将结果返回给调用者
                // TODO: 2017/10/10 需要参数:最大可拍照数量、copy的路径、水印文字
                //设置内部参数
                mMediaConfig.setCameraFeature(CameraFeature.BUTTON_STATE_ONLY_CAPTURE);
                mMediaConfig.setMaxOptionalNum(mConfigParams.getMaxOptionalNum() == 0 ? 9 : mConfigParams.getMaxOptionalNum());
                mIntent = createIntent(TakeCameraActivity.class, MultiMediaConfig.REQUEST_CODE_HOME_TAKECAMERA);
                requestCode = MultiMediaConfig.REQUEST_CODE_HOME_TAKECAMERA;
                Timber.d("跳转快速拍照页面(模式:仅拍照),存储的文件夹名称:" + mConfigParams.getFolderName() + ",最大可选:" + mConfigParams.getMaxOptionalNum() + ",水印文字:" + mConfigParams.getFlagText());
                break;
            case 2:
                // TODO: 2017/10/12 打开多图选择页面 带拍照按钮
                // TODO: 2017/10/12 需要参数:最大可选数量、copy的路径、水印文字
                //设置内部参数
                mMediaConfig.setCameraFeature(CameraFeature.BUTTON_STATE_ONLY_CAPTURE);
                mImagePicker.setCrop(false);//关闭裁剪
                mImagePicker.setShowCamera(true);
                mMediaConfig.setMaxOptionalNum(mConfigParams.getMaxOptionalNum() == 0 ? 9 : mConfigParams.getMaxOptionalNum());
                mIntent = createIntent(ImageGridActivity.class, MultiMediaConfig.REQUEST_CODE_HOME_IMAGE_PIKER);
                requestCode = MultiMediaConfig.REQUEST_CODE_HOME_IMAGE_PIKER;
                Timber.d("跳转多图选择+拍照页面(模式:多图选择+拍照),存储的文件夹名称:" + mConfigParams.getFolderName() + ",最大可选:" + mConfigParams.getMaxOptionalNum() + ",水印文字:" + mConfigParams.getFlagText());
                break;
            case 3:
                // TODO: 2017/10/13 图片预览模式 可选择是否具备删除功能
                // TODO: 2017/10/13  需要参数:copy的路径、是否具备删除功能
                if (TextUtils.isEmpty(mConfigParams.getUrlPathHeader())
                        || mConfigParams.getData() == null
                        || mConfigParams.getData().size() == 0) {
                    Timber.e("预览时参数不能为空，请检查UrlPathHeader、Data字段");
                    return;
                }
                mImagePicker.setCrop(false);//关闭裁剪
                mImagePicker.setShowCamera(false);
                mMediaConfig.setCanDelete(mConfigParams.isCanDelete());
                mMediaConfig.setParentUrl(mConfigParams.getUrlPathHeader());
                mMediaConfig.setPreViewData(mConfigParams.getData());
                mIntent = createIntent(ImageGridActivity.class, MultiMediaConfig.REQUEST_CODE_HOME_IMAGE_PREVIEW);
                requestCode = MultiMediaConfig.REQUEST_CODE_HOME_IMAGE_PREVIEW;
                Timber.d("跳转图片预览页面(是否具备删除功能:" + mConfigParams.isCanDelete() + "),存储的文件夹名称:" + mConfigParams.getFolderName() + ",网络请求前缀:" + mConfigParams.getUrlPathHeader() + ",预览的数据长度:" + mConfigParams.getData().size());
                break;
        }
        if (mIntent == null || requestCode == 0) {
            ArmsUtils.showToast("参数为空");
            return;
        }
        mMediaConfig.setFolderName(TextUtils.isEmpty(mConfigParams.getFolderName()) ? "defaultfolder" : mConfigParams.getFolderName());
        mMediaConfig.setDoType(mConfigParams.getType());
        startActivityForResult(mIntent, requestCode);
    }


    /**
     * 初始化存储目录，不存在则创建
     */
    private void initFile() {
        File saveFilesDir = new File(mMediaConfig.getFileSavedPath());
        File cameraDir = new File(MultiMediaConfig.CAMERA_FILE_PATH);

        if (!saveFilesDir.exists())
            saveFilesDir.mkdir();

        if (!cameraDir.exists())
            cameraDir.mkdir();
        Timber.d(cameraDir.exists() ? "相册目录存在,路径:" + cameraDir.getPath() : "相册目录不存在，创建成功,路径:" + cameraDir.getPath());
        Timber.d(saveFilesDir.exists() ? "文件存储目录存在,路径:" + saveFilesDir.getPath() : "文件存储目录不存在，创建成功,路径:" + saveFilesDir.getPath());
    }


    /*未使用的构造*/
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return false;
    }

    /*未使用的构造*/
    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        return false;
    }

    /**
     * 日志初始化
     */
    private static void init() {
        if (isInitLog) {
            return;
        }
         /*初始化日志打印工具,Timber内部可以动态的切换成任何日志框架(打印策略)进行日志打印,内部可以做到同时使用多个策略,比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器*/
        Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                .methodOffset(5).build()));

        Timber.plant(new Timber.DebugTree() {
            @Override
            protected void log(int priority, String tag, String message, Throwable t) {
                Logger.log(priority, tag, message, t);
            }
        });
        ButterKnife.setDebug(true);
        Timber.tag("MultiMediaPlugin");
        isInitLog = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Timber.d("MultiMediaPlugin - onActivityResult :requestCode = " + requestCode + ",resultCode = " + resultCode + ",intent == null?" + (intent == null));
        if (intent == null) return;
        if (requestCode == MultiMediaConfig.REQUEST_CODE_HOME_TAKECAMERA
                && resultCode == MultiMediaConfig.REQUEST_CODE_HOME_TAKECAMERA) {
            Timber.d("仅拍照模式返回数据:(未确定，未打印)");
        }
    }

    /**
     * 内部使用方法
     *
     * @param mIntent
     * @param requestCode
     */
    private void startActivityForResult(Intent mIntent, int requestCode) {
        mActivity.startActivityForResult(mIntent, requestCode);
    }

    private Intent createIntent(Class<? extends Activity> mClass, int requestCode) {
        Intent mIntent = new Intent(mActivity, mClass);
        mIntent.putExtra(Key.REQUEST_CODE, requestCode);
        return mIntent;
    }
}
