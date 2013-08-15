package com.google.code.broccolis.xydroid.ui.component.model;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.component.view.WallView;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import java.util.ArrayList;

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.NEXUS_WIDTH;

public class Level1 implements Level
{

    ArrayList<WallView> walls = new ArrayList<WallView>();
    double val = 0.1;
    Bitmap broccoli;

    public Level1(Resources res)
    {
        walls.add(new WallView(new PointOnScreen(0, 650f / NEXUS_HEIGHT), new PointOnScreen(1300f / NEXUS_WIDTH, 675f / NEXUS_HEIGHT), Color.RED));
        walls.add(new WallView(new PointOnScreen(500f / NEXUS_WIDTH, 400f / NEXUS_HEIGHT), new PointOnScreen(1300f / NEXUS_WIDTH, 425f / NEXUS_HEIGHT), Color.BLUE));
        walls.add(new WallView(new PointOnScreen(0, 500f / NEXUS_HEIGHT), new PointOnScreen(300f / NEXUS_WIDTH, 525f / NEXUS_HEIGHT), Color.GREEN));
        walls.add(new WallView(new PointOnScreen(700f / NEXUS_WIDTH, 200f / NEXUS_HEIGHT), new PointOnScreen(1300f / NEXUS_WIDTH, 225f / NEXUS_HEIGHT), Color.YELLOW));

        broccoli = BitmapFactory.decodeResource(res, R.drawable.broccoli);
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

        canvas.drawBitmap(broccoli, 700, 300, paint);
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
        return false;
    }
}
