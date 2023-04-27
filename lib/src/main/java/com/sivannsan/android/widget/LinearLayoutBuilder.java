package com.sivannsan.android.widget;

import android.widget.LinearLayout;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

public final class LinearLayoutBuilder extends LinearLayoutAbstractBuilder<LinearLayoutBuilder, LinearLayout> {
    public LinearLayoutBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public LinearLayoutBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public LinearLayout build(@Nonnull LinearLayout linearLayout) {
        return finish(start(Validate.nonnull(linearLayout)));
    }

    @Nonnull
    public LinearLayout build() {
        return build(new LinearLayout(scene));
    }
}
