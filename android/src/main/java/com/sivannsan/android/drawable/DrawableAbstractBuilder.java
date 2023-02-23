package com.sivannsan.android.drawable;

import com.sivannsan.android.builder.AbstractBuilder;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class DrawableAbstractBuilder<Builder, Drawable extends android.graphics.drawable.Drawable> extends AbstractBuilder<Builder> {
    protected DrawableAbstractBuilder() {
    }

    @Nonnull
    protected Drawable prebuild(@Nonnull Drawable drawable) {
        return drawable;
    }
}
