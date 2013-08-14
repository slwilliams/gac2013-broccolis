package com.google.code.broccolis.xydroid.ui.component.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
        Path path = model.getPath();
        paint.setStrokeWidth(1.f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawPath(path, paint); //TODO check this
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public boolean collidesWith(int x, int y)
    {
        return model.contains(x, y);
    }
}
