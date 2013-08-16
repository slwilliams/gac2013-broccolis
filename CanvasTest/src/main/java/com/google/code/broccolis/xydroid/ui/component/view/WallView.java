package com.google.code.broccolis.xydroid.ui.component.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.component.model.WallModel;
import com.google.code.broccolis.xydroid.ui.interfaces.Terrain;
import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import static android.graphics.Bitmap.createScaledBitmap;
import static android.graphics.BitmapFactory.decodeResource;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToFloat;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToFloat;
import static java.lang.Math.abs;

public class WallView implements Terrain
{
    private boolean visible = false;
    private Bitmap textureBitmap;
    private WallModel model;

    public WallView(PointOnScreen top, PointOnScreen bottom)
    {
        model = new WallModel(top, bottom);
    }

    public WallView(PointOnScreen top, PointOnScreen bottom, Resources resources)
    {
        this(top, bottom);

        visible = true;

        textureBitmap = decodeResource(resources, R.drawable.grass);
        textureBitmap = createScaledBitmap(textureBitmap,
                abs(model.getXMax() - model.getXMin()), abs(model.getYMax() - model.getYMin()), true);
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
        if (visible)
        {
            canvas.drawBitmap(textureBitmap, model.getXMin(), model.getYMin(), paint);
        }
//        paint.setColor(GRASS_COLOR);
//        canvas.drawRect(model.getXMin(), model.getYMin(), model.getXMax(), model.getYMax() - (model.getYMax() - model.getYMin()) * 3 / 4, paint);
//        paint.setColor(GROUND_COLOR);
//        canvas.drawRect(model.getXMin(), model.getYMax() - (model.getYMax() - model.getYMin()) * 3 / 4, model.getXMax(), model.getYMax(), paint);
    }
}
