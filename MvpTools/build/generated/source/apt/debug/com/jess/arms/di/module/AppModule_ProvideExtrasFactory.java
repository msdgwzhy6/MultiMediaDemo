package com.jess.arms.di.module;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideExtrasFactory implements Factory<Map<String, Object>> {
  private final AppModule module;

  public AppModule_ProvideExtrasFactory(AppModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Map<String, Object> get() {
    return Preconditions.checkNotNull(
        module.provideExtras(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Map<String, Object>> create(AppModule module) {
    return new AppModule_ProvideExtrasFactory(module);
  }
}
