package com.jess.arms.widget.dialog.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jess.arms.R;

import java.util.Map;


public class BuildBean implements Styleable {

    public BuildBean(Context context,CharSequence msg, boolean cancleable, boolean outsideTouchable, boolean isWhiteBg) {
        this.mContext = context;
        this.msg = msg;
        this.isWhiteBg = isWhiteBg;
        this.gravity = Gravity.CENTER;
        this.cancelable = cancleable;
        this.outsideTouchable = outsideTouchable;
        this.type = CommonConfig.TYPE_LOADING_HORIZONTAL;
    }

    /**
     * 上下文
     */
    public Context mContext;
    /**
     * 构建dialog的类型
     */
    public int type;

    public int gravity;
    public int tag;

    public CharSequence title;
    public CharSequence msg;
    public CharSequence text1 = CommonConfig.dialogui_btnTxt1;
    public CharSequence text2 = CommonConfig.dialogui_btnTxt2;
    public CharSequence text3;

    public DialogUIListener listener;

    /**
     * 是否是白色背景
     */
    public boolean isWhiteBg = true;
    /**
     * 是否可以取消
     */
    public boolean cancelable = true;
    /**
     * 面板外是否可以点击
     */
    public boolean outsideTouchable = true;

    public Dialog dialog;

    //三个以下按钮,颜色按此顺序
    @ColorRes
    public int btn1Color = CommonConfig.iosBtnColor;
    @ColorRes
    public int btn2Color = CommonConfig.iosBtnColor;
    @ColorRes
    public int btn3Color = CommonConfig.iosBtnColor;


    @ColorRes
    public int titleTxtColor = CommonConfig.titleTxtColor;
    @ColorRes
    public int msgTxtColor = CommonConfig.msgTxtColor;
    @ColorRes
    public int lvItemTxtColor = CommonConfig.lvItemTxtColor;
    @ColorRes
    public int inputTxtColor = CommonConfig.inputTxtColor;

    public Map<Integer, Integer> colorOfPosition;//listview 的item的特殊颜色:ColorRes

    //字体大小
    public int btnTxtSize = 17;// in sp
    public int titleTxtSize = 14;
    public int msgTxtSize = 14;
    public int itemTxtSize = 14;
    public int inputTxtSize = 14;

    @Override
    public BuildBean setBtnColor(@ColorRes int btn1Color, @ColorRes int btn2Color, @ColorRes int btn3Color) {
        if (btn1Color > 0)
            this.btn1Color = btn1Color;
        if (btn2Color > 0)
            this.btn2Color = btn2Color;
        if (btn3Color > 0)
            this.btn3Color = btn3Color;
        return this;
    }

    @Override
    public BuildBean setListItemColor(@ColorRes int lvItemTxtColor, Map<Integer, Integer> colorOfPosition) {
        if (lvItemTxtColor > 0)
            this.lvItemTxtColor = lvItemTxtColor;
        if (colorOfPosition != null && colorOfPosition.size() > 0) {
            this.colorOfPosition = colorOfPosition;
        }
        return this;
    }

    @Override
    public BuildBean setTitleColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.titleTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public BuildBean setMsgColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.msgTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public BuildBean seInputColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.inputTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public BuildBean setTitleSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.titleTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setMsgSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.msgTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setBtnSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.btnTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setLvItemSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.itemTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public BuildBean setInputSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.inputTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public Dialog show() {
        buildByType(this);
        if (dialog != null && !dialog.isShowing()) {
            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dialog;
        }
        return null;
    }

    private void buildByType(BuildBean bean) {
        Dialog dialog = new Dialog(bean.mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bean.dialog = dialog;

        View root = View.inflate(bean.mContext, R.layout.dialogui_loading_horizontal, null);
        View llBg = (View) root.findViewById(R.id.dialogui_ll_bg);
        ProgressBar pbBg = (ProgressBar) root.findViewById(R.id.pb_bg);
        TextView tvMsg = (TextView) root.findViewById(R.id.dialogui_tv_msg);
        tvMsg.setText(bean.msg);
        if (bean.isWhiteBg) {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_shape_progress));
            tvMsg.setTextColor(bean.mContext.getResources().getColor(R.color.text_black));
        } else {
            llBg.setBackgroundResource(R.drawable.dialogui_shape_gray_round_corner);
            pbBg.setIndeterminateDrawable(bean.mContext.getResources().getDrawable(R.drawable.dialogui_shape_progress_light));
            tvMsg.setTextColor(Color.WHITE);
        }
        bean.dialog.setContentView(root);
    }

    @Override
    public BuildBean setBtnText(CharSequence btn1Text, @Nullable CharSequence btn2Text, @Nullable CharSequence btn3Text) {
        this.text1 = btn1Text;
        this.text2 = btn2Text;
        this.text3 = btn3Text;

        return this;
    }

    @Override
    public BuildBean setBtnText(CharSequence positiveTxt, @Nullable CharSequence negtiveText) {
        return setBtnText(positiveTxt, negtiveText, "");
    }

    @Override
    public BuildBean setListener(DialogUIListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
        return this;
    }

    @Override
    public BuildBean setCancelable(boolean cancelable, boolean outsideCancelable) {
        this.cancelable = cancelable;
        this.outsideTouchable = outsideCancelable;
        return this;
    }


    @Override
    public void cycle() {
        dialog=null;
        listener=null;
        mContext=null;
    }



}
