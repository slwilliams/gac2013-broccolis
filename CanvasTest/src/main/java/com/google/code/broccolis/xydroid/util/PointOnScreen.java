package com.google.code.broccolis.xydroid.util;

import android.graphics.PointF;
import android.util.Log;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.SCREEN_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.SCREEN_WIDTH;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToInt;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToInt;

public class PointOnScreen extends PointF
{
    public PointOnScreen(float x, float y) throws IllegalArgumentException
    {
        super(x, y);

        if (x > 1 || y > 1 || x < 0 || y < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    public int getScreenX()
    {
        return parseXToInt(x);
    }

    public int getScreenY()
    {
        return parseYToInt(y);
    }

}
