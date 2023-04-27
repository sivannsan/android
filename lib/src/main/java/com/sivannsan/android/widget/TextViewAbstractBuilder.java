package com.sivannsan.android.widget;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.fonts.FontStyle;
import android.view.View;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

public abstract class TextViewAbstractBuilder<Builder, TextView extends android.widget.TextView> extends ViewAbstractBuilder<Builder, TextView> {
    private Integer gravity, textSize, textAlignment, textFontStyle, paddingLeft, paddingTop, paddingRight, paddingBottom;
    private String text, textColor;
    private Typeface textFont;

    protected TextViewAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected TextViewAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    public Builder setGravity(Integer value) {
        gravity = value;
        return builder;
    }

    @Nonnull
    public Builder setText(String value) {
        text = value;
        return builder;
    }

    @Nonnull
    public Builder setTextFont(Typeface value) {
        textFont = value;
        return builder;
    }

    @Nonnull
    public Builder setTextFont(Typeface value, Integer style) {
        textFont = value;
        textFontStyle = style;
        return builder;
    }

    @Nonnull
    public Builder setTextSize(Integer value) {
        textSize = value;
        return builder;
    }

    @Nonnull
    public Builder setTextColor(String value) {
        textColor = value;
        return builder;
    }

    @Nonnull
    public Builder setTextAlignment(Integer value) {
        textAlignment = value;
        return builder;
    }

    @Nonnull
    public Builder setTextAlignmentAsGravity() {
        return setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
    }

    @Nonnull
    public Builder setPaddingLeft(Integer value) {
        paddingLeft = value;
        return builder;
    }

    @Nonnull
    public Builder setPaddingTop(Integer value) {
        paddingTop = value;
        return builder;
    }

    @Nonnull
    public Builder setPaddingRight(Integer value) {
        paddingRight = value;
        return builder;
    }

    @Nonnull
    public Builder setPaddingBottom(Integer value) {
        paddingBottom = value;
        return builder;
    }

    @Nonnull
    public Builder setPadding(Integer left, Integer top, Integer right, Integer bottom) {
        setPaddingLeft(left);
        setPaddingTop(top);
        setPaddingRight(right);
        setPaddingBottom(bottom);
        return builder;
    }

    @Nonnull
    protected TextView start(@Nonnull TextView textView) {
        super.start(textView);
        if (gravity != null) textView.setGravity(gravity);
        if (text != null) textView.setText(text);
        if (textFont != null) {
            if (textFontStyle != null) textView.setTypeface(textFont, textFontStyle);
            else textView.setTypeface(textFont);
        }
        if (textSize != null) textView.setTextSize(0, textSize);
        if (textColor != null) textView.setTextColor(Color.parseColor(textColor));
        if (textAlignment != null) textView.setTextAlignment(textAlignment);
        if (paddingLeft == null) paddingLeft = 0;
        if (paddingTop == null) paddingTop = 0;
        if (paddingRight == null) paddingRight = 0;
        if (paddingBottom == null) paddingBottom = 0;
        textView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        return textView;
    }
}
