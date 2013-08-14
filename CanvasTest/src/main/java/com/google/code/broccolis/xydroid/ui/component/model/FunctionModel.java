package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.google.code.broccolis.xydroid.util.Constants;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class FunctionModel
{
    private static final String NAME = "FunctionModel ";

    private int[] points;
    private int varX;
    private Point origin;
    private int length;
    private Calculable calc = null;

    public FunctionModel(String function, Point initialCoordinate, int xMax)
    {
        origin = initialCoordinate;
        length = xMax;
        try
        {
            calc = new ExpressionBuilder(function).withVariable("x", varX).build();
            int diff = length - origin.x;
            points = new int[diff];
            for(int i = 0; i < diff; i ++)
            {
                calc.setVariable("x", i);
                points[i] = (int)calc.calculate();
            }
        }
        catch (Exception e)
        {
            Log.e(Constants.TAG, NAME + "can't parse the function", e);
        }
    }

    public void draw(Canvas canvas, Paint paint)
    {
        //draw self using canvas.path here
        paint.setColor(Color.BLACK);
        for(int i = 0; i < points.length-1; i ++)
        {
            canvas.drawLine(origin.x+i,origin.y+points[i], origin.x+i+1, origin.y+points[i+1], paint);
        }
    }

    public int getGrad(int x)
    {
        // derivative
        return 0;
    }
}
