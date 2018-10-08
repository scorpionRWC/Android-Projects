package com.example.riley.mysimplecalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static double firstNumber, secondNumber, resultNumber;
    static boolean newNumber, pointUsed;
    String previousOperation;

    static TextView tvanswer, tvhistory;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bdivide, bplus, bminus, btimes, bequals, bpoint, bclear, bback, bdraw;
    View drawView;

    //used for switch case logic
    boolean isNumber;
    int whichNumber;
    boolean errorSet;
    //used for the back_space button
    String tempAnswer;
    static String tempHistory;

    //for switching between draw and button numbers
    boolean isDrawing;

    Vibrator vibe;
    int vibeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumber = 0.0;
        secondNumber = 0.0;
        resultNumber = 0.0;
        newNumber = true;
        pointUsed = false;
        previousOperation = "none";
        isDrawing = false;

        tvanswer = findViewById(R.id.answer);
        tvhistory = findViewById(R.id.history);
        b0 = findViewById(R.id.zero);
        b1 = findViewById(R.id.one);
        b2 = findViewById(R.id.two);
        b3 = findViewById(R.id.three);
        b4 = findViewById(R.id.four);
        b5 = findViewById(R.id.five);
        b6 = findViewById(R.id.six);
        b7 = findViewById(R.id.seven);
        b8 = findViewById(R.id.eight);
        b9 = findViewById(R.id.nine);
        bdivide = findViewById(R.id.divide);
        bplus = findViewById(R.id.plus);
        bminus = findViewById(R.id.minus);
        bequals = findViewById(R.id.equals);
        btimes = findViewById(R.id.times);
        bpoint = findViewById(R.id.point);
        bclear = findViewById(R.id.clear);
        bback = findViewById(R.id.back);
        bdraw = findViewById(R.id.draw);
        drawView = findViewById(R.id.single_touch_view);

        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bplus.setOnClickListener(this);
        bminus.setOnClickListener(this);
        btimes.setOnClickListener(this);
        bdivide.setOnClickListener(this);
        bequals.setOnClickListener(this);
        bpoint.setOnClickListener(this);
        bclear.setOnClickListener(this);
        bback.setOnClickListener(this);
        bdraw.setOnClickListener(this);
        drawView.setOnClickListener(this);

        drawView.setVisibility(View.INVISIBLE);
        vibe  = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibeTime = 10;
    }

    void mathLogic(){
        newNumber = true;
        pointUsed = false;
        errorSet = false;
        secondNumber = Double.parseDouble(tvanswer.getText().toString());
        switch(previousOperation){
            case "plus":
                resultNumber = firstNumber + secondNumber;
                break;
            case "minus":
                resultNumber = firstNumber - secondNumber;
                break;
            case "times":
                resultNumber = firstNumber * secondNumber;
                break;
            case "divide":
                resultNumber = firstNumber / secondNumber;
                break;
            default:
                resultNumber = secondNumber;
        }

        if(errorSet){
            tvanswer.setText(String.format(Locale.getDefault(), "%s", Integer.toString(R.string.Error)));
            pointUsed = false;
            firstNumber = 0.0;
            secondNumber = 0.0;
            resultNumber = 0.0;
            newNumber = true;
            previousOperation = "none";
        }
        else {
            firstNumber = resultNumber;
            if (Math.floor(resultNumber) == resultNumber)
                tvanswer.setText(String.format(Locale.getDefault(), "%s", Integer.toString((int) resultNumber)));
            else
                tvanswer.setText(String.format(Locale.getDefault(), "%s", Double.toString(resultNumber)));
            if(tvanswer.getText().toString().length() > 10){
                tvanswer.setText(String.format(Locale.getDefault(), "%e", resultNumber));
            }
        }
    }

    @Override
    public void onClick(View view) {
        isNumber = false;
        whichNumber = 9;
        switch(view.getId()){
            case R.id.zero:
                whichNumber--;
            case R.id.one:
                whichNumber--;
            case R.id.two:
                whichNumber--;
            case R.id.three:
                whichNumber--;
            case R.id.four:
                whichNumber--;
            case R.id.five:
                whichNumber--;
            case R.id.six:
                whichNumber--;
            case R.id.seven:
                whichNumber--;
            case R.id.eight:
                whichNumber--;
            case R.id.nine:
                isNumber = true;
                vibe.vibrate(vibeTime);
                break;
            case R.id.plus:
                if(!tvanswer.getText().toString().equals("...") && !tvanswer.getText().toString().equals(Integer.toString(R.string.Error))) {
                    mathLogic();
                    previousOperation = "plus";
                    if (Math.floor(resultNumber) == resultNumber)
                        tvhistory.setText(String.format(Locale.getDefault(), "%s + ", Integer.toString((int) resultNumber)));
                    else
                        tvhistory.setText(String.format(Locale.getDefault(), "%s + ", Double.toString(resultNumber)));
                    if(tvanswer.getText().toString().length() > 10){
                        tvhistory.setText(String.format(Locale.getDefault(), "%e + ", resultNumber));
                    }
                }
                vibe.vibrate(vibeTime);
                break;
            case R.id.minus:
                if(!tvanswer.getText().toString().equals("...") && !tvanswer.getText().toString().equals(Integer.toString(R.string.Error))) {
                    mathLogic();
                    previousOperation = "minus";
                    if (Math.floor(resultNumber) == resultNumber)
                        tvhistory.setText(String.format(Locale.getDefault(), "%s - ", Integer.toString((int) resultNumber)));
                    else
                        tvhistory.setText(String.format(Locale.getDefault(), "%s - ", Double.toString(resultNumber)));
                    if(tvanswer.getText().toString().length() > 10){
                        tvhistory.setText(String.format(Locale.getDefault(), "%e - ", resultNumber));
                    }
                }
                vibe.vibrate(vibeTime);
                break;
            case R.id.times:
                if(!tvanswer.getText().toString().equals("...") && !tvanswer.getText().toString().equals(Integer.toString(R.string.Error))) {
                    mathLogic();
                    previousOperation = "times";
                    if (Math.floor(resultNumber) == resultNumber)
                        tvhistory.setText(String.format(Locale.getDefault(), "%s X ", Integer.toString((int) resultNumber)));
                    else
                        tvhistory.setText(String.format(Locale.getDefault(), "%s X ", Double.toString(resultNumber)));
                    if(tvanswer.getText().toString().length() > 10){
                        tvhistory.setText(String.format(Locale.getDefault(), "%e X ", resultNumber));
                    }
                }
                vibe.vibrate(vibeTime);
                break;
            case R.id.divide:
                if(!tvanswer.getText().toString().equals("...") && !tvanswer.getText().toString().equals(Integer.toString(R.string.Error))) {
                    mathLogic();
                    previousOperation = "divide";
                    if (Math.floor(resultNumber) == resultNumber)
                        tvhistory.setText(String.format(Locale.getDefault(), "%s / ", Integer.toString((int) resultNumber)));
                    else
                        tvhistory.setText(String.format(Locale.getDefault(), "%s / ", Double.toString(resultNumber)));
                    if(tvanswer.getText().toString().length() > 10){
                        tvhistory.setText(String.format(Locale.getDefault(), "%e / ", resultNumber));
                    }
                }
                vibe.vibrate(vibeTime);
                break;
            case R.id.equals:
                if(!tvanswer.getText().toString().equals("...") && !tvanswer.getText().toString().equals(Integer.toString(R.string.Error))) {
                    mathLogic();
                    previousOperation = "none";
                    if (Math.floor(resultNumber) == resultNumber)
                        tvhistory.setText(String.format(Locale.getDefault(), "= %s", Integer.toString((int) resultNumber)));
                    else
                        tvhistory.setText(String.format(Locale.getDefault(), "= %s", Double.toString(resultNumber)));
                    if(tvanswer.getText().toString().length() > 10){
                        tvhistory.setText(String.format(Locale.getDefault(), "= %e", resultNumber));
                    }
                }
                vibe.vibrate(vibeTime);
                break;
            case R.id.point:
                tempHistory = tvhistory.getText().toString();
                if(!newNumber && !pointUsed) {
                    tvanswer.append(".");
                    if(tempHistory.equals("...") || tempHistory.charAt(0) == '='){
                        tvhistory.setText("0.");
                    }
                    else{
                        tvhistory.append(".");
                    }
                    pointUsed = true;
                }
                else if(!pointUsed){
                    newNumber = false;
                    pointUsed = true;
                    tvanswer.setText("0.");
                    if(tempHistory.equals("...") || tempHistory.charAt(0) == '='){
                        tvhistory.setText("0.");
                    }
                    else{
                        tvhistory.append(".");
                    }
                }
                vibe.vibrate(vibeTime);
                break;
            case R.id.clear:
                pointUsed = false;
                firstNumber = 0.0;
                secondNumber = 0.0;
                resultNumber = 0.0;
                newNumber = true;
                previousOperation = "none";
                tvanswer.setText("...");
                tvhistory.setText("...");
                vibe.vibrate(vibeTime);
                break;
            case R.id.back:
                tempAnswer = tvanswer.getText().toString();
                tempHistory = tvhistory.getText().toString();
                if(!tempAnswer.equals("...") && !tempAnswer.equals(Integer.toString(R.string.Error)) && tempAnswer.length() > 0) {
                    if(tempAnswer.charAt(tempAnswer.length() - 1) == '.'){
                        pointUsed = false;
                    }
                    tempAnswer = tempAnswer.substring(0, tempAnswer.length() - 1);
                    tempHistory = tempHistory.substring(0, tempHistory.length() - 1);
                    tvanswer.setText(tempAnswer);
                    tvhistory.setText(tempHistory);
                }
                vibe.vibrate(vibeTime);
                break;
            case R.id.draw:
                //startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                if(!isDrawing) { // go to drawing mode
                    drawView.setVisibility(View.VISIBLE);
                    b0.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    b5.setVisibility(View.INVISIBLE);
                    b6.setVisibility(View.INVISIBLE);
                    b7.setVisibility(View.INVISIBLE);
                    b8.setVisibility(View.INVISIBLE);
                    b9.setVisibility(View.INVISIBLE);
                    bpoint.setVisibility(View.INVISIBLE);
                    bdraw.setText(R.string.backToKeypad);
                    isDrawing = true;
                }
                else{// go back to button mode
                    drawView.setVisibility(View.INVISIBLE);
                    b0.setVisibility(View.VISIBLE);
                    b1.setVisibility(View.VISIBLE);
                    b2.setVisibility(View.VISIBLE);
                    b3.setVisibility(View.VISIBLE);
                    b4.setVisibility(View.VISIBLE);
                    b5.setVisibility(View.VISIBLE);
                    b6.setVisibility(View.VISIBLE);
                    b7.setVisibility(View.VISIBLE);
                    b8.setVisibility(View.VISIBLE);
                    b9.setVisibility(View.VISIBLE);
                    bpoint.setVisibility(View.VISIBLE);
                    bdraw.setText(R.string.freeDraw);
                    isDrawing = false;
                }
                vibe.vibrate(vibeTime);
                break;
            default:
        }
        if(isNumber) {
            if (!newNumber && ((Math.floor(resultNumber) != resultNumber && tvanswer.getText().toString().length() < 10) || Math.floor(resultNumber) == resultNumber && tvanswer.getText().toString().length() < 9)){
                tvanswer.append(Integer.toString(whichNumber));
                tvhistory.append(Integer.toString(whichNumber));
            }
            else if(newNumber){
                newNumber = false;
                tvanswer.setText(String.format(Locale.getDefault(), "%s", Integer.toString(whichNumber)));
                if(tvhistory.getText().toString().equals("...") || tvhistory.getText().toString().charAt(0) == '='){
                    tvhistory.setText(String.format(Locale.getDefault(), "%s", Integer.toString(whichNumber)));
                }
                else{
                    tvhistory.append(Integer.toString(whichNumber));
                }
            }
        }
    } // end onClick

    public static void setDrawnText(String txt){
        if(txt.equals(".")){
            tempHistory = tvhistory.getText().toString();
            if(!newNumber && !pointUsed) {
                tvanswer.append(".");
                if(tempHistory.equals("...") || tempHistory.charAt(0) == '='){
                    tvhistory.setText("0.");
                }
                else{
                    tvhistory.append(".");
                }
                pointUsed = true;
            }
            else if(!pointUsed){
                newNumber = false;
                pointUsed = true;
                tvanswer.setText("0.");
                if(tempHistory.equals("...") || tempHistory.charAt(0) == '='){
                    tvhistory.setText("0.");
                }
                else{
                    tvhistory.append(".");
                }
            }
        }
        else if (!newNumber && ((Math.floor(resultNumber) != resultNumber && tvanswer.getText().toString().length() < 10) || Math.floor(resultNumber) == resultNumber && tvanswer.getText().toString().length() < 9)){
        tvanswer.append(txt);
        tvhistory.append(txt);
        }
        else if(newNumber){
            newNumber = false;
            tvanswer.setText(String.format(Locale.getDefault(), "%s", txt));
            if(tvhistory.getText().toString().equals("...") || tvhistory.getText().toString().charAt(0) == '='){
                tvhistory.setText(String.format(Locale.getDefault(), "%s", txt));
            }
            else{
                tvhistory.append(txt);
            }
        }
    }
}
