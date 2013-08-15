package com.google.code.broccolis.xydroid.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.R;

/**
 * Created by demouser on 8/15/13.
 */
public class Broccoli {

    private int x;
    private int y;
    private Bitmap broccolimage;
    private boolean visible = true;

    public Broccoli(int x, int y, Resources res)
    {
        this.x = x;
        this.y = y;
        broccolimage = BitmapFactory.decodeResource(res, R.drawable.broccoli);
    }

    public int getXTop() {
        return x;
    }

    public int getYTop() {
        return y;
    }
    public int getXBottom()
    {
        return x+broccolimage.getWidth();
    }

    public int getYBottom()
    {
        return y+broccolimage.getHeight();
    }
    public void setVisibility(boolean visibility)
    {
        visible = visibility;
    }

    public boolean contains(Player player, Point movingAmount){

        int x = player.getX();
        int y = player.getY();

        int width = player.getWidth();
        int height = player.getHeight();
        Point pTop = new Point(x + movingAmount.x, y + movingAmount.y);
        Point pBottom = new Point(x + width + movingAmount.x, y + height + movingAmount.y);

        return pBottom.y >= getYTop() && pTop.y <= getYBottom() && pBottom.x >= getXTop() && pTop.x <= getXBottom();
    }

    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawBitmap(broccolimage, x, y, paint);
    }

    public boolean isVisible()
    {
        return visible;
    }
}
