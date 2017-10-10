package com.jess.arms.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.delegate.IFragment;
import com.jess.arms.mvp.IPresenter;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

/**
 * 因为java只能单继承,所以如果有需要继承特定Fragment的三方库,那你就需要自己自定义Fragment
 * 继承于这个特定的Fragment,然后按照将BaseFragment的格式,复制过去,记住一定要实现{@link IFragment}
 */
public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IFragment {
    protected final String TAG = this.getClass().getSimpleName();

    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;

    @Inject
    protected P mPresenter;
    protected Bundle savedInstanceState;

    public BaseFragment() {
        //必须确保在Fragment实例化时setArguments()
        setArguments(new Bundle());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        isInit = true;
        /**初始化的时候去加载数据**/
//        isCanLoadData(savedInstanceState);
        return initView(inflater, container, savedInstanceState);
    }


    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData(savedInstanceState);
    }

    @Override
    public void isCanLoadData(Bundle savedInstanceState) {
        if (!isInit) {
            return;
        }

        if (getUserVisibleHint()) {//可见
            loadDataEveryTime();
            if (!isLoad) {//没加载过数据
                initData(savedInstanceState);
                isLoad = true;
            }
        } else {//不可见
            if (isLoad) {
                stopLoadData();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isInit = false;
        isLoad = false;
        this.savedInstanceState = null;
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }


    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    @Override
    public boolean useEventBus() {
        return true;
    }


}
