package com.hyty.cordova.mvp.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;

import com.hyty.cordova.MultiMediaConfig;
import com.hyty.cordova.R;
import com.hyty.cordova.bean.Key;
import com.hyty.cordova.camera.JCameraView;
import com.hyty.cordova.camera.listener.ErrorListener;
import com.hyty.cordova.camera.listener.JCameraListener;
import com.hyty.cordova.di.component.DaggerTakeCameraComponent;
import com.hyty.cordova.di.module.TakeCameraModule;
import com.hyty.cordova.mvp.contract.TakeCameraContract;
import com.hyty.cordova.mvp.presenter.TakeCameraPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

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
        mCameraView.setTip("点击拍照");
        mCameraView.setErrorLisenter(mErrorListener);
        mCameraView.setJCameraLisenter(mCameraListener);
        mCameraView.setLeftClickListener(() -> finishPage(null));
        mCameraView.setRightClickListener(() -> {
            Intent mIntent = new Intent();
            mIntent.putExtra("ccc", "123");// TODO: 2017/10/10 预留返回数据格式
            finishPage(mIntent);
        });
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
            // TODO: 2017/10/10 预留连续拍照的bug
        }

        @Override
        public void recordSuccess(String url, Bitmap firstFrame) {

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
        ArmsUtils.snackbarText(message);
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
    }
}
