/*
 *  Copyright (C) 2017 Bilibili
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.jess.arms.widget.boxing.tool;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jess.arms.R;
import com.jess.arms.http.imageloader.glide.GlideArms;
import com.jess.arms.http.imageloader.glide.GlideRequest;
import com.jess.arms.widget.boxing.loader.IBoxingCallback;
import com.jess.arms.widget.boxing.loader.IBoxingMediaLoader;

import timber.log.Timber;

//
//import com.bumptech.glide.BitmapTypeRequest;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.target.Target;
//import com.jess.arms.R;

/**
 * use https://github.com/bumptech/glide as media loader.
 * can <b>not</b> be used in Production Environment.
 *
 * @author ChenSL
 */
public class BoxingGlideLoader implements IBoxingMediaLoader {

    @Override
    public void displayThumbnail(@NonNull ImageView img, @NonNull String absPath, int width, int height) {
        String path = "file://" + absPath;
        try {
            // https://github.com/bumptech/glide/issues/1531
            // TODO: 2017/6/2 Glide 4.0
            GlideRequest<Drawable> glideRequest = GlideArms.with(img.getContext())
                    .load(path);
            glideRequest.centerCrop().placeholder(R.drawable.defaultimage).override(width,height).into(img);
        } catch(IllegalArgumentException ignore) {
            Timber.e("图片加载失败，"+ignore);
        }

    }

    @Override
    public void displayRaw(@NonNull final ImageView img, @NonNull String absPath, int width, int height, final IBoxingCallback callback) {
        String path = "file://" + absPath;
        // TODO: 2017/8/20  Glide 4.0


        GlideRequest<Drawable> glideRequest = GlideArms.with(img.getContext())
                .load(path);
        if (width > 0 && height > 0) {
            glideRequest.override(width, height);
        }
        glideRequest.centerCrop().placeholder(R.drawable.defaultimage);
        glideRequest.listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                if (callback != null) {
                    callback.onFail(e);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                if (resource != null && callback != null) {
                    img.setImageDrawable(resource);
                    callback.onSuccess();
                    return true;
                }
                return false;
            }
        }).into(img);
    }

}
