package com.sivannsan.android.widget;

import static android.widget.Spinner.MODE_DIALOG;
import static android.widget.Spinner.MODE_DROPDOWN;

import android.view.View;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

import java.util.function.Consumer;

public abstract class SpinnerAbstractBuilder<Builder, Spinner extends android.widget.Spinner> extends ViewAbstractBuilder<Builder, Spinner> {
    private Integer mode;
    private SpinnerAdapter adapter;
    private Integer selection, dropDownHorizontalOffset, dropDownVerticalOffset;
    private Consumer<Object> onElementSelected;

    protected SpinnerAbstractBuilder(@Nonnull Scene scene) {
        super(scene);
    }

    protected SpinnerAbstractBuilder(@Nonnull Scene scene, int width, int height) {
        super(scene, width, height);
    }

    @Nonnull
    public Builder setMode(Integer value) {
        mode = value;
        return builder;
    }

    @Nonnull
    public Builder setModeAsDropDown() {
        return setMode(MODE_DROPDOWN);
    }

    @Nonnull
    public Builder setModeAsDialog() {
        return setMode(MODE_DIALOG);
    }

    @Nonnull
    public Builder setAdapter(SpinnerAdapter value) {
        adapter = value;
        return builder;
    }

    @Nonnull
    public Builder setSelection(Integer value) {
        selection = value;
        return builder;
    }

    @Nonnull
    public Builder setDropDownHorizontalOffset(Integer value) {
        dropDownHorizontalOffset = value;
        return builder;
    }

    @Nonnull
    public Builder setDropDownVerticalOffset(Integer value) {
        dropDownVerticalOffset = value;
        return builder;
    }

    @Nonnull
    public Builder setOnElementSelected(Consumer<Object> value) {
        onElementSelected = value;
        return builder;
    }

    @Nonnull
    protected Spinner start(@Nonnull Spinner spinner) {
        super.start(spinner);
        if (mode != null) spinner.setLayoutMode(mode);
        if (adapter != null) spinner.setAdapter(adapter);
        if (selection != null) spinner.setSelection(selection);
        if (dropDownHorizontalOffset != null)
            spinner.setDropDownHorizontalOffset(dropDownHorizontalOffset);
        if (dropDownVerticalOffset != null)
            spinner.setDropDownVerticalOffset(dropDownVerticalOffset);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (onElementSelected != null) onElementSelected.accept(spinner.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return spinner;
    }
}
