package com.example.shooter.objects.players;

import android.graphics.Rect;

import com.example.shooter.objects.GameObject;

public abstract class Players extends GameObject {
    protected float xVel, yVel;
    protected int life;
    protected boolean left, right;
    protected boolean toRight = true;

    public abstract void move();
    public abstract void checkCollision(GameObject ob);
    public void setLife(int life){
        this.life += life;
    }
    public int getLife(){
        return this.life;
    }

    public Rect getUpBounds(){
        return new Rect((int)((x+width/2)-(width/4)), (int)y,(int)((x+width/2)-(width/4))+width/2, (int)y+height/2);
    }
    public Rect getRightBounds(){
        return new Rect((int)(x+width-5), (int)y + 5,(int)(x+width-5)+5, (int)y + 5 + height-10);
    }
    public Rect getDownBounds(){
        return new Rect((int)((x+width/2)-(width/4)), (int)(y + height/2),(int)((x+width/2)-(width/4))+width/2, (int)(y + height/2)+height/2+1);
    }
    public Rect getLeftBounds(){
        return new Rect((int)x, (int)y + 5,(int)x+5, (int)y + 5+height-10);
    }

    public float getxVel() {
        return xVel;
    }

    public void setxVel(float xVel) {
        this.xVel = xVel;
    }

    public float getyVel() {
        return yVel;
    }

    public void setyVel(float yVel) {
        this.yVel = yVel;
    }

    public boolean getToRight() {
        return toRight;
    }

    public void setToRight(boolean toRight) {
        this.toRight = toRight;
    }
}
