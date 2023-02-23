package com.sivannsan.android.widget;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public final class TextInputBuilder extends TextInputAbstractBuilder<TextInputBuilder, TextInput> {
    public TextInputBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public TextInputBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public TextInput build(@Nonnull TextInput textInput) {
        return finish(this.start(Validate.nonnull(textInput)));
    }

    @Nonnull
    public TextInput build() {
        return build(new TextInput(scene));
    }
}
