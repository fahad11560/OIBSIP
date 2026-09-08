package com.fahad.unitconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Apply saved theme before onCreate
        applySavedTheme();
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // --- Animations ---
        androidx.constraintlayout.widget.ConstraintLayout rootLayout = findViewById(R.id.splash_root);
        LinearLayout mainLayout = findViewById(R.id.splash_content);
        TextView appTitle = findViewById(R.id.app_title);
        TextView appSlogan = findViewById(R.id.app_slogan);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        
        // Apply animation to elements
        if (rootLayout != null) rootLayout.startAnimation(fadeIn);
        if (mainLayout != null) mainLayout.startAnimation(fadeIn);
        if (appTitle != null) appTitle.startAnimation(fadeIn);
        if (appSlogan != null) appSlogan.startAnimation(fadeIn);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }

    private void applySavedTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        boolean isDarkMode = sharedPreferences.getBoolean("IsDarkMode", false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}