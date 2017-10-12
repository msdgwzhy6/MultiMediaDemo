package com.hyty.cordova.bean;

import java.io.Serializable;

/**
 * ================================================================
 * 创建时间：2017/10/10 16:12
 * 创建人：赵文贇
 * 文件描述：多媒体插件入参字段
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class ConfigParams implements Serializable {

    private int type;//业务类型
    private int maxOptionalNum;//最大可选/可拍数量 默认为9张
    private String folderName;//存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片 默认为:defaultfolder
    private String flagText;//水印文字

    /**
     * 快速拍照需要传入的参数
     *
     * @param mType           业务类型
     * @param mMaxOptionalNum 最大可选/可拍数量 默认为9张
     * @param mFolderName     存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片
     * @param flagText        水印文字
     */
    public ConfigParams(int mType, int mMaxOptionalNum, String mFolderName, String flagText) {
        type = mType;
        maxOptionalNum = mMaxOptionalNum;
        folderName = mFolderName;
        this.flagText = flagText;
    }


    public int getType() {
        return type;
    }

    public void setType(int mType) {
        type = mType;
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

    public String getFlagText() {
        return flagText;
    }

    public void setFlagText(String mFlagText) {
        flagText = mFlagText;
    }
}
