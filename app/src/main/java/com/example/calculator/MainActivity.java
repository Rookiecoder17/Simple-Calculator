package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.calculator.Calculate.add;
import static com.example.calculator.Calculate.divide;
import static com.example.calculator.Calculate.multiply;
import static com.example.calculator.Calculate.subtract;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int Y_OFFSET = 208;
    private TextView mInputTextView;
    private TextView mResultTextView;
    private Button mDecimalButton;
    private Button mZeroButton;
    private Button mClearButton;
    private Button mOneButton;
    private Button mTwoButton;
    private Button mThreeButton;
    private Button mFourButton;
    private Button mFiveButton;
    private Button mSixButton;
    private Button mSevenButton;
    private Button mEightButton;
    private Button mNineButton;
    private Button mAddButton;
    private Button mSubButton;
    private Button mMultiplyButton;
    private Button mDivideButton;
    private Button mEqualsButton;
    private Button mDeleteButton;
    private String mOperate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializePage();
        buttonClicks();
    }

    private void buttonClicks() {
        mDecimalButton.setOnClickListener(this);
        mZeroButton.setOnClickListener(this);
        mClearButton.setOnClickListener(this);
        mOneButton.setOnClickListener(this);
        mTwoButton.setOnClickListener(this);
        mThreeButton.setOnClickListener(this);
        mFourButton.setOnClickListener(this);
        mFiveButton.setOnClickListener(this);
        mSixButton.setOnClickListener(this);
        mSevenButton.setOnClickListener(this);
        mEightButton.setOnClickListener(this);
        mNineButton.setOnClickListener(this);
        mAddButton.setOnClickListener(this);
        mSubButton.setOnClickListener(this);
        mMultiplyButton.setOnClickListener(this);
        mDivideButton.setOnClickListener(this);
        mEqualsButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
    }

    private void initializePage() {
        // TextViews
        mInputTextView = findViewById(R.id.input_tv);
        mResultTextView = findViewById(R.id.result_tv);

        // Buttons
        mDecimalButton = findViewById(R.id.btnDP);
        mZeroButton = findViewById(R.id.btnZero);
        mClearButton = findViewById(R.id.btnClear);
        mOneButton = findViewById(R.id.btnOne);
        mTwoButton = findViewById(R.id.btnTwo);
        mThreeButton = findViewById(R.id.btnThree);
        mFourButton = findViewById(R.id.btnFour);
        mFiveButton = findViewById(R.id.btnFive);
        mSixButton = findViewById(R.id.btnSix);
        mSevenButton = findViewById(R.id.btnSeven);
        mEightButton = findViewById(R.id.btnEight);
        mNineButton = findViewById(R.id.btnNine);

        mAddButton = findViewById(R.id.btnAdd);
        mSubButton = findViewById(R.id.btnSub);
        mMultiplyButton = findViewById(R.id.btnMultiply);
        mDivideButton = findViewById(R.id.btnDivide);
        mEqualsButton = findViewById(R.id.equalsBtn);
        mDeleteButton = findViewById(R.id.delBtn);
    }

    @Override
    public void onClick(View v) {
        if (v == mDecimalButton)
            input(".");
        else if (v == mZeroButton)
            input("0");
        else if (v == mClearButton) {
            mInputTextView.setText(null);
            mResultTextView.setText(null);
        }
        else if (v == mOneButton)
            input("1");
        else if (v == mTwoButton)
            input("2");
        else if (v == mThreeButton)
            input("3");
        else if (v == mFourButton)
            input("4");
        else if (v == mFiveButton)
            input("5");
        else if (v == mSixButton)
            input("6");
        else if (v == mSevenButton)
            input("7");
        else if (v == mEightButton)
            input("8");
        else if (v == mNineButton)
            input("9");
        else if (v == mAddButton)
            input("+");
        else if (v == mSubButton)
            input("-");
        else if (v == mMultiplyButton)
            input("x");
        else if (v == mDivideButton)
            input("/");
        else if (v == mEqualsButton)
            doCalculation(returnArguments());
        else if (v == mDeleteButton)
            deleteInput();
    }

    private void doCalculation(List<Integer> arguments) {
        double a = (double) arguments.get(0);
        double b = (double) arguments.get(arguments.size() - 1);
        double result = 0;
        Toast toast = Toast.makeText(getApplicationContext(), "First argument : " + a + "\n" +
                "Second argument : " + b + "\n" +
                "Operator : " + mOperate, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, Y_OFFSET);
        toast.show();

        if (mOperate == "+") {
            result = add(a, b);
        } else if (mOperate == "-") {
            result = subtract(a, b);
        } else if (mOperate == "x") {
            result = multiply(a, b);
        }
        else if (mOperate == "/") {
            result = divide(a, b);
        }

        mResultTextView.setText(Double.toString(result));
    }

    private void deleteInput() {
        String original = mInputTextView.getText().toString();
        String afterPressed = original.substring(0 ,original.length()-1);
        mInputTextView.setText(afterPressed);
    }

    private void input(String s) {
        String original = mInputTextView.getText().toString();
        mInputTextView.setText(original+s);
    }

    private List<Integer> returnArguments(){
        String original = mInputTextView.getText().toString();
        String firstArgument = null; String secondArgument = null;
        List<Integer> arguments = new ArrayList<>();
        String[] operators = {"+", "-", "x", "/"};

        for (String operator: operators){
            if (original.contains(operator)){
                mOperate = operator;
                firstArgument = original.substring(0, original.indexOf(operator));
                secondArgument = original.substring(original.indexOf(operator)+1);
            }
        }
        arguments.add(Integer.parseInt(firstArgument));
        arguments.add(Integer.parseInt(secondArgument));
        return arguments;
    }
}