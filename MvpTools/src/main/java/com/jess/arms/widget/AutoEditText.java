package com.jess.arms.widget;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * ================================================================
 * 创建时间：07/07/2017 5:18 PM
 * 创建人：Alan
 * 文件描述：附带
 * 至尊宝：长夜漫漫无心睡眠，我以为只有我睡不着，原来晶晶姑娘你也睡不着 ！
 * ================================================================
 */
public class AutoEditText extends AppCompatEditText {

    public AutoEditText(Context context) {
        super(context);
    }

    public AutoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addListener();
    }

    private ArrayList<Object> mResultsValues = new ArrayList();

    public AutoEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addListener();
    }

    private String strDef = null;

    /**
     * @param mResultsValues 自动提示的字符串list
     */
    public void setResultsValues(ArrayList mResultsValues) {
        this.mResultsValues = mResultsValues;
    }


    private void addListener(){
        this.addTextChangedListener(new TextWatcher() {
            String strNow = null;
            String strOld = null;
            int strLength = -1;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strNow = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                int startPositon = AutoEditText.this.getSelectionStart();
                int endPositon;
                // 第一阶段代码
                if (strOld != null) {
                    //
                    if (strOld.length() != 0)
                        if (strOld.equals(strNow)) {
                            // 判断是否是结尾
                            strOld = strNow;
                            return;
                        }
                    if (strNow.length() <= strOld.length()) {
                        // 防止删除的时候重新进入到第二阶段代码
                        strOld = strNow;
                        return;
                    }
                }
                // 第二阶段代码
                for (int i = 0; i < mResultsValues.size(); i++) {
                    strDef = String.valueOf(mResultsValues.get(i));
                    if (strDef.startsWith(strNow) && !strDef.equals(strNow)) {
                        // 防止用户在从最后一位删除字符的时候字符串重新变回元字符串（如sumile,删除，成为sumile，两者的区别是后面的sumile中的e是被选中的状态）
                        if (strDef.substring(0, strDef.length() - 1).equals(strNow)) {
                            strOld = strNow;
                            return;
                        }
                        // 获得字符串的末尾
                        endPositon = strDef.length();
                        // 将上一个字符设置为新的字符
                        strOld = strNow;
                        // 设置文本显示
                        AutoEditText.this.setText(strDef);
                        // 设置文本选择
                        AutoEditText.this.setSelection(startPositon, endPositon);
                        // 这个break貌似不起作用，但是也没有把它删掉
                        break;
                    }
                }
            }
        });
    }
}