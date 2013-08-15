package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Point;

import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

public class WallModel
{
    private PointOnScreen top;
    private PointOnScreen bottom;
    private int color;

    public WallModel(PointOnScreen top, PointOnScreen bottom, int color)
    {
        this.top = top;
        this.bottom = bottom;
        this.color = color;
    }

    public void move(float x, float y)
    {
        top.x += x;
        bottom.x += x;
        top.y += y;
        bottom.y += y;
    }

    public int getYMin()
    {
        return top.getScreenY();
    }

    public int getXMin()
    {
        return top.getScreenX();
    }

    public int getYMax()
    {
        return bottom.getScreenY();
    }

    public int getXMax()
    {
        return bottom.getScreenX();
    }

    public int getColor()
    {
        return color;
    }

    public boolean contains(Player player, Point moveAmount)
    {
        int x = player.getX();
        int y = player.getY();

        int width = player.getWidth();
        int height = player.getHeight();
        Point pTop = new Point(x + moveAmount.x, y + moveAmount.y);
        Point pBottom = new Point(x + width + moveAmount.x, y + height + moveAmount.y);

        return pBottom.y >= getYMin() && pTop.y <= getYMax() && pBottom.x >= getXMin() && pTop.x <= getXMax();
    }
}
