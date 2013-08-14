package com.google.code.broccolis.xydroid.ui.interfaces;

import android.graphics.Point;

import com.google.code.broccolis.xydroid.util.Player;

public interface Colliding
{
    public boolean collidesWith(Player player, Point movingAmount);
}
