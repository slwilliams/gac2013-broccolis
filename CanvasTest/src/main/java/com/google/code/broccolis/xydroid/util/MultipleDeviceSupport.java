package com.google.code.broccolis.xydroid.util;

public class MultipleDeviceSupport
{
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    public static float parseXToFloat(int x)
    {
        return ((float) x) / ((float) SCREEN_WIDTH);
    }

    public static float parseYToFloat(int y)
    {
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
