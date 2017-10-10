package com.jess.arms.di.module;

import android.support.annotation.Nullable;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideRetrofitConfigurationFactory
    implements Factory<ClientModule.RetrofitConfiguration> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvideRetrofitConfigurationFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  @Nullable
  public ClientModule.RetrofitConfiguration get() {
    return module.provideRetrofitConfiguration();
  }

  public static Factory<ClientModule.RetrofitConfiguration> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvideRetrofitConfigurationFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#provideRetrofitConfiguration()}. */
  public static ClientModule.RetrofitConfiguration proxyProvideRetrofitConfiguration(
      GlobalConfigModule instance) {
    return instance.provideRetrofitConfiguration();
  }
}
