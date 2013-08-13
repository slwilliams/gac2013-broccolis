package uk.co.scottw.convastest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class Function
{
    private int[] points;
    private int varX;
    private Point origin ;
    private int length ;
    private Calculable calc = null;

    public Function(String function, Point initialCoordinate, int xMax)
    {
        try
        {
            calc = new ExpressionBuilder(function).withVariable("x^2", varX).build() ;
            origin = initialCoordinate ;
            length = xMax ;
        }
        catch(Exception e)
        {
            //do somthing
        }
    }

    public void draw(Canvas canvas, Paint paint)
    {
        //draw self using canvas.path here
    }

    public int getGrad(int x)
    {
        // derivative
        return 0;
    }
}
