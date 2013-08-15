package com.google.code.broccolis.xydroid.ui.component.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.ui.component.model.WallModel;
import com.google.code.broccolis.xydroid.ui.interfaces.Terrain;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToFloat;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToFloat;

public class WallView implements Terrain
{
    private WallModel model;

    public WallView(PointOnScreen top, PointOnScreen bottom, int color)
    {
        model = new WallModel(top, bottom, color);
    }

    public void move(int x, int y)
    {
        model.move(parseXToFloat(x), parseYToFloat(y));
    }

    @Override
    public boolean collidesWith(Player player, Point movingAmount)
    {
        return model.contains(player, movingAmount);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(model.getColor());
        canvas.drawRect(model.getXMin(), model.getYMin(), model.getXMax(), model.getYMax(), paint);
    }
}
