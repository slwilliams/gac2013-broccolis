package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Color;
import android.graphics.Point;

/**
 * Created by demouser on 8/14/13.
 */
public class SpikeModel {

    private Point[] points;
    private int color;

    public SpikeModel(Point[] points, int color){

        this.points = points;
        this.color = color;
    }

    public int getColor(){
        return  color;
    }
}
