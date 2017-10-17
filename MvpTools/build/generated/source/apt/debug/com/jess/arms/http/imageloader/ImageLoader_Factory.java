package com.jess.arms.http.imageloader;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ImageLoader_Factory implements Factory<ImageLoader> {
  private final Provider<BaseImageLoaderStrategy> strategyProvider;

  public ImageLoader_Factory(Provider<BaseImageLoaderStrategy> strategyProvider) {
    assert strategyProvider != null;
    this.strategyProvider = strategyProvider;
  }

  @Override
  public ImageLoader get() {
    return new ImageLoader(strategyProvider.get());
  }

  public static Factory<ImageLoader> create(Provider<BaseImageLoaderStrategy> strategyProvider) {
    return new ImageLoader_Factory(strategyProvider);
  }
}
