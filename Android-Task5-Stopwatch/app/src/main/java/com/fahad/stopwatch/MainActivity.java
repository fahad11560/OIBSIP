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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private MaterialButton btnStartPause, btnReset, btnLap;
    private LinearLayout lapListLayout;

    private Handler handler = new Handler();
    private long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updatedTime = 0L;
    private boolean isRunning = false;
    private int lapCount = 1;

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
        btnStartPause = findViewById(R.id.btnStartPause);
        btnReset = findViewById(R.id.btnReset);
        btnLap = findViewById(R.id.btnLap);
        lapListLayout = findViewById(R.id.lapListLayout);

        btnStartPause.setOnClickListener(v -> {
            if (!isRunning) {
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(updateTimerThread, 0);
                isRunning = true;
                btnStartPause.setText("Pause");
                btnStartPause.setBackgroundColor(Color.parseColor("#F44336")); // Red for pause
            } else {
                timeSwapBuff += timeInMilliseconds;
                handler.removeCallbacks(updateTimerThread);
                isRunning = false;
                btnStartPause.setText("Start");
                btnStartPause.setBackgroundColor(Color.parseColor("#00C853")); // Green for start
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
            tvTimer.setText("00:00:00:00");
            btnStartPause.setText("Start");
            btnStartPause.setBackgroundColor(Color.parseColor("#00C853"));
            lapListLayout.removeAllViews();
        });

        btnLap.setOnClickListener(v -> {
            if (isRunning) {
                TextView lapText = new TextView(this);
                lapText.setText("Lap " + lapCount + " ➔ " + tvTimer.getText().toString());
                lapText.setTextColor(Color.WHITE);
                lapText.setTextSize(20f);
                lapText.setPadding(0, 16, 0, 16);
                lapListLayout.addView(lapText, 0); // Add at the top
                lapCount++;
            }
        });
    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int hours = (int) (updatedTime / 3600000);
            int mins = (int) (updatedTime / 60000) % 60;
            int secs = (int) (updatedTime / 1000) % 60;
            int milliseconds = (int) ((updatedTime % 1000) / 10);

            tvTimer.setText(String.format(Locale.getDefault(), "%02d:%02d:%02d:%02d", hours, mins, secs, milliseconds));
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