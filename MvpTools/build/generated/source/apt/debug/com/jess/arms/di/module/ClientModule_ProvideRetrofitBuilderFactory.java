package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ClientModule_ProvideRetrofitBuilderFactory implements Factory<Retrofit.Builder> {
  private final ClientModule module;

  public ClientModule_ProvideRetrofitBuilderFactory(ClientModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Retrofit.Builder get() {
    return Preconditions.checkNotNull(
        module.provideRetrofitBuilder(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Retrofit.Builder> create(ClientModule module) {
    return new ClientModule_ProvideRetrofitBuilderFactory(module);
  }

  /** Proxies {@link ClientModule#provideRetrofitBuilder()}. */
  public static Retrofit.Builder proxyProvideRetrofitBuilder(ClientModule instance) {
    return instance.provideRetrofitBuilder();
  }
}
