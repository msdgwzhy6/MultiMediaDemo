package com.jess.arms.di.module;

import android.support.annotation.Nullable;
import dagger.internal.Factory;
import java.util.List;
import javax.annotation.Generated;
import okhttp3.Interceptor;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideInterceptorsFactory
    implements Factory<List<Interceptor>> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvideInterceptorsFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  @Nullable
  public List<Interceptor> get() {
    return module.provideInterceptors();
  }

  public static Factory<List<Interceptor>> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvideInterceptorsFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#provideInterceptors()}. */
  public static List<Interceptor> proxyProvideInterceptors(GlobalConfigModule instance) {
    return instance.provideInterceptors();
  }
}
