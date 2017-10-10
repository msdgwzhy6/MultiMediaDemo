package com.jess.arms.base.delegate;

import com.jess.arms.integration.ActivityLifecycle;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppDelegate_MembersInjector implements MembersInjector<AppDelegate> {
  private final Provider<ActivityLifecycle> mActivityLifecycleProvider;

  public AppDelegate_MembersInjector(Provider<ActivityLifecycle> mActivityLifecycleProvider) {
    assert mActivityLifecycleProvider != null;
    this.mActivityLifecycleProvider = mActivityLifecycleProvider;
  }

  public static MembersInjector<AppDelegate> create(
      Provider<ActivityLifecycle> mActivityLifecycleProvider) {
    return new AppDelegate_MembersInjector(mActivityLifecycleProvider);
  }

  @Override
  public void injectMembers(AppDelegate instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mActivityLifecycle = mActivityLifecycleProvider.get();
  }

  public static void injectMActivityLifecycle(
      AppDelegate instance, Provider<ActivityLifecycle> mActivityLifecycleProvider) {
    instance.mActivityLifecycle = mActivityLifecycleProvider.get();
  }
}
