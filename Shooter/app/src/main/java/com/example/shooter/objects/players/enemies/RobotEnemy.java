package com.example.shooter.objects.players.enemies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.shooter.SoundHandler;
import com.example.shooter.TextureLoader;
import com.example.shooter.objects.GameObject;
import com.example.shooter.objects.blocks.Block;
import com.example.shooter.objects.players.Players;
import com.example.shooter.objects.players.player.Player;
import com.example.shooter.objects.shoots.Shoot;

import java.util.ArrayList;
import java.util.List;

public class RobotEnemy extends Players implements MovableEnemy {
    private int maxSpeed = 2;
    private int startPos;
    private boolean falling = true;
    private float gravity = 0.4f;
    private Bitmap enemyRight;
    private Bitmap enemyLeft;
    private List<Shoot> shoots;
    private int speedFire = 16;
    private int firing = 0;
    private  boolean fire = false;

    public RobotEnemy(float x, float y){
        this.life = 50;
        this.x = x;
        this.y = y;
        this.yVel = 0;
        this.startPos = (int)x;
        this.shoots = new ArrayList<>();
        TextureLoader textureLoader = TextureLoader.getInstance();

        this.enemyLeft = textureLoader.getTexture(13);
        this.enemyRight = textureLoader.getTexture(14);
        this.width = enemyLeft.getWidth();
        this.height = enemyLeft.getHeight();

        if(toRight){
            xVel = maxSpeed;
        }
        else {
            xVel = -maxSpeed;
        }

    }
    @Override
    public void move() {

        if(!fire){
            x += xVel;
            y += yVel;
        }


        if(falling){
            yVel += gravity;

            if(yVel > maxSpeed){
                yVel = maxSpeed;
            }
        }

        if(fire){
            firing++;

            if(firing > speedFire){
                firing = 0;
                if(toRight){
                    shoots.add(new Shoot(x+width-9, y+height/5*2, toRight));
                    SoundHandler.getInstance().enemyPistol();
                }
                else{
                    shoots.add(new Shoot(x-12, y+height/5*2, toRight));
                    SoundHandler.getInstance().enemyPistol();
                }
            }
        }


        for(Shoot s : shoots){
            s.move();
        }
    }

    @Override
    public void checkCollision(GameObject ob) {

        if(ob instanceof Player){
            fire = false;

            if(getLeftFireBounds().intersect(ob.getBounds())){
                fire = true;
                toRight = false;
                xVel = -maxSpeed;
            }
            else if(getRightFireBounds().intersect(ob.getBounds())){
                fire = true;
                toRight = true;
                xVel = maxSpeed;
            }
        }
        //else if(!(ob instanceof Players) && !(ob instanceof Packs)){
        else if(ob instanceof Block){
            if(getUpBounds().intersect(ob.getBounds())){
                y = ob.getY() + ob.getHeight()+1;
                yVel = 0;
                falling = true;
            }
            if(getDownBounds().intersect(ob.getBounds())){
                y = ob.getY() - height;
                yVel = 0;
                falling = false;
            }
            else {
                falling = true;
            }
            if(getRightBounds().intersect(ob.getBounds())){
                x = ob.getX() - width;
                xVel = -maxSpeed;
                toRight = false;
            }
            if(getLeftBounds().intersect(ob.getBounds())){
                x = ob.getX() + ob.getWidth();
                xVel = +maxSpeed;
                toRight = true;
            }

            if((getLeftDownBounds().intersect(ob.getBounds()))){
                left = true;
            }
            if((getRightDownBounds().intersect(ob.getBounds()))){
                right = true;
            }
        }

        for(int i = 0; i < shoots.size(); i++){
            shoots.get(i).checkCollision(ob);
            if(shoots.get(i).isCol()){
                shoots.remove(i);
            }
        }

    }

    @Override
    public void draw(Canvas canvas) {

        if(toRight){
            canvas.drawBitmap(enemyRight, null, new Rect((int)x, (int)y, (int)(x+width), (int)(y+height)), null);
        }
        else{
            canvas.drawBitmap(enemyLeft, null, new Rect((int)x, (int)y, (int)(x+width), (int)(y+height)), null);
        }

        for(Shoot s : shoots){
            s.draw(canvas);
        }

        /*Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStrokeWidth(1);
        p.setStyle(Paint.Style.STROKE);
       canvas.drawRect(getLeftFireBounds(), p);
       canvas.drawRect(getRightFireBounds(), p);
        canvas.drawRect(getLeftDownBounds(), p);
        canvas.drawRect(getRightDownBounds(), p);*/
    }

    public Rect getRightFireBounds(){
        return new Rect((int)(x+width-5), (int)y + 5,(int)(x+width-5)+width*5, (int)y + 5 + height-10);
    }

    public Rect getLeftFireBounds(){
        return new Rect((int)x-width*5, (int)y + 5,(int)x+5, (int)y + 5+height-10);
    }

    public Rect getRightDownBounds(){
        return new Rect((int)(x+width-5), (int)y + height/4*3,(int)(x+width-5)+5, (int)y + height/4*5);
    }

    public Rect getLeftDownBounds(){
        return new Rect((int)x, (int)y + height/4*3,(int)x+5, (int)y + height/4*5);
    }

    public boolean shootsEmpty(){
        return shoots.isEmpty();
    }

    @Override
    public boolean getLeft() {
        return left;
    }

    @Override
    public boolean getRight() {
        return right;
    }

    @Override
    public void setLeft(boolean b) {
        this.left = b;
    }

    @Override
    public void setRight(boolean b) {
        this.right = b;
    }
}
