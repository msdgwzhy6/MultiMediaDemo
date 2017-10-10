package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ClientModule_ProvideClientBuilderFactory
    implements Factory<OkHttpClient.Builder> {
  private final ClientModule module;

  public ClientModule_ProvideClientBuilderFactory(ClientModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public OkHttpClient.Builder get() {
    return Preconditions.checkNotNull(
        module.provideClientBuilder(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient.Builder> create(ClientModule module) {
    return new ClientModule_ProvideClientBuilderFactory(module);
  }

  /** Proxies {@link ClientModule#provideClientBuilder()}. */
  public static OkHttpClient.Builder proxyProvideClientBuilder(ClientModule instance) {
    return instance.provideClientBuilder();
  }
}
