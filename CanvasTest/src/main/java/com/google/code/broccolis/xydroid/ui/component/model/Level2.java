package com.google.code.broccolis.xydroid.ui.component.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.widget.Toast;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.component.view.SpikeView;
import com.google.code.broccolis.xydroid.ui.component.view.WallView;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;
import com.google.code.broccolis.xydroid.util.Broccoli;
import com.google.code.broccolis.xydroid.util.MultipleDeviceSupport;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import java.util.ArrayList;

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_WIDTH;


public class Level2 implements Level
{

    private ArrayList<WallView> walls = new ArrayList<WallView>();
    private ArrayList<SpikeView> spikes = new ArrayList<SpikeView>();
    private ArrayList<Broccoli> broccolis = new ArrayList<Broccoli>(3);

    private double val = 0.1;
    private int score = 0;

    Bitmap b;
    private Point pointY;

    public Level2(Resources res)
    {

        b = BitmapFactory.decodeResource(res, R.drawable.y);
        pointY = new Point(20, 50);

        walls.add(new WallView(new PointOnScreen(0f/NEXUS_WIDTH, 100f/NEXUS_HEIGHT), new PointOnScreen(100f/NEXUS_WIDTH, 125f/NEXUS_HEIGHT), Color.GREEN));
        walls.add(new WallView(new PointOnScreen(1000f/NEXUS_WIDTH, 100f/NEXUS_HEIGHT), new PointOnScreen(1100f/NEXUS_WIDTH, 160f/NEXUS_HEIGHT), Color.GREEN));
        walls.add(new WallView(new PointOnScreen(300f/NEXUS_WIDTH, 300f/NEXUS_HEIGHT), new PointOnScreen(850f/NEXUS_WIDTH, 355f/NEXUS_HEIGHT), Color.GREEN));
        walls.add(new WallView(new PointOnScreen(300f/NEXUS_WIDTH, 500f/NEXUS_HEIGHT), new PointOnScreen(400f/NEXUS_WIDTH, 555f/NEXUS_HEIGHT), Color.GREEN));
        walls.add(new WallView(new PointOnScreen(600f/NEXUS_WIDTH, 500f/NEXUS_HEIGHT), new PointOnScreen(800f/NEXUS_WIDTH, 555f/NEXUS_HEIGHT), Color.GREEN));
        walls.add(new WallView(new PointOnScreen(1000f/NEXUS_WIDTH, 450f/NEXUS_HEIGHT), new PointOnScreen(1100f/NEXUS_WIDTH, 475f/NEXUS_HEIGHT), Color.GREEN));

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
        walls.add(new WallView(new PointOnScreen(0, 0), new PointOnScreen(0, 700f/NEXUS_HEIGHT), Color.BLACK));
        walls.add(new WallView(new PointOnScreen(0, 0), new PointOnScreen(1270f/NEXUS_WIDTH, 0), Color.BLACK));
        walls.add(new WallView(new PointOnScreen(1270f/NEXUS_WIDTH, 0), new PointOnScreen(1270f/NEXUS_WIDTH, 700f/NEXUS_HEIGHT), Color.BLACK));
        walls.add(new WallView(new PointOnScreen(0, 700f/NEXUS_HEIGHT), new PointOnScreen(1270f/NEXUS_WIDTH, 700f/NEXUS_HEIGHT), Color.BLACK));

        broccolis.add(new Broccoli(1020, 50, res));
        broccolis.add(new Broccoli(330, 250, res));
        broccolis.add(new Broccoli(550, 400, res));


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
            if(br.isVisible())
                br.draw(canvas, paint);
        }

        canvas.drawBitmap(b, pointY.x, pointY.y, paint);
        paint.setTextSize(20);
        canvas.drawText("Broccolis: "+score, 1110, 50, paint);

    }

    public ArrayList<WallView> getWalls()
    {
        return walls;
    }

    @Override
    public boolean collidesWith(Player player, Point movingAmount)
    {
        if(goalReached(player, movingAmount))
        {
            Log.i("LEVEL COMPLETE", "LEVEL COMPLETE");
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

    public boolean goalReached(Player player, Point movingAmount)
    {
        int x = player.getX();
        int y = player.getY();

        int width = player.getWidth();
        int height = player.getHeight();
        Point pTop = new Point(x + movingAmount.x, y + movingAmount.y);
        Point pBottom = new Point(x + width + movingAmount.x, y + height + movingAmount.y);

        return pBottom.y >= getGoalTopY() && pTop.y <= getGoalBottomY() && pBottom.x >= getGoalTopX() && pTop.x <= getGoalBottomX();

    }

    public int getGoalBottomX()
    {
        return pointY.x+b.getWidth();
    }

    public int getGoalBottomY()
    {
        return pointY.y+b.getHeight();
    }
    public int getGoalTopX() {
        return pointY.x;
    }

    public int getGoalTopY() {
        return pointY.y;
    }
}
