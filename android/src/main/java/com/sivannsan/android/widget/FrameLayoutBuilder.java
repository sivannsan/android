package com.sivannsan.android.widget;

import android.widget.FrameLayout;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public final class FrameLayoutBuilder extends FrameLayoutAbstractBuilder<FrameLayoutBuilder, FrameLayout> {
    public FrameLayoutBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public FrameLayoutBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public FrameLayout build(@Nonnull FrameLayout frameLayout) {
        return finish(this.start(Validate.nonnull(frameLayout)));
    }

    @Nonnull
    public FrameLayout build() {
        return build(new FrameLayout(scene));
    }
}
