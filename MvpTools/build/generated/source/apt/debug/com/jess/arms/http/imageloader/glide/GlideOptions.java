package com.jess.arms.http.imageloader.glide;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;
import java.lang.Class;
import java.lang.Override;
import java.lang.SuppressWarnings;

/**
 * Automatically generated from {@link com.bumptech.glide.annotation.GlideExtension} annotated classes.
 *
 * @see RequestOptions
 */
@SuppressWarnings("deprecation")
public final class GlideOptions extends RequestOptions {
  private static GlideOptions fitCenterTransform0;

  private static GlideOptions centerInsideTransform1;

  private static GlideOptions centerCropTransform2;

  private static GlideOptions circleCropTransform3;

  private static GlideOptions noTransformation4;

  private static GlideOptions noAnimation5;

  /**
   * @see RequestOptions#sizeMultiplierOf(float)
   */
  public static GlideOptions sizeMultiplierOf(float sizeMultiplier) {
    return new GlideOptions().sizeMultiplier(sizeMultiplier);
  }

  /**
   * @see RequestOptions#diskCacheStrategyOf(DiskCacheStrategy)
   */
  public static GlideOptions diskCacheStrategyOf(DiskCacheStrategy arg0) {
    return new GlideOptions().diskCacheStrategy(arg0);
  }

  /**
   * @see RequestOptions#priorityOf(Priority)
   */
  public static GlideOptions priorityOf(Priority arg0) {
    return new GlideOptions().priority(arg0);
  }

  /**
   * @see RequestOptions#placeholderOf(Drawable)
   */
  public static GlideOptions placeholderOf(Drawable arg0) {
    return new GlideOptions().placeholder(arg0);
  }

  /**
   * @see RequestOptions#placeholderOf(int)
   */
  public static GlideOptions placeholderOf(int placeholderId) {
    return new GlideOptions().placeholder(placeholderId);
  }

  /**
   * @see RequestOptions#errorOf(Drawable)
   */
  public static GlideOptions errorOf(Drawable arg0) {
    return new GlideOptions().error(arg0);
  }

  /**
   * @see RequestOptions#errorOf(int)
   */
  public static GlideOptions errorOf(int errorId) {
    return new GlideOptions().error(errorId);
  }

  /**
   * @see RequestOptions#skipMemoryCacheOf(boolean)
   */
  public static GlideOptions skipMemoryCacheOf(boolean skipMemoryCache) {
    return new GlideOptions().skipMemoryCache(skipMemoryCache);
  }

  /**
   * @see RequestOptions#overrideOf(int, int)
   */
  public static GlideOptions overrideOf(int width, int height) {
    return new GlideOptions().override(width, height);
  }

  /**
   * @see RequestOptions#overrideOf(int)
   */
  public static GlideOptions overrideOf(int size) {
    return new GlideOptions().override(size);
  }

  /**
   * @see RequestOptions#signatureOf(Key)
   */
  public static GlideOptions signatureOf(Key arg0) {
    return new GlideOptions().signature(arg0);
  }

  /**
   * @see RequestOptions#fitCenterTransform()
   */
  public static GlideOptions fitCenterTransform() {
    if (GlideOptions.fitCenterTransform0 == null) {
      GlideOptions.fitCenterTransform0 =
          new GlideOptions().fitCenter().autoClone();
    }
    return GlideOptions.fitCenterTransform0;
  }

  /**
   * @see RequestOptions#centerInsideTransform()
   */
  public static GlideOptions centerInsideTransform() {
    if (GlideOptions.centerInsideTransform1 == null) {
      GlideOptions.centerInsideTransform1 =
          new GlideOptions().centerInside().autoClone();
    }
    return GlideOptions.centerInsideTransform1;
  }

  /**
   * @see RequestOptions#centerCropTransform()
   */
  public static GlideOptions centerCropTransform() {
    if (GlideOptions.centerCropTransform2 == null) {
      GlideOptions.centerCropTransform2 =
          new GlideOptions().centerCrop().autoClone();
    }
    return GlideOptions.centerCropTransform2;
  }

