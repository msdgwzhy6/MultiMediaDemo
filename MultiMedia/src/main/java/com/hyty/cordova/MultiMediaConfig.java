package com.hyty.cordova;

/**
 * ================================================================
 * 创建时间：2017/10/10 16:33
 * 创建人：赵文贇
 * 文件描述：存放插件的全局参数和工具
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class MultiMediaConfig {
    private static MultiMediaConfig mMediaConfig;

    public static MultiMediaConfig getInstance() {
        mMediaConfig = mMediaConfig == null ? new MultiMediaConfig() : mMediaConfig;
        return mMediaConfig;
    }

    //参数定义
    private int maxOptionalNum;//最大可选/可拍数量 默认为9张
    private String folderName;//存储文件的名称 该文件将在SD卡根目录下出现 存储压缩过的图片


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
}
