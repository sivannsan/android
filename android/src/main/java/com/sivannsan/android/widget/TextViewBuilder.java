package com.sivannsan.android.widget;

import android.widget.TextView;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public final class TextViewBuilder extends TextViewAbstractBuilder<TextViewBuilder, TextView> {
    public TextViewBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public TextViewBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public TextView build(@Nonnull TextView textView) {
        return finish(start(Validate.nonnull(textView)));
    }

    @Nonnull
    public TextView build() {
        return build(new TextView(scene));
    }
}
