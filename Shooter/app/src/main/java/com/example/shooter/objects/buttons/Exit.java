package com.example.shooter.objects.buttons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.shooter.TextureLoader;
import com.example.shooter.objects.GameObject;

public class Exit extends GameObject {
    private Bitmap exit;

    public Exit(float x, float y){
        this.x = x;
        this.y = y;

        TextureLoader textureLoader = TextureLoader.getInstance();
        this.exit = textureLoader.getTexture(17);
        this.width = exit.getWidth();
        this.height = exit.getHeight();

    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(exit, null, new Rect((int)x, (int)y, (int)(x+width), (int)(y+height)), null);

    }
}
