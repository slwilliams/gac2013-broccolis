package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;

import com.google.code.broccolis.xydroid.util.Player;

public class SpikeModel
{
    private Path path;
    private int color;
    private Region region;

    public SpikeModel(Path path, int color)
    {

        this.path = path;
        this.color = color;
        region = new Region();

        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        region.setPath(path, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
    }

    public int getColor()
    {
        return color;
    }

    public Path getPath()
    {
        return path;
    }

    public boolean contains(Player player, Point moveAmount)
    {

        int x = player.getX();
        int y = player.getY();

        int width = player.getWidth();
        int height = player.getHeight();
        Point pTop = new Point(x + moveAmount.x, y + moveAmount.y);
        Point pBottom = new Point(x + width + moveAmount.x, y + height + moveAmount.y);
        Point pMiddle = new Point(x + moveAmount.x + width / 2, y + height / 2 + moveAmount.y);

        return region.contains(pTop.x, pTop.y) || region.contains(pTop.x + width, pTop.y)
                || region.contains(x, pTop.y + height) || region.contains(pTop.x + width, pTop.y + height)
                || region.contains(pMiddle.x, pMiddle.y);
    }
}
