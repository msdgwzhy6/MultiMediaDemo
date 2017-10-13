package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideResponseErrorListenerFactory
    implements Factory<ResponseErrorListener> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvideResponseErrorListenerFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public ResponseErrorListener get() {
    return Preconditions.checkNotNull(
        module.provideResponseErrorListener(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<ResponseErrorListener> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvideResponseErrorListenerFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#provideResponseErrorListener()}. */
  public static ResponseErrorListener proxyProvideResponseErrorListener(
      GlobalConfigModule instance) {
    return instance.provideResponseErrorListener();
  }
}
