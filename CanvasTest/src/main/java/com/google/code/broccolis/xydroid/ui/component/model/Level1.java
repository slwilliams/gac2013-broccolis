package com.google.code.broccolis.xydroid.ui.component.model;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.ui.component.view.WallView;
import com.google.code.broccolis.xydroid.ui.interfaces.Level;

import java.util.ArrayList;

public class Level1 implements Level {

    ArrayList<WallView> walls = new ArrayList<WallView>();
    double val = 0.1;

    public Level1() {
        walls.add(new WallView(new Point(0, 500), new Point(1300, 525), Color.RED));
        walls.add(new WallView(new Point(500, 400), new Point(1300, 425), Color.BLUE));
        walls.add(new WallView(new Point(0, 300), new Point(300, 325), Color.GREEN));
        walls.add(new WallView(new Point(700, 200), new Point(1300, 225), Color.YELLOW));
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        walls.get(2).move((int) (Math.sin(val) * 5), 0);
        val += 0.03;

        for (WallView w : walls)
        {
            w.draw(canvas, paint);
        }
    }

    @Override
    public boolean collidesWith(int playerX, int playerY) {
        for (WallView w : walls)
        {
            if (w.collidesWith(playerX, playerY))
            {
                return true;
            }
        }
        return false;
    }
}
