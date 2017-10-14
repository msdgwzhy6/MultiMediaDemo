package com.hyty.cordova;

import android.app.Application;
import android.os.Environment;

import com.blankj.utilcode.util.FileUtils;
import com.hyty.cordova.bean.CameraFeature;
import com.hyty.cordova.bean.DataBean;
import com.hyty.cordova.bean.Key;
import com.hyty.cordova.mvp.impl.CopyFilesListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

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
    public static final int REQUEST_CODE_HOME_IMAGE_PIKER = 0x02;//从插件首页跳转多图选择页面请求码
    public static final int REQUEST_CODE_IMAGEPICKER_TAKECAMERA = 0x03;//从多图选择页面跳转拍照页面请求码
    public static final int REQUEST_CODE_HOME_IMAGE_PREVIEW = 0x04;//从插件首页跳转图片预览页面(原多图选择页面，隐藏部分view)


    public static Long startTime;
    private int p = 0;//拷贝文件重命名的表示，从0开始，使用完毕清0
    private CameraTextFlagLocation mFlagLocation;//需要将水印打入的位置

    public static MultiMediaConfig getInstance() {
        mMediaConfig = mMediaConfig == null ? new MultiMediaConfig(CameraTextFlagLocation.DEFAULT) : mMediaConfig;
        return mMediaConfig;
    }

    private MultiMediaConfig(CameraTextFlagLocation mFlagLocation) {
        this.mFlagLocation = mFlagLocation;//相机位置默认
    }

    //lib内部参数定义
    private CameraFeature mCameraFeature;//相机模式 仅拍照/录像/两者都可
    private String fileSavedPath;//存储文件的文件夹
    private String flagText_willUse;//待使用的水印文字
    private int doType;//当前操作类型

    //外部传入参数定义
    private int maxOptionalNum;//最大可选/可拍数量 默认为9张
    private String folderName;//存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片
    private String flagText;//水印文字
    private boolean isCanDelete = false;//预览模式下是否可以删除
//    private String parentUrl;//预览模式下的网络图片请求前缀
    private List<DataBean> preViewData;//预览的数据源
    private String lat_lng ;//外部传入的经纬度

    /**
     * 将文件集合拷贝进指定存储目录并返回拷贝后的路径集合
     *
     * @param fromFiles
     */
    public void copyFileToSavePath(ArrayList<File> fromFiles, CopyFilesListener mCopyFilesListener, Application mApplication) {
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


    public RxErrorHandler getRxErrorHandler(Application mApplication) {
        return RxErrorHandler
                .builder()
                .with(mApplication)
                .responseErrorListener((context, t) -> Timber.e("异常"))
                .build();
    }

    /**
     * 图片压缩
     *
     * @param formFilesPath
     * @param mApplication
     * @param mCopyFilesListener
     */
    public void commpImages(ArrayList<String> formFilesPath, Application mApplication, CopyFilesListener mCopyFilesListener) {
        ArrayList<String> toFilesPath = new ArrayList<>();
        Luban.with(mApplication)
                .load(formFilesPath)
                // 传人要压缩的图片列表
                .ignoreBy(100)                                  // 忽略不压缩图片的大小
                .setTargetDir(getFileSavedPath())                        // 设置压缩后文件存储位置
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        File mFromFile = new File(formFilesPath.get(p));
                        File mToFile = rename(file, mFromFile.getName());
                        if (mToFile == null) {
                            mCopyFilesListener.onError("重命名失败");
                            return;
                        }
                        toFilesPath.add(mToFile.getPath());
                        p++;
                        if (p == formFilesPath.size()) {
                            mCopyFilesListener.onSucc(toFilesPath);
                            p = 0;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        p = 0;
                        mCopyFilesListener.onError(e.getLocalizedMessage());
                    }
                }).launch();    //启动压缩

    }


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

    public String getFlagText() {
        return flagText;
    }

    public void setFlagText(String mFlagText) {
        flagText = mFlagText;
    }

    public String getFlagText_willUse() {
        return flagText_willUse;
    }

    public void setFlagText_willUse(String mFlagText_willUse) {
        flagText_willUse = mFlagText_willUse;
    }

    public CameraTextFlagLocation getFlagLocation() {
        return mFlagLocation;
    }

    public void setFlagLocation(CameraTextFlagLocation mFlagLocation) {
        this.mFlagLocation = mFlagLocation;
    }

    public int getDoType() {
        return doType;
    }

    public void setDoType(int mDoType) {
        doType = mDoType;
    }

    public boolean isCanDelete() {
        return isCanDelete;
    }

    public void setCanDelete(boolean mCanDelete) {
        isCanDelete = mCanDelete;
    }

//    public String getParentUrl() {
//        return parentUrl;
//    }
//
//    public void setParentUrl(String mParentUrl) {
//        parentUrl = mParentUrl;
//    }

    public List<DataBean> getPreViewData() {
        return preViewData;
    }

    public void setPreViewData(List<DataBean> mPreViewData) {
        preViewData = mPreViewData;
    }

    public String getLat_lng() {
        return lat_lng;
    }

    public void setLat_lng(String mLat_lng) {
        lat_lng = mLat_lng;
    }

    public static enum CameraTextFlagLocation {
        DEFAULT,//默认直接打入右下角
        LEFT,//逆时针旋转90度后打入右下角
        RIGHT,//顺时针旋转90度后打入右下角
    }

    private int renameTimes = 0;//重命名次数 ==100时自动取消重命名

    /**
     * 重命名文件，如果失败时自动添加新名称+随机数
     *
     * @param file    原文件
     * @param newName 新文件名
     * @return 新命名后的文件
     */
    private File rename(File file, String newName) {
        if (renameTimes == 99) {
            renameTimes = 0;
            return null;
        }
        if (FileUtils.rename(file, newName)) {
            renameTimes = 0;
            return new File(mMediaConfig.getFileSavedPath() + "/" + newName);
        } else {
            int r = new Random().nextInt(100);
            renameTimes++;
            String startName = newName.split("\\.")[0];
            String endName = newName.split("\\.")[1];
            return rename(file, "copyFrom-" + startName + "(" + r + ")" + "." + endName);
        }

        //                        if (FileUtils.rename(file, .getName())) {
//                            toFilesPath.add(mMediaConfig.getFileSavedPath() + "/" + new File(formFilesPath.get(p)).getName());
//                        } else {
//

//                            int r = new Random().nextInt(100);
//                            toFilesPath.add(mMediaConfig.getFileSavedPath() + "/" + "copyFrom-" + startName + "(" + r + ")" + "." + endName);
//
//                            Timber.e("重命名失败，目标目录下同名文件存在，重新命名:" + "copyFrom-" + startName + "(" + r + ")" + "." + endName);
//                            if (file.delete()) {
//                                Timber.w("删除生成的新文件(未重命名)成功");
//                            } else {
//                                Timber.e("删除生成的新文件(未重命名)失败");
//                            }
//                        }
    }
}
