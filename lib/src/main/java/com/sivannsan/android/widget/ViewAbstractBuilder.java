package com.sivannsan.android.widget;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.sivannsan.android.builder.AbstractBuilder;
import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ViewAbstractBuilder<Builder, View extends android.view.View> extends AbstractBuilder<Builder> {
    @Nonnull
    protected final Scene scene;
    private Integer id, width, height, elevation;
    private Consumer<View> onStart, onClick, onFinish;
    private Function<View, Boolean> onLongClick, onActionDown, onActionMove, onActionUp, onActionCancel;
    private ViewGroup.LayoutParams layoutParams;
    private Drawable background;
    private Consumer<? super android.view.View> onFocusEnter, onFocusExit;

    protected ViewAbstractBuilder(@Nonnull Scene scene) {
        this(scene, WRAP_CONTENT, WRAP_CONTENT);
    }

    protected ViewAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        this.scene = scene;
        this.width = width;
        this.height = height;
    }

    @Nonnull
    public Builder setOnStart(Consumer<View> value) {
        onStart = value;
        return builder;
    }

    @Nonnull
    public Builder setID(Integer value) {
        id = value;
        return builder;
    }

    @Nonnull
    public Builder setWidth(Integer value) {
        width = value;
        return builder;
    }

    @Nonnull
    public Builder setWidthAsMatchParent() {
        return setWidth(MATCH_PARENT);
    }

    @Nonnull
    public Builder setWidthAsWrapContent() {
        return setWidth(WRAP_CONTENT);
    }

    @Nonnull
    public Builder setHeight(Integer value) {
        height = value;
        return builder;
    }

    @Nonnull
    public Builder setHeightAsMatchParent() {
        return setHeight(MATCH_PARENT);
    }

    @Nonnull
    public Builder setHeightAsWrapContent() {
        return setHeight(WRAP_CONTENT);
    }

    @Nonnull
    public Builder setLayoutParams(ViewGroup.LayoutParams value) {
        layoutParams = value;
        return builder;
    }

    @Nonnull
    public Builder setLayoutParams(Supplier<ViewGroup.LayoutParams> supplier) {
        return setLayoutParams(supplier == null ? null : supplier.get());
    }

    @Nonnull
    public Builder setBackground(Drawable value) {
        background = value;
        return builder;
    }

    @Nonnull
    public Builder setBackground(String color) {
        return color == null ? builder : setBackground(new ColorDrawable(Color.parseColor(color)));
    }

    @Nonnull
    public Builder setElevation(Integer value) {
        elevation = value;
        return builder;
    }

    @Nonnull
    public Builder setOnClick(Consumer<View> value) {
        onClick = value;
        return builder;
    }

    @Nonnull
    public Builder setOnLongClick(Function<View, Boolean> onLongClick) {
        this.onLongClick = onLongClick;
        return builder;
    }

    @Nonnull
    public Builder setOnActionDown(Function<View, Boolean> onActionDown) {
        this.onActionDown = onActionDown;
        return builder;
    }

    @Nonnull
    public Builder setOnActionMove(Function<View, Boolean> onActionMove) {
        this.onActionMove = onActionMove;
        return builder;
    }

    @Nonnull
    public Builder setOnActionUp(Function<View, Boolean> onActionUp) {
        this.onActionUp = onActionUp;
        return builder;
    }

    @Nonnull
    public Builder setOnActionCancel(Function<View, Boolean> onActionCancel) {
        this.onActionCancel = onActionCancel;
        return builder;
    }

    @Nonnull
    public Builder setOnFocusEnter(Consumer<? super android.view.View> value) {
        onFocusEnter = value;
        return builder;
    }

    @Nonnull
    public Builder setOnFocusExit(Consumer<? super android.view.View> value) {
        onFocusExit = value;
        return builder;
    }

    @Nonnull
    public Builder setOnFinish(Consumer<View> value) {
        onFinish = value;
        return builder;
    }

    @Nonnull
    protected View start(@Nonnull View view) {
        Validate.nonnull(view);
        if (onStart != null) onStart.accept(view);
        if (id != null) view.setId(id);
        if (width == null) width = WRAP_CONTENT;
        if (height == null) height = WRAP_CONTENT;
        if (layoutParams != null) view.setLayoutParams(layoutParams);
        else view.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        if (background != null) view.setBackground(background);
        if (onClick != null) view.setOnClickListener(v -> onClick.accept(view));
        if (onLongClick != null) view.setOnLongClickListener(v -> onLongClick.apply(view));
        if (elevation != null) view.setElevation(elevation);
        view.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN && onActionDown != null) return onActionDown.apply(view);
            if (event.getAction() == MotionEvent.ACTION_MOVE && onActionMove != null) return onActionMove.apply(view);
            if (event.getAction() == MotionEvent.ACTION_UP && onActionUp != null) return onActionUp.apply(view);
            if (event.getAction() == MotionEvent.ACTION_CANCEL && onActionCancel != null) return onActionCancel.apply(view);
            return false;
        });
        view.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && onFocusEnter != null) onFocusEnter.accept(v);
            if (!hasFocus && onFocusExit != null) onFocusExit.accept(v);
        });
        return view;
    }

    @Nonnull
    protected View finish(@Nonnull View view) {
        Validate.nonnull(view);
        if (onFinish != null) onFinish.accept(view);
        return view;
    }
}
