package com.sivannsan.android.widget;

import android.widget.ScrollView;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

public final class ScrollViewBuilder extends ScrollViewAbstractBuilder<ScrollViewBuilder, ScrollView> {
    public ScrollViewBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public ScrollViewBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public ScrollView build(@Nonnull ScrollView scrollView) {
        return finish(start(Validate.nonnull(scrollView)));
    }

    @Nonnull
    public ScrollView build() {
        return build(new ScrollView(scene));
    }
}
