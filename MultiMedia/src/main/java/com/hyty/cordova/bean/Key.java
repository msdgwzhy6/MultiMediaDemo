package com.hyty.cordova.bean;

import java.io.Serializable;

/**
 * ================================================================
 * 创建时间：2017/10/10 20:47
 * 创建人：赵文贇
 * 文件描述：intent传递请求码的key
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class Key implements Serializable {
    public static final String REQUEST_CODE = "requestCode";//传递请求吗的key
    public static final String RESULT_INTENT = "resulrIntent";//响应数据的key
    public static final String REQUEST_ALLREADY_SELECTED_IMAGES = "requestAlreadySelectedImages";//请求跳转拍照页面，携带已选中照片


}
