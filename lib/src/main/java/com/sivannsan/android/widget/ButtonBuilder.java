package com.sivannsan.android.widget;

import android.widget.Button;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public final class ButtonBuilder extends ButtonAbstractBuilder<ButtonBuilder, Button> {
    public ButtonBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public ButtonBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public Button build(@Nonnull Button button) {
        return finish(this.start(Validate.nonnull(button)));
    }

    @Nonnull
    public Button build() {
        return build(new Button(scene));
    }
}
