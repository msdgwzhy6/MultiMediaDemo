package com.hyty.cordova.plugins;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hyty.cordova.MultiMediaConfig;
import com.hyty.cordova.bean.ConfigParams;
import com.jess.arms.utils.logger.AndroidLogAdapter;
import com.jess.arms.utils.logger.Logger;
import com.jess.arms.utils.logger.PrettyFormatStrategy;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import butterknife.ButterKnife;
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
    private static MultiMediaPlugin mPlugin;// TODO: 2017/10/10 记得删除单利测试模式
    private MultiMediaConfig mMediaConfig;
    private static boolean isInitLog = false;

    public static MultiMediaPlugin getInstance() {
        mPlugin = mPlugin == null ? new MultiMediaPlugin() : mPlugin;
        return mPlugin;
    }

    private MultiMediaPlugin() {
        mMediaConfig = MultiMediaConfig.getInstance();
    }

    @Override
    public boolean execute(String action, String rawArgs, CallbackContext callbackContext) throws JSONException {
        init();//初始化参数
        if (TextUtils.isEmpty(rawArgs)) {
            Timber.e("传入参数不能为空");
            return false;
        }
        ConfigParams mConfigParams = null;
        try {
            mConfigParams = new Gson().fromJson(rawArgs, ConfigParams.class);
        } catch (JsonSyntaxException mE) {
            mE.printStackTrace();
            Timber.e("传入参数解析出错");
        }
        if (mConfigParams == null || mConfigParams.getType() == 0) {
            Timber.e("传入参数不合法");
            return false;
        }
        Timber.d("传入参数:" + rawArgs);
        switch (mConfigParams.getType()) {
            case 1:
                // TODO: 2017/10/10 直接打开拍照页面，需要最大可拍照数量，拍照结束后点击返回按钮关闭所有页面不返回任何参数，点击保存按钮对图片进行copy、压缩(只压缩copy进指定文件夹的图片) ,并将结果返回给调用者
                // TODO: 2017/10/10 需要参数:最大可拍照数量、copy的路径
                mMediaConfig.setMaxOptionalNum(mConfigParams.getMaxOptionalNum() == 0 ? 9 : mConfigParams.getMaxOptionalNum());
                mMediaConfig.setFolderName(TextUtils.isEmpty(mConfigParams.getFolderName()) ? "defaultfolder" : mConfigParams.getFolderName());
                Timber.d("跳转快速拍照页面,存储的文件夹名称:" + mMediaConfig.getFolderName() + ",最大可选:" + mMediaConfig.getMaxOptionalNum());
                onDestroy();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;

        }
        return true;
    }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return false;
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        return false;
    }

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
    public void onDestroy() {
        super.onDestroy();
    }
}
