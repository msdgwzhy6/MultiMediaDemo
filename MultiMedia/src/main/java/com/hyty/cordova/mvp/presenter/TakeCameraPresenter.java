package com.hyty.cordova.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;

import com.blankj.utilcode.util.FileUtils;
import com.hyty.cordova.MultiMediaConfig;
import com.hyty.cordova.bean.Key;
import com.hyty.cordova.camera.util.FileUtil;
import com.hyty.cordova.mvp.impl.CopyFilesListener;
import com.hyty.cordova.mvp.impl.OnGetIntent;
import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;

import javax.inject.Inject;

import com.hyty.cordova.mvp.contract.TakeCameraContract;

import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;


/**
 * ================================================================
 * 创建时间:2017-10-10 19:07:25
 * 创建人:赵文贇
 * 文件描述：拍照页面
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
public class TakeCameraPresenter extends BasePresenter<TakeCameraContract.Model, TakeCameraContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private MultiMediaConfig mMultiMediaConfig;//全局配置类
    private ArrayList<File> canUseFiles_camera;//原图文件
    private ArrayList<String> canUseFilePaths_end;//压缩拷贝后


    @Inject
    public TakeCameraPresenter(TakeCameraContract.Model model, TakeCameraContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
        this.mMultiMediaConfig = MultiMediaConfig.getInstance();
        this.canUseFiles_camera = new ArrayList<>();
        this.canUseFilePaths_end = new ArrayList<>();
    }

    /**
     * 保存图片到相册
     *
     * @param mBitmap
     */
    public void saveBitmapToCameraFile(Bitmap mBitmap) {
        File mFile = new File(mModel.saveBitmapAndResturnFileName(mBitmap));
        canUseFiles_camera.add(mFile);
        FileUtil.galleryAddPic(mApplication, mFile);//刷新图库
    }

    public void getResultData(CopyFilesListener mCopyFilesListener) {
        //将照片压缩后添加到指定存储文件的目录   copy 压缩
        //图片copy
        Timber.d("开始拷贝图片,原目录 " + MultiMediaConfig.CAMERA_FILE_PATH + ",目标目录 " + mMultiMediaConfig.getFileSavedPath() + ",总计" + canUseFiles_camera.size() + "张图片");
        mMultiMediaConfig.copyFileToSavePath(canUseFiles_camera, mCopyFilesListener, mApplication);
        //图片压缩
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
        this.mMultiMediaConfig = null;
        this.canUseFiles_camera = null;
        this.canUseFilePaths_end = null;
    }

}
