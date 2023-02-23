package com.sivannsan.android.common;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

import java.util.function.Consumer;

public final class ImagePicker {
    private static ActivityResultLauncher<Intent> launcher;

    /**
     * Call this from onCreate()
     */
    public static void register(@Nonnull Scene scene, Consumer<Uri> onPicked) {
        launcher = scene.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (onPicked == null) return;
            if (result.getResultCode() != Activity.RESULT_OK) return;
            if (result.getData() == null) return;
            onPicked.accept(result.getData().getData());
        });
    }

    /**
     * Register before call this method
     */
    public static void pick() {
        if (launcher == null) return;
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        launcher.launch(intent);
    }
}
