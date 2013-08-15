package com.google.code.broccolis.xydroid.ui.interfaces;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.component.view.SpikeView;
import com.google.code.broccolis.xydroid.ui.component.view.WallView;
import com.google.code.broccolis.xydroid.util.Broccoli;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import java.util.ArrayList;

import static android.graphics.Bitmap.createScaledBitmap;
import static android.graphics.BitmapFactory.decodeResource;
import static android.graphics.Color.BLACK;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusX;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusY;

public abstract class Level
{
    protected ArrayList<WallView> walls = new ArrayList<WallView>();
    protected ArrayList<SpikeView> spikes = new ArrayList<SpikeView>();
    protected ArrayList<Broccoli> broccolis = new ArrayList<Broccoli>(3);
    protected Bitmap yBitmap;
    protected Point pointY;
    protected double val = 0.1;
    protected int score = 0;

    public Level(Resources res)
    {
        walls.add(new WallView(new PointOnScreen(0, 0), new PointOnScreen(0, parseNexusY(700))));
        walls.add(new WallView(new PointOnScreen(0, 0), new PointOnScreen(parseNexusX(1270), 0)));
        walls.add(new WallView(new PointOnScreen(parseNexusX(1270), 0), new PointOnScreen(parseNexusX(1270), parseNexusY(700))));
        walls.add(new WallView(new PointOnScreen(0, parseNexusY(700)), new PointOnScreen(parseNexusX(1270), parseNexusY(700))));

        yBitmap = decodeResource(res, R.drawable.y);
        yBitmap = createScaledBitmap(yBitmap, parseNexusX(yBitmap.getWidth()), parseNexusY(yBitmap.getHeight()), true);
    }

    public final boolean goalReached(Player player, Point movingAmount)
    {
        int x = player.getX();
        int y = player.getY();

        int width = player.getWidth();
        int height = player.getHeight();
        Point pTop = new Point(x + movingAmount.x, y + movingAmount.y);
        Point pBottom = new Point(x + width + movingAmount.x, y + height + movingAmount.y);

        return pBottom.y >= getGoalTopY() && pTop.y <= getGoalBottomY() && pBottom.x >= getGoalTopX() && pTop.x <= getGoalBottomX();
    }

    private int getGoalBottomX()
    {
        return pointY.x + yBitmap.getWidth();
    }

    private int getGoalBottomY()
    {
        return pointY.y + yBitmap.getHeight();
    }

    private int getGoalTopX()
    {
        return pointY.x;
    }

    private int getGoalTopY()
    {
        return pointY.y;
    }

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

    public void draw(Canvas canvas, Paint paint)
    {
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
        paint.setColor(BLACK);
        canvas.drawRect(0, parseNexusY(300), parseNexusX(20), parseNexusY(400), paint);
        paint.setColor(Color.WHITE);
        canvas.drawLine(parseNexusX(3), parseNexusY(325), parseNexusX(17), parseNexusY(350), paint);

        canvas.drawLine(parseNexusX(3), parseNexusY(375), parseNexusX(17), parseNexusY(350), paint);


        canvas.drawBitmap(yBitmap, pointY.x, pointY.y, paint);
        paint.setColor(BLACK);
        paint.setTextSize(parseNexusY(25));
        canvas.drawText("Broccolis: " + score, parseNexusX(1110), parseNexusY(50), paint);
    }
}
