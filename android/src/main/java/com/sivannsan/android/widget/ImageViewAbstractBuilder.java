package com.sivannsan.android.widget;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class ImageViewAbstractBuilder<Builder, ImageView extends android.widget.ImageView> extends ViewAbstractBuilder<Builder, ImageView> {
    private Integer imageResource;
    private Drawable imageDrawable;
    private Bitmap imageBitmap;

    protected ImageViewAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected ImageViewAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    public Builder setImage(@DrawableRes Integer value) {
        imageResource = value;
        return builder;
    }

    @Nonnull
    public Builder setImage(Drawable value) {
        imageDrawable = value;
        return builder;
    }

    @Nonnull
    public Builder setImage(Bitmap value) {
        imageBitmap = value;
        return builder;
    }

    @Nonnull
    protected ImageView start(@Nonnull ImageView imageView) {
        super.start(imageView);
        if (imageResource != null) imageView.setImageResource(imageResource);
        if (imageDrawable != null) imageView.setImageDrawable(imageDrawable);
        if (imageBitmap != null) imageView.setImageBitmap(imageBitmap);
        return imageView;
    }
}
