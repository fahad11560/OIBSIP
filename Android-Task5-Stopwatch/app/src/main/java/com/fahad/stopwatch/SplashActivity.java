package com.fahad.stopwatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView tvSplashTitle = findViewById(R.id.tvSplashTitle);
        TextView tvSplashSubtitle = findViewById(R.id.tvSplashSubtitle);
        
        // Sophisticated animations
        tvSplashTitle.setAlpha(0f);
        tvSplashTitle.setScaleX(0.8f);
        tvSplashTitle.setScaleY(0.8f);
        
        tvSplashTitle.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(1200)
                .setInterpolator(new android.view.animation.OvershootInterpolator())
                .start();

        tvSplashSubtitle.animate()
                .alpha(0.8f)
                .setDuration(800)
                .setStartDelay(600)
                .start();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            // Add cross-fade transition
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 2000);
    }
}