  /**
   * @see RequestOptions#circleCropTransform()
   */
  public static GlideOptions circleCropTransform() {
    if (GlideOptions.circleCropTransform3 == null) {
      GlideOptions.circleCropTransform3 =
          new GlideOptions().circleCrop().autoClone();
    }
    return GlideOptions.circleCropTransform3;
  }

  /**
   * @see RequestOptions#bitmapTransform(Transformation)
   */
  public static GlideOptions bitmapTransform(Transformation<Bitmap> arg0) {
    return new GlideOptions().transform(arg0);
  }

  /**
   * @see RequestOptions#noTransformation()
   */
  public static GlideOptions noTransformation() {
    if (GlideOptions.noTransformation4 == null) {
      GlideOptions.noTransformation4 =
          new GlideOptions().dontTransform().autoClone();
    }
    return GlideOptions.noTransformation4;
  }

  /**
   * @see RequestOptions#option(Option, T)
   */
  public static <T> GlideOptions option(Option<T> arg0, T arg1) {
    return new GlideOptions().set(arg0, arg1);
  }

  /**
   * @see RequestOptions#decodeTypeOf(Class)
   */
  public static GlideOptions decodeTypeOf(Class<?> arg0) {
    return new GlideOptions().decode(arg0);
  }

  /**
   * @see RequestOptions#formatOf(DecodeFormat)
   */
  public static GlideOptions formatOf(DecodeFormat arg0) {
    return new GlideOptions().format(arg0);
  }

  /**
   * @see RequestOptions#frameOf(long)
   */
  public static GlideOptions frameOf(long frameTimeMicros) {
    return new GlideOptions().frame(frameTimeMicros);
  }

  /**
   * @see RequestOptions#downsampleOf(DownsampleStrategy)
   */
  public static GlideOptions downsampleOf(DownsampleStrategy arg0) {
    return new GlideOptions().downsample(arg0);
  }

  /**
   * @see RequestOptions#encodeQualityOf(int)
   */
  public static GlideOptions encodeQualityOf(int quality) {
    return new GlideOptions().encodeQuality(quality);
  }

  /**
   * @see RequestOptions#encodeFormatOf(CompressFormat)
   */
  public static GlideOptions encodeFormatOf(Bitmap.CompressFormat arg0) {
    return new GlideOptions().encodeFormat(arg0);
  }

  /**
   * @see RequestOptions#noAnimation()
   */
  public static GlideOptions noAnimation() {
    if (GlideOptions.noAnimation5 == null) {
      GlideOptions.noAnimation5 =
          new GlideOptions().dontAnimate().autoClone();
    }
    return GlideOptions.noAnimation5;
  }

  @Override
  public GlideOptions sizeMultiplier(float sizeMultiplier) {
    return (GlideOptions) super.sizeMultiplier(sizeMultiplier);
  }

  @Override
  public GlideOptions useUnlimitedSourceGeneratorsPool(boolean flag) {
    return (GlideOptions) super.useUnlimitedSourceGeneratorsPool(flag);
  }

  @Override
  public GlideOptions onlyRetrieveFromCache(boolean flag) {
    return (GlideOptions) super.onlyRetrieveFromCache(flag);
  }

  @Override
  public GlideOptions diskCacheStrategy(DiskCacheStrategy arg0) {
    return (GlideOptions) super.diskCacheStrategy(arg0);
  }

  @Override
  public GlideOptions priority(Priority arg0) {
    return (GlideOptions) super.priority(arg0);
  }

  @Override
  public GlideOptions placeholder(Drawable arg0) {
    return (GlideOptions) super.placeholder(arg0);
  }

  @Override
  public GlideOptions placeholder(int resourceId) {
    return (GlideOptions) super.placeholder(resourceId);
  }

  @Override
  public GlideOptions fallback(Drawable drawable) {
    return (GlideOptions) super.fallback(drawable);
  }

  @Override
  public GlideOptions fallback(int resourceId) {
    return (GlideOptions) super.fallback(resourceId);
  }

