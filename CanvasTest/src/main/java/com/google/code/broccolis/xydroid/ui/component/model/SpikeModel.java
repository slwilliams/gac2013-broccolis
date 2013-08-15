package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Path;

public class SpikeModel
{
    private Path path;
    private int color;

    public SpikeModel(Path path, int color)
    {

        this.path = path;
        this.color = color;
    }

    public int getColor()
    {
        return color;
    }

    public Path getPath()
    {
        return path;
    }
}
