package com.sivannsan.android.widget;

import androidx.viewpager.widget.PagerAdapter;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class ViewPagerAbstractBuilder<Builder, ViewPager extends androidx.viewpager.widget.ViewPager> extends ViewGroupAbstractBuilder<Builder, ViewPager> {
    private PagerAdapter adapter;

    protected ViewPagerAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected ViewPagerAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    public Builder setAdapter(PagerAdapter value) {
        adapter = value;
        return builder;
    }

    @Nonnull
    protected ViewPager start(@Nonnull ViewPager viewPager) {
        super.start(viewPager);
        if (adapter != null) viewPager.setAdapter(adapter);
        return viewPager;
    }
}
