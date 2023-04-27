package com.sivannsan.android;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.sivannsan.android.drawable.GradientDrawableBuilder;
import com.sivannsan.android.integratedview.TitledBoxBuilder;
import com.sivannsan.android.integratedview.ToolbarBuilder;
import com.sivannsan.android.scene.Scene;
import com.sivannsan.android.widget.*;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

import static android.view.Gravity.*;

public final class Android {
    // TODO: 4/27/2023 Check permission processing

    private Android() {
    }

    public interface Color {
        //FIRST
        String TRANSPARENT = "#00000000";
        String BLACK = "#FF000000";
        String RED = "#FFFF0000";
        String GREEN = "#FF00FF00";
        String BLUE = "#FF0000FF";
        String WHITE = "#FFFFFFFF";

        //SECOND
        String GRAY = "#FF999999";
        String DARK_GRAY = "#FF272727";
        String GOOD_BLACK = "#FF3C3C3C";//"#FF1E222B";
        String GOOD_GRAY = "#FF4D4D4D";
        String BLACKER1 = "#FF4E5366";
        String BLACKER2 = "#FF757A97";

        //THIRD
        String BACKGROUND_1 = "#FFF5F5F5";
        String BACKGROUND_2 = "#FFF4F4F4";
        String BACKGROUND_3 = "#FFFDFDFF";
        String BACKGROUND = "#FFE3E6EB";
        String INPUT_BACKGROUND = "#FFEDEDED";
        String STATUS_BAR = "#FFF5F5F5";
    }

    public static final class Common {
        public static void copyToClipboard(@Nonnull Scene scene, @Nonnull String text) {
            ((ClipboardManager) Validate.nonnull(scene).getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("label", Validate.nonnull(text)));
        }

        public static int getHeightOfView(@Nonnull View view) {
            Validate.nonnull(view).measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            return view.getMeasuredHeight();
        }

