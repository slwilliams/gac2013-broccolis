package com.google.code.broccolis.xydroid.ui.component.model;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.component.view.SpikeView;
import com.google.code.broccolis.xydroid.ui.component.view.WallView;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;
import com.google.code.broccolis.xydroid.util.Broccoli;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import java.util.ArrayList;

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_WIDTH;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToInt;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToInt;

public class Level1 implements Level
{

    private ArrayList<WallView> walls = new ArrayList<WallView>();
    private ArrayList<SpikeView> spikes = new ArrayList<SpikeView>();
    private ArrayList<Broccoli> broccolis = new ArrayList<Broccoli>(3);
    private double val = 0.1;
    private int score = 0;
    private Point pointY;
    private Bitmap yBitmap;

    public Level1(Resources res)
    {

        walls.add(new WallView(new PointOnScreen(0f / NEXUS_WIDTH, 100f / NEXUS_HEIGHT), new PointOnScreen(100f / NEXUS_WIDTH, 125f / NEXUS_HEIGHT), Color.GREEN));
        walls.add(new WallView(new PointOnScreen(500f / NEXUS_WIDTH, 400f / NEXUS_HEIGHT), new PointOnScreen(800f / NEXUS_WIDTH, 450f / NEXUS_HEIGHT), Color.BLUE));
        walls.add(new WallView(new PointOnScreen(150f / NEXUS_WIDTH, 500f / NEXUS_HEIGHT), new PointOnScreen(300f / NEXUS_WIDTH, 525f / NEXUS_HEIGHT), Color.GREEN));
        walls.add(new WallView(new PointOnScreen(930f / NEXUS_WIDTH, 100f / NEXUS_HEIGHT), new PointOnScreen(1050f / NEXUS_WIDTH, 130f / NEXUS_HEIGHT), Color.YELLOW));
        walls.add(new WallView(new PointOnScreen(300f / NEXUS_WIDTH, 130f / NEXUS_HEIGHT), new PointOnScreen(400f / NEXUS_WIDTH, 190f / NEXUS_HEIGHT), Color.BLUE));
        walls.add(new WallView(new PointOnScreen(1160f / NEXUS_WIDTH, 550f / NEXUS_HEIGHT), new PointOnScreen(1280f / NEXUS_WIDTH, 590f / NEXUS_HEIGHT), Color.BLUE));
        walls.add(new WallView(new PointOnScreen(1160f / NEXUS_WIDTH, 450f / NEXUS_HEIGHT), new PointOnScreen(1280f / NEXUS_WIDTH, 475f / NEXUS_HEIGHT), Color.BLUE));

        yBitmap = BitmapFactory.decodeResource(res, R.drawable.y);
        pointY = new Point(960, 50);

        for (int i = 945; i < 1020; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(i, 130),
                    new Point(i + 30, 130),
                    new Point(i + 15, 200)
            }, Color.BLACK));
        }
        for (int i = 500; i < 790; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(i, 450),
                    new Point(i + 30, 450),
                    new Point(i + 15, 480)
            }, Color.BLACK));
        }
        for (int i = 305; i < 395; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(i, 130),
                    new Point(i + 30, 130),
                    new Point(i + 15, 100)
            }, Color.BLACK));
        }

        for (int i = 305; i < 395; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(i, 190),
                    new Point(i + 30, 190),
                    new Point(i + 15, 220)
            }, Color.BLACK));
        }

        for (int i = 550; i < 590; i += 20)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(1160, i),
                    new Point(1160, i + 20),
                    new Point(1100, i + 10)
            }, Color.BLACK));
        }

        //bounds
        walls.add(new WallView(new PointOnScreen(0, 0), new PointOnScreen(0, 700f / NEXUS_HEIGHT), Color.BLACK));
        walls.add(new WallView(new PointOnScreen(0, 0), new PointOnScreen(1270f / NEXUS_WIDTH, 0), Color.BLACK));
        walls.add(new WallView(new PointOnScreen(1270f / NEXUS_WIDTH, 0), new PointOnScreen(1270f / NEXUS_WIDTH, 700f / NEXUS_HEIGHT), Color.BLACK));
        walls.add(new WallView(new PointOnScreen(0, 700f / NEXUS_HEIGHT), new PointOnScreen(1270f / NEXUS_WIDTH, 700f / NEXUS_HEIGHT), Color.BLACK));

        broccolis.add(new Broccoli(20f / NEXUS_WIDTH, 50f / NEXUS_HEIGHT, res));
        broccolis.add(new Broccoli(500f / NEXUS_WIDTH, 100f / NEXUS_HEIGHT, res));
        broccolis.add(new Broccoli(1200f / NEXUS_WIDTH, 500f / NEXUS_HEIGHT, res));
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        walls.get(2).move((int) (Math.sin(val) * 5), 0);
        val += 0.03;

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

        canvas.drawBitmap(yBitmap, pointY.x, pointY.y, paint);
        paint.setTextSize(25);
        canvas.drawText("Broccolis: " + score, 1110, 50, paint);
    }

    public ArrayList<WallView> getWalls()
    {
        return walls;
    }

    @Override
    public boolean collidesWith(Player player, Point moveAmount)
    {
        for (WallView w : walls)
        {
            if (w.collidesWith(player, moveAmount))
            {
                return true;
            }
        }
        for (SpikeView s : spikes)
        {
            if (s.collidesWith(player, moveAmount))
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
            if (br.contains(player, moveAmount) && br.isVisible())
            {
                br.setVisibility(false);
                score++;
                return true;
            }

        }

        return false;
    }
}
