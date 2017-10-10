package com.jess.arms.di.module;

import com.jess.arms.http.RequestInterceptor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Interceptor;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ClientModule_ProvideInterceptorFactory implements Factory<Interceptor> {
  private final ClientModule module;

  private final Provider<RequestInterceptor> interceptProvider;

  public ClientModule_ProvideInterceptorFactory(
      ClientModule module, Provider<RequestInterceptor> interceptProvider) {
    assert module != null;
    this.module = module;
    assert interceptProvider != null;
    this.interceptProvider = interceptProvider;
  }

  @Override
  public Interceptor get() {
    return Preconditions.checkNotNull(
        module.provideInterceptor(interceptProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Interceptor> create(
      ClientModule module, Provider<RequestInterceptor> interceptProvider) {
    return new ClientModule_ProvideInterceptorFactory(module, interceptProvider);
  }

  /** Proxies {@link ClientModule#provideInterceptor(RequestInterceptor)}. */
  public static Interceptor proxyProvideInterceptor(
      ClientModule instance, RequestInterceptor intercept) {
    return instance.provideInterceptor(intercept);
  }
}