        public static void showKeyboard(@Nonnull TextInput textInput) {
            ((InputMethodManager) Validate.nonnull(textInput).getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(textInput, InputMethodManager.SHOW_IMPLICIT);
        }

        public static void closeKeyboard(@Nonnull Scene scene) {
            ((InputMethodManager) scene.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(scene.getWindow().getDecorView().getRootView().getWindowToken(), 0);
        }
    }

    public static final class Factory {

        //DRAWABLE

        @Nonnull
        public static Drawable newBackground(@Nonnull String color, int cornerRadius) {
            return new GradientDrawableBuilder()
                    .setShapeAsRectangle()
                    .setColor(color)
                    .setCornerRadius(cornerRadius)
                    .build();
        }

        //LAYOUT

        @Nonnull
        public static FrameLayout newBox(@Nonnull Scene scene, int width, int height, View... children) {
            FrameLayoutBuilder builder = new FrameLayoutBuilder(scene)
                    .setWidth(width)
                    .setHeight(height);
            if (children != null) for (View child : children) builder.addView(child);
            return builder.build();
        }

        @Nonnull
        public static LinearLayout newVBox(@Nonnull Scene scene, int width, int height, View... children) {
            LinearLayoutBuilder builder = new LinearLayoutBuilder(scene)
                    .setWidth(width)
                    .setHeight(height)
                    .setOrientationAsVertical()
                    .setGravity(CENTER_HORIZONTAL);
            if (children != null) for (View child : children) builder.addView(child);
            return builder.build();
        }

        @Nonnull
        public static LinearLayout newHBox(@Nonnull Scene scene, int width, int height, View... children) {
            LinearLayoutBuilder builder = new LinearLayoutBuilder(scene)
                    .setWidth(width)
                    .setHeight(height)
                    .setOrientationAsHorizontal()
                    .setGravity(CENTER_VERTICAL);
            if (children != null) for (View child : children) builder.addView(child);
            return builder.build();
        }

        //WIDGET

        @Nonnull
        public static Space newSpace(@Nonnull Scene scene, int width, int height) {
            return new SpaceBuilder(scene)
                    .setWidth(width)
                    .setHeight(height)
                    .build();
        }

        @Nonnull
        public static Space newHSpace(@Nonnull Scene scene, int length) {
            return newSpace(scene, length, 0);
        }

        @Nonnull
        public static Space newVSpace(@Nonnull Scene scene, int length) {
            return newSpace(scene, 0, length);
        }

        @Nonnull
        public static Button newButton(@Nonnull Scene scene, int width, int height, String text, Consumer<Button> onClick) {
            return new ButtonBuilder(scene)
                    .setWidth(width)
                    .setHeight(height)
                    .setText(text)
                    .setTextFont(Asset.Font.getDefault(scene))
                    .setTextSize(scene.sp(16))
                    .setTextColor(Color.BLACK)
                    .setBackground("#FFD6D8D7")
                    //.setTextColor(Colors.WHITE)
                    //.setBackground("#FF5865F2")
                    .setOnClick(onClick)
                    .build();
        }

        @Nonnull
        public static Button newButton(@Nonnull Scene scene, int width, String text, Consumer<Button> onClick) {
            return newButton(scene, width, scene.dp(50), text, onClick);
        }

        @Nonnull
        public static TextView newTextView(@Nonnull Scene scene, String text, Typeface font, int size, String color) {
            return new TextViewBuilder(scene)
                    .setWidthAsWrapContent()
                    .setHeightAsWrapContent()
                    .setText(text)
                    .setTextFont(font)
                    .setTextSize(size)
                    .setTextColor(color)
                    .build();
        }

        @Nonnull
        public static TextView newTextView(@Nonnull Scene scene, String text, int size, String color) {
            return newTextView(scene, text, Asset.Font.getDefault(scene), size, color);
        }

        @Nonnull
        public static ImageView newImageView(@Nonnull Scene scene, int width, int height, Bitmap bitmap, Consumer<ImageView> onClick) {
            return new ImageViewBuilder(scene)
                    .setWidth(width)
                    .setHeight(height)
                    .setImage(bitmap)
                    .setOnClick(onClick)
                    .build();
        }

        @Nonnull
        public static ImageView newImageView(@Nonnull Scene scene, int width, int height, Bitmap bitmap) {
            return newImageView(scene, width, height, bitmap, null);
        }

        //THIRD

        @Nonnull
        public static LinearLayout newCBox(@Nonnull Scene scene, int width, int height, View content) {
            return new LinearLayoutBuilder(scene)
                    .setWidth(width)
                    .setHeight(height)
                    .setGravity(CENTER)
                    .addView(content)
                    .build();
        }

        @Nonnull
        public static FrameLayout newBoxWithBackground(@Nonnull Scene scene, int width, int height, @Nonnull String backgroundColor, int backgroundCornerRadius) {
            return new FrameLayoutBuilder(scene)
                    .setWidth(width)
                    .setHeight(height)
                    .setBackground(newBackground(backgroundColor, backgroundCornerRadius))
                    .build();
        }

        @Nonnull
        public static FrameLayout newBoxWithBackgroundWithImage(@Nonnull Scene scene, int width, int height, @Nonnull String backgroundColor, int backgroundCornerRadius, @Nonnull Bitmap imageBitmap) {
            return new FrameLayoutBuilder(scene)
                    .setWidth(width)
                    .setHeight(height)
                    .setBackground(newBackground(backgroundColor, backgroundCornerRadius))
                    .addView(newImageView(scene, width, height, imageBitmap))
                    .build();
        }

        @Nonnull
        public static FrameLayout newBoxWithBackgroundWithImage(@Nonnull Scene scene, int width, int height, @Nonnull String backgroundColor, int backgroundCornerRadius, @Nonnull Bitmap imageBitmap, int imageWidth, int imageHeight) {
            return new FrameLayoutBuilder(scene)
                    .setWidth(width)
                    .setHeight(height)
                    .setBackground(newBackground(backgroundColor, backgroundCornerRadius))
                    .addView(newImageView(scene, imageWidth, imageHeight, imageBitmap))
                    .build();
        }

        @Nonnull
        public static View newTextViewTitledBox(@Nonnull Scene scene, int width, String title, String content) {
            return new TitledBoxBuilder(scene)
                    .setWidth(width)
                    .setTitle(title)
                    .setContent(new TextViewBuilder(scene)
                            .setWidth(width)
                            .setHeightAsWrapContent()
                            .setText(content)
                            .setTextFont(Asset.Font.getDefault(scene))
                            .setTextSize(scene.sp(16))
                            .setTextColor(Color.DARK_GRAY)
                            .build())
                    .build();
        }

        @Nonnull
        public static View newTextInputTitledBox(@Nonnull Scene scene, int width, boolean singleLine, String title, String defaultValue, Consumer<TextInput> fedbackInput) {
            TextInput input = new TextInputBuilder(scene)
                    .setWidth(width)
                    .setHeightAsWrapContent()
                    .setBackground(Color.TRANSPARENT)
                    .setSingleLine(singleLine)
                    .setText(defaultValue)
                    .setTextFont(Asset.Font.getDefault(scene))
                    .setTextSize(scene.sp(16))
                    .setTextColor(Color.DARK_GRAY)
                    .build();
            if (fedbackInput != null) fedbackInput.accept(input);

            return new TitledBoxBuilder(scene)
                    .setWidth(width)
                    .setTitle(title)
                    .setBackground(Color.INPUT_BACKGROUND)
                    .setContent(input)
                    .build();
        }

        @Nonnull
        public static LinearLayout newContentView(@Nonnull Scene scene, View toolbar, View body) {
            return new LinearLayoutBuilder(scene)
                    .setWidthAsMatchParent()
                    .setHeightAsMatchParent()
                    .setOrientationAsVertical()
                    .setBackground(Color.WHITE)
                    .addView(toolbar)
                    .addView(new LinearLayoutBuilder(scene)
                            .setWidthAsMatchParent()
                            .setHeight(2)
                            .setBackground("#FFCCCCCC")
                            .build())
                    .addView(body)
                    .build();
        }

        @Nonnull
        public static View newScrollViewBody(@Nonnull Scene scene, View content) {
            return new ScrollViewBuilder(scene)
                    .setWidthAsMatchParent()
                    .setHeight(scene.height() - scene.dp(ToolbarBuilder.HEIGHT_IN_DP))
                    .addView(content)
                    .build();
        }
    }

    public static final class Asset {
        public static String[] list(@Nonnull Scene scene, @Nonnull String path) {
            try {
                return scene.getAssets().list(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static InputStream open(@Nonnull Scene scene, @Nonnull String path) {
            try {
                return scene.getAssets().open(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Typeface getTypeface(@Nonnull Scene scene, @Nonnull String path) {
            return Typeface.createFromAsset(scene.getAssets(), path);
        }

        public static Bitmap getBitmap(@Nonnull Scene scene, @Nonnull String path) {
            return BitmapFactory.decodeStream(open(scene, path));
        }

        public static final class Image {
            @Nonnull
            private final Scene scene;

            public Image(@Nonnull Scene scene) {
                this.scene = scene;
            }

            public Bitmap get(@Nonnull String path) {
                return get(scene, path);
            }

            public static Bitmap get(@Nonnull Scene scene, @Nonnull String path) {
                return getBitmap(scene, "image/" + path);
            }
        }

        public static final class Font {
            @Nonnull
            private final Scene scene;

            public Font(@Nonnull Scene scene) {
                this.scene = scene;
            }

            public Typeface get(@Nonnull String path) {
                return get(scene, path);
            }

            public Typeface getDefault() {
                return getDefault(scene);
            }

            public static Typeface get(@Nonnull Scene scene, @Nonnull String path) {
                return getTypeface(scene, "font/" + path);
            }

            public static Typeface getDefault(@Nonnull Scene scene) {
                return Typeface.DEFAULT;
            }
        }
    }

    public static class Converter {
        public static Bitmap convertByteArrayToBitmap(byte[] bytes) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
    }

    public static final class Storage {
        private static File directory;

        public static void requestPermissions(@Nonnull Scene scene, Consumer<Boolean> onGrantResult) {
            if (scene.doesPermissionGrant(Manifest.permission.MANAGE_EXTERNAL_STORAGE)) {
                onGrantResult.accept(true);
                return;
            }
            scene.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0, result -> {
                if (result.getRequestCode() != 0) return;
                boolean grant = true;
                for (int grantResult : result.getGrantResult()) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        grant = false;
                        break;
                    }
                }
                onGrantResult.accept(grant);
            });
        }

        public static boolean reload(@Nonnull Scene scene, @Nonnull String filename) {
            directory = new File(Environment.getStorageDirectory(), filename);
            if (!directory.exists()) if (!directory.mkdirs()) {
                Toast.makeText(scene, "Could not make '" + filename + "' directory!", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }

        @Nonnull
        public static File getDirectory() {
            return directory;
        }
    }

    public static final class Translator {
        @Nonnull
        private static final Map<Character, Character> ENGLISH_KHMER = new TreeMap<>();

        static {
            ENGLISH_KHMER.put('0', '០');
            ENGLISH_KHMER.put('1', '១');
            ENGLISH_KHMER.put('2', '២');
            ENGLISH_KHMER.put('3', '៣');
            ENGLISH_KHMER.put('4', '៤');
            ENGLISH_KHMER.put('5', '៥');
            ENGLISH_KHMER.put('6', '៦');
            ENGLISH_KHMER.put('7', '៧');
            ENGLISH_KHMER.put('8', '៨');
            ENGLISH_KHMER.put('9', '៩');
        }

        public static @Nonnull String toKhmer(@Nonnull String english) {
            for (Map.Entry<Character, Character> entry : ENGLISH_KHMER.entrySet()) english = english.replace(entry.getKey(), entry.getValue());
            return english;
        }

        public static @Nonnull String toEnglish(@Nonnull String khmer) {
            for (Map.Entry<Character, Character> entry : ENGLISH_KHMER.entrySet()) khmer = khmer.replace(entry.getValue(), entry.getKey());
            return khmer;
        }
    }
}
