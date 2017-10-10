package com.hyty.cordova.camera.util;

import android.app.Activity;

import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.widget.dialog.alertdialog.AlertDialog;

import timber.log.Timber;

/**
 * ================================================================
 * 创建时间：2017/10/10 20:08
 * 创建人：赵文贇
 * 文件描述：展示进度条对话框
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class ViewUtils {
    public static void showDialog(Activity mActivity, String msg, DialogClickListenr mListenr) {
        new AlertDialog(mActivity).builder()
                .setTitle("提示")
                .setMsg(msg)
                .setNegativeButton("取消", view -> mListenr.onClickCancel())
                .setPositiveButton("确定", view -> mListenr.onClickSubmit())
                .setCancelable(true)
                .show();
    }

    public static interface DialogClickListenr {
        void onClickSubmit();
        void onClickCancel();
    }
}
