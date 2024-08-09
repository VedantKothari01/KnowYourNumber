package com.example.knowyournumber;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText numberInput;
    private RadioGroup optionsGroup;
    private RadioButton factorialOption, evenOddOption;
    private Button calculateButton, aboutButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberInput = findViewById(R.id.numberInput);
        optionsGroup = findViewById(R.id.optionsGroup);
        factorialOption = findViewById(R.id.factorialOption);
        evenOddOption = findViewById(R.id.evenOddOption);
        calculateButton = findViewById(R.id.calculateButton);
        aboutButton = findViewById(R.id.aboutButton);
        resultText = findViewById(R.id.resultText);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOption = optionsGroup.getCheckedRadioButtonId();
                if (selectedOption == -1) {
                    resultText.setText("Please select an option.");
                    return;
                }

                String input = numberInput.getText().toString();
                if (input.isEmpty()) {
                    resultText.setText("Please enter a number.");
                    return;
                }

                int number = Integer.parseInt(input);

                if (selectedOption == R.id.factorialOption) {
                    resultText.setText("Factorial: " + calculateFactorial(number));
                } else if (selectedOption == R.id.evenOddOption) {
                    resultText.setText("The number is " + (number % 2 == 0 ? "Even" : "Odd"));
                }
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        optionsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                hideKeyboard();
            }
        });
    }

    private int calculateFactorial(int n) {
        if (n == 0) return 1;
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) {
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
    }
}
