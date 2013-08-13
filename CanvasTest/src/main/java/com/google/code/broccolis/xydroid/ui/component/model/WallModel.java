package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Point;

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

    public boolean contains(int x, int y)
    {
        return y + 5 >= getYMin() && x + 5 > getXMin() && y - 5 <= getYMax() && x - 5 <= getXMax();
    }



  /*  public boolean collision(Player player)
    {
        return (player.getX() <= bottom.getX() && player.getX() >= top.getX() && player.getY() <= bottom.getY() && player.getY() >= top.getY());
    }*/
}
