package com.hyty.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.hyty.cordova.MultiMediaConfig;
import com.hyty.cordova.bean.ConfigParams;
import com.hyty.cordova.bean.Key;
import com.hyty.cordova.camera.util.ViewUtils;
import com.hyty.cordova.imagepicker.bean.ImageItem;
import com.hyty.cordova.plugins.MultiMediaPlugin;
import com.jess.arms.utils.ArmsUtils;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;


/**
 * ================================================================
 * 创建时间:2017-10-10 15:44:05
 * 创建人:赵文贇
 * 文件描述：测试各业务类型
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class Main extends AppCompatActivity {


    @BindView(R.id.bt1)
    Button mBt1;
    @BindView(R.id.bt2)
    Button mBt2;
    @BindView(R.id.bt3)
    Button mBt3;
    @BindView(R.id.bt4)
    Button mBt4;
    @BindView(R.id.bt5)
    Button mBt5;
    @BindView(R.id.bt6)
    Button mBt6;
    @BindView(R.id.bt7)
    Button mBt7;
    @BindView(R.id.bt8)
    Button mBt8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8})
    public void onClick(View view) {
        String str = "";
        switch (view.getId()) {
            case R.id.bt1:
                log("快速拍照");
                str = new Gson().toJson(new ConfigParams(1, 3, "cgzf", "测试的水印文字"));
                break;
            case R.id.bt2:
                log("多图选择+拍照");
                str = new Gson().toJson(new ConfigParams(2, 10, "cgzf", "测试的水印文字"));
                break;
            case R.id.bt3:
                log("快速录像");
                break;
            case R.id.bt4:
                log("录像选择+录像");
                break;
            case R.id.bt5:
                log("快速录音");
                break;
            case R.id.bt6:
                log("录音选取列表+录音");
                break;
            case R.id.bt7:
                log("图片、视频预览列表");
                break;
            case R.id.bt8:
                log("流媒体播放(单一页面，全屏播放)");
                break;
        }
        if (TextUtils.isEmpty(str)) {
            ArmsUtils.showToast("入参不能为空");
            return;
        }
        try {
            MultiMediaPlugin.getInstance(this).execute("", str, null);
        } catch (JSONException mE) {
            mE.printStackTrace();
            log("调用插件失败");
        }
    }


    public void log(String msg) {
        Log.d("多媒体测试demo", msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Timber.d("MultiMediaPlugin - onActivityResult :requestCode = " + requestCode + ",resultCode = " + resultCode + ",intent == null?" + (intent == null));
        if (intent == null) return;
        if (requestCode == MultiMediaConfig.REQUEST_CODE_HOME_TAKECAMERA
                && resultCode == MultiMediaConfig.REQUEST_CODE_HOME_TAKECAMERA) {
            printLog(intent, "仅拍照");
        } else if (requestCode == MultiMediaConfig.REQUEST_CODE_HOME_IMAGE_PIKER
                && resultCode == MultiMediaConfig.REQUEST_CODE_HOME_IMAGE_PIKER) {
            printLog(intent, "多图选择");
        }
    }

    private void printLog(Intent mIntent, String msg) {
        ArrayList<String> list = mIntent.getStringArrayListExtra(Key.RESULT_INTENT);
        Timber.d(msg + "模式返回数据:list.size():" + list.size());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + "\n");
        }
        Timber.d(sb.toString());
        ArmsUtils.showToast(sb.toString());
    }
}
