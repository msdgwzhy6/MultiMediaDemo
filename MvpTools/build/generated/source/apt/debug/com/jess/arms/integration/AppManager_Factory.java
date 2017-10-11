package com.jess.arms.integration;

import android.app.Application;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppManager_Factory implements Factory<AppManager> {
  private final Provider<Application> applicationProvider;

  public AppManager_Factory(Provider<Application> applicationProvider) {
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
  }

  @Override
  public AppManager get() {
    return new AppManager(applicationProvider.get());
  }

  public static Factory<AppManager> create(Provider<Application> applicationProvider) {
    return new AppManager_Factory(applicationProvider);
  }
}
