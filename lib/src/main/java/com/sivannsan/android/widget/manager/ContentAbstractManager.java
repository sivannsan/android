package com.sivannsan.android.widget.manager;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.sivannsan.android.scene.Scene;
import com.sivannsan.android.widget.LinearLayoutBuilder;
import com.sivannsan.foundation.annotation.Nonnull;

public abstract class ContentAbstractManager<Manager, S extends Scene, H extends View, B extends View> extends WidgetAbstractManager<Manager, S, LinearLayout> {
    @Nonnull
    private final WidgetManager<S, H> head;
    @Nonnull
    private final WidgetManager<S, B> body;

    protected ContentAbstractManager(@Nonnull S scene, @Nonnull WidgetManager<S, H> head, @Nonnull WidgetManager<S, B> body) {
        super(scene, new LinearLayout(scene));
        this.head = head;
        this.body = body;
        new LinearLayoutBuilder(scene)
                .setWidthAsMatchParent()
                .setHeightAsMatchParent()
                .setOrientationAsVertical()
                .setGravity(Gravity.START | Gravity.TOP)
                .addView(head.getWidget())
                .addView(body.getWidget())
                .build(getWidget());
    }

    @Nonnull
    public WidgetManager<S, H> getHead() {
        return head;
    }

    @Nonnull
    public WidgetManager<S, B> getBody() {
        return body;
    }
}
