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

import java.util.ArrayList;

public class Level1 implements Level {

    ArrayList<WallView> walls = new ArrayList<WallView>();
    double val = 0.1;
    Bitmap broccoli;

    public Level1(Resources res) {
        walls.add(new WallView(new Point(0, 500), new Point(1300, 525), Color.RED));
        walls.add(new WallView(new Point(500, 400), new Point(1300, 425), Color.BLUE));
        walls.add(new WallView(new Point(0, 300), new Point(300, 325), Color.GREEN));
        walls.add(new WallView(new Point(700, 200), new Point(1300, 225), Color.YELLOW));


        broccoli = BitmapFactory.decodeResource(res, R.drawable.broccoli);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        walls.get(2).move((int) (Math.sin(val) * 5), 0);
        val += 0.03;

        for (WallView w : walls)
        {
            w.draw(canvas, paint);
        }

        canvas.drawBitmap(broccoli, 700, 300, paint);
    }

    @Override
    public boolean collidesWith(Player player, Point moveAmount) {
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
