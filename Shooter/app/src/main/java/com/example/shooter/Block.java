package com.example.shooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Block {
    private Bitmap bitmap;

    //coordinates
    private int x;
    private int y;

    public Block(Context context) {
        x = 75;
        y = 50;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_block1);
    }

    public void update(){

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
