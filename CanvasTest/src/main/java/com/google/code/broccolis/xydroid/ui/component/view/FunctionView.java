package com.google.code.broccolis.xydroid.ui.component.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.ui.component.model.FunctionModel;
import com.google.code.broccolis.xydroid.ui.interfaces.Colliding;
import com.google.code.broccolis.xydroid.ui.interfaces.Drawable;

public class
        FunctionView implements Drawable, Colliding
{
    private FunctionModel model;

    public FunctionView(String function, Point initialCoordinate, int xMax)
    {
        model = new FunctionModel(function, initialCoordinate, xMax);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        model.draw(canvas,paint);
    }

    @Override
    public boolean collidesWith(int x, int y)
    {
        return false; //TODO colliding with function
    }
}