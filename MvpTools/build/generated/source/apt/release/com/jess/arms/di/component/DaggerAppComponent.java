package com.jess.arms.di.component;

import android.app.Application;
import com.google.gson.Gson;
import com.jess.arms.base.delegate.AppDelegate;
import com.jess.arms.base.delegate.AppDelegate_MembersInjector;
import com.jess.arms.di.module.AppModule;
import com.jess.arms.di.module.AppModule_ProvideApplicationFactory;
import com.jess.arms.di.module.AppModule_ProvideExtrasFactory;
import com.jess.arms.di.module.AppModule_ProvideGsonFactory;
import com.jess.arms.di.module.AppModule_ProvideRepositoryManagerFactory;
import com.jess.arms.di.module.ClientModule;
import com.jess.arms.di.module.ClientModule_ProRxErrorHandlerFactory;
import com.jess.arms.di.module.ClientModule_ProvideClientBuilderFactory;
import com.jess.arms.di.module.ClientModule_ProvideClientFactory;
import com.jess.arms.di.module.ClientModule_ProvideInterceptorFactory;
import com.jess.arms.di.module.ClientModule_ProvideRetrofitBuilderFactory;
import com.jess.arms.di.module.ClientModule_ProvideRetrofitFactory;
import com.jess.arms.di.module.ClientModule_ProvideRxCacheDirectoryFactory;
import com.jess.arms.di.module.ClientModule_ProvideRxCacheFactory;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.di.module.GlobalConfigModule_ProvideBaseUrlFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideCacheFileFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideGlobalHttpHandlerFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideGsonConfigurationFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideImageLoaderStrategyFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideInterceptorsFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideOkhttpConfigurationFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvidePrintHttpLogLevelFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideResponseErrorListenerFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideRetrofitConfigurationFactory;
import com.jess.arms.di.module.GlobalConfigModule_ProvideRxCacheConfigurationFactory;
import com.jess.arms.http.GlobalHttpHandler;
import com.jess.arms.http.RequestInterceptor;
import com.jess.arms.http.RequestInterceptor_Factory;
import com.jess.arms.http.imageloader.BaseImageLoaderStrategy;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.ImageLoader_Factory;
import com.jess.arms.integration.ActivityLifecycle;
import com.jess.arms.integration.ActivityLifecycle_Factory;
import com.jess.arms.integration.AppManager;
import com.jess.arms.integration.AppManager_Factory;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.integration.RepositoryManager;
import com.jess.arms.integration.RepositoryManager_Factory;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import io.rx_cache2.internal.RxCache;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<Application> provideApplicationProvider;

  private Provider<ClientModule.RetrofitConfiguration> provideRetrofitConfigurationProvider;

  private Provider<Retrofit.Builder> provideRetrofitBuilderProvider;

  private Provider<ClientModule.OkhttpConfiguration> provideOkhttpConfigurationProvider;

  private Provider<OkHttpClient.Builder> provideClientBuilderProvider;

  private Provider<GlobalHttpHandler> provideGlobalHttpHandlerProvider;

  private Provider<RequestInterceptor.Level> providePrintHttpLogLevelProvider;

  private Provider<RequestInterceptor> requestInterceptorProvider;

  private Provider<Interceptor> provideInterceptorProvider;

  private Provider<List<Interceptor>> provideInterceptorsProvider;

  private Provider<OkHttpClient> provideClientProvider;

  private Provider<HttpUrl> provideBaseUrlProvider;

  private Provider<AppModule.GsonConfiguration> provideGsonConfigurationProvider;

  private Provider<Gson> provideGsonProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private Provider<ClientModule.RxCacheConfiguration> provideRxCacheConfigurationProvider;

  private Provider<File> provideCacheFileProvider;

  private Provider<File> provideRxCacheDirectoryProvider;

  private Provider<RxCache> provideRxCacheProvider;

  private Provider<RepositoryManager> repositoryManagerProvider;

  private Provider<IRepositoryManager> provideRepositoryManagerProvider;

  private Provider<ResponseErrorListener> provideResponseErrorListenerProvider;

  private Provider<RxErrorHandler> proRxErrorHandlerProvider;

  private Provider<BaseImageLoaderStrategy> provideImageLoaderStrategyProvider;

  private Provider<ImageLoader> imageLoaderProvider;

  private Provider<AppManager> appManagerProvider;

  private Provider<Map<String, Object>> provideExtrasProvider;

  private Provider<ActivityLifecycle> activityLifecycleProvider;

  private MembersInjector<AppDelegate> appDelegateMembersInjector;

  private DaggerAppComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideApplicationProvider =
        DoubleCheck.provider(AppModule_ProvideApplicationFactory.create(builder.appModule));

    this.provideRetrofitConfigurationProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideRetrofitConfigurationFactory.create(
                builder.globalConfigModule));

    this.provideRetrofitBuilderProvider =
        DoubleCheck.provider(
            ClientModule_ProvideRetrofitBuilderFactory.create(builder.clientModule));

    this.provideOkhttpConfigurationProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideOkhttpConfigurationFactory.create(
                builder.globalConfigModule));

    this.provideClientBuilderProvider =
        DoubleCheck.provider(ClientModule_ProvideClientBuilderFactory.create(builder.clientModule));

    this.provideGlobalHttpHandlerProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideGlobalHttpHandlerFactory.create(builder.globalConfigModule));

    this.providePrintHttpLogLevelProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvidePrintHttpLogLevelFactory.create(builder.globalConfigModule));

    this.requestInterceptorProvider =
        DoubleCheck.provider(
            RequestInterceptor_Factory.create(
                provideGlobalHttpHandlerProvider, providePrintHttpLogLevelProvider));

    this.provideInterceptorProvider =
        DoubleCheck.provider(
            ClientModule_ProvideInterceptorFactory.create(
                builder.clientModule, requestInterceptorProvider));

    this.provideInterceptorsProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideInterceptorsFactory.create(builder.globalConfigModule));

    this.provideClientProvider =
        DoubleCheck.provider(
            ClientModule_ProvideClientFactory.create(
                builder.clientModule,
                provideApplicationProvider,
                provideOkhttpConfigurationProvider,
                provideClientBuilderProvider,
                provideInterceptorProvider,
                provideInterceptorsProvider,
                provideGlobalHttpHandlerProvider));

    this.provideBaseUrlProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideBaseUrlFactory.create(builder.globalConfigModule));

    this.provideGsonConfigurationProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideGsonConfigurationFactory.create(builder.globalConfigModule));

    this.provideGsonProvider =
        DoubleCheck.provider(
            AppModule_ProvideGsonFactory.create(
                builder.appModule, provideApplicationProvider, provideGsonConfigurationProvider));

    this.provideRetrofitProvider =
        DoubleCheck.provider(
            ClientModule_ProvideRetrofitFactory.create(
                builder.clientModule,
                provideApplicationProvider,
                provideRetrofitConfigurationProvider,
                provideRetrofitBuilderProvider,
                provideClientProvider,
                provideBaseUrlProvider,
                provideGsonProvider));

    this.provideRxCacheConfigurationProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideRxCacheConfigurationFactory.create(
                builder.globalConfigModule));

    this.provideCacheFileProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideCacheFileFactory.create(
                builder.globalConfigModule, provideApplicationProvider));

    this.provideRxCacheDirectoryProvider =
        DoubleCheck.provider(
            ClientModule_ProvideRxCacheDirectoryFactory.create(
                builder.clientModule, provideCacheFileProvider));

    this.provideRxCacheProvider =
        DoubleCheck.provider(
            ClientModule_ProvideRxCacheFactory.create(
                builder.clientModule,
                provideApplicationProvider,
                provideRxCacheConfigurationProvider,
                provideRxCacheDirectoryProvider));

    this.repositoryManagerProvider =
        DoubleCheck.provider(
            RepositoryManager_Factory.create(
                provideRetrofitProvider, provideRxCacheProvider, provideApplicationProvider));

    this.provideRepositoryManagerProvider =
        DoubleCheck.provider(
            AppModule_ProvideRepositoryManagerFactory.create(
                builder.appModule, repositoryManagerProvider));

    this.provideResponseErrorListenerProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideResponseErrorListenerFactory.create(
                builder.globalConfigModule));

    this.proRxErrorHandlerProvider =
        DoubleCheck.provider(
            ClientModule_ProRxErrorHandlerFactory.create(
                builder.clientModule,
                provideApplicationProvider,
                provideResponseErrorListenerProvider));

    this.provideImageLoaderStrategyProvider =
        DoubleCheck.provider(
            GlobalConfigModule_ProvideImageLoaderStrategyFactory.create(
                builder.globalConfigModule));

    this.imageLoaderProvider =
        DoubleCheck.provider(ImageLoader_Factory.create(provideImageLoaderStrategyProvider));

    this.appManagerProvider =
        DoubleCheck.provider(AppManager_Factory.create(provideApplicationProvider));

    this.provideExtrasProvider =
        DoubleCheck.provider(AppModule_ProvideExtrasFactory.create(builder.appModule));

    this.activityLifecycleProvider =
        DoubleCheck.provider(
            ActivityLifecycle_Factory.create(
                appManagerProvider, provideApplicationProvider, provideExtrasProvider));

    this.appDelegateMembersInjector = AppDelegate_MembersInjector.create(activityLifecycleProvider);
  }

  @Override
  public Application application() {
    return provideApplicationProvider.get();
  }

  @Override
  public IRepositoryManager repositoryManager() {
    return provideRepositoryManagerProvider.get();
  }

  @Override
  public RxErrorHandler rxErrorHandler() {
    return proRxErrorHandlerProvider.get();
  }

  @Override
  public OkHttpClient okHttpClient() {
    return provideClientProvider.get();
  }

  @Override
  public ImageLoader imageLoader() {
    return imageLoaderProvider.get();
  }

  @Override
  public Gson gson() {
    return provideGsonProvider.get();
  }

  @Override
  public File cacheFile() {
    return provideCacheFileProvider.get();
  }

  @Override
  public AppManager appManager() {
    return appManagerProvider.get();
  }

  @Override
  public Map<String, Object> extras() {
    return provideExtrasProvider.get();
  }

  @Override
  public void inject(AppDelegate delegate) {
    appDelegateMembersInjector.injectMembers(delegate);
  }

  public static final class Builder {
    private AppModule appModule;

    private GlobalConfigModule globalConfigModule;

    private ClientModule clientModule;

    private Builder() {}

    public AppComponent build() {
      if (appModule == null) {
        throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
      }
      if (globalConfigModule == null) {
        throw new IllegalStateException(
            GlobalConfigModule.class.getCanonicalName() + " must be set");
      }
      if (clientModule == null) {
        this.clientModule = new ClientModule();
      }
      return new DaggerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder clientModule(ClientModule clientModule) {
      this.clientModule = Preconditions.checkNotNull(clientModule);
      return this;
    }

    public Builder globalConfigModule(GlobalConfigModule globalConfigModule) {
      this.globalConfigModule = Preconditions.checkNotNull(globalConfigModule);
      return this;
    }
  }
}
