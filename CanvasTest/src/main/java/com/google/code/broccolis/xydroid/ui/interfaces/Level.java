package com.google.code.broccolis.xydroid.ui.interfaces;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Level
{
    public void draw(Canvas canvas, Paint paint);
    public boolean collidesWith(int playerX, int playerY);
}
