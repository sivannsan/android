package com.sivannsan.android.widget;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class HorizontalScrollViewAbstractBuilder<Builder, HorizontalScrollView extends android.widget.HorizontalScrollView> extends ViewGroupAbstractBuilder<Builder, HorizontalScrollView> {
    protected HorizontalScrollViewAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected HorizontalScrollViewAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    protected HorizontalScrollView start(@Nonnull HorizontalScrollView horizontalScrollView) {
        super.start(horizontalScrollView);
        return horizontalScrollView;
    }
}
