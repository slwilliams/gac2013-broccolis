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
        return ((float) x) / ((float) SCREEN_WIDTH);
    }

    public static float parseYToFloat(int y) throws IllegalArgumentException
    {
        return ((float) y) / ((float) SCREEN_HEIGHT);
    }

    public static int parseXToInt(float x)
    {
        if (x < 0 || x > 1)
        {
            IllegalArgumentException exception = new IllegalArgumentException("Argument out of bounds x=" + x);
            Log.e(TAG, NAME, exception);
        }
        return (int) (x * SCREEN_WIDTH);
    }

    public static int parseYToInt(float y)
    {
        if (y < 0 || y > 1)
        {
            IllegalArgumentException exception = new IllegalArgumentException("Argument out of bounds y=" + y);
            Log.e(TAG, NAME, exception);
        }
        return (int) (y * SCREEN_HEIGHT);
    }

    public static int parseNexusX(int x)
    {
        return parseXToInt(((float) x) / NEXUS_WIDTH);
    }

    public static int parseNexusY(int y)
    {
        return parseYToInt(((float) y) / NEXUS_HEIGHT);
    }

}
