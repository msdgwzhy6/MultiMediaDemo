package com.jess.arms.di.module;

import android.support.annotation.Nullable;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideRxCacheConfigurationFactory
    implements Factory<ClientModule.RxCacheConfiguration> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvideRxCacheConfigurationFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  @Nullable
  public ClientModule.RxCacheConfiguration get() {
    return module.provideRxCacheConfiguration();
  }

  public static Factory<ClientModule.RxCacheConfiguration> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvideRxCacheConfigurationFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#provideRxCacheConfiguration()}. */
  public static ClientModule.RxCacheConfiguration proxyProvideRxCacheConfiguration(
      GlobalConfigModule instance) {
    return instance.provideRxCacheConfiguration();
  }
}
