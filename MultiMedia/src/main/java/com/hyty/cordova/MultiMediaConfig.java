package com.hyty.cordova;

import android.app.Application;
import android.os.Environment;

import com.blankj.utilcode.util.FileUtils;
import com.hyty.cordova.bean.CameraFeature;
import com.hyty.cordova.bean.Key;
import com.hyty.cordova.mvp.impl.CopyFilesListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;

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

    /**
     * 将文件集合拷贝进指定存储目录并返回拷贝后的路径集合
     *
     * @param fromFiles
     */
    public void copyFileToSavePath(ArrayList<File> fromFiles, CopyFilesListener mCopyFilesListener,Application mApplication) {
        Observable.create(new ObservableOnSubscribe<ArrayList<String>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<ArrayList<String>> array) throws Exception {
                ArrayList<String> canUseFilePaths_end = new ArrayList<>();
                for (File file : fromFiles) {
                    String newPath = getFileSavedPath() + "/" + file.getName();
                    boolean isSucc = FileUtils.copyFile(file.getPath(), newPath);
                    if (!isSucc) {
                        array.onError(new ConcurrentModificationException());
                        return;
                    }
                    canUseFilePaths_end.add(newPath);
                }
                array.onNext(canUseFilePaths_end);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//设置下游接收事件的线程
                .subscribe(new ErrorHandleSubscriber<ArrayList<String>>(getRxErrorHandler(mApplication)) {
                    @Override
                    public void onNext(@NonNull ArrayList<String> mStrings) {
                        Timber.d(fromFiles.size() + "张图片已拷贝完成");
                        //图片压缩
                        mCopyFilesListener.onSucc(mStrings);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e("图片拷贝出错，e" + e);
                        mCopyFilesListener.onError("请重试");
                    }
                });
    }


    public RxErrorHandler getRxErrorHandler(Application mApplication){
      return   RxErrorHandler
                .builder()
                .with(mApplication)
                .responseErrorListener((context, t) -> Timber.e("异常"))
                .build();
    }
}
