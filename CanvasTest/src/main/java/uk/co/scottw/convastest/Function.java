package uk.co.scottw.convastest;

import android.graphics.Canvas;
import android.graphics.Paint;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class Function
{
    private int[] points;
    private int varX;
    private Calculable calc = null;

    public Function(String function)
    {
        try
        {
            this.calc = new ExpressionBuilder(function).withVariable("x", varX).build();
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
        //return grad at point x
        return 0;
    }
}
