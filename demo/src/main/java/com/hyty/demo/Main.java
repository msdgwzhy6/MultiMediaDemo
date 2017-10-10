package com.hyty.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.hyty.cordova.bean.ConfigParams;
import com.hyty.cordova.plugins.MultiMediaPlugin;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
        switch (view.getId()) {
            case R.id.bt1:
                log("快速拍照");
                String str = new Gson().toJson(new ConfigParams(1, 10, "cgzf"));
                try {
                    MultiMediaPlugin.getInstance().execute("", str, null);
                } catch (JSONException mE) {
                    mE.printStackTrace();
                    log("调用插件失败");
                }
                break;
            case R.id.bt2:
                log("多图选择+拍照");
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
    }


    public void log(String msg) {
        Log.d("多媒体测试demo", msg);
    }
}
