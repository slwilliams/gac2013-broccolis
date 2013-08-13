package uk.co.scottw.convastest.ui.component.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import uk.co.scottw.convastest.util.Player;
import uk.co.scottw.convastest.ui.interfaces.Colliding;
import uk.co.scottw.convastest.ui.interfaces.Drawable;
import uk.co.scottw.convastest.ui.component.model.WallModel;

public class WallView implements Drawable, Colliding
{
    private WallModel model;

    public WallView(Point bottom, Point top, int color)
    {
        model = new WallModel(top, bottom, color);
    }

    public void move(int x, int y)
    {
        model.move(x,y);
    }

    public boolean collidesWith(Player player)
    {
        return collidesWith(player.getX(),player.getY());
    }

    @Override
    public boolean collidesWith(int x, int y)
    {
        return model.contains(x,y);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(model.getColor());
        canvas.drawRect(model.getXMin(), model.getYMin(), model.getXMax(), model.getYMax(), paint);

    }
}
