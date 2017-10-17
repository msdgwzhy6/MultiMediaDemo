package com.jess.arms.di.module;

import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.RepositoryManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideRepositoryManagerFactory
    implements Factory<IRepositoryManager> {
  private final AppModule module;

  private final Provider<RepositoryManager> repositoryManagerProvider;

  public AppModule_ProvideRepositoryManagerFactory(
      AppModule module, Provider<RepositoryManager> repositoryManagerProvider) {
    assert module != null;
    this.module = module;
    assert repositoryManagerProvider != null;
    this.repositoryManagerProvider = repositoryManagerProvider;
  }

  @Override
  public IRepositoryManager get() {
    return Preconditions.checkNotNull(
        module.provideRepositoryManager(repositoryManagerProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<IRepositoryManager> create(
      AppModule module, Provider<RepositoryManager> repositoryManagerProvider) {
    return new AppModule_ProvideRepositoryManagerFactory(module, repositoryManagerProvider);
  }
}
