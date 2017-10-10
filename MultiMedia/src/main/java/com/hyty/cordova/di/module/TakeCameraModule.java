package com.hyty.cordova.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hyty.cordova.mvp.contract.TakeCameraContract;
import com.hyty.cordova.mvp.model.TakeCameraModel;

/**
 * ================================================================
 * 创建时间:2017-10-10 19:07:25
 * 创建人:赵文贇
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@Module
public class TakeCameraModule {
    private TakeCameraContract.View view;

    /**
     * 构建TakeCameraModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public TakeCameraModule(TakeCameraContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TakeCameraContract.View provideTakeCameraView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TakeCameraContract.Model provideTakeCameraModel(TakeCameraModel model) {
        return model;
    }
}