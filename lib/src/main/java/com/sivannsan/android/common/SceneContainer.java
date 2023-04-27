package com.sivannsan.android.common;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

public abstract class SceneContainer<T extends Scene> {
    @Nonnull
    protected final T scene;

    public SceneContainer(@Nonnull T scene) {
        this.scene = Validate.nonnull(scene);
    }

    @Nonnull
    public T getScene() {
        return scene;
    }
}
