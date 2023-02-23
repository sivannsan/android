package com.sivannsan.android.widget;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class ButtonAbstractBuilder<Builder, Button extends android.widget.Button> extends TextViewAbstractBuilder<Builder, Button> {
    protected ButtonAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected ButtonAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    protected Button start(@Nonnull Button button) {
        super.start(button);
        return button;
    }
}
