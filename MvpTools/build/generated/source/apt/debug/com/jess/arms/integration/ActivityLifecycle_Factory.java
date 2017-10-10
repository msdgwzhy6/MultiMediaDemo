package com.jess.arms.integration;

import android.app.Application;
import dagger.internal.Factory;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ActivityLifecycle_Factory implements Factory<ActivityLifecycle> {
  private final Provider<AppManager> appManagerProvider;

  private final Provider<Application> applicationProvider;

  private final Provider<Map<String, Object>> extrasProvider;

  public ActivityLifecycle_Factory(
      Provider<AppManager> appManagerProvider,
      Provider<Application> applicationProvider,
      Provider<Map<String, Object>> extrasProvider) {
    assert appManagerProvider != null;
    this.appManagerProvider = appManagerProvider;
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
    assert extrasProvider != null;
    this.extrasProvider = extrasProvider;
  }

  @Override
  public ActivityLifecycle get() {
    return new ActivityLifecycle(
        appManagerProvider.get(), applicationProvider.get(), extrasProvider.get());
  }

  public static Factory<ActivityLifecycle> create(
      Provider<AppManager> appManagerProvider,
      Provider<Application> applicationProvider,
      Provider<Map<String, Object>> extrasProvider) {
    return new ActivityLifecycle_Factory(appManagerProvider, applicationProvider, extrasProvider);
  }
}
