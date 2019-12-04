package com.example.shooter.objects.players.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.Image;

import com.example.shooter.R;
import com.example.shooter.TextureLoader;

public class PlayerAnimation {
    private Bitmap[] animations = new Bitmap[8];
    private int speed;
    private int frames;
    private int indexR = 0;
    private int indexL = 4;
    private int countR = 0, countL = 4;
    private Bitmap currentImage = null;

    public PlayerAnimation(Context context){
        TextureLoader textureLoader = TextureLoader.getInstance();
        animations[0] = textureLoader.getTexture(3);
        animations[1] = textureLoader.getTexture(4);
        animations[2] = textureLoader.getTexture(5);
        animations[3] = textureLoader.getTexture(6);
        animations[4] = textureLoader.getTexture(7);
        animations[5] = textureLoader.getTexture(8);
        animations[6] = textureLoader.getTexture(9);
        animations[7] = textureLoader.getTexture(10);


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
