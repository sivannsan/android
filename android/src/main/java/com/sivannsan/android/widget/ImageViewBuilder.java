package com.sivannsan.android.widget;

import android.widget.ImageView;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public final class ImageViewBuilder extends ImageViewAbstractBuilder<ImageViewBuilder, ImageView> {
    public ImageViewBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public ImageViewBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public ImageView build(@Nonnull ImageView imageView) {
        return finish(start(Validate.nonnull(imageView)));
    }

    @Nonnull
    public ImageView build() {
        return build(new ImageView(scene));
    }
}
