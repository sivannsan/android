package com.sivannsan.android.integratedview;

import android.view.Gravity;
import android.view.View;

import com.sivannsan.android.Android;
import com.sivannsan.android.scene.Scene;
import com.sivannsan.android.widget.LinearLayoutBuilder;
import com.sivannsan.foundation.annotation.Nonnull;

public final class TitledBoxBuilder {
    public static final int TAB_LENGTH = 40;

    @Nonnull
    private final Scene scene;
    private int width;
    private String title;
    private View content;
    private boolean tabbedContent = true, trimmedHeight = false;
    private String backgroundColor = Android.Color.WHITE;

    public TitledBoxBuilder(@Nonnull Scene scene) {
        this.scene = scene;
    }

    @Nonnull
    public TitledBoxBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    @Nonnull
    public TitledBoxBuilder setTitle(String value) {
        title = value;
        return this;
    }

    @Nonnull
    public TitledBoxBuilder setContent(View value) {
        content = value;
        return this;
    }

    @Nonnull
    public TitledBoxBuilder setTabbedContent(boolean value) {
        tabbedContent = value;
        return this;
    }

    @Nonnull
    public TitledBoxBuilder setTrimmedHeight(boolean value) {
        trimmedHeight = value;
        return this;
    }

    @Nonnull
    public TitledBoxBuilder setBackground(String color) {
        backgroundColor = color;
        return this;
    }

    @Nonnull
    public View build() {
        int contentSpace = tabbedContent ? TAB_LENGTH : 0;
        int outerSpace = trimmedHeight ? 0 : scene.dp(9);
        return new LinearLayoutBuilder(scene)
                .setWidth(width)
                .setHeightAsWrapContent()
                .setOrientationAsVertical()
                .setBackground(backgroundColor)
                .addView(Android.Factory.newVSpace(scene, outerSpace))
                .addView(new LinearLayoutBuilder(scene)
                        .setWidthAsMatchParent()
                        .setHeightAsWrapContent()
                        .setOrientationAsHorizontal()
                        .setGravity(Gravity.CENTER_VERTICAL)
                        .addView(Android.Factory.newHSpace(scene, scene.dp(14)))
                        .addView(Android.Factory.newTextView(scene, title, scene.sp(14), Android.Color.GRAY))
                        .build())
                .addView(new LinearLayoutBuilder(scene)
                        .setWidthAsMatchParent()
                        .setHeightAsWrapContent()
                        .setOrientationAsHorizontal()
                        .setGravity(Gravity.CENTER_VERTICAL)
                        .addView(Android.Factory.newHSpace(scene, contentSpace))
                        .addView(new LinearLayoutBuilder(scene)
                                .setWidth(width - 2 * contentSpace)
                                .setHeightAsWrapContent()
                                .setOrientationAsVertical()
                                .addView(content)
                                .build())
                        .build())
                .addView(Android.Factory.newVSpace(scene, outerSpace))
                .build();
    }
}
