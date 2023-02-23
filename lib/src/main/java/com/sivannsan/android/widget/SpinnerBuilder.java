package com.sivannsan.android.widget;

import android.widget.Spinner;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public final class SpinnerBuilder extends SpinnerAbstractBuilder<SpinnerBuilder, Spinner> {
    public SpinnerBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public SpinnerBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public Spinner build(@Nonnull Spinner spinner) {
        return finish(start(Validate.nonnull(spinner)));
    }

    @Nonnull
    public Spinner build() {
        return build(new Spinner(scene));
    }
}
