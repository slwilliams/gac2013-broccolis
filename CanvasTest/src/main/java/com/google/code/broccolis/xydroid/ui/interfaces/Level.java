package com.google.code.broccolis.xydroid.ui.interfaces;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.code.broccolis.xydroid.util.Player;

public interface Level
{
    public void draw(Canvas canvas, Paint paint);
    public boolean collidesWith(Player player, Point movingAmount);
}
