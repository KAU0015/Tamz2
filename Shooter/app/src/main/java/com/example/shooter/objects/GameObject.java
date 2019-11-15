package com.example.shooter.objects;

import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class GameObject {
    protected float x, y;
    protected int width, height;

    public abstract void draw(Canvas canvas);

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Rect getBounds(){
        return new Rect((int)x, (int)y, width, height);
    }
}
