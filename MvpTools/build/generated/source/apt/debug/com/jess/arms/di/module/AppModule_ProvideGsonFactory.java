package com.jess.arms.di.module;

import android.app.Application;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideGsonFactory implements Factory<Gson> {
  private final AppModule module;

  private final Provider<Application> applicationProvider;

  private final Provider<AppModule.GsonConfiguration> configurationProvider;

  public AppModule_ProvideGsonFactory(
      AppModule module,
      Provider<Application> applicationProvider,
      Provider<AppModule.GsonConfiguration> configurationProvider) {
    assert module != null;
    this.module = module;
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
    assert configurationProvider != null;
    this.configurationProvider = configurationProvider;
  }

  @Override
  public Gson get() {
    return Preconditions.checkNotNull(
        module.provideGson(applicationProvider.get(), configurationProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Gson> create(
      AppModule module,
      Provider<Application> applicationProvider,
      Provider<AppModule.GsonConfiguration> configurationProvider) {
    return new AppModule_ProvideGsonFactory(module, applicationProvider, configurationProvider);
  }
}
