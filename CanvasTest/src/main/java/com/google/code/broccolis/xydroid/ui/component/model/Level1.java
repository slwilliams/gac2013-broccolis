package com.google.code.broccolis.xydroid.ui.component.model;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.ui.component.view.WallView;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;
import com.google.code.broccolis.xydroid.util.Broccoli;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.scaleToNexusX;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.scaleToNexusY;

public class Level1 extends Level
{
    public Level1(Resources res)
    {
        super(res);

        //need to rethink multiple device scaling I think
        pointY = new Point(1040, 50);

        walls.add(new WallView(new PointOnScreen(0.75f, scaleToNexusY(100)), new PointOnScreen(1.0f, scaleToNexusY(125)), res));
        walls.add(new WallView(new PointOnScreen(scaleToNexusX(380), scaleToNexusY(345)), new PointOnScreen(scaleToNexusX(750), scaleToNexusY(370)), res));

        broccolis.add(new Broccoli(scaleToNexusX(207), scaleToNexusY(460), res));
        broccolis.add(new Broccoli(scaleToNexusX(830), scaleToNexusY(190), res));
    }

    @Override
    public Point playerStartPosition()
    {
        return new Point(25, 650);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        super.draw(canvas, paint);
    }
}
