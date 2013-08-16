package com.google.code.broccolis.xydroid.ui.component.model;


import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.ui.component.view.SpikeView;
import com.google.code.broccolis.xydroid.ui.component.view.WallView;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;
import com.google.code.broccolis.xydroid.util.Broccoli;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_WIDTH;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusX;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusY;

public class Level1 extends Level
{
    public Level1(Resources res)
    {
        super(res);

        pointY = new Point(parseNexusX(960), parseNexusY(50));

        walls.add(new WallView(new PointOnScreen(0f / NEXUS_WIDTH, 100f / NEXUS_HEIGHT), new PointOnScreen(100f / NEXUS_WIDTH, 125f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(500f / NEXUS_WIDTH, 400f / NEXUS_HEIGHT), new PointOnScreen(800f / NEXUS_WIDTH, 450f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(150f / NEXUS_WIDTH, 500f / NEXUS_HEIGHT), new PointOnScreen(300f / NEXUS_WIDTH, 525f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(930f / NEXUS_WIDTH, 100f / NEXUS_HEIGHT), new PointOnScreen(1050f / NEXUS_WIDTH, 130f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(300f / NEXUS_WIDTH, 130f / NEXUS_HEIGHT), new PointOnScreen(400f / NEXUS_WIDTH, 190f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(1160f / NEXUS_WIDTH, 550f / NEXUS_HEIGHT), new PointOnScreen(1280f / NEXUS_WIDTH, 590f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(1160f / NEXUS_WIDTH, 450f / NEXUS_HEIGHT), new PointOnScreen(1280f / NEXUS_WIDTH, 475f / NEXUS_HEIGHT), res));

        for (int i = 945; i < 1020; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(parseNexusX(i), parseNexusY(130)),
                    new Point(parseNexusX(i + 30), parseNexusY(130)),
                    new Point(parseNexusX(i + 15), parseNexusY(200))
            }, Color.BLACK));
        }
        for (int i = 500; i < 790; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(parseNexusX(i), parseNexusY(450)),
                    new Point(parseNexusX(i + 30), parseNexusY(450)),
                    new Point(parseNexusX(i + 15), parseNexusY(480))
            }, Color.BLACK));
        }
        for (int i = 305; i < 395; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(parseNexusX(i), parseNexusY(130)),
                    new Point(parseNexusX(i + 30), parseNexusY(130)),
                    new Point(parseNexusX(i + 15), parseNexusY(100))
            }, Color.BLACK));
        }

        for (int i = 305; i < 395; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(parseNexusX(i), parseNexusY(190)),
                    new Point(parseNexusX(i + 30), parseNexusY(190)),
                    new Point(parseNexusX(i + 15), parseNexusY(220))
            }, Color.BLACK));
        }

        for (int i = 550; i < 590; i += 20)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(parseNexusX(1160), parseNexusY(i)),
                    new Point(parseNexusX(1160), parseNexusY(i + 20)),
                    new Point(parseNexusX(1100), parseNexusY(i + 10))
            }, Color.BLACK));
        }

        broccolis.add(new Broccoli(20f / NEXUS_WIDTH, 50f / NEXUS_HEIGHT, res));
        broccolis.add(new Broccoli(500f / NEXUS_WIDTH, 100f / NEXUS_HEIGHT, res));
        broccolis.add(new Broccoli(1200f / NEXUS_WIDTH, 500f / NEXUS_HEIGHT, res));
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        walls.get(6).move((int) (Math.sin(val) * 5), 0);
        val += 0.03;

        super.draw(canvas, paint);
    }
}
