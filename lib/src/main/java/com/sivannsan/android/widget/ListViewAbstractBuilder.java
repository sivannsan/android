package com.sivannsan.android.widget;

import android.view.View;
import android.widget.ListAdapter;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class ListViewAbstractBuilder<Builder, ListView extends android.widget.ListView> extends ViewGroupAbstractBuilder<Builder, ListView> {
    private ListAdapter adapter;
    private View headerView, footerView;

    protected ListViewAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected ListViewAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    public Builder setAdapter(ListAdapter value) {
        adapter = value;
        return builder;
    }

    @Nonnull
    public Builder setHeaderView(View value) {
        headerView = value;
        return builder;
    }

    @Nonnull
    public Builder setFooterView(View value) {
        footerView = value;
        return builder;
    }

    @Nonnull
    protected ListView start(@Nonnull ListView listView) {
        super.start(listView);
        if (adapter != null) listView.setAdapter(adapter);
        if (headerView != null) listView.addHeaderView(headerView);
        if (footerView != null) listView.addFooterView(footerView);
        return listView;
    }
}
