package com.sivannsan.android.widget;

import android.view.View;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class ViewGroupAbstractBuilder<Builder, ViewGroup extends android.view.ViewGroup> extends ViewAbstractBuilder<Builder, ViewGroup> {
    protected List<View> children;

    protected ViewGroupAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected ViewGroupAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    public Builder addView(View value) {
        if (children == null && value != null) children = new ArrayList<>();
        if (value != null) children.add(value);
        return builder;
    }

    @Nonnull
    public Builder addView(Supplier<View> supplier) {
        return addView(supplier == null ? null : supplier.get());
    }

    @Nonnull
    public Builder addViews(List<? extends View> value) {
        if (value != null) for (View v : value) addView(v);
        return builder;
    }

    @Nonnull
    protected ViewGroup start(@Nonnull ViewGroup viewGroup) {
        super.start(viewGroup);
        if (children != null) for (View child : children) viewGroup.addView(child);
        return viewGroup;
    }
}
