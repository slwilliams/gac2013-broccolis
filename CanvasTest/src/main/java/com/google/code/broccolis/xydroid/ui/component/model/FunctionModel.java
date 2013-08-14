package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

import com.google.code.broccolis.xydroid.util.Constants;

import java.util.ArrayList;
import java.util.List;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class FunctionModel
{
    private static final int RADIUS = 5;
    private static final String NAME = "FunctionModel ";
    private Calculable calculable = null;
    private List<Point> points;
    private List<Point> allPoints;

    public FunctionModel(String function, Point initialCoordinate, int xMax)
    {
        try
        {
            int x = initialCoordinate.x;
            calculable = new ExpressionBuilder(function).withVariable("x", 0).build();
            points = new ArrayList<Point>();
            for (int i = 0; i < xMax; i++)
            {
                points.add(new Point(x + i, (int) calculable.calculate(x + i)));
            }
            allPoints = new ArrayList<Point>(points);
        }
        catch (Exception e)
        {
            Log.e(Constants.TAG, NAME + "can't parse the function", e);
        }
        optimizePoints();
    }

    private void optimizePoints()
    {
        int i = 0;
        while (i + 2 < points.size())
        {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            Point p3 = points.get(i + 2);

            float a = (((float) p1.y - (float) p3.y)) / ((float) p1.x - (float) p3.x);
            float b = (float) p1.y - a * (float) p1.x;

            if (p2.x * a + b == p2.y)
            {
                points.remove(i + 1);
            }
            else
            {
                i++;
            }
        }
    }

    public Path getPath()
    {
        Path path = new Path();

        for (Point point : points)
        {
            path.lineTo(point.x, point.y);
        }

        return path;
    }

    public int getGrad(int x)
    {
        double h = (double) x * 1e-8;
        return (int) ((calculable.calculate(x + h) - calculable.calculate(x)) / h); //TODO its just approximation, can implement better algorithm
    }

    public boolean contains(int x, int y)
    {
        for (Point point : allPoints)
        {
            if (min(abs(point.x - x), abs(point.y - y)) < RADIUS)
            {
                return true;
            }
        }
        return false;
    }
}
