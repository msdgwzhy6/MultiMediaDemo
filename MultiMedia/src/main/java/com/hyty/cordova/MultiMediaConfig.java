package com.hyty.cordova;

import android.os.Environment;

import com.hyty.cordova.bean.CameraFeature;

/**
 * ================================================================
 * 创建时间：2017/10/10 16:33
 * 创建人：赵文贇
 * 文件描述：存放插件的全局参数和工具
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class MultiMediaConfig {
    private static MultiMediaConfig mMediaConfig;//单例模式
    public static final String CAMERA_FILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Camera";//系统相册目录
    public static final String FILE_SAVED_PATH = Environment.getExternalStorageDirectory().toString() + "/";//文件存储目录头
    public static final int REQUEST_CODE_HOME_TAKECAMERA = 0x01;//从插件首页跳转拍照页面的请求码

    public static MultiMediaConfig getInstance() {
        mMediaConfig = mMediaConfig == null ? new MultiMediaConfig() : mMediaConfig;
        return mMediaConfig;
    }

    //lib内部参数定义
    private CameraFeature mCameraFeature;//相机模式 仅拍照/录像/两者都可
    private String fileSavedPath;//存储文件的文件夹

    //外部传入参数定义
    private int maxOptionalNum;//最大可选/可拍数量 默认为9张
    private String folderName;//存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片


    public int getMaxOptionalNum() {
        return maxOptionalNum;
    }

    public void setMaxOptionalNum(int mMaxOptionalNum) {
        maxOptionalNum = mMaxOptionalNum;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String mFolderName) {
        folderName = mFolderName;
    }

    public CameraFeature getCameraFeature() {
        return mCameraFeature;
    }

    public void setCameraFeature(CameraFeature mCameraFeature) {
        this.mCameraFeature = mCameraFeature;
    }

    public String getFileSavedPath() {
        return fileSavedPath;
    }

    public void setFileSavedPath(String mFileSavedPath) {
        fileSavedPath = FILE_SAVED_PATH + mFileSavedPath;
    }
}
