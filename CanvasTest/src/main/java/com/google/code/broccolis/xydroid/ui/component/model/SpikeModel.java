package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;

public class SpikeModel
{
    private Path path;
    private int color;

    public SpikeModel(Path path, int color){

        this.path = path;
        this.color = color;
    }

    public int getColor()
    {
        return  color;
    }

    public Path getPath()
    {
        return path;
    }
}
