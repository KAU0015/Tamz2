package com.example.shooter.objects.players.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.shooter.PlayerUI;
import com.example.shooter.R;
import com.example.shooter.TextureLoader;
import com.example.shooter.objects.GameObject;
import com.example.shooter.objects.blocks.Block;
import com.example.shooter.objects.buttons.Exit;
import com.example.shooter.objects.packs.AidKit;
import com.example.shooter.objects.packs.AmmoPack;
import com.example.shooter.objects.packs.Packs;
import com.example.shooter.objects.players.Players;
import com.example.shooter.objects.shoots.Shoot;

import java.util.ArrayList;
import java.util.List;

public class Player extends Players {
    private float gravity = 0.4f;
    private boolean jumping = false;
    private boolean falling = true;
    private final float maxSpeed = 8;
    private Bitmap imageIdleRight;
    private Bitmap imageIdleLeft;
    private PlayerAnimation animation;
    private boolean fire = false;
    private List<Shoot> shoots;
    private int speedFire = 8;
    private int firing = 0;
    private boolean nearExit = false;


    public Player(Context context, float x, float y){
        this.life = 100;
        this.x = x;
        this.y = y;
        this.xVel = 0;
        this.yVel = 0;

        this.animation = new PlayerAnimation(context);
        this.shoots = new ArrayList<Shoot>();
        TextureLoader textureLoader = TextureLoader.getInstance();
        this.imageIdleRight = textureLoader.getTexture(1);
        this.imageIdleLeft = textureLoader.getTexture(2);

        this.width = imageIdleRight.getWidth();
        this.height = imageIdleRight.getHeight();
    }

    public boolean isNearExit() {
        return nearExit;
    }

    public void setYVel(float yVel){
        this.yVel = yVel;
    }

    public void setXVel(float xVel){
        this.xVel = xVel;
    }

    public void draw(Canvas canvas){

        if(xVel != 0 && !jumping && yVel == 0){
            animation.drawAnimation(canvas, (int)x, (int)y);
        }
        else if(toRight){
            canvas.drawBitmap(imageIdleRight, null, new Rect((int)x, (int)y, (int)(x+width), (int)(y+height)), null);
        }
        else if(!toRight){
            canvas.drawBitmap(imageIdleLeft, null, new Rect((int)x, (int)y, (int)(x+width), (int)(y+height)), null);
        }

        for(Shoot s : shoots){
            s.draw(canvas);
        }

        /*Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStrokeWidth(1);
        p.setStyle(Paint.Style.STROKE);*/
       /* canvas.drawRect(getLeftBounds(), p);
        canvas.drawRect(getRightBounds(), p);
        canvas.drawRect(getDownBounds(), p);
        canvas.drawRect(getUpBounds(), p);*/
       // canvas.drawRect(getBounds(), p);

    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void  setToRight(boolean toRight){
        this.toRight = toRight;
    }

    @Override
    public void move() {
        x += xVel;
        y += yVel;

        if(falling || jumping){
            yVel += gravity;

            if(yVel > maxSpeed){
                yVel = maxSpeed;
            }
        }

        if(xVel > 0)
            animation.runAnimationR();
        else if(xVel < 0)
            animation.runAnimationL();

        if(PlayerUI.getInstance().getAmmo() > 0){
            if(fire){
                firing++;

                if(firing > speedFire){
                    firing = 0;
                    if(toRight){
                        shoots.add(new Shoot(x+width-9, y+(height/3)*2-4, toRight));
                        PlayerUI.getInstance().setAmmo(-1);

                    }
                    else{
                        shoots.add(new Shoot(x-12, y+(height/3)*2-4, toRight));
                        PlayerUI.getInstance().setAmmo(-1);
                    }
                }
            }
        }

        for(Shoot s : shoots){
            s.move();
        }
    }

    @Override
    public void checkCollision(GameObject ob) {
        //nearExit = false;

        if(!(ob instanceof Players) && !(ob instanceof Packs) && !(ob instanceof Exit)){
            if(getUpBounds().intersect(ob.getBounds())){
                y = ob.getY() + ob.getHeight()+1;
                yVel = 0;
                falling = true;
            }

            if(getDownBounds().intersect(ob.getBounds())){
                y = ob.getY() - height;
                yVel = 0;
                jumping = false;
                falling = false;
            }
            else {
                falling = true;
            }
            if(getRightBounds().intersect(ob.getBounds())){
                x = ob.getX() - width;
                // xVel = 0;
            }
            if(getLeftBounds().intersect(ob.getBounds())){
                x = ob.getX() + ob.getWidth();
                // xVel = 0;
            }

        }

        if(ob instanceof Exit && getBounds().intersect(ob.getBounds())){
            nearExit = true;
        }

        if(ob instanceof Packs){
            if(ob instanceof AmmoPack){
                if(getBounds().intersect(ob.getBounds())){
                    PlayerUI.getInstance().setAmmo(((Packs) ob).getBonus());
                    ((Packs) ob).setActivated(true);
                }
            }
            if(ob instanceof AidKit){
                if(getBounds().intersect(ob.getBounds()) && life < 100){
                    PlayerUI.getInstance().setLife(((Packs) ob).getBonus());
                    life += ((AidKit) ob).getBonus();
                    if(life > 100){
                        life = 100;
                    }
                    ((Packs) ob).setActivated(true);
                }
            }

        }


        for(int i = 0; i < shoots.size(); i++){
            shoots.get(i).checkCollision(ob);
            if(shoots.get(i).isCol()){
                shoots.remove(i);
            }
        }
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }
}
