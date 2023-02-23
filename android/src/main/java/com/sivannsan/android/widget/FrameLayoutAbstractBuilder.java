package com.sivannsan.android.widget;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class FrameLayoutAbstractBuilder<Builder, FrameLayout extends android.widget.FrameLayout> extends ViewGroupAbstractBuilder<Builder, FrameLayout> {
    protected FrameLayoutAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected FrameLayoutAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    protected FrameLayout start(@Nonnull FrameLayout frameLayout) {
        super.start(frameLayout);
        return frameLayout;
    }
}
