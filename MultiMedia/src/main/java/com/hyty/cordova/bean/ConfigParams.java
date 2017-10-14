package com.hyty.cordova.bean;

import java.io.Serializable;
import java.util.List;

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
    private boolean isCanDelete;//是否具备删除功能,仅在预览模式有效
//    private String urlPathHeader;//当本地文件不存在时在网络获取的请求前缀 仅在预览模式有效
    private List<DataBean> data;
    private String lat_lng;//经纬度信息
    /**
     * 快速拍照需要传入的参数
     *
     * @param mType           业务类型 1 ~ 快速拍照   2 ~ 多图选择+拍照
     * @param mMaxOptionalNum 最大可选/可拍数量 默认为9张
     * @param mFolderName     存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片
     * @param flagText        水印文字
     */
    public ConfigParams(int mType, int mMaxOptionalNum, String mFolderName, String flagText,String lat_lng) {
        type = mType;
        maxOptionalNum = mMaxOptionalNum;
        folderName = mFolderName;
        this.flagText = flagText;
        this.lat_lng =lat_lng;
    }

    /**
     * 图片预览 构造参数
     *
     * @param mType         业务类型
     * @param mFolderName   存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片
     * @param mIsCanDelete  是否具备删除功能
     * @param data          预览的数据源
     */
    public ConfigParams(int mType, String mFolderName, boolean mIsCanDelete, List<DataBean> data) {
        type = mType;
        folderName = mFolderName;
        isCanDelete = mIsCanDelete;
        this.data = data;
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

    public boolean isCanDelete() {
        return isCanDelete;
    }

    public void setCanDelete(boolean mCanDelete) {
        isCanDelete = mCanDelete;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> mData) {
        data = mData;
    }

    public String getLat_lng() {
        return lat_lng;
    }

    public void setLat_lng(String mLat_lng) {
        lat_lng = mLat_lng;
    }
}
