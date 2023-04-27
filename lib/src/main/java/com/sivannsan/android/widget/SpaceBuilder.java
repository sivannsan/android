package com.sivannsan.android.widget;

import android.widget.Space;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

public final class SpaceBuilder extends SpaceAbstractBuilder<SpaceBuilder, Space> {
    public SpaceBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public SpaceBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public Space build(@Nonnull Space space) {
        return finish(start(Validate.nonnull(space)));
    }

    @Nonnull
    public Space build() {
        return build(new Space(scene));
    }
}
