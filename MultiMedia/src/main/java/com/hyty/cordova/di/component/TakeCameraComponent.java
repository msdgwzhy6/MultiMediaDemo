package com.hyty.cordova.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.hyty.cordova.di.module.TakeCameraModule;

import com.hyty.cordova.mvp.ui.activity.TakeCameraActivity;

/**
 * ================================================================
 * 创建时间:2017-10-10 19:07:25
 * 创建人:赵文贇
 * 看淡身边的虚伪，静心宁神做好自己。路那么长，无愧走好每一步。
 * ================================================================
 */
@ActivityScope
@Component(modules = TakeCameraModule.class, dependencies = AppComponent.class)
public interface TakeCameraComponent {
    void inject(TakeCameraActivity activity);
}