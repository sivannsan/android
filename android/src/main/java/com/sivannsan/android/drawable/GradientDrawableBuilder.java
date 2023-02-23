package com.sivannsan.android.drawable;

import android.graphics.drawable.GradientDrawable;

import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public final class GradientDrawableBuilder extends GradientDrawableAbstractBuilder<GradientDrawableBuilder, GradientDrawable> {
    public GradientDrawableBuilder() {
        builder = this;
    }

    @Nonnull
    public GradientDrawable build(@Nonnull GradientDrawable gradientDrawable) {
        return prebuild(Validate.nonnull(gradientDrawable));
    }

    @Nonnull
    public GradientDrawable build() {
        return build(new GradientDrawable());
    }
}
