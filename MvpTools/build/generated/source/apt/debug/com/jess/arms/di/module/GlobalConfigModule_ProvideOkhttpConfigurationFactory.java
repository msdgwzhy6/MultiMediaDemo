package com.jess.arms.di.module;

import android.support.annotation.Nullable;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideOkhttpConfigurationFactory
    implements Factory<ClientModule.OkhttpConfiguration> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvideOkhttpConfigurationFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  @Nullable
  public ClientModule.OkhttpConfiguration get() {
    return module.provideOkhttpConfiguration();
  }

  public static Factory<ClientModule.OkhttpConfiguration> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvideOkhttpConfigurationFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#provideOkhttpConfiguration()}. */
  public static ClientModule.OkhttpConfiguration proxyProvideOkhttpConfiguration(
      GlobalConfigModule instance) {
    return instance.provideOkhttpConfiguration();
  }
}
