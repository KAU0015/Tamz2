package com.example.shooter.objects.packs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.shooter.TextureLoader;

public class AmmoPack extends Packs {
    private Bitmap img;

    public AmmoPack(float x, float y, int bonus){
        this.x = x;
        this.y = y;
        this.bonus = bonus;

        TextureLoader textureLoader = TextureLoader.getInstance();

        this.img = textureLoader.getTexture(15);
        this.width = img.getWidth();
        this.height = img.getHeight();


    }
    @Override
    public void draw(Canvas canvas) {
       /* Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStrokeWidth(1);
        p.setStyle(Paint.Style.STROKE);

        canvas.drawRect(getBounds(), p);*/
        canvas.drawBitmap(img, null, new Rect((int)x, (int)y, (int)(x+width), (int)(y+height)), null);
    }
}
