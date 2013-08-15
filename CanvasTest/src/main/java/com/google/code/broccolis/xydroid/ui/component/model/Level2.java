package com.google.code.broccolis.xydroid.ui.component.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.component.view.SpikeView;
import com.google.code.broccolis.xydroid.ui.component.view.WallView;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;
import com.google.code.broccolis.xydroid.util.Broccoli;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import java.util.ArrayList;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_WIDTH;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusX;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusY;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToFloat;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToFloat;


public class Level2 extends Level
{
    private static final String NAME = "Level2 ";
    private static final int levelNum = 2;

    public Level2(Resources res)
    {
        super();

        yBitmap = BitmapFactory.decodeResource(res, R.drawable.y);
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
    public void draw(Canvas canvas, Paint paint)
    {

        walls.get(5).move(0, (int) (Math.sin(val) * 5));
        val -= 0.03;

        for (WallView w : walls)
        {
            w.draw(canvas, paint);
        }
        for (SpikeView s : spikes)
        {
            s.draw(canvas, paint);
        }

        for (Broccoli br : broccolis)
        {
            if (br.isVisible())
            {
                br.draw(canvas, paint);
            }
        }
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, parseNexusY(300), parseNexusX(20), parseNexusY(400), paint);
        paint.setColor(Color.WHITE);
        canvas.drawLine(parseNexusX(3), parseNexusY(325), parseNexusX(17), parseNexusY(350), paint);

        canvas.drawLine(parseNexusX(3), parseNexusY(375), parseNexusX(17), parseNexusY(350), paint);




        canvas.drawBitmap(yBitmap, pointY.x, pointY.y, paint);
        paint.setTextSize(25);
        canvas.drawText("Broccolis: " + score, parseNexusX(1110), parseNexusY(50), paint);

    }

    public ArrayList<WallView> getWalls()
    {
        return walls;
    }

    @Override
    public boolean collidesWith(Player player, Point movingAmount)
    {
        if (goalReached(player, movingAmount))
        {
            Log.i(TAG, "complete");
        }

        for (WallView w : walls)
        {
            if (w.collidesWith(player, movingAmount))
            {
                return true;
            }
        }
        for (SpikeView s : spikes)
        {
            if (s.collidesWith(player, movingAmount))
            {
                player.setY(player.getStartY());
                player.setX(player.getStartX());
                score = 0;
                for (Broccoli br : broccolis)
                {
                    br.setVisibility(true);
                }

                return true;
            }
        }

        for (Broccoli br : broccolis)
        {
            if (br.contains(player, movingAmount) && br.isVisible())
            {
                br.setVisibility(false);
                score++;
                return true;
            }

        }
        return false;
    }



}
