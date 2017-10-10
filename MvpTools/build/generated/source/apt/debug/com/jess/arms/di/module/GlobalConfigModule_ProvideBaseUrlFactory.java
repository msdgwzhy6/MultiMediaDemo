package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.HttpUrl;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideBaseUrlFactory implements Factory<HttpUrl> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvideBaseUrlFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public HttpUrl get() {
    return Preconditions.checkNotNull(
        module.provideBaseUrl(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<HttpUrl> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvideBaseUrlFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#provideBaseUrl()}. */
  public static HttpUrl proxyProvideBaseUrl(GlobalConfigModule instance) {
    return instance.provideBaseUrl();
  }
}
