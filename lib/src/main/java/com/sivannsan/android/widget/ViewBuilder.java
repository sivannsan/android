package com.sivannsan.android.widget;

import android.view.View;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public final class ViewBuilder extends ViewAbstractBuilder<ViewBuilder, View> {
    public ViewBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public ViewBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public View build(@Nonnull View view) {
        return finish(start(Validate.nonnull(view)));
    }

    @Nonnull
    public View build() {
        return build(new View(scene));
    }
}
