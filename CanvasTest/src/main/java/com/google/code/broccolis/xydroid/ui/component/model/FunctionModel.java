package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

import com.google.code.broccolis.xydroid.util.Player;

import java.util.ArrayList;
import java.util.List;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;

public class FunctionModel
{
    private static final String NAME = "FunctionModel ";
    private Calculable calculable = null;
    private List<Point> points;
    private List<Point> allPoints;
    private Path path;

    public FunctionModel(String function, Point initialCoordinate, int xMax, int screenWidth, int screenHeight)
    {
        this.points = new ArrayList<Point>();
        try
        {
            calculable = new ExpressionBuilder(function).withVariable("x", 0).build();
            for (int i = 0; i < xMax; i++)
            {
                int calc = (int) calculable.calculate(i);
                int x = i + initialCoordinate.x;
                int y = calc + initialCoordinate.y;
                if(x > screenWidth || y > screenHeight || x < 0 || y < 0)
                    continue;
                points.add(new Point(x, y));
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, NAME + "can't parse the function " + function, e);
        }

        allPoints = new ArrayList<Point>(points);
        optimizePoints();
        generatePath();
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

    public void generatePath()
    {
        path = new Path();
        path.moveTo(points.get(0).x, points.get(0).y);

        for(Point point : points)
        {
            path.lineTo(point.x, point.y);
        }
    }

    public Path getPath()
    {
        return path;
    }

    public int getGrad(int x)
    {
        double h = (double) x * 1e-8;
        return (int) ((calculable.calculate(x + h) - calculable.calculate(x)) / h); //TODO its just approximation, can implement better algorithm
    }

    public boolean contains(Player player, Point moveVector)
    {
        int x = player.getX();
        int y = player.getY();

        int moveX = moveVector.x < 0 ? moveVector.x : 0;
        int moveY = moveVector.y < 0 ? moveVector.y : 0;
        Point bottom = new Point(x + moveX, y + moveY);

        moveX = moveVector.x > 0 ? moveVector.x : 0;
        moveY = moveVector.y > 0 ? moveVector.y : 0;
        Point top = new Point(x + player.getWidth() + moveX, y + player.getHeight() + moveY);

        for (Point point : allPoints)
        {
            if (top.x < point.x || bottom.x > point.x || top.y < point.y || bottom.y > point.y)
            {
                continue;
            }
            return true;
        }
        return false;
    }
}
