package com.jess.arms.di.module;

import android.app.Application;
import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ClientModule_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final ClientModule module;

  private final Provider<Application> applicationProvider;

  private final Provider<ClientModule.RetrofitConfiguration> configurationProvider;

  private final Provider<Retrofit.Builder> builderProvider;

  private final Provider<OkHttpClient> clientProvider;

  private final Provider<HttpUrl> httpUrlProvider;

  private final Provider<Gson> gsonProvider;

  public ClientModule_ProvideRetrofitFactory(
      ClientModule module,
      Provider<Application> applicationProvider,
      Provider<ClientModule.RetrofitConfiguration> configurationProvider,
      Provider<Retrofit.Builder> builderProvider,
      Provider<OkHttpClient> clientProvider,
      Provider<HttpUrl> httpUrlProvider,
      Provider<Gson> gsonProvider) {
    assert module != null;
    this.module = module;
    assert applicationProvider != null;
    this.applicationProvider = applicationProvider;
    assert configurationProvider != null;
    this.configurationProvider = configurationProvider;
    assert builderProvider != null;
    this.builderProvider = builderProvider;
    assert clientProvider != null;
    this.clientProvider = clientProvider;
    assert httpUrlProvider != null;
    this.httpUrlProvider = httpUrlProvider;
    assert gsonProvider != null;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public Retrofit get() {
    return Preconditions.checkNotNull(
        module.provideRetrofit(
            applicationProvider.get(),
            configurationProvider.get(),
            builderProvider.get(),
            clientProvider.get(),
            httpUrlProvider.get(),
            gsonProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Retrofit> create(
      ClientModule module,
      Provider<Application> applicationProvider,
      Provider<ClientModule.RetrofitConfiguration> configurationProvider,
      Provider<Retrofit.Builder> builderProvider,
      Provider<OkHttpClient> clientProvider,
      Provider<HttpUrl> httpUrlProvider,
      Provider<Gson> gsonProvider) {
    return new ClientModule_ProvideRetrofitFactory(
        module,
        applicationProvider,
        configurationProvider,
        builderProvider,
        clientProvider,
        httpUrlProvider,
        gsonProvider);
  }

  /**
   * Proxies {@link ClientModule#provideRetrofit(Application, ClientModule.RetrofitConfiguration,
   * Retrofit.Builder, OkHttpClient, HttpUrl, Gson)}.
   */
  public static Retrofit proxyProvideRetrofit(
      ClientModule instance,
      Application application,
      ClientModule.RetrofitConfiguration configuration,
      Retrofit.Builder builder,
      OkHttpClient client,
      HttpUrl httpUrl,
      Gson gson) {
    return instance.provideRetrofit(application, configuration, builder, client, httpUrl, gson);
  }
}
