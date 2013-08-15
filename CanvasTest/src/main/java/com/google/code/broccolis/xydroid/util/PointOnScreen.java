package com.google.code.broccolis.xydroid.util;

import android.graphics.PointF;
import android.util.Log;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToInt;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToInt;

public class PointOnScreen extends PointF
{
    private static final String NAME = "Point ";

    public PointOnScreen(float x, float y) throws IllegalArgumentException
    {
        super(x, y);

        if (x > 1 || y > 1 || x < 0 || y < 0)
        {
            Log.e(TAG,NAME+"at least one of arguments out of bound",new IllegalArgumentException(x+" "+y));
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
