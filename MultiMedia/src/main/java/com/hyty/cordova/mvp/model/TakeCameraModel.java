package com.hyty.cordova.mvp.model;

import android.app.Application;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.hyty.cordova.MultiMediaConfig;
import com.hyty.cordova.camera.util.FileUtil;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;

import com.hyty.cordova.mvp.contract.TakeCameraContract;

/**
 * ================================================================
 * 创建时间:2017-10-10 19:07:25
 * 创建人:赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class TakeCameraModel extends BaseModel implements TakeCameraContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public TakeCameraModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    /**
     * 保存bitmap到系统相册并返回文件全路径
     *
     * @param mBitmap
     * @return
     */
    @Override
    public String saveBitmapAndResturnFileName(Bitmap mBitmap) {
        return FileUtil.saveBitmapToFolder(MultiMediaConfig.CAMERA_FILE_PATH, mBitmap);
    }
}