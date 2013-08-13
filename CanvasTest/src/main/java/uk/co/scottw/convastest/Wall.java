package uk.co.scottw.convastest;

import android.graphics.Point;

public class Wall
{
    private Point top;
    private Point bottom;


    public Wall(Point top, Point bottom)
    {
        this.top = top;
        this.bottom = bottom;
    }

  /*  public boolean collision(Player player)
    {
        return (player.getX() <= bottom.getX() && player.getX() >= top.getX() && player.getY() <= bottom.getY() && player.getY() >= top.getY());
    }*/
}
