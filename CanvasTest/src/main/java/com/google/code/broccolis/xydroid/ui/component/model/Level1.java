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

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusX;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusY;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToInt;

public class Level1 extends Level
{
    public Level1(Resources res)
    {
        super(res);

        pointY = new Point(parseXToInt(parseNexusX(960)), parseNexusY(50));

        walls.add(new WallView(new PointOnScreen(0, parseNexusY(100)), new PointOnScreen(parseNexusX(100), parseNexusY(125)), res));
        walls.add(new WallView(new PointOnScreen(parseNexusX(500), parseNexusY(400)), new PointOnScreen(parseNexusX(800), parseNexusY(450)), res));
        walls.add(new WallView(new PointOnScreen(parseNexusX(150), parseNexusY(500)), new PointOnScreen(parseNexusX(300), parseNexusY(525)), res));
        walls.add(new WallView(new PointOnScreen(parseNexusX(930), parseNexusY(100)), new PointOnScreen(parseNexusX(1050), parseNexusY(130)), res));
        walls.add(new WallView(new PointOnScreen(parseNexusX(300), parseNexusY(130)), new PointOnScreen(parseNexusX(400), parseNexusY(190)), res));
        walls.add(new WallView(new PointOnScreen(parseNexusX(1160), parseNexusY(550)), new PointOnScreen(parseNexusX(1280), parseNexusY(590)), res));
        walls.add(new WallView(new PointOnScreen(parseNexusX(1160), parseNexusY(450)), new PointOnScreen(parseNexusX(1280), parseNexusY(475)), res));

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

        broccolis.add(new Broccoli(parseNexusX(20), parseNexusY(50), res));
        broccolis.add(new Broccoli(parseNexusX(500), parseNexusY(100), res));
        broccolis.add(new Broccoli(parseNexusX(1200), parseNexusY(500), res));
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        walls.get(6).move((int) (Math.sin(val) * 5), 0);
        val += 0.03;

        super.draw(canvas, paint);
    }
}
