package com.fahad.unitconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // UI Elements
    private Spinner spinnerCategory, spinnerFrom, spinnerTo;
    private EditText etInput;
    private Button btnConvert;
    private ImageButton btnSwap, btnThemeToggle;
    private TextView tvResult;

    // ... (rest of data)
    private final String[] categories = {"Length", "Weight", "Temperature"};

    private final String[] lengthUnits = {
            "Kilometer", "Meter", "Centimeter", "Millimeter", "Mile", "Yard", "Foot", "Inch"
    };
    private final String[] weightUnits = {
            "Kilogram", "Gram", "Milligram", "Pound", "Ounce"
    };
    private final String[] temperatureUnits = {
            "Celsius", "Fahrenheit", "Kelvin"
    };

    // Length units to Meter (base unit)
    private final double[] lengthToBase = {
            1000.0,   // Kilometer
            1.0,      // Meter
            0.01,     // Centimeter
            0.001,    // Millimeter
            1609.344, // Mile
            0.9144,   // Yard
            0.3048,   // Foot
            0.0254    // Inch
    };

    // Weight units to Gram (base unit)
    private final double[] weightToBase = {
            1000.0,      // Kilogram
            1.0,         // Gram
            0.001,       // Milligram
            453.592,     // Pound
            28.3495      // Ounce
    };

    private final DecimalFormat df = new DecimalFormat("#.######");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Apply saved theme before onCreate
        applySavedTheme();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- Bind views ---
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        etInput = findViewById(R.id.etInput);
        btnConvert = findViewById(R.id.btnConvert);
        btnSwap = findViewById(R.id.btnSwap);
        tvResult = findViewById(R.id.tvResult);
        btnThemeToggle = findViewById(R.id.btnThemeToggle);

        // --- Theme Toggle Logic ---
        updateThemeIcon();
        btnThemeToggle.setOnClickListener(v -> toggleTheme());

        // --- Category spinner ---
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                populateUnitSpinners(position);
                resetResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // --- Unit spinners trigger auto-convert on change ---
        AdapterView.OnItemSelectedListener unitChangeListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                autoConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };
        spinnerFrom.setOnItemSelectedListener(unitChangeListener);
        spinnerTo.setOnItemSelectedListener(unitChangeListener);

        // --- TextWatcher for real-time auto-conversion ---
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                autoConvert();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        // --- Convert button (manual trigger) ---
        btnConvert.setOnClickListener(v -> {
            String input = etInput.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(this, R.string.error_empty_input, Toast.LENGTH_SHORT).show();
                return;
            }
            autoConvert();
        });

        // --- Swap button ---
        btnSwap.setOnClickListener(v -> {
            int fromPos = spinnerFrom.getSelectedItemPosition();
            int toPos = spinnerTo.getSelectedItemPosition();
            spinnerFrom.setSelection(toPos);
            spinnerTo.setSelection(fromPos);
            // autoConvert will be triggered by the spinner listeners
        });
    }

    /**
     * Populates the From/To spinners based on the selected category index.
     */
    private void populateUnitSpinners(int categoryIndex) {
        String[] units;
        switch (categoryIndex) {
            case 1:
                units = weightUnits;
                break;
            case 2:
                units = temperatureUnits;
                break;
            default:
                units = lengthUnits;
                break;
        }

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, units);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(unitAdapter);
        spinnerTo.setAdapter(unitAdapter);

        // Default "To" to second item so it's different from "From"
        if (units.length > 1) {
            spinnerTo.setSelection(1);
        }
    }

    /**
     * Performs conversion automatically whenever input or unit selection changes.
     */
    private void autoConvert() {
        String input = etInput.getText().toString().trim();
        if (input.isEmpty() || input.equals("-") || input.equals(".")) {
            tvResult.setText("---");
            return;
        }

        try {
            double value = Double.parseDouble(input);
            int categoryIndex = spinnerCategory.getSelectedItemPosition();
            int fromIndex = spinnerFrom.getSelectedItemPosition();
            int toIndex = spinnerTo.getSelectedItemPosition();

            double result;

            if (categoryIndex == 2) {
                // Temperature — special formulas
                result = convertTemperature(value, fromIndex, toIndex);
            } else {
                // Length or Weight — base-unit approach
                double[] factors = (categoryIndex == 0) ? lengthToBase : weightToBase;
                double baseValue = value * factors[fromIndex];
                result = baseValue / factors[toIndex];
            }

            tvResult.setText(df.format(result));
        } catch (NumberFormatException e) {
            tvResult.setText("---");
        }
    }

    /**
     * Converts temperature between Celsius (0), Fahrenheit (1), Kelvin (2).
     */
    private double convertTemperature(double value, int fromIndex, int toIndex) {
        if (fromIndex == toIndex) return value;

        // Step 1: Convert source to Celsius
        double celsius;
        switch (fromIndex) {
            case 1: // Fahrenheit → Celsius
                celsius = (value - 32.0) * 5.0 / 9.0;
                break;
            case 2: // Kelvin → Celsius
                celsius = value - 273.15;
                break;
            default: // Already Celsius
                celsius = value;
                break;
        }

        // Step 2: Convert Celsius to target
        switch (toIndex) {
            case 1: // Celsius → Fahrenheit
                return celsius * 9.0 / 5.0 + 32.0;
            case 2: // Celsius → Kelvin
                return celsius + 273.15;
            default: // Celsius
                return celsius;
        }
    }

    /**
     * Resets the result display.
     */
    private void resetResult() {
        tvResult.setText("---");
    }

    private void toggleTheme() {
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            saveThemePreference(false);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            saveThemePreference(true);
        }
    }

    private void updateThemeIcon() {
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
            btnThemeToggle.setImageResource(R.drawable.ic_light_mode);
        } else {
            btnThemeToggle.setImageResource(R.drawable.ic_dark_mode);
        }
    }

    private void saveThemePreference(boolean isDarkMode) {
        SharedPreferences sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("IsDarkMode", isDarkMode);
        editor.apply();
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