package com.jess.arms.widget.dialog.loading;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jess.arms.R;

import timber.log.Timber;

/**
 * ================================================================
 * 创建时间：2017/8/28 上午8:02
 * 创建人：Alan
 * 文件描述：简易的加载进度条
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
public class LoadingView implements DialogInterface.OnCancelListener {
    private Context mContext;
    private static LoadingView mLoadingView;
    private CharSequence msg;
    private boolean isCanCancle;
    private Dialog mDialog;
    private View rootView;
    private LinearLayout mLinearLayout;
    private ProgressBar mProgressBar;
    private TextView mTextView;
    private OnCancelListener mCancelListener;
    private final String TAG = getClass().getSimpleName();
    public LoadingView(Context context) {
        this.mContext = context;
    }

    public static LoadingView init(Context context) {
        return mLoadingView == null ? new LoadingView(context) : mLoadingView;
    }


    public LoadingView showLoading(CharSequence msg, boolean cancleabl) {

        mDialog = new Dialog(mContext);// TODO: 2017/8/28 内存泄露时这里也修改为弱引用
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        rootView = View.inflate(mContext, R.layout.dialogui_loading_horizontal, null);
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.dialogui_ll_bg);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.pb_bg);
        mTextView = (TextView) rootView.findViewById(R.id.dialogui_tv_msg);
        mTextView.setText(msg);
        mLinearLayout.setBackgroundResource(R.drawable.dialogui_shape_wihte_round_corner);
        mProgressBar.setIndeterminateDrawable(mContext.getResources().getDrawable(R.drawable.dialogui_shape_progress));
        mTextView.setTextColor(mContext.getResources().getColor(R.color.text_black));
        mDialog.setContentView(rootView);

        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog.setCancelable(cancleabl);
            mDialog.setOnCancelListener(this);
            try {
                mDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
                Timber.e("进度条展示失败。操作过快。。。");
            }
        }
        return this;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.mCancelListener = onCancelListener;
    }


    public void dissMissDialog() {
        try {
            mDialog.dismiss();
            destroy();
        } catch (Exception e) {
            Timber.tag(TAG).d(e);
        }
    }

    public void destroy() {
        this.mContext = null;
        this.msg = null;
        this.mDialog = null;
        this.rootView = null;
        this.mLinearLayout = null;
        this.mProgressBar = null;
        this.mTextView = null;
        this.mCancelListener = null;
        mLoadingView = null;
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        try {
            mCancelListener.onCancel();
        } catch (Exception e) {
            Timber.tag(TAG).d(e);
        }
    }
}
