package com.fahad.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer, tvStartPauseLabel;
    private MaterialButton btnStartPause, btnReset, btnLap, btnThemeToggle;
    private LinearLayout lapListLayout;
    private android.widget.ProgressBar pbTimer;

    private Handler handler = new Handler();
    private long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updatedTime = 0L;
    private boolean isRunning = false;
    private int lapCount = 1;
    private boolean isDarkMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTimer = findViewById(R.id.tvTimer);
        tvStartPauseLabel = findViewById(R.id.tvStartPauseLabel);
        btnStartPause = findViewById(R.id.btnStartPause);
        btnReset = findViewById(R.id.btnReset);
        btnLap = findViewById(R.id.btnLap);
        lapListLayout = findViewById(R.id.lapListLayout);
        pbTimer = findViewById(R.id.pbTimer);
        btnThemeToggle = findViewById(R.id.btnThemeToggle);

        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        isDarkMode = currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES;
        updateThemeIcon();

        btnThemeToggle.setOnClickListener(v -> toggleTheme());

        btnStartPause.setOnClickListener(v -> {
            if (!isRunning) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(updateTimerThread, 0);
                isRunning = true;
                btnStartPause.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_pause));
                btnStartPause.setIconTint(android.content.res.ColorStateList.valueOf(Color.WHITE));
                tvStartPauseLabel.setText(R.string.btn_pause);
            } else {
                timeSwapBuff += timeInMilliseconds;
                handler.removeCallbacks(updateTimerThread);
                isRunning = false;
                btnStartPause.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_play));
                btnStartPause.setIconTint(android.content.res.ColorStateList.valueOf(Color.WHITE));
                tvStartPauseLabel.setText(R.string.btn_start);
            }
        });

        btnReset.setOnClickListener(v -> {
            startTime = 0L;
            timeInMilliseconds = 0L;
            timeSwapBuff = 0L;
            updatedTime = 0L;
            lapCount = 1;
            isRunning = false;
            handler.removeCallbacks(updateTimerThread);
            tvTimer.setText(getString(R.string.timer_default));
            btnStartPause.setIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_play));
            btnStartPause.setIconTint(android.content.res.ColorStateList.valueOf(Color.WHITE));
            tvStartPauseLabel.setText(R.string.btn_start);
            lapListLayout.removeAllViews();
            pbTimer.setProgress(0);
        });

        btnLap.setOnClickListener(v -> {
            if (isRunning) {
                String lapInfo = getString(R.string.lap_format, lapCount, tvTimer.getText().toString());
                addLapToView(lapInfo);
                lapCount++;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("startTime", startTime);
        outState.putLong("timeSwapBuff", timeSwapBuff);
        outState.putBoolean("isRunning", isRunning);
        outState.putInt("lapCount", lapCount);
        
        ArrayList<String> laps = new ArrayList<>();
        for (int i = 0; i < lapListLayout.getChildCount(); i++) {
            View v = lapListLayout.getChildAt(i);
            if (v instanceof TextView) {
                laps.add(((TextView) v).getText().toString());
            }
        }
        outState.putStringArrayList("laps", laps);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        startTime = savedInstanceState.getLong("startTime");
        timeSwapBuff = savedInstanceState.getLong("timeSwapBuff");
        isRunning = savedInstanceState.getBoolean("isRunning");
        lapCount = savedInstanceState.getInt("lapCount");

        if (isRunning) {
            btnStartPause.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_pause));
            btnStartPause.setIconTint(android.content.res.ColorStateList.valueOf(Color.WHITE));
            tvStartPauseLabel.setText(R.string.btn_pause);
            handler.postDelayed(updateTimerThread, 0);
        }

        ArrayList<String> laps = savedInstanceState.getStringArrayList("laps");
        if (laps != null) {
            for (int i = laps.size() - 1; i >= 0; i--) {
                addLapToView(laps.get(i));
            }
        }
        
        // Update timer text immediately
        timeInMilliseconds = isRunning ? (SystemClock.uptimeMillis() - startTime) : 0;
        updatedTime = timeSwapBuff + timeInMilliseconds;
        updateTimerUI();
    }

    private void updateTimerUI() {
        int mins = (int) (updatedTime / 60000);
        int secs = (int) (updatedTime / 1000) % 60;
        int milliseconds = (int) ((updatedTime % 1000) / 10);
        tvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d", mins, secs, milliseconds));
        pbTimer.setProgress((int) (updatedTime % 60000));
    }

    private void addLapToView(String lapInfo) {
        TextView lapText = new TextView(this);
        lapText.setText(lapInfo);
        lapText.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        lapText.setTextSize(16f);
        lapText.setTypeface(android.graphics.Typeface.DEFAULT_BOLD);
        lapText.setGravity(android.view.Gravity.CENTER_VERTICAL);
        lapText.setBackground(ContextCompat.getDrawable(this, R.drawable.lap_item_bg));
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 16);
        lapText.setLayoutParams(params);
        lapText.setPadding(32, 24, 32, 24);
        
        lapListLayout.addView(lapText, 0);
    }

    private void updateThemeIcon() {
        if (isDarkMode) {
            btnThemeToggle.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_light_mode));
        } else {
            btnThemeToggle.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_dark_mode));
        }
    }

    private void toggleTheme() {
        btnThemeToggle.animate().rotationBy(360).setDuration(500).withEndAction(() -> {
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }).start();
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            updateTimerUI();
            handler.postDelayed(this, 10);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (isRunning) {
            handler.removeCallbacks(updateTimerThread);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRunning) {
            handler.postDelayed(updateTimerThread, 0);
        }
    }
}