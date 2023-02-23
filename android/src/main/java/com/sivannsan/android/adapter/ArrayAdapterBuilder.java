package com.sivannsan.android.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.android.widget.TextViewBuilder;
import com.sivannsan.foundation.annotation.Nonnull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public final class ArrayAdapterBuilder<Element> {
    @Nonnull
    private final Scene scene;
    @Nonnull
    private final List<Element> elements;
    private Function<Element, View> onGetView;
    private Function<Element, TextView> onGetDropDownView;
    private Consumer<ArrayAdapter<Element>> onFinish;

    public ArrayAdapterBuilder(@Nonnull Scene scene, @Nonnull List<Element> elements) {
        this.scene = scene;
        this.elements = elements;
    }

    @Nonnull
    public ArrayAdapterBuilder<Element> setOnGetView(Function<Element, View> value) {
        onGetView = value;
        return this;
    }

    @Nonnull
    public ArrayAdapterBuilder<Element> setOnGetDropDownView(Function<Element, TextView> value) {
        onGetDropDownView = value;
        return this;
    }

    @Nonnull
    public ArrayAdapterBuilder<Element> setOnFinish(Consumer<ArrayAdapter<Element>> value) {
        onFinish = value;
        return this;
    }

    @Nonnull
    public ArrayAdapter<Element> build() {
        if (onGetView == null)
            onGetView = element -> new TextViewBuilder(scene)
                    .setText(String.valueOf(element))
                    .setTextSize(scene.sp(15))
                    .build(); //TODO: Customize text view?
        if (onGetDropDownView == null)
            onGetDropDownView = element -> new TextViewBuilder(scene)
                    .setText(String.valueOf(element))
                    .setTextSize(scene.sp(15))
                    .build();
        ArrayAdapter<Element> adapter = new ArrayAdapter<Element>(scene, 0, elements) {
            @Override
            @Nonnull
            public View getView(int position, View convertView, ViewGroup parent) {
                return onGetView.apply(getItem(position));
            }

            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
                return onGetDropDownView.apply(getItem(position));
            }
        };
        if (onFinish != null) onFinish.accept(adapter);
        return adapter;
    }
}
