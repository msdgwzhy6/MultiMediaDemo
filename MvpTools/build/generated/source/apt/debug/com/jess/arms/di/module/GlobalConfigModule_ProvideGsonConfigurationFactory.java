package com.jess.arms.di.module;

import android.support.annotation.Nullable;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideGsonConfigurationFactory
    implements Factory<AppModule.GsonConfiguration> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvideGsonConfigurationFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  @Nullable
  public AppModule.GsonConfiguration get() {
    return module.provideGsonConfiguration();
  }

  public static Factory<AppModule.GsonConfiguration> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvideGsonConfigurationFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#provideGsonConfiguration()}. */
  public static AppModule.GsonConfiguration proxyProvideGsonConfiguration(
      GlobalConfigModule instance) {
    return instance.provideGsonConfiguration();
  }
}
