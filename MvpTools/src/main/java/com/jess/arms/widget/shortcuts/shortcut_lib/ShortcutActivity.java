package com.jess.arms.widget.shortcuts.shortcut_lib;

import android.app.Activity;
import android.os.Bundle;

import com.jess.arms.R;

/**
 * 通过指定多个Launcher入口实现
 * <p/>
 * Created by xuyisheng on 15/10/30.
 * Version 1.0
 */
public class ShortcutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shortcut_main);
    }
}
