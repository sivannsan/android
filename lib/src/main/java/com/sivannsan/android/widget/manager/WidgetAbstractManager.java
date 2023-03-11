package com.sivannsan.android.widget.manager;

import android.view.View;
import com.sivannsan.android.manager.AbstractManager;
import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.millidata.MilliMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class WidgetAbstractManager<Manager, S extends Scene, W extends View> extends AbstractManager<Manager> {
    @Nonnull
    private final S scene;
    @Nonnull
    private final W widget;
    @Nonnull
    private final MilliMap metadata = new MilliMap();
    private Consumer<Manager> updater;
    private Map<String, Consumer<Manager>> subupdaters;

    protected WidgetAbstractManager(@Nonnull S scene, @Nonnull W widget) {
        this.scene = scene;
        this.widget = widget;
    }

    @Nonnull
    public Manager consume(Consumer<Manager> consumer) {
        if (consumer != null) consumer.accept(manager);
        return manager;
    }

    @Nonnull
    public Manager setUpdater(Consumer<Manager> value) {
        updater = value;
        return manager;
    }

    @Nonnull
    public Manager addSubupdater(@Nonnull String name, Consumer<Manager> value) {
        if (subupdaters == null) subupdaters = new HashMap<>();
        subupdaters.put(name, value);
        return manager;
    }

    @Nonnull
    public Manager removeSubupdater(@Nonnull String name) {
        if (subupdaters != null) {
            subupdaters.remove(name);
        }
        return manager;
    }

    @Nonnull
    public S getScene() {
        return scene;
    }

    @Nonnull
    public W getWidget() {
        return widget;
    }

    @Nonnull
    public MilliMap getMetadata() {
        return metadata;
    }

    public void update() {
        if (updater != null) updater.accept(manager);
    }

    public void update(@Nonnull String subupdater) {
        Consumer<Manager> s = subupdaters.get(subupdater);
        if (s != null) s.accept(manager);
    }
}
