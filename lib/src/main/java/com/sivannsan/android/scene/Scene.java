package com.sivannsan.android.scene;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.sivannsan.android.permission.RequestPermissionsResult;
import com.sivannsan.foundation.annotation.Nonnull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Scene extends AppCompatActivity {
    @Nonnull
    public final Map<String, Object> meta = new HashMap<>();

    @Nonnull
    private final List<Consumer<RequestPermissionsResult>> requestPermissionsResultListeners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate();
    }

    protected void onCreate() {
    }

    public void setStatusBarColor(@Nonnull String color) {
        getWindow().setStatusBarColor(Color.parseColor(color));
    }

    /**
     * Text and Icons become black
     */
    public void setStatusBarAsLight() {
        getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
    }

    /**
     * Text and Icons become white
     */
    public void setStatusBarAsDark() {
        getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
    }

    public void setNavigationBarColor(@Nonnull String color) {
        getWindow().setNavigationBarColor(Color.parseColor(color));
    }

    public void setNavigationBarDividerColor(@Nonnull String color) {
        getWindow().setNavigationBarDividerColor(Color.parseColor(color));
    }

    public void showActionBar() {
        if (getSupportActionBar() != null) getSupportActionBar().show();
    }

    public void hideActionBar() {
        if (getSupportActionBar() != null) getSupportActionBar().hide();
    }

    public void setOrientationAsPortrait() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void setOrientationAsLandscape() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public View getRootView() {
        return getWindow().getDecorView().getRootView();
    }

    /**
     * WARNING! Do NOT call this method.
     */
    @Override
    @Deprecated
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (Consumer<RequestPermissionsResult> listener : requestPermissionsResultListeners) listener.accept(new RequestPermissionsResult(requestCode, permissions, grantResults));
        requestPermissionsResultListeners.clear();
    }

    public boolean doesPermissionGrant(String permission) {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions(String[] permissions, int requestCode, Consumer<RequestPermissionsResult> onResult) {
        requestPermissionsResultListeners.add(onResult);
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    public void start(Class<? extends Scene> anotherScene, boolean finishHere) {
        startActivity(new Intent(this, anotherScene));
        if (finishHere) finish();
    }

    public void start(Class<? extends Scene> anotherScene) {
        start(anotherScene, false);
    }

    /**
     * @param text  Text to show
     */
    public void toastShort(@Nonnull String text) {
        toast(text, 0);
    }

    /**
     * @param text  Text to show
     */
    public void toastLong(@Nonnull String text) {
        toast(text, 1);
    }

    /**
     * @return  Width of the current window (including navigation bars)
     */
    public int width() {
        return bounds().width();
    }

    /**
     * @return  Height of the current window (including navigation bars)
     */
    public int height() {
        return bounds().height();
    }

    /**
     * @return  Navigation bars width
     */
    public int insetsWidth() {
        Insets insets = insets();
        return insets.left + insets.right;
    }

    /**
     * @return  Navigation bars height
     */
    public int insetsHeight() {
        Insets insets = insets();
        return insets.top + insets.bottom;
    }

    /**
     * Unit converter from dp to px
     * @param dp    Input dp
     * @return  px
     */
    public int dp(float dp) {
        return (int) (density() * dp);
    }

    /**
     * Unit converter from sp to px
     * @param sp    Input sp
     * @return  px
     */
    public int sp(float sp) {
        return (int) (scaledDensity() * sp);
    }

    //

    public void toast(@Nonnull String text, int duration) {
        Toast.makeText(this, text, duration).show();
    }

    /**
     * @return  Bounds of the current window (including navigation bars)
     */
    @Nonnull
    private Rect bounds() {
        return getWindowManager().getCurrentWindowMetrics().getBounds();
    }

    /**
     * @return  Insets of the current window
     */
    @Nonnull
    private Insets insets() {
        return getWindowManager().getCurrentWindowMetrics().getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars() | WindowInsets.Type.displayCutout());
    }

    private float density() {
        return getResources().getDisplayMetrics().density;
    }

    private float scaledDensity() {
        return getResources().getDisplayMetrics().scaledDensity;
    }
}
