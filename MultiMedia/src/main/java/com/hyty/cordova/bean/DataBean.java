package com.hyty.cordova.bean;

import java.io.Serializable;

/**
 * ================================================================
 * 创建时间：2017/10/13 08:55
 * 创建人：赵文贇
 * 文件描述：图片预览时传入的参数实体 以集合形式出现
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class DataBean implements Serializable {
    private String fileName;//本地文件名称
    private String filePath_www;//网络存储的名称

    public DataBean(String mFileName, String mFilePath_www) {
        fileName = mFileName;
        filePath_www = mFilePath_www;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String mFileName) {
        fileName = mFileName;
    }

    public String getFilePath_www() {
        return filePath_www;
    }

    public void setFilePath_www(String mFilePath_www) {
        filePath_www = mFilePath_www;
    }
}
