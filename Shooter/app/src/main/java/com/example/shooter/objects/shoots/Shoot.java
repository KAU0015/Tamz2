package com.example.shooter.objects.shoots;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.shooter.PlayerUI;
import com.example.shooter.TextureLoader;
import com.example.shooter.objects.GameObject;
import com.example.shooter.objects.players.Players;
import com.example.shooter.objects.players.player.Player;

public class Shoot extends Players {
    public boolean col = false;
    private int maxSpeed = 10;
    private boolean toRigth;
    private Bitmap img;

    public Shoot(float x, float y, boolean toRigth){
        this.x = x;
        this.y = y;
        this.toRigth = toRigth;
        TextureLoader textureLoader = TextureLoader.getInstance();

        if(toRigth){
            img = textureLoader.getTexture(11);
        }
        else{
            img = textureLoader.getTexture(12);
        }

        this.width = img.getWidth();
        this.height = img.getHeight();

        if(this.toRigth){
            this.xVel = maxSpeed;
        }
        else{
            this.xVel = -maxSpeed;
        }
    }


    @Override
    public void move() {
        this.x += xVel;
    }

    @Override
    public void checkCollision(GameObject ob) {

        if( getBounds().intersect(ob.getBounds())){
            xVel = 0;
            col = true;
        }

        if(col && ob instanceof Players){
            ((Players) ob).setLife(-10);
            if(ob instanceof Player){
                PlayerUI.getInstance().setLife(-10);
            }
        }
    }

    public boolean isCol() {
        return col;
    }

    public void setCol(boolean col) {
        this.col = col;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, null, new Rect((int)x, (int)y, (int)(x+width), (int)(y+height)), null);
    }
}
