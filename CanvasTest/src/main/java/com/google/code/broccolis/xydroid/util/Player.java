package com.google.code.broccolis.xydroid.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.code.broccolis.xydroid.R;
import com.google.code.broccolis.xydroid.ui.interfaces.Drawable;

import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToInt;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToInt;

public class Player implements Drawable
{
    private int playerX = 0;
    private int playerY = 0;
    private int color = Color.BLACK;
    private Bitmap playerImage;
    private int startX;
    private int startY;

    public Player(float initialX, float initialY, int color, Resources res)
    {
        this.playerX = parseXToInt(initialX);
        this.playerY = parseYToInt(initialY);
        startX = parseXToInt(initialX);
        startY = parseYToInt(initialY);
        this.color = color;
        this.playerImage = BitmapFactory.decodeResource(res, R.drawable.x);
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

    public int getStartX()
    {
        return startX;
    }

    public int getStartY()
    {
        return startY;
    }

    public void setX(int x)
    {
        playerX = x;
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
        paint.setColor(color);
        canvas.drawBitmap(playerImage, playerX, playerY, paint);
    }
}
