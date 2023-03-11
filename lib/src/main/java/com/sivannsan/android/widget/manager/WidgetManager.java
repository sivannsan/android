package com.sivannsan.android.widget.manager;

import android.view.View;
import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

public final class WidgetManager<S extends Scene, W extends View> extends WidgetAbstractManager<WidgetManager<S, W>, S, W> {
    public WidgetManager(@Nonnull S scene, @Nonnull W widget) {
        super(scene, widget);
        manager = this;
    }
}
