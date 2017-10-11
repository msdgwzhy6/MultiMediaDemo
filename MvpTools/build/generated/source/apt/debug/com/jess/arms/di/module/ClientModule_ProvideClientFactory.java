package com.jess.arms.di.module;

import android.app.Application;
import com.jess.arms.http.GlobalHttpHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ClientModule_ProvideClientFactory implements Factory<OkHttpClient> {
  private final ClientModule module;

  private final Provider<Application> applicationProvider;

  private final Provider<ClientModule.OkhttpConfiguration> configurationProvider;

  private final Provider<OkHttpClient.Builder> builderProvider;

  private final Provider<Interceptor> interceptProvider;

  private final Provider<List<Interceptor>> interceptorsProvider;

  private final Provider<GlobalHttpHandler> handlerProvider;

  public ClientModule_ProvideClientFactory(
      ClientModule module,
      Provider<Application> applicationProvider,
      Provider<ClientModule.OkhttpConfiguration> configurationProvider,
      Provider<OkHttpClient.Builder> builderProvider,
      Provider<Interceptor> interceptProvider,
      Provider<List<Interceptor>> interceptorsProvider,
      Provider<GlobalHttpHandler> handlerProvider) {
    assert module != null;
    this.module = module;
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
    assert configurationProvider != null;
    this.configurationProvider = configurationProvider;
    assert builderProvider != null;
    this.builderProvider = builderProvider;
    assert interceptProvider != null;
    this.interceptProvider = interceptProvider;
    assert interceptorsProvider != null;
    this.interceptorsProvider = interceptorsProvider;
    assert handlerProvider != null;
    this.handlerProvider = handlerProvider;
  }

  @Override
  public OkHttpClient get() {
    return Preconditions.checkNotNull(
        module.provideClient(
            applicationProvider.get(),
            configurationProvider.get(),
            builderProvider.get(),
            interceptProvider.get(),
            interceptorsProvider.get(),
            handlerProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<OkHttpClient> create(
      ClientModule module,
      Provider<Application> applicationProvider,
      Provider<ClientModule.OkhttpConfiguration> configurationProvider,
      Provider<OkHttpClient.Builder> builderProvider,
      Provider<Interceptor> interceptProvider,
      Provider<List<Interceptor>> interceptorsProvider,
      Provider<GlobalHttpHandler> handlerProvider) {
    return new ClientModule_ProvideClientFactory(
        module,
        applicationProvider,
        configurationProvider,
        builderProvider,
        interceptProvider,
        interceptorsProvider,
        handlerProvider);
  }

  /**
   * Proxies {@link ClientModule#provideClient(Application, ClientModule.OkhttpConfiguration,
   * OkHttpClient.Builder, Interceptor, List, GlobalHttpHandler)}.
   */
  public static OkHttpClient proxyProvideClient(
      ClientModule instance,
      Application application,
      ClientModule.OkhttpConfiguration configuration,
      OkHttpClient.Builder builder,
      Interceptor intercept,
      List<Interceptor> interceptors,
      GlobalHttpHandler handler) {
    return instance.provideClient(
        application, configuration, builder, intercept, interceptors, handler);
  }
}