  @Override
  public GlideOptions error(Drawable arg0) {
    return (GlideOptions) super.error(arg0);
  }

  @Override
  public GlideOptions error(int resourceId) {
    return (GlideOptions) super.error(resourceId);
  }

  @Override
  public GlideOptions theme(Resources.Theme theme) {
    return (GlideOptions) super.theme(theme);
  }

  @Override
  public GlideOptions skipMemoryCache(boolean skip) {
    return (GlideOptions) super.skipMemoryCache(skip);
  }

  @Override
  public GlideOptions override(int width, int height) {
    return (GlideOptions) super.override(width, height);
  }

  @Override
  public GlideOptions override(int size) {
    return (GlideOptions) super.override(size);
  }

  @Override
  public GlideOptions signature(Key arg0) {
    return (GlideOptions) super.signature(arg0);
  }

  @Override
  public GlideOptions clone() {
    return (GlideOptions) super.clone();
  }

  @Override
  public <T> GlideOptions set(Option<T> arg0, T arg1) {
    return (GlideOptions) super.set(arg0, arg1);
  }

  @Override
  public GlideOptions decode(Class<?> arg0) {
    return (GlideOptions) super.decode(arg0);
  }

  @Override
  public GlideOptions encodeFormat(Bitmap.CompressFormat arg0) {
    return (GlideOptions) super.encodeFormat(arg0);
  }

  @Override
  public GlideOptions encodeQuality(int quality) {
    return (GlideOptions) super.encodeQuality(quality);
  }

  @Override
  public GlideOptions format(DecodeFormat arg0) {
    return (GlideOptions) super.format(arg0);
  }

  @Override
  public GlideOptions frame(long frameTimeMicros) {
    return (GlideOptions) super.frame(frameTimeMicros);
  }

  @Override
  public GlideOptions downsample(DownsampleStrategy arg0) {
    return (GlideOptions) super.downsample(arg0);
  }

  @Override
  public GlideOptions optionalCenterCrop() {
    return (GlideOptions) super.optionalCenterCrop();
  }

  @Override
  public GlideOptions centerCrop() {
    return (GlideOptions) super.centerCrop();
  }

  @Override
  public GlideOptions optionalFitCenter() {
    return (GlideOptions) super.optionalFitCenter();
  }

  @Override
  public GlideOptions fitCenter() {
    return (GlideOptions) super.fitCenter();
  }

  @Override
  public GlideOptions optionalCenterInside() {
    return (GlideOptions) super.optionalCenterInside();
  }

  @Override
  public GlideOptions centerInside() {
    return (GlideOptions) super.centerInside();
  }

  @Override
  public GlideOptions optionalCircleCrop() {
    return (GlideOptions) super.optionalCircleCrop();
  }

  @Override
  public GlideOptions circleCrop() {
    return (GlideOptions) super.circleCrop();
  }

  @Override
  public GlideOptions transform(Transformation<Bitmap> arg0) {
    return (GlideOptions) super.transform(arg0);
  }

  @Override
  public GlideOptions optionalTransform(Transformation<Bitmap> transformation) {
    return (GlideOptions) super.optionalTransform(transformation);
  }

  @Override
  public <T> GlideOptions optionalTransform(Class<T> resourceClass,
      Transformation<T> transformation) {
    return (GlideOptions) super.optionalTransform(resourceClass, transformation);
  }

  @Override
  public <T> GlideOptions transform(Class<T> resourceClass, Transformation<T> transformation) {
    return (GlideOptions) super.transform(resourceClass, transformation);
  }

  @Override
  public GlideOptions dontTransform() {
    return (GlideOptions) super.dontTransform();
  }

  @Override
  public GlideOptions dontAnimate() {
    return (GlideOptions) super.dontAnimate();
  }

  @Override
  public GlideOptions apply(RequestOptions other) {
    return (GlideOptions) super.apply(other);
  }

  @Override
  public GlideOptions lock() {
    return (GlideOptions) super.lock();
  }

  @Override
  public GlideOptions autoClone() {
    return (GlideOptions) super.autoClone();
  }
}
