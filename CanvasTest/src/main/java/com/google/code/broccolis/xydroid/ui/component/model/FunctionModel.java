package com.google.code.broccolis.xydroid.ui.component.model;

import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

import com.google.code.broccolis.xydroid.util.Player;
import com.google.code.broccolis.xydroid.util.PointOnScreen;

import java.util.ArrayList;
import java.util.List;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

import static com.google.code.broccolis.xydroid.util.Constants.TAG;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.SCREEN_HEIGHT;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.SCREEN_WIDTH;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseXToFloat;
import static com.google.code.broccolis.xydroid.util.MultipleDeviceSupport.parseYToFloat;
import static java.lang.Character.isDigit;

public class FunctionModel
{
    private static final String NAME = "FunctionModel ";
    private Calculable calculable = null;
    private List<PointOnScreen> points;
    private List<PointOnScreen> allPoints;
    private Path path;

    public FunctionModel(String function, PointOnScreen initialCoordinate, int xMax) throws IllegalArgumentException
    {
        this.points = new ArrayList<PointOnScreen>();

        function = repairSmallMistakes(function);

        try
        {
            calculable = new ExpressionBuilder("-1*(" + function + ")").withVariable("x", 0).build();
            int i = (xMax < 0 ? xMax : 0);
            int limit = (i == 0 ? xMax : 0);
            for (; i < limit; i++)
            {
                int calc = (int) calculable.calculate(i);
                int x = i + initialCoordinate.getScreenX();
                int y = calc + initialCoordinate.getScreenY();
                if (x > SCREEN_WIDTH || y > SCREEN_HEIGHT || x < 0 || y < 0)
                {
                    continue;
                }
                points.add(new PointOnScreen(parseXToFloat(x), parseYToFloat(y)));
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, NAME + "can't parse the function " + function, e);
            throw new IllegalArgumentException();
        }

        allPoints = new ArrayList<PointOnScreen>(points);
        optimizePoints();
        generatePath();
    }

    private String repairSmallMistakes(String function)
    {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < function.length(); i++)
        {
            char c = function.charAt(i);

            if (isDigit(c) && builder.length() > 0 && builder.toString().endsWith("x"))
            {
                builder.append("*");
            }

            if (c == 'x' && builder.length() > 0 && isDigit(builder.charAt(builder.length() - 1)))
            {
                builder.append("*");
            }

            builder.append(c);
        }

        return builder.toString();
    }

    private void optimizePoints()
    {
        int i = 0;
        while (i + 2 < points.size())
        {
            PointOnScreen p1 = points.get(i);
            PointOnScreen p2 = points.get(i + 1);
            PointOnScreen p3 = points.get(i + 2);

            float a = (((float) p1.getScreenY() - (float) p3.getScreenY())) / ((float) p1.getScreenX() - (float) p3.getScreenX());
            float b = (float) p1.getScreenY() - a * (float) p1.getScreenX();

            if (p2.x * a + b == p2.y)
            {
                points.remove(i + 1);
            }
            else
            {
                i++;
            }
        }

        i = 0;
        while (i < allPoints.size())
        {
            if (isOutOfBounds(allPoints.get(i)))
            {
                allPoints.remove(i);
            }
            else
            {
                i++;
            }
        }
    }

    private boolean isOutOfBounds(PointOnScreen point)
    {
        return point.getScreenX() > SCREEN_WIDTH || point.getScreenX() < 0 || point.getScreenY() < 0 || point.getScreenY() > SCREEN_HEIGHT;
    }

    public void generatePath()
    {
        path = new Path();
        path.moveTo(points.get(0).getScreenX(), points.get(0).getScreenY());

        for (PointOnScreen point : points)
        {
            path.lineTo(point.getScreenX(), point.getScreenY());
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

        for (PointOnScreen point : allPoints)
        {
            if (top.x < point.getScreenX())
            {
                break;
            }
            if (bottom.x > point.getScreenX() || top.y < point.getScreenY() || bottom.y > point.getScreenY())
            {
                continue;
            }
            return true;
        }
        return false;
    }
}
