package com.jess.arms.di.module;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideCacheFileFactory implements Factory<File> {
  private final GlobalConfigModule module;

  private final Provider<Application> applicationProvider;

  public GlobalConfigModule_ProvideCacheFileFactory(
      GlobalConfigModule module, Provider<Application> applicationProvider) {
    assert module != null;
    this.module = module;
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
  }

  @Override
  public File get() {
    return Preconditions.checkNotNull(
        module.provideCacheFile(applicationProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<File> create(
      GlobalConfigModule module, Provider<Application> applicationProvider) {
    return new GlobalConfigModule_ProvideCacheFileFactory(module, applicationProvider);
  }

  /** Proxies {@link GlobalConfigModule#provideCacheFile(Application)}. */
  public static File proxyProvideCacheFile(GlobalConfigModule instance, Application application) {
    return instance.provideCacheFile(application);
  }
}
