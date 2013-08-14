package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;
import static java.lang.Math.abs;

public class FunctionModel
{
    private static final int RADIUS = 5;
    private static final String NAME = "FunctionModel ";
    private Calculable calculable = null;
    private Point origin;
    private List<Point> points;
    private List<Point> allPoints;

    public FunctionModel(String function, Point initialCoordinate, int xMax)
    {
        points = new ArrayList<Point>();

        try
        {
            int x = initialCoordinate.x;
            this.origin = initialCoordinate;
            calculable = new ExpressionBuilder(function).withVariable("x", 0).build();
            for (int i = 0; i < xMax; i++)
            {
                points.add(new Point(i, (int) calculable.calculate(i)));
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, NAME + "can't parse the function", e);
        }

        allPoints = new ArrayList<Point>(points);
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
        path.moveTo(points.get(0).x + origin.x, points.get(0).y + origin.y);
        for (Point point : points)
        {
            path.lineTo(point.x + origin.x, point.y + origin.y);
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
            if (abs(point.x + origin.x - x) < RADIUS && abs(point.y + origin.y - y) < RADIUS)
            {
                return true;
            }
        }
        return false;
    }
}
