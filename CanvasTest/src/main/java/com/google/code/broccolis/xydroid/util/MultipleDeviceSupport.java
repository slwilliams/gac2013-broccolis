package com.google.code.broccolis.xydroid.util;

import android.util.Log;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;

public class MultipleDeviceSupport
{
    public static final float NEXUS_WIDTH = 1280;
    public static final float NEXUS_HEIGHT = 736;
    private static final String NAME = "MultipleDeviceSupport ";
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static float parseXToFloat(int x) throws IllegalArgumentException
    {
        if (x < 0 || x > 1)
        {
            IllegalArgumentException exception = new IllegalArgumentException("Argument out of bounds");
            Log.e(TAG, NAME, exception);
        }
        return ((float) x) / ((float) SCREEN_WIDTH);
    }

    public static float parseYToFloat(int y) throws IllegalArgumentException
    {
        if (y < 0 || y > 1)
        {
            IllegalArgumentException exception = new IllegalArgumentException("Argument out of bounds");
            Log.e(TAG, NAME, exception);
        }
        return ((float) y) / ((float) SCREEN_HEIGHT);
    }

    public static int parseXToInt(float x)
    {
        return (int) (x * SCREEN_WIDTH);
    }

    public static int parseYToInt(float y)
    {
        return (int) (y * SCREEN_HEIGHT);
    }
}
