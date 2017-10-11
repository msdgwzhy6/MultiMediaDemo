package com.hyty.cordova.mvp.impl;

import java.io.File;
import java.util.ArrayList;

/**
 * ================================================================
 * 创建时间：2017/10/11 10:17
 * 创建人：赵文贇
 * 文件描述：
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public interface CopyFilesListener {
    /**
     * copy成功
     *
     * @param toFilesPath 目标路径集合
     */
    void onSucc(ArrayList<String> toFilesPath);

    void onError(String errorMsg);
}
