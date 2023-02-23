package com.sivannsan.android.common;

import android.os.Handler;
import android.os.Looper;

import com.sivannsan.foundation.annotation.Nonnull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskProvider {
    /**
     * Run on the main thread
     */
    public static void newSyncTask(@Nonnull Runnable command) {
        new Handler(Looper.getMainLooper()).post(command);
    }

    /**
     * Run on a new async thread
     */
    public static void newAsyncTask(@Nonnull Runnable command) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(command);
    }
}
