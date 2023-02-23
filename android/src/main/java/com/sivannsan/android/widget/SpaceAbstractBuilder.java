package com.sivannsan.android.widget;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class SpaceAbstractBuilder<Builder, Space extends android.widget.Space> extends ViewAbstractBuilder<Builder, Space> {
    protected SpaceAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected SpaceAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    protected Space start(@Nonnull Space space) {
        super.start(space);
        return space;
    }
}
