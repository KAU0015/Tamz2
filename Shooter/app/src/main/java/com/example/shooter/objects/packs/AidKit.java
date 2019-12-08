package com.example.shooter.objects.packs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.shooter.GameSettings;
import com.example.shooter.TextureLoader;

public class AidKit extends Packs {
    private Bitmap img;

    public AidKit(float x, float y, int bonus){
        this.x = x;
        this.y = y;
        this.bonus = bonus/GameSettings.getInstance().getDifficulty();

        TextureLoader textureLoader = TextureLoader.getInstance();

        this.img = textureLoader.getTexture(16);
        this.width = img.getWidth();
        this.height = img.getHeight();

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, null, new Rect((int)x, (int)y, (int)(x+width), (int)(y+height)), null);
    }
}
