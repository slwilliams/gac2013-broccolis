package com.google.code.broccolis.xydroid.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.interfaces.Drawable;

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToInt;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToInt;

public class Broccoli implements Drawable
{
    private float x;
    private float y;
    private boolean visible = true;
    private Bitmap broccoliImage;

    public Broccoli(float x, float y, Resources res)
    {
        this.x = x;
        this.y = y;
        broccoliImage = BitmapFactory.decodeResource(res, R.drawable.broccoli);
    }

    public int getXTop()
    {
        return parseXToInt(x);
    }

    public int getYTop()
    {
        return parseYToInt(y);
    }

    public int getXBottom()
    {
        return parseXToInt(x) + broccoliImage.getWidth();
    }

    public int getYBottom()
    {
        return parseYToInt(y) + broccoliImage.getHeight();
    }

    public void setVisibility(boolean visibility)
    {
        visible = visibility;
    }

    public boolean contains(Player player, Point movingAmount)
    {

        int x = player.getX();
        int y = player.getY();

        int width = player.getWidth();
        int height = player.getHeight();
        Point pTop = new Point(x + movingAmount.x, y + movingAmount.y);
        Point pBottom = new Point(x + width + movingAmount.x, y + height + movingAmount.y);

        return pBottom.y >= getYTop() && pTop.y <= getYBottom() && pBottom.x >= getXTop() && pTop.x <= getXBottom();
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawBitmap(broccoliImage, parseXToInt(x), parseYToInt(y), paint);
    }

    public boolean isVisible()
    {
        return visible;
    }
}
