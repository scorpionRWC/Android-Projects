package com.example.riley.mysimplecalculator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;
public class MainDrawingView extends View {
    private Paint paint;
    private Path path;
    private final int MIN_LENGTH, MIN_LOOP;
    private float startX, startY, initialX, initialY;
    private boolean makesLoop;
    private String [] motions;
    private int currentMotionIndex;
    private String currentMotion;
    private static String numberDrawn;
    private int strIndex;

    public MainDrawingView(Context context, AttributeSet attrs) {

        super(context, attrs);

        paint = new Paint();
        path = new Path();
        motions = new String[8];

        paint.setAntiAlias(true);
        paint.setStrokeWidth(5f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);

        numberDrawn = "...?";

        MIN_LENGTH = 100;
        MIN_LOOP = 300;

        strIndex = 0;
        makesLoop = false;

        clearMotions();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Get the coordinates of the touch event.
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Set a new starting point
                path.moveTo(eventX, eventY);
                startX = eventX;
                startY = eventY;
                initialX = eventX;
                initialY = eventY;
                return true;
            case MotionEvent.ACTION_MOVE:
                // Connect the points
                path.lineTo(eventX, eventY);
                if(Math.abs(eventX - startX) >= MIN_LENGTH && eventX > startX){
                    currentMotion = "Right";
                    startX = eventX;
                    startY = eventY;
                }
                else if(Math.abs(startX - eventX) >= MIN_LENGTH && eventX < startX){
                    currentMotion = "Left";
                    startX = eventX;
                    startY = eventY;
                }
                else if(Math.abs(eventY - startY) >= MIN_LENGTH && eventY < startY){
                    currentMotion = "Up";
                    startX = eventX;
                    startY = eventY;
                }
                else if(Math.abs(startY - eventY) >= MIN_LENGTH && eventY > startY){
                    currentMotion = "Down";
                    startX = eventX;
                    startY = eventY;
                }
                if(currentMotionIndex == 0 && !currentMotion.equals("")){
                    motions[0] = currentMotion;
                    currentMotionIndex++;
                    currentMotion = "";
                    startX = eventX;
                    startY = eventY;
                }
                else if(currentMotionIndex < 8 && currentMotionIndex > 0){ if( !motions[currentMotionIndex - 1].equals(currentMotion) && !currentMotion.equals("")){
                    motions[currentMotionIndex] = currentMotion;
                    currentMotionIndex++;
                    currentMotion = "";
                    startX = eventX;
                    startY = eventY;
                }}
                break;
            case MotionEvent.ACTION_UP:
                // Clears the canvas
                if(Math.abs(initialX - eventX) <= MIN_LOOP && Math.abs(initialY - eventY) <= MIN_LOOP){
                    makesLoop = true;
                }
                else{
                    makesLoop = false;
                }
                checkMotionsForNumber();
                path.reset();
                clearMotions();
                MainActivity.setDrawnText(numberDrawn);
                invalidate();
            default:
                return false;
        }

        // Makes our view repaint and call onDraw
        invalidate();
        return true;
    }

    private void clearMotions(){
        currentMotionIndex = 0;
        currentMotion = "";
        for(int i = 0; i < 8; i++){
            motions[i] = "";
        }
    }

    public static String getNumber()
    {
            return numberDrawn;
    }

    public void checkMotionsForNumber(){
        strIndex = 0;
        numberDrawn = "?";

        if(motions[strIndex].equals("")){
            numberDrawn = ".";
        }

        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Down")){
                numberDrawn = "1";
            }
        }


        strIndex = 0;
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Right")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Down")){
                        numberDrawn = "7";
                    }
                }
            }
        }


        strIndex = 0;
        //if(motions[0].equals("Down") && motions[1].equals("") && motions[2].equals("") && motions[3].equals("") && motions[4].equals("") && motions[5].equals("") && motions[6].equals("") && motions[7].equals("")){
          //  numberDrawn = "1";
        //}
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Right")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Down")){
                        for(; strIndex < 8; strIndex++){
                            if(motions[strIndex].equals("Right")){
                                numberDrawn = "2";
                            }
                        }
                    }
                }
            }
        }


        strIndex = 0;
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Left")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Down")){
                        for(; strIndex < 8; strIndex++){
                            if(motions[strIndex].equals("Left")){
                                numberDrawn = "5";
                            }
                        }
                    }
                }
            }
        }


        strIndex = 0;
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Up")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Left")){
                        for(; strIndex < 8; strIndex++){
                            if(motions[strIndex].equals("Down")){
                                numberDrawn = "9";
                            }
                        }
                    }
                }
            }
        }



        strIndex = 0;
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Down")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Right")){
                        for(; strIndex < 8; strIndex++){
                            if(motions[strIndex].equals("Up")){
                                for(; strIndex < 8; strIndex++){
                                    if(motions[strIndex].equals("Down")){
                                        numberDrawn = "4";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        strIndex = 0;
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Down")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Right")){
                        for(; strIndex < 8; strIndex++){
                            if(motions[strIndex].equals("Up")){
                                for(; strIndex < 8; strIndex++){
                                    if(motions[strIndex].equals("Left")){
                                        numberDrawn = "6";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        strIndex = 0;
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Down")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Right") && makesLoop){
                        numberDrawn = "0";
                    }
                }
            }
        }


        strIndex = 0;
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Left")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Down")){
                        for(; strIndex < 8; strIndex++){
                            if(motions[strIndex].equals("Left")){
                                for(; strIndex < 8; strIndex++){
                                    if(motions[strIndex].equals("Up") && makesLoop){
                                        numberDrawn = "8";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        strIndex = 0;
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Left")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Down")){
                        for(; strIndex < 8; strIndex++){
                            if(motions[strIndex].equals("Right")){
                                for(; strIndex < 8; strIndex++){
                                    if(motions[strIndex].equals("Up")){
                                        for(; strIndex < 8; strIndex++){
                                            if(motions[strIndex].equals("Down") && !numberDrawn.equals("6")){
                                                numberDrawn = "9";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        strIndex = 0;
        for(; strIndex < 8; strIndex++){
            if(motions[strIndex].equals("Right")){
                for(; strIndex < 8; strIndex++){
                    if(motions[strIndex].equals("Down")){
                        for(; strIndex < 8; strIndex++){
                            if(motions[strIndex].equals("Left")){
                                for(; strIndex < 8; strIndex++){
                                    if(motions[strIndex].equals("Down")){
                                        for(; strIndex < 8; strIndex++){
                                            if(motions[strIndex].equals("Left")){
                                                numberDrawn = "3";
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } // end checkMotionsForNumber
} // end class
