package com.jess.arms.di.module;

import android.support.annotation.Nullable;
import com.jess.arms.http.GlobalHttpHandler;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideGlobalHttpHandlerFactory
    implements Factory<GlobalHttpHandler> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvideGlobalHttpHandlerFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  @Nullable
  public GlobalHttpHandler get() {
    return module.provideGlobalHttpHandler();
  }

  public static Factory<GlobalHttpHandler> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvideGlobalHttpHandlerFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#provideGlobalHttpHandler()}. */
  public static GlobalHttpHandler proxyProvideGlobalHttpHandler(GlobalConfigModule instance) {
    return instance.provideGlobalHttpHandler();
  }
}
