package com.sivannsan.android.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;
import com.sivannsan.foundation.common.Validate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class BitmapUtility {
    public static Bitmap toBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap toBitmap(@Nonnull Scene scene, @Nonnull Uri uri) {
        try {
            return ImageDecoder.decodeBitmap(ImageDecoder.createSource(Validate.nonnull(scene).getContentResolver(), uri));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nonnull
    public static Bitmap toBitmap(@Nonnull Drawable drawable) {
        if (drawable instanceof BitmapDrawable) return ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth() <= 0 ? 1 : drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() <= 0 ? 1 : drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    @Nonnull
    public static Bitmap toBitmap(@Nonnull View view) {
        if (view instanceof ImageView) return ((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap();
        Validate.nonnull(view).measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return bitmap;
    }

    @Nonnull
    public static Drawable toDrawable(@Nonnull Scene scene, @Nonnull Bitmap bitmap) {
        return new BitmapDrawable(scene.getResources(), bitmap);
    }

    public static byte[] toByteArray(@Nonnull Bitmap bitmap, @Nonnull Bitmap.CompressFormat format, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Validate.nonnull(bitmap).compress(Validate.nonnull(format), quality, stream);
        return stream.toByteArray();
    }

    public static byte[] toByteArray(@Nonnull Scene scene, Uri uri) {
        if (uri == null) return null;
        try {
            InputStream inputStream = scene.getContentResolver().openInputStream(uri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1024 * 4];
            int len;
            while ((len = inputStream.read(b)) != -1) outputStream.write(b, 0, len);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
