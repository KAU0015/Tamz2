package com.example.shooter.objects.players.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.Image;

import com.example.shooter.R;

public class PlayerAnimation {
    private Bitmap[] animations = new Bitmap[8];
    private int speed;
    private int frames;
    private int indexR = 0;
    private int indexL = 4;
    private int countR = 0, countL = 4;
    private Bitmap currentImage = null;

    public PlayerAnimation(Context context){

        animations[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_right2);
        animations[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_right1);
        animations[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_right0);
        animations[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_right1);
        animations[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_left2);
        animations[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_left1);
        animations[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_left0);
        animations[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_left1);


        this.frames = animations.length;
        this.speed = 6;
    }

    public void runAnimationR(){
        indexR++;
        if(indexR > speed){
            indexR = 0;
            nextFrameR();
        }
    }

    public void runAnimationL(){
        indexL++;
        if(indexL > speed){
            indexL = 0;
            nextFrameL();
        }
    }

    private void nextFrameR(){
        for(int i = 0; i < frames/2; i++){
            if(countR == i){
                currentImage = animations[i];
            }
        }
        countR++;

        if(countR > frames/2){
            countR = 0;
        }
    }

    private void nextFrameL(){
        for(int i = frames/2; i < frames; i++){
            if(countL == i){
                currentImage = animations[i];
            }
        }
        countL++;

        if(countL > frames){
            countL = 4;
        }
    }

    public void drawAnimation(Canvas canvas, int x, int y){
        if(currentImage != null)
         canvas.drawBitmap(currentImage, null, new Rect((int)x, (int)y, (int)(x+currentImage.getWidth()), (int)(y+currentImage.getHeight())), null);

    }
}
