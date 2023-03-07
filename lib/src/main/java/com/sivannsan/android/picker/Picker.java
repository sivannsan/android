package com.sivannsan.android.picker;

import com.sivannsan.foundation.Validate;
import com.sivannsan.foundation.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Picker<T> {
    @Nonnull
    private final List<T> list = new ArrayList<>();
    @Nonnull
    private PickingMode mode = PickingMode.SINGLE_SELECT;
    private final BiConsumer<Picker<T>, T> onPicked, onUnpicked;
    private final BiConsumer<Picker<T>, PickingMode> onModeChanged;

    /**
     * @param onPicked      called after object is added to the list
     * @param onUnpicked    called after object is removed from the list
     * @param onModeChanged called after picking and unpicking events
     */
    public Picker(BiConsumer<Picker<T>, T> onPicked, BiConsumer<Picker<T>, T> onUnpicked, BiConsumer<Picker<T>, PickingMode> onModeChanged) {
        this.onPicked = onPicked;
        this.onUnpicked = onUnpicked;
        this.onModeChanged = onModeChanged;
    }

    /**
     * @return  a list of picked objects
     */
    @Nonnull
    public List<T> getList() {
        return list;
    }

    @Nonnull
    public PickingMode getMode() {
        return mode;
    }

    public void setMode(@Nonnull PickingMode value) {
        Validate.nonnull(value);
        if (mode == value) return;
        mode = value;
        if (onModeChanged != null) onModeChanged.accept(this, value);
    }

    public void pick(@Nonnull T t) {
        Validate.nonnull(t);
        if (list.contains(t)) return;
        list.add(t);
        if (onPicked != null) onPicked.accept(this, t);
    }

    public void unpick(@Nonnull T t) {
        Validate.nonnull(t);
        if (!list.contains(t)) return;
        list.remove(t);
        if (onUnpicked != null) onUnpicked.accept(this, t);
    }
}
