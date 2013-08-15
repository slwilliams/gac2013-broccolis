package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Point;
import android.util.Log;

import com.google.code.broccolis.xydroid.util.Player;

public class WallModel
{
    private Point top;
    private Point bottom;
    private int color;

    public WallModel(Point top, Point bottom, int color)
    {
        this.top = top;
        this.bottom = bottom;
        this.color = color;
    }

    public void move(int x, int y)
    {
        top.x += x;
        bottom.x += x;
        top.y += y;
        bottom.y += y;
    }

    public int getYMin()
    {
        return top.y;
    }

    public int getXMin()
    {
        return top.x;
    }

    public int getYMax()
    {
        return bottom.y;
    }

    public int getXMax()
    {
        return bottom.x;
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
