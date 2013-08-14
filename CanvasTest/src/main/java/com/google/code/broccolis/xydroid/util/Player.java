package com.google.code.broccolis.xydroid.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.code.broccolis.xydroid.R;

public class Player
{
    private int playerX = 0;
    private int playerY = 0;
    private int color = Color.BLACK;
    Bitmap b;

    public Player(int initialX, int initialY, int color, Resources res)
    {
        this.playerX = initialX;
        this.playerY = initialY;
        this.color = color;
        b = BitmapFactory.decodeResource(res, R.drawable.x);
    }

    public int getX()
    {
        return playerX;
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

    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(color);
//        canvas.drawLine(playerX - 10, playerY - 10, playerX + 10, playerY + 10, paint);
//        canvas.drawLine(playerX - 10, playerY + 10, playerX + 10, playerY - 10, paint);
        canvas.drawBitmap(b, playerX, playerY - 20, paint);
    }
}
