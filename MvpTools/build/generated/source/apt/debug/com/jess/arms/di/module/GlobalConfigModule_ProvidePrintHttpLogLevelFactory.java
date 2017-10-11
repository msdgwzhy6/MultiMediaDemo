package com.jess.arms.di.module;

import android.support.annotation.Nullable;
import com.jess.arms.http.RequestInterceptor;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvidePrintHttpLogLevelFactory
    implements Factory<RequestInterceptor.Level> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvidePrintHttpLogLevelFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  @Nullable
  public RequestInterceptor.Level get() {
    return module.providePrintHttpLogLevel();
  }

  public static Factory<RequestInterceptor.Level> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvidePrintHttpLogLevelFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#providePrintHttpLogLevel()}. */
  public static RequestInterceptor.Level proxyProvidePrintHttpLogLevel(
      GlobalConfigModule instance) {
    return instance.providePrintHttpLogLevel();
  }
}
