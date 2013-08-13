package uk.co.scottw.convastest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;

public class DrawView extends View
{
    Paint paint = new Paint();

    public static int height = 0;
    public static int width = 0;
    int playerX = 200;
    int playerY = 400;

    int floorTopLeftX = 10;
    int floorTopLeftY = 450;

    int floorTopRightX = width - 10;
    int floorTopRightY = 450;


    public DrawView(Context context)
    {
        super(context);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        if(leftDown)
        {
            if(!collision(playerX + 5, playerY))
                playerX -= 5;
        }

        if(rightDown)
        {
            if(!collision(playerX + 5, playerY))
                playerX += 5;
        }

        doPhysics();

        drawFloor(canvas);
        drawButtons(canvas);
        drawPlayer(canvas);
        invalidate();
    }

    public void drawFloor(Canvas canvas)
    {
        paint.setColor(Color.RED);
        canvas.drawRect(floorTopLeftX, floorTopLeftY, floorTopRightX, floorTopLeftY + 20, paint);
    }

    public void doPhysics()
    {
        if(!collision(playerX, playerY + 2))
            playerY += 1;
    }

    public boolean collision(int newPlayerX, int newPlayerY)
    {
        if(newPlayerY + 5 >= floorTopLeftY)
            return true;
        else
            return false;
    }

    public void drawButtons(Canvas canvas)
    {
        paint.setColor(Color.BLACK);
        //height = 756
        //width = 1280
        canvas.drawRect(100.0f, (float)(height-200), 300.0f, (float)(height -150), paint);
        canvas.drawRect(350.0f, (float)(height-200), 550.0f, (float)(height -150), paint);
        canvas.drawRect((float)(width-300), (float)(height-200), (float)(width-100), (float)(height -150), paint);

        paint.setColor(Color.WHITE);
        canvas.drawLine(150, ((height-200) + (height -150)) / 2, 160, ((height-200) + (height -150)) /2 + 10, paint);
        canvas.drawLine(150, ((height-200) + (height -150)) / 2, 160, ((height-200) + (height -150)) /2 - 10, paint);

        canvas.drawLine(475, ((height-200) + (height -150)) / 2, 465, ((height-200) + (height -150)) /2 + 10, paint);
        canvas.drawLine(475, ((height-200) + (height -150)) / 2, 465, ((height-200) + (height -150)) /2 - 10, paint);

        canvas.drawLine((float)(width-300) + 20, ((height-200) + (height -150)) / 2 - 10, (float)(width-300) + 20 + 10, ((height-200) + (height -150)) / 2 + 10, paint);
        canvas.drawLine((float)(width-300) + 20, ((height-200) + (height -150)) / 2 - 10, (float)(width-300) + 20 - 10, ((height-200) + (height -150)) / 2 + 10, paint);


        //left,top,right,btm
        //st,sy,ex,ey
    }

    public void drawPlayer(Canvas canvas)
    {
        paint.setColor(Color.BLACK);
        canvas.drawLine(playerX-10, playerY-10, playerX+10, playerY+10, paint);
        canvas.drawLine(playerX-10, playerY+10, playerX+10, playerY-10, paint);

        //sx sy ex ey
    }

    boolean leftDown = false;
    boolean rightDown = false;


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float eventX = event.getX();
        float eventY = event.getY();

        if(eventX <= 300 && eventX >= 100 && eventY <= (float)(height -150) && eventY >= (float)(height-200))
        {
            if(event.getAction() == MotionEvent.ACTION_DOWN)
                leftDown = true;
            else if(event.getAction() == MotionEvent.ACTION_UP)
                leftDown = false;
        }
        else
        {
            leftDown = false;
        }

        if(eventX <= 550 && eventX >= 350 && eventY <= (float)(height -150) && eventY >= (float)(height-200))
        {
            if(event.getAction() == MotionEvent.ACTION_DOWN)
                rightDown = true;
            else if(event.getAction() == MotionEvent.ACTION_UP)
                rightDown = false;
        }
        else
        {
            rightDown = false;
        }

        //canvas.drawRect((float)(width-300), (float)(height-200), (float)(width-100), (float)(height -150), paint);

        //if(eventX <= )


        invalidate();
        return true;
    }
}