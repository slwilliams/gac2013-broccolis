package com.google.code.broccolis.xydroid.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.interfaces.Drawable;

import static android.graphics.Bitmap.createScaledBitmap;
import static android.graphics.BitmapFactory.decodeResource;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusX;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseNexusY;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToInt;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToInt;

public class Player implements Drawable
{
    private int playerX = 0;
    private int playerY = 0;
    private Bitmap playerImage;
    private int startX;
    private int startY;

    public Player(float initialX, float initialY, Resources res)
    {
        playerX = parseXToInt(initialX);
        playerY = parseYToInt(initialY);
        startX = parseXToInt(initialX);
        startY = parseYToInt(initialY);

        playerImage = decodeResource(res, R.drawable.x);
        playerImage = createScaledBitmap(playerImage, parseNexusX(playerImage.getWidth()), parseNexusY(playerImage.getHeight()), true);
    }

    public int getWidth()
    {
        return playerImage.getWidth();
    }

    public int getHeight()
    {
        return playerImage.getHeight();
    }

    public int getX()
    {
        return playerX;
    }

    public void setX(int x)
    {
        playerX = x;
    }

    public int getStartX()
    {
        return startX;
    }

    public int getStartY()
    {
        return startY;
    }

    public int getY()
    {
        return playerY;
    }

    public void setY(int y)
    {
        playerY = y;
    }

    public void move(int xAmt, int yAmt)
    {
        playerX += xAmt;
        playerY += yAmt;
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawBitmap(playerImage, playerX, playerY, paint);
    }
}
