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

/**
 * Created by demouser on 8/14/13.
 */
public class Level2 implements Level {

    ArrayList<WallView> walls = new ArrayList<WallView>();
    Bitmap b;
    Bitmap broccoli;

    public Level2(Resources res){

        b = BitmapFactory.decodeResource(res, R.drawable.y);
        broccoli = BitmapFactory.decodeResource(res, R.drawable.broccoli);

        walls.add(new WallView(new Point(0, 100), new Point(100,125), Color.GREEN));
        walls.add(new WallView(new Point(1000, 100), new Point(1100,160), Color.GREEN));
        walls.add(new WallView(new Point(300, 300), new Point(850,355), Color.GREEN));
        walls.add(new WallView(new Point(300, 500), new Point(400,555), Color.GREEN));
        walls.add(new WallView(new Point(600, 500), new Point(800,555), Color.GREEN));
        walls.add(new WallView(new Point(1000, 450), new Point(1100,475), Color.GREEN));

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        for (WallView w : walls)
        {
            w.draw(canvas, paint);
        }
        canvas.drawBitmap(b, 20, 50, paint);
        canvas.drawBitmap(broccoli, 1020, 50, paint);
        canvas.drawBitmap(broccoli, 330, 250, paint);
        canvas.drawBitmap(broccoli, 550, 400, paint);
    }
    public ArrayList<WallView> getWalls(){
        return walls;
    }

    @Override
    public boolean collidesWith(Player player, Point movingAmount) {
        for (WallView w : walls)
        {
            if (w.collidesWith(player, movingAmount))
            {
                return true;
            }
        }
        return false;
    }
}
