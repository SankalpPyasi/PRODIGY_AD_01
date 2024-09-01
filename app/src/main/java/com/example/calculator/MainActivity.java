package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentNumber = "";
    private String firstNumber = "";
    private String operator = "";
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout .activity_main);

        display = findViewById(R.id.tvDisplay);

        int[] numberButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (isNewOp) {
                    display.setText("");
                    isNewOp = false;
                }
                currentNumber += button.getText().toString();
                display.setText(currentNumber);
            }
        };
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        int[] operatorButtons = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};
        View.OnClickListener operatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (!currentNumber.isEmpty()) {
                    firstNumber = currentNumber;
                    operator = button.getText().toString();
                    currentNumber = "";
                    isNewOp = true;
                }
            }
        };
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(operatorClickListener);
        }

        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.isEmpty() && !firstNumber.isEmpty()) {
                    double result = calculate(Double.parseDouble(firstNumber), Double.parseDouble(currentNumber), operator);
                    display.setText(String.valueOf(result));
                    currentNumber = String.valueOf(result);
                    firstNumber = "";
                    operator = "";
                    isNewOp = true;
                }
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNumber = "";
                firstNumber = "";
                operator = "";
                display.setText("0");
                isNewOp = true;
            }
        });

        findViewById(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() > 0) {
                    currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
                    display.setText(currentNumber);
                }
            }
        });

        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.contains(".")) {
                    currentNumber += ".";
                    display.setText(currentNumber);
                }
            }
        });
    }

    private double calculate(double firstNumber, double secondNumber, String operator) {
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                return firstNumber / secondNumber;
            default:
                return 0;
        }
    }
}