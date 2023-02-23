package com.sivannsan.android.widget;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class LinearLayoutAbstractBuilder<Builder, LinearLayout extends android.widget.LinearLayout> extends ViewGroupAbstractBuilder<Builder, LinearLayout> {
    private Integer orientation, gravity;
    private Float weightSum;

    protected LinearLayoutAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected LinearLayoutAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    public Builder setOrientation(Integer value) {
        orientation = value;
        return builder;
    }

    @Nonnull
    public Builder setOrientationAsHorizontal() {
        return setOrientation(HORIZONTAL);
    }

    @Nonnull
    public Builder setOrientationAsVertical() {
        return setOrientation(VERTICAL);
    }

    @Nonnull
    public Builder setGravity(Integer value) {
        gravity = value;
        return builder;
    }

    @Nonnull
    public Builder setWeightSum(Float value) {
        weightSum = value;
        return builder;
    }

    @Nonnull
    protected LinearLayout start(@Nonnull LinearLayout linearLayout) {
        super.start(linearLayout);
        if (orientation != null) linearLayout.setOrientation(orientation);
        if (gravity != null) linearLayout.setGravity(gravity);
        if (weightSum != null) linearLayout.setWeightSum(weightSum);
        return linearLayout;
    }
}
