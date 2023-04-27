package com.sivannsan.android.widget;

import android.widget.HorizontalScrollView;
import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.common.Validate;
import com.sivannsan.foundation.annotation.Nonnull;

public final class HorizontalScrollViewBuilder extends HorizontalScrollViewAbstractBuilder<HorizontalScrollViewBuilder, HorizontalScrollView> {
    public HorizontalScrollViewBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public HorizontalScrollViewBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public HorizontalScrollView build(@Nonnull HorizontalScrollView scrollView) {
        return finish(start(Validate.nonnull(scrollView)));
    }

    @Nonnull
    public HorizontalScrollView build() {
        return build(new HorizontalScrollView(scene));
    }
}
