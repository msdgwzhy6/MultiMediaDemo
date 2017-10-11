package com.jess.arms.di.module;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ClientModule_ProRxErrorHandlerFactory implements Factory<RxErrorHandler> {
  private final ClientModule module;

  private final Provider<Application> applicationProvider;

  private final Provider<ResponseErrorListener> listenerProvider;

  public ClientModule_ProRxErrorHandlerFactory(
      ClientModule module,
      Provider<Application> applicationProvider,
      Provider<ResponseErrorListener> listenerProvider) {
    assert module != null;
    this.module = module;
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
    assert listenerProvider != null;
    this.listenerProvider = listenerProvider;
  }

  @Override
  public RxErrorHandler get() {
    return Preconditions.checkNotNull(
        module.proRxErrorHandler(applicationProvider.get(), listenerProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<RxErrorHandler> create(
      ClientModule module,
      Provider<Application> applicationProvider,
      Provider<ResponseErrorListener> listenerProvider) {
    return new ClientModule_ProRxErrorHandlerFactory(module, applicationProvider, listenerProvider);
  }

  /** Proxies {@link ClientModule#proRxErrorHandler(Application, ResponseErrorListener)}. */
  public static RxErrorHandler proxyProRxErrorHandler(
      ClientModule instance, Application application, ResponseErrorListener listener) {
    return instance.proRxErrorHandler(application, listener);
  }
}
