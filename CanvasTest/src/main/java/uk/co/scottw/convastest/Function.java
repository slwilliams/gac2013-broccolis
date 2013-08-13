package uk.co.scottw.convastest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import uk.co.scottw.convastest.util.Constants;

public class Function
{
    private static final String NAME = "Function ";

    private int[] points;
    private int varX;
    private Point origin;
    private int length;
    private Calculable calc = null;

    public Function(String function, Point initialCoordinate, int xMax)
    {
        origin = initialCoordinate;
        length = xMax;
        try
        {
            calc = new ExpressionBuilder(function).withVariable("x^2", varX).build();
        }
        catch (Exception e)
        {
            Log.e(Constants.TAG,NAME+"can't parse the function",e);
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
