package com.sivannsan.android.widget;

import androidx.viewpager.widget.ViewPager;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public final class ViewPagerBuilder extends ViewPagerAbstractBuilder<ViewPagerBuilder, ViewPager> {
    public ViewPagerBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public ViewPagerBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public ViewPager build(ViewPager viewPager) {
        return finish(this.start(Validate.nonnull(viewPager)));
    }

    @Nonnull
    public ViewPager build() {
        return build(new ViewPager(scene));
    }
}
