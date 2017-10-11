package com.jess.arms.http;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RequestInterceptor_Factory implements Factory<RequestInterceptor> {
  private final Provider<GlobalHttpHandler> handlerProvider;

  private final Provider<RequestInterceptor.Level> levelProvider;

  public RequestInterceptor_Factory(
      Provider<GlobalHttpHandler> handlerProvider,
      Provider<RequestInterceptor.Level> levelProvider) {
    assert handlerProvider != null;
    this.handlerProvider = handlerProvider;
    assert levelProvider != null;
    this.levelProvider = levelProvider;
  }

  @Override
  public RequestInterceptor get() {
    return new RequestInterceptor(handlerProvider.get(), levelProvider.get());
  }

  public static Factory<RequestInterceptor> create(
      Provider<GlobalHttpHandler> handlerProvider,
      Provider<RequestInterceptor.Level> levelProvider) {
    return new RequestInterceptor_Factory(handlerProvider, levelProvider);
  }
}
