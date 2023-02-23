package com.sivannsan.android.common;

import com.sivannsan.android.scene.Scene;
import com.sivannsan.foundation.annotation.Nonnull;

import java.io.File;

public class FileProvider {
    /**
     * Use without permission requirement
     *
     * @return ../data/com.sivannsan.sncbookshop/cache
     */
    @Nonnull
    public static File getCacheFolder(@Nonnull Scene scene) {
        return scene.getExternalCacheDir();
    }

    /**
     * Use without permission requirement
     *
     * @return ../data/com.sivannsan.sncbookshop/files
     */
    @Nonnull
    public static File getFilesFolder(@Nonnull Scene scene) {
        return scene.getExternalFilesDir(".");
    }
}
