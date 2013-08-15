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
import com.google.code.broccolis.xydroid.util.Player;

import java.util.ArrayList;


public class Level2 implements Level
{

    ArrayList<WallView> walls = new ArrayList<WallView>();
    ArrayList<SpikeView> spikes = new ArrayList<SpikeView>();
    double val = 0.1;
    Bitmap b;
    Bitmap broccoli;

    public Level2(Resources res)
    {

        b = BitmapFactory.decodeResource(res, R.drawable.y);
        broccoli = BitmapFactory.decodeResource(res, R.drawable.broccoli);

        walls.add(new WallView(new Point(0, 100), new Point(100, 125), Color.GREEN));
        walls.add(new WallView(new Point(1000, 100), new Point(1100, 160), Color.GREEN));
        walls.add(new WallView(new Point(300, 300), new Point(850, 355), Color.GREEN));
        walls.add(new WallView(new Point(300, 500), new Point(400, 555), Color.GREEN));
        walls.add(new WallView(new Point(600, 500), new Point(800, 555), Color.GREEN));
        walls.add(new WallView(new Point(1000, 450), new Point(1100, 475), Color.GREEN));

        for (int i = 1005; i < 1090; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(i, 160),
                    new Point(i + 30, 160),
                    new Point(i + 15, 200)
            }, Color.BLACK));
        }

        for (int i = 310; i < 850; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(i, 355),
                    new Point(i + 30, 355),
                    new Point(i + 15, 420)
            }, Color.BLACK));
        }

        for (int i = 305; i < 390; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(i, 500),
                    new Point(i + 30, 500),
                    new Point(i + 15, 480)
            }, Color.BLACK));
        }

        for (int i = 610; i < 780; i += 30)
        {
            spikes.add(new SpikeView(new Point[]{
                    new Point(i, 500),
                    new Point(i + 30, 500),
                    new Point(i + 15, 480)
            }, Color.BLACK));
        }

        //bounds
        walls.add(new WallView(new Point(0, 0), new Point(0, 700), Color.BLACK));
        walls.add(new WallView(new Point(0, 0), new Point(1270, 0), Color.BLACK));
        walls.add(new WallView(new Point(1270, 0), new Point(1270, 700), Color.BLACK));
        walls.add(new WallView(new Point(0, 700), new Point(1270, 700), Color.BLACK));

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
        canvas.drawBitmap(b, 20, 50, paint);
        canvas.drawBitmap(broccoli, 1020, 50, paint);
        canvas.drawBitmap(broccoli, 330, 250, paint);
        canvas.drawBitmap(broccoli, 550, 400, paint);
    }

    public ArrayList<WallView> getWalls()
    {
        return walls;
    }

    @Override
    public boolean collidesWith(Player player, Point movingAmount)
    {
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
                return true;
            }
        }
        return false;
    }
}
