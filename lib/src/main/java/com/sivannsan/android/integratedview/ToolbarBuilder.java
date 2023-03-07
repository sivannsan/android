package com.sivannsan.android.integratedview;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sivannsan.android.Android;
import com.sivannsan.android.scene.Scene;
import com.sivannsan.android.widget.LinearLayoutBuilder;
import com.sivannsan.android.widget.TextViewBuilder;
import com.sivannsan.foundation.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class ToolbarBuilder {
    public static final int HEIGHT_IN_DP = 58;

    @Nonnull
    private final Scene scene;
    @Nonnull
    private final Android.Asset.Image image;
    @Nonnull
    private String title = "";
    private Typeface titleFont;
    /**
     * If not set, null, this will be 3dp in default
     */
    private Integer elevation;
    @Nonnull
    private final List<View> left = new ArrayList<>();
    @Nonnull
    private final List<View> right = new ArrayList<>();

    private final int boxWidth;
    private final int boxHeight;
    private final int iconLength;

    public ToolbarBuilder(@Nonnull Scene scene) {
        this.scene = scene;
        image = new Android.Asset.Image(scene);
        boxWidth = scene.dp(HEIGHT_IN_DP - 14);
        boxHeight = scene.dp(HEIGHT_IN_DP);
        iconLength = scene.dp(30);
    }

    @Nonnull
    public ToolbarBuilder setTitle(String value) {
        title = value;
        return this;
    }

    @Nonnull
    public ToolbarBuilder setTitleFont(Typeface value) {
        titleFont = value;
        return this;
    }

    /**
     * If not set, null, this will be 3dp in default
     */
    @Nonnull
    public ToolbarBuilder setElevation(Integer value) {
        elevation = value;
        return this;
    }

    @Nonnull
    public ToolbarBuilder addBackButton(Consumer<ImageView> onClick) {
        left.add(Android.Factory.newCBox(scene, boxWidth, boxHeight, Android.Factory.newImageView(scene, iconLength, iconLength, image.get("back_icon.png"), onClick)));
        return this;
    }

    @Nonnull
    public ToolbarBuilder addCrossButton(Consumer<ImageView> onClick) {
        left.add(Android.Factory.newCBox(scene, boxWidth, boxHeight, Android.Factory.newImageView(scene, iconLength, iconLength, image.get("cross_icon.png"), onClick)));
        return this;
    }

    @Nonnull
    public ToolbarBuilder addCheckButton(Consumer<ImageView> onClick) {
        right.add(Android.Factory.newCBox(scene, boxWidth, boxHeight, Android.Factory.newImageView(scene, iconLength, iconLength, image.get("check_icon.png"), onClick)));
        return this;
    }

    @Nonnull
    public ToolbarBuilder addEditButton(Consumer<ImageView> onClick) {
        right.add(Android.Factory.newCBox(scene, boxWidth, boxHeight, Android.Factory.newImageView(scene, iconLength, iconLength, image.get("edit_icon.png"), onClick)));
        return this;
    }

    @Nonnull
    public ToolbarBuilder addPlusButton(Consumer<ImageView> onClick) {
        right.add(Android.Factory.newCBox(scene, boxWidth, boxHeight, Android.Factory.newImageView(scene, iconLength, iconLength, image.get("plus_icon.png"), onClick)));
        return this;
    }

    @Nonnull
    public ToolbarBuilder addFilterButton(Consumer<ImageView> onClick) {
        right.add(Android.Factory.newCBox(scene, boxWidth, boxHeight, Android.Factory.newImageView(scene, iconLength, iconLength, image.get("filter_icon.png"), onClick)));
        return this;
    }

    @Nonnull
    public ToolbarBuilder addSearchButton(Consumer<ImageView> onClick) {
        right.add(Android.Factory.newCBox(scene, boxWidth, boxHeight, Android.Factory.newImageView(scene, iconLength, iconLength, image.get("search_icon.png"), onClick)));
        return this;
    }

    @Nonnull
    public ToolbarBuilder addLeft(@Nonnull View uiWithIcon) {
        left.add(uiWithIcon);
        return this;
    }

    @Nonnull
    public ToolbarBuilder addRight(@Nonnull View uiWithIcon) {
        right.add(uiWithIcon);
        return this;
    }

    @Nonnull
    public LinearLayout build() {
        if (titleFont == null)  titleFont = Typeface.DEFAULT;
        int totalWidth = boxWidth * (left.size() + right.size()) + scene.dp(24);

        if (elevation == null) elevation = scene.dp(3);
        LinearLayoutBuilder builder = new LinearLayoutBuilder(scene)
                .setWidth(scene.width())
                .setHeight(scene.dp(HEIGHT_IN_DP))
                .setOrientationAsHorizontal()
                .setBackground(Android.Color.WHITE)
                .setElevation(elevation);
        builder.addView(Android.Factory.newHSpace(scene, scene.dp(8)));
        for (View view : left) builder.addView(view);
        builder.addView(new LinearLayoutBuilder(scene)
                .setWidthAsWrapContent()
                .setHeightAsWrapContent()
                .setOrientationAsHorizontal()
                .setGravity(Gravity.START | Gravity.CENTER_VERTICAL)
                .addView(Android.Factory.newHSpace(scene, scene.dp(8)))
                .addView(new TextViewBuilder(scene)
                        .setWidth(scene.width() - totalWidth)
                        .setHeight(scene.dp(HEIGHT_IN_DP))
                        .setGravity(Gravity.CENTER_VERTICAL | Gravity.START)
                        .setText(title)
                        .setTextFont(titleFont)
                        .setTextSize(scene.sp(20))
                        .setTextColor(Android.Color.GOOD_BLACK)
                        .build())
                .build());
        for (View view : right) builder.addView(view);
        builder.addView(Android.Factory.newHSpace(scene, scene.dp(8)));
        return builder.build();
    }

    @Nonnull
    public static View newUIWithIcon(@Nonnull Scene scene, @Nonnull Bitmap icon, Consumer<View> onClick) {
        return Android.Factory.newCBox(scene, scene.dp(HEIGHT_IN_DP - 14), scene.dp(HEIGHT_IN_DP), Android.Factory.newImageView(scene, scene.dp(30), scene.dp(30), icon, iv -> {if (onClick != null) onClick.accept(iv);}));
    }
}
