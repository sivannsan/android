package com.sivannsan.android.drawable;

import android.graphics.Color;

import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.Validate;

public abstract class GradientDrawableAbstractBuilder<Builder, GradientDrawable extends android.graphics.drawable.GradientDrawable> extends DrawableAbstractBuilder<Builder, GradientDrawable> {
    private Integer shape, cornerRadius, strokeWidth;
    private String color, strokeColor;

    protected GradientDrawableAbstractBuilder() {
    }

    @Nonnull
    public Builder setShape(Integer value) {
        shape = value;
        return builder;
    }

    @Nonnull
    public Builder setShapeAsRectangle() {
        return setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
    }

    @Nonnull
    public Builder setShapeAsOval() {
        return setShape(android.graphics.drawable.GradientDrawable.OVAL);
    }

    @Nonnull
    public Builder setShapeAsLine() {
        return setShape(android.graphics.drawable.GradientDrawable.LINE);
    }

    @Nonnull
    public Builder setShapeAsRing() {
        return setShape(android.graphics.drawable.GradientDrawable.RING);
    }

    @Nonnull
    public Builder setCornerRadius(Integer value) {
        cornerRadius = value;
        return builder;
    }

    @Nonnull
    public Builder setColor(String value) {
        color = value;
        return builder;
    }

    @Nonnull
    public Builder setStroke(int width, @Nonnull String color) {
        strokeWidth = width;
        strokeColor = Validate.nonnull(color);
        return builder;
    }

    @Override
    @Nonnull
    protected GradientDrawable prebuild(@Nonnull GradientDrawable drawable) {
        super.prebuild(drawable);
        if (shape != null) drawable.setShape(shape);
        if (color != null) drawable.setColor(Color.parseColor(color));
        if (cornerRadius != null) drawable.setCornerRadius(cornerRadius);
        if (strokeWidth != null && strokeColor != null) drawable.setStroke(strokeWidth, Color.parseColor(strokeColor));
        return drawable;
    }
}
