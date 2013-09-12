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


public class Level2 extends Level
{
    private static final String NAME = "Level2 ";
    private static final int levelNum = 2;

    public Level2(Resources res)
    {
        super(res);

        pointY = new Point(parseNexusX(20), parseNexusY(50));

        walls.add(new WallView(new PointOnScreen(0f / NEXUS_WIDTH, 100f / NEXUS_HEIGHT), new PointOnScreen(100f / NEXUS_WIDTH, 125f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(1000f / NEXUS_WIDTH, 100f / NEXUS_HEIGHT), new PointOnScreen(1100f / NEXUS_WIDTH, 160f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(300f / NEXUS_WIDTH, 300f / NEXUS_HEIGHT), new PointOnScreen(850f / NEXUS_WIDTH, 355f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(300f / NEXUS_WIDTH, 500f / NEXUS_HEIGHT), new PointOnScreen(400f / NEXUS_WIDTH, 530f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(600f / NEXUS_WIDTH, 500f / NEXUS_HEIGHT), new PointOnScreen(800f / NEXUS_WIDTH, 530f / NEXUS_HEIGHT), res));
        walls.add(new WallView(new PointOnScreen(1000f / NEXUS_WIDTH, 450f / NEXUS_HEIGHT), new PointOnScreen(1100f / NEXUS_WIDTH, 475f / NEXUS_HEIGHT), res));

        for (int i = 1005; i < 1090; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(parseNexusX(i), parseNexusY(160)),
                    new Point(parseNexusX(i + 30), parseNexusY(160)),
                    new Point(parseNexusX(i + 15), parseNexusY(200))
            }, Color.BLACK));
        }

        for (int i = 310; i < 850; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(parseNexusX(i), parseNexusY(355)),
                    new Point(parseNexusX(i + 30), parseNexusY(355)),
                    new Point(parseNexusX(i + 15), parseNexusY(420))
            }, Color.BLACK));
        }

        for (int i = 305; i < 390; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(parseNexusX(i), parseNexusY(500)),
                    new Point(parseNexusX(i + 30), parseNexusY(500)),
                    new Point(parseNexusX(i + 15), parseNexusY(480))
            }, Color.BLACK));
        }

        for (int i = 610; i < 780; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(parseNexusX(i), parseNexusY(500)),
                    new Point(parseNexusX(i + 30), parseNexusY(500)),
                    new Point(parseNexusX(i + 15), parseNexusY(480))
            }, Color.BLACK));
        }

        broccolis.add(new Broccoli(1020f / NEXUS_WIDTH, 50f / NEXUS_HEIGHT, res));
        broccolis.add(new Broccoli(330f / NEXUS_WIDTH, 250f / NEXUS_HEIGHT, res));
        broccolis.add(new Broccoli(550f / NEXUS_WIDTH, 400f / NEXUS_HEIGHT, res));
    }

    @Override
    public Point playerStartPosition()
    {
        return new Point(25, 650);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        walls.get(9).move(0, (int) (Math.sin(platformOffset) * 5));
        platformOffset -= 0.03;

        super.draw(canvas, paint);
    }
}
