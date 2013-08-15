package com.google.code.broccolis.xydroid.ui.component.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;

import com.google.code.broccolis.xydroid.ui.component.model.SpikeModel;
import com.google.code.broccolis.xydroid.ui.interfaces.Drawable;
import com.google.code.broccolis.xydroid.ui.interfaces.Obstacle;
import com.google.code.broccolis.xydroid.util.Player;


public class SpikeView implements Drawable, Obstacle
{
    private SpikeModel model;
    Path path;

    public SpikeView(Point[] points, int color)
    {
        path = new Path();


        path.moveTo(points[0].x, points[0].y);
        for(Point p : points)
        {
            path.lineTo(p.x, p.y);
        }
        model = new SpikeModel(path, color);

    }
    @Override
    public boolean collidesWith(Player player, Point movingAmount)
    {
        return model.contains(player, movingAmount);
    }
    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        Path path = model.getPath();
        paint.setColor(model.getColor());
        canvas.drawPath(path,paint);
    }

}
