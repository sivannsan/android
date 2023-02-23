package com.sivannsan.android.widget;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class ScrollViewAbstractBuilder<Builder, ScrollView extends android.widget.ScrollView> extends ViewGroupAbstractBuilder<Builder, ScrollView> {
    protected ScrollViewAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected ScrollViewAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    protected ScrollView start(@Nonnull ScrollView scrollView) {
        super.start(scrollView);
        return scrollView;
    }
}
