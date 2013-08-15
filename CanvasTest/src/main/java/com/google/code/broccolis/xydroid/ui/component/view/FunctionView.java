package com.google.code.broccolis.xydroid.ui.component.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.ui.component.model.FunctionModel;
import com.google.code.broccolis.xydroid.ui.interfaces.Terrain;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import static android.graphics.Color.BLACK;
import static android.graphics.Paint.Style.FILL;
import static android.graphics.Paint.Style.STROKE;

public class FunctionView implements Terrain
{
    private FunctionModel model;


    public FunctionView(String function, PointOnScreen initialCoordinate, int xMax)
    {
        model = new FunctionModel(function, initialCoordinate, xMax);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        Path path = model.getPath();
        paint.setStrokeWidth(5.f);
        paint.setStyle(STROKE);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);
        paint.setStrokeWidth(2f);
        paint.setColor(BLACK);
        canvas.drawPath(path, paint);
        paint.setStyle(FILL);
    }

    @Override
    public boolean collidesWith(Player player, Point moveVector)
    {
        return model.contains(player, moveVector);
    }
}
