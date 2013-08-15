package com.google.code.broccolis.xydroid.ui.component.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.ui.component.model.SpikeModel;
import com.google.code.broccolis.xydroid.ui.interfaces.Drawable;
import com.google.code.broccolis.xydroid.ui.interfaces.Obstacle;

/**
 * Created by demouser on 8/14/13.
 */
public class SpikeView implements Drawable {

    private SpikeModel model;

    public SpikeView(Point[] points, int color){

        model = new SpikeModel(points, color);
    }


    @Override
    public void draw(Canvas canvas, Paint paint) {

        Point[] points = model.getPoints();
        paint.setColor(model.getColor());
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(points[0].x,points[0].y);
        path.lineTo(points[1].x,points[1].y);
        path.lineTo(points[2].x,points[2].y);
        path.lineTo(points[0].x,points[0].y);
        path.close();

        canvas.drawPath(path,paint);
    }
}
