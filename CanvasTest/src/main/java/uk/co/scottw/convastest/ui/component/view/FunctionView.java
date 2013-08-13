package uk.co.scottw.convastest.ui.component.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import uk.co.scottw.convastest.ui.interfaces.Colliding;
import uk.co.scottw.convastest.ui.interfaces.Drawable;
import uk.co.scottw.convastest.ui.component.model.FunctionModel;

public class FunctionView implements Drawable, Colliding
{
    private FunctionModel model;

    public FunctionView(String function, Point initialCoordinate, int xMax)
    {
        model = new FunctionModel(function,initialCoordinate,xMax);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        //TODO drawing the function
    }

    @Override
    public boolean collidesWith(int x, int y)
    {
        return false; //TODO colliding with function
    }
}
