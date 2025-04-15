package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnit, toUnit;
    Button convertButton;
    TextView resultView;

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        convertButton = findViewById(R.id.convertButton);
        resultView = findViewById(R.id.resultView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String inputStr = inputValue.getText().toString();
        if (inputStr.isEmpty()) {
            resultView.setText("Please enter a value.");
            return;
        }

        double input = Double.parseDouble(inputStr);
        String from = fromUnit.getSelectedItem().toString();
        String to = toUnit.getSelectedItem().toString();

        double inMeters = toMeters(input, from);
        double result = fromMeters(inMeters, to);

        resultView.setText(String.format("%.4f %s", result, to));
    }

    private double toMeters(double value, String fromUnit) {
        switch (fromUnit) {
            case "Feet": return value * 0.3048;
            case "Inches": return value * 0.0254;
            case "Centimeters": return value * 0.01;
            case "Meters": return value;
            case "Yards": return value * 0.9144;
            default: return 0;
        }
    }

    private double fromMeters(double value, String toUnit) {
        switch (toUnit) {
            case "Feet": return value / 0.3048;
            case "Inches": return value / 0.0254;
            case "Centimeters": return value / 0.01;
            case "Meters": return value;
            case "Yards": return value / 0.9144;
            default: return 0;
        }
    }
}
