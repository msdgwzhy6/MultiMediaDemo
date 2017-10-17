package com.jess.arms.integration;

import android.app.Application;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import io.rx_cache2.internal.RxCache;
import javax.annotation.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RepositoryManager_Factory implements Factory<RepositoryManager> {
  private final Provider<Retrofit> retrofitProvider;

  private final Provider<RxCache> rxCacheProvider;

  private final Provider<Application> applicationProvider;

  public RepositoryManager_Factory(
      Provider<Retrofit> retrofitProvider,
      Provider<RxCache> rxCacheProvider,
      Provider<Application> applicationProvider) {
    assert retrofitProvider != null;
    this.retrofitProvider = retrofitProvider;
    assert rxCacheProvider != null;
    this.rxCacheProvider = rxCacheProvider;
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
  }

  @Override
  public RepositoryManager get() {
    return new RepositoryManager(
        DoubleCheck.lazy(retrofitProvider),
        DoubleCheck.lazy(rxCacheProvider),
        applicationProvider.get());
  }

  public static Factory<RepositoryManager> create(
      Provider<Retrofit> retrofitProvider,
      Provider<RxCache> rxCacheProvider,
      Provider<Application> applicationProvider) {
    return new RepositoryManager_Factory(retrofitProvider, rxCacheProvider, applicationProvider);
  }
}
