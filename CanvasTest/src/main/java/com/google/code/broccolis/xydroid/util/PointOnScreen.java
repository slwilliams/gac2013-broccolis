package com.google.code.broccolis.xydroid.util;

import android.graphics.PointF;
import android.util.Log;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;
import static com.google.code.broccolis.xydroid.util.DeviceDependantVariables.SCREEN_HEIGHT;
import static com.google.code.broccolis.xydroid.util.DeviceDependantVariables.SCREEN_WIDTH;

public class PointOnScreen extends PointF
{
    public PointOnScreen(float x, float y) throws IllegalArgumentException
    {
        super(x, y);
        Log.i(TAG,"x "+x+" y "+y);
        Log.i(TAG,"Sx "+SCREEN_WIDTH+" Sy "+SCREEN_WIDTH);

        if (x > 1 || y > 1 || x < 0 || y < 0)
        {
            throw new IllegalArgumentException();
        }
    }

    public int getScreenX()
    {
        return (int) (x * SCREEN_WIDTH);
    }

    public int getScreenY()
    {
        return (int) (y * SCREEN_HEIGHT);
    }

}
