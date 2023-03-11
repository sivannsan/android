package com.sivannsan.android.widget.manager;

import android.view.View;
import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

/**
 * @param <S>   the current scene
 * @param <H>   root widget of the head
 * @param <B>   root widget of the body
 */
public final class ContentManager<S extends Scene, H extends View, B extends View> extends ContentAbstractManager<ContentManager<S, H, B>, S, H, B> {
    public ContentManager(@Nonnull S scene, @Nonnull WidgetManager<S, H> head, @Nonnull WidgetManager<S, B> body) {
        super(scene, head, body);
        manager = this;
    }
}
