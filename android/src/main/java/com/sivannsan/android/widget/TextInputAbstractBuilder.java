package com.sivannsan.android.widget;

import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

import java.util.function.Consumer;

public abstract class TextInputAbstractBuilder<Builder, TextInput extends android.widget.EditText> extends TextViewAbstractBuilder<Builder, TextInput> {
    private String hint, hintColor;
    private Boolean focusable, singleLine;
    private Integer inputType;
    private Consumer<String> onTextChanged;

    protected TextInputAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected TextInputAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    public Builder setHint(String value) {
        hint = value;
        return builder;
    }

    @Nonnull
    public Builder setHintColor(String value) {
        hintColor = value;
        return builder;
    }

    @Nonnull
    public Builder setFocusable(Boolean value) {
        focusable = value;
        return builder;
    }

    @Nonnull
    public Builder setSingleLine(Boolean value) {
        singleLine = value;
        return builder;
    }

    @Nonnull
    public Builder setInputType(int value) {
        inputType = value;
        return builder;
    }

    /**
     * e.g. 270
     */
    @Nonnull
    public Builder setInputTypeAsUnsignedIntegerNumber() {
        inputType = InputType.TYPE_CLASS_NUMBER;
        return builder;
    }

    /**
     * e.g. 3.14
     */
    @Nonnull
    public Builder setInputTypeAsUnsignedDecimalNumber() {
        inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
        return builder;
    }

    @Nonnull
    public Builder setOnTextChanged(Consumer<String> value) {
        onTextChanged = value;
        return builder;
    }

    @Nonnull
    protected TextInput start(@Nonnull TextInput textInput) {
        super.start(textInput);
        if (hint != null) textInput.setHint(hint);
        if (hintColor != null) textInput.setHintTextColor(Color.parseColor(hintColor));
        if (focusable != null) textInput.setFocusable(focusable);
        if (singleLine != null) textInput.setSingleLine(singleLine);
        if (inputType != null) textInput.setInputType(inputType);
        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (onTextChanged != null) onTextChanged.accept(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return textInput;
    }
}
