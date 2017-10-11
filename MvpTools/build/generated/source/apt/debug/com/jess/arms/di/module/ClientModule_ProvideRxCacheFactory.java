package com.jess.arms.di.module;

import android.app.Application;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import io.rx_cache2.internal.RxCache;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ClientModule_ProvideRxCacheFactory implements Factory<RxCache> {
  private final ClientModule module;

  private final Provider<Application> applicationProvider;

  private final Provider<ClientModule.RxCacheConfiguration> configurationProvider;

  private final Provider<File> cacheDirectoryProvider;

  public ClientModule_ProvideRxCacheFactory(
      ClientModule module,
      Provider<Application> applicationProvider,
      Provider<ClientModule.RxCacheConfiguration> configurationProvider,
      Provider<File> cacheDirectoryProvider) {
    assert module != null;
    this.module = module;
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
    assert configurationProvider != null;
    this.configurationProvider = configurationProvider;
    assert cacheDirectoryProvider != null;
    this.cacheDirectoryProvider = cacheDirectoryProvider;
  }

  @Override
  public RxCache get() {
    return Preconditions.checkNotNull(
        module.provideRxCache(
            applicationProvider.get(), configurationProvider.get(), cacheDirectoryProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<RxCache> create(
      ClientModule module,
      Provider<Application> applicationProvider,
      Provider<ClientModule.RxCacheConfiguration> configurationProvider,
      Provider<File> cacheDirectoryProvider) {
    return new ClientModule_ProvideRxCacheFactory(
        module, applicationProvider, configurationProvider, cacheDirectoryProvider);
  }

  /**
   * Proxies {@link ClientModule#provideRxCache(Application, ClientModule.RxCacheConfiguration,
   * File)}.
   */
  public static RxCache proxyProvideRxCache(
      ClientModule instance,
      Application application,
      ClientModule.RxCacheConfiguration configuration,
      File cacheDirectory) {
    return instance.provideRxCache(application, configuration, cacheDirectory);
  }
}
