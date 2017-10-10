package com.jess.arms.di.module;

import com.jess.arms.http.imageloader.BaseImageLoaderStrategy;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GlobalConfigModule_ProvideImageLoaderStrategyFactory
    implements Factory<BaseImageLoaderStrategy> {
  private final GlobalConfigModule module;

  public GlobalConfigModule_ProvideImageLoaderStrategyFactory(GlobalConfigModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public BaseImageLoaderStrategy get() {
    return Preconditions.checkNotNull(
        module.provideImageLoaderStrategy(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<BaseImageLoaderStrategy> create(GlobalConfigModule module) {
    return new GlobalConfigModule_ProvideImageLoaderStrategyFactory(module);
  }

  /** Proxies {@link GlobalConfigModule#provideImageLoaderStrategy()}. */
  public static BaseImageLoaderStrategy proxyProvideImageLoaderStrategy(
      GlobalConfigModule instance) {
    return instance.provideImageLoaderStrategy();
  }
}
