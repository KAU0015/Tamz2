package com.example.shooter.objects.blocks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.shooter.R;
import com.example.shooter.objects.GameObject;

public class Block extends GameObject {

    private Bitmap bitmap;

    public Block(Context context, float x, float y) {
       this.x = x;
       this.y = y;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_block1);
        this.width = bitmap.getWidth();
        this.height = bitmap.getHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, new Rect((int)x, (int)y, (int)(x+width), (int)(y+height)), null);
       /* Paint p = new Paint();
        p.setColor(Color.GREEN);
        p.setStrokeWidth(1);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawRect(getBounds(), p);*/
    }

}
