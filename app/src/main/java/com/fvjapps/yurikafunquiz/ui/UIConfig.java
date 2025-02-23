package com.fvjapps.yurikafunquiz.ui;

import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

public class UIConfig {
    public static void setupStatusBar(@NonNull AppCompatActivity activity, int colorAttr) {
        TypedValue tv = new TypedValue();
        Window window = activity.getWindow();
        activity.getTheme().resolveAttribute(colorAttr, tv, true);
        int colorPrimary = tv.data;
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        boolean isColorPrimaryDark = isDark(colorPrimary);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(colorPrimary);
            WindowCompat.getInsetsController(window, window.getDecorView()).setAppearanceLightStatusBars(!isColorPrimaryDark);
        }
        Log.i("UIConfig", "Status bar color set to: #" + Integer.toHexString(colorPrimary));
        Log.i("UIConfig", String.format(
                "Status bar color is: %s, status bar elements\' color set to: %s",
                (isColorPrimaryDark) ? "Dark": "Light",
                (isColorPrimaryDark) ? "White": "Black"
        ));
    }
    public static boolean isDark(int color) {
        return (1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255) >= 0.5;
    }
}
