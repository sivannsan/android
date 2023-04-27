package com.sivannsan.android.widget;

import android.widget.ListView;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

public final class ListViewBuilder extends ListViewAbstractBuilder<ListViewBuilder, ListView> {
    public ListViewBuilder(@Nonnull Scene scene) {
        super(scene);
        builder = this;
    }

    public ListViewBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
        builder = this;
    }

    @Nonnull
    public ListView build(@Nonnull ListView listView) {
        return finish(start(Validate.nonnull(listView)));
    }

    @Nonnull
    public ListView build() {
        return build(new ListView(scene));
    }
}
