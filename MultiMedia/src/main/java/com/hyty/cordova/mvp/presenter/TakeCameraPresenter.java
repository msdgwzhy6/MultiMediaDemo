package com.hyty.cordova.mvp.presenter;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.blankj.utilcode.util.FileUtils;
import com.hyty.cordova.MultiMediaConfig;
import com.hyty.cordova.bean.DataBean;
import com.hyty.cordova.bean.Key;
import com.hyty.cordova.camera.util.FileUtil;
import com.hyty.cordova.camera.util.ImageUtil;
import com.hyty.cordova.imagepicker.bean.ImageItem;
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
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import javax.inject.Inject;

import com.hyty.cordova.mvp.contract.TakeCameraContract;
import com.jess.arms.utils.ArmsUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
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
    }

    /**
     * 保存图片到相册
     *
     * @param mBitmap
     */
    public void saveBitmapToCameraFile(Bitmap mBitmap) {
        //打入水印
        String str = "";
        Bitmap mBitmap_Saved = null;
        long t = new Date().getTime();
        switch (mMultiMediaConfig.getFlagLocation()) {
            case DEFAULT:
                str = "将水印文字直接打入右下角";
                mBitmap_Saved = ImageUtil.drawTextToRightBottom(mApplication, mBitmap, mMultiMediaConfig.getFlagText_willUse(), 35, Color.RED, 15, 15);
                break;
            case LEFT:
                str = "图片逆时针旋转90°后将水印文字直接打入右下角";
                //ImageUtil.rotateBitmap(mBitmap, 270)
                mBitmap_Saved = ImageUtil.drawTextToRightBottom(mApplication, mBitmap, mMultiMediaConfig.getFlagText_willUse(), 35, Color.RED, 15, 15);
                break;
            case RIGHT:
                str = "图片顺时针旋转90°后将水印文字直接打入右下角";
                //ImageUtil.rotateBitmap(mBitmap, 90)
                mBitmap_Saved = ImageUtil.drawTextToRightBottom(mApplication, mBitmap, mMultiMediaConfig.getFlagText_willUse(), 35, Color.RED, 15, 15);
                break;
        }

        if (mBitmap_Saved == null) {
            mRootView.showMessage("bitmap操作失败，请稍后再试...");
            return;
        }
        //加入水印

        Timber.d(str);
        File mFile = new File(mModel.saveBitmapAndResturnFileName(mBitmap_Saved));
        canUseFiles_camera.add(mFile);
        FileUtil.galleryAddPic(mApplication, mFile);//刷新图库
        ArmsUtils.dissMissLoading();

        mRootView.setPreviewCount(canUseFiles_camera.size());
        Timber.d("图片处理耗时:" + (new Date().getTime() - t) + " ms");
        Timber.d("图片处理结束，耗时:" + (new Date().getTime() - MultiMediaConfig.startTime) + " ms");

    }

    public List<DataBean> getSelectedImages() {
        List<DataBean> mPreViewData = new ArrayList<>();
        for (File mFile : canUseFiles_camera) {
//            ImageItem mImageItem = new ImageItem();
//            mImageItem.name = mFile.getName();
//            mImageItem.path = mFile.getPath();
            mPreViewData.add(new DataBean(mFile.getName(), "unknow"));
        }

        return mPreViewData;
    }

    public void setSelectedImages(ArrayList<File> canUseFiles_camera) {
        if (canUseFiles_camera == null) {
            this.canUseFiles_camera = new ArrayList<>();
            mRootView.setPreviewCount(0);
            return;
        }
        this.canUseFiles_camera = canUseFiles_camera;
        mRootView.setPreviewCount(canUseFiles_camera.size());
    }

    public void getResultData(CopyFilesListener mCopyFilesListener) {
        if (canUseFiles_camera.size() == 0) {
            mCopyFilesListener.onError("");
            return;
        }
        ArrayList<String> formFilesPath = new ArrayList<>();
        for (File mFile : canUseFiles_camera) {
            formFilesPath.add(mFile.getPath());
        }
        if (mCopyFilesListener != null)
            mMultiMediaConfig.commpImages(formFilesPath, mApplication, mCopyFilesListener);
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
    }

}
