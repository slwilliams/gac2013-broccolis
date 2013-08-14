package com.google.code.broccolis.xydroid.ui.component.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.ui.component.model.FunctionModel;
import com.google.code.broccolis.xydroid.ui.interfaces.Colliding;
import com.google.code.broccolis.xydroid.ui.interfaces.Drawable;
import com.google.code.broccolis.xydroid.util.Player;

import static android.graphics.Color.BLACK;
import static android.graphics.Paint.Style.FILL;
import static android.graphics.Paint.Style.STROKE;

public class FunctionView implements Drawable, Colliding
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
        paint.setStyle(STROKE);
        paint.setColor(BLACK);

        canvas.drawPath(path, paint);

        paint.setStyle(FILL);
    }

    public boolean collidesWith(Player player)
    {
        //TODO
        return model.contains(0, 0);
    }
}
