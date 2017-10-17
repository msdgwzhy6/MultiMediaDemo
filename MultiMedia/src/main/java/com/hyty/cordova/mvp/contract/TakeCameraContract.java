package com.hyty.cordova.mvp.contract;

import android.graphics.Bitmap;

import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.IModel;

/**
 * ================================================================
 * 创建时间:2017-10-10 19:07:25
 * 创建人:赵文贇
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public interface TakeCameraContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        /**
         * 展示一个指定msg的进度条
         *
         * @param msg
         */
        void showLoading(String msg);

        /**
         * 设置预览的数字
         * @param count
         */
        void setPreviewCount(int count);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        /**
         * 保存bitmap到系统相册并返回文件全路径
         *
         * @param mBitmap
         * @return
         */
        String saveBitmapAndResturnFileName(Bitmap mBitmap);
    }
}
