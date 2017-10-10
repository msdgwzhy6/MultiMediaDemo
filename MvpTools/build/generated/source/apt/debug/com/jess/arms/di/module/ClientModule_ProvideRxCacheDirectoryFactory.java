package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.io.File;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ClientModule_ProvideRxCacheDirectoryFactory implements Factory<File> {
  private final ClientModule module;

  private final Provider<File> cacheDirProvider;

  public ClientModule_ProvideRxCacheDirectoryFactory(
      ClientModule module, Provider<File> cacheDirProvider) {
    assert module != null;
    this.module = module;
    assert cacheDirProvider != null;
    this.cacheDirProvider = cacheDirProvider;
  }

  @Override
  public File get() {
    return Preconditions.checkNotNull(
        module.provideRxCacheDirectory(cacheDirProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<File> create(ClientModule module, Provider<File> cacheDirProvider) {
    return new ClientModule_ProvideRxCacheDirectoryFactory(module, cacheDirProvider);
  }

  /** Proxies {@link ClientModule#provideRxCacheDirectory(File)}. */
  public static File proxyProvideRxCacheDirectory(ClientModule instance, File cacheDir) {
    return instance.provideRxCacheDirectory(cacheDir);
  }
}
