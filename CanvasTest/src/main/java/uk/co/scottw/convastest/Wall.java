package uk.co.scottw.convastest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Wall
{
    private Point top;
    private Point bottom;
    private int color;


    public Wall(Point top, Point bottom, int color)
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

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(color);
        canvas.drawRect(top.x, top.y, bottom.x, bottom.y, paint);

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



  /*  public boolean collision(Player player)
    {
        return (player.getX() <= bottom.getX() && player.getX() >= top.getX() && player.getY() <= bottom.getY() && player.getY() >= top.getY());
    }*/
}
