package com.example.shooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class TextureLoader {

    private static TextureLoader single_instance = null;
    private ArrayList<Bitmap> textures;
    private Context context;

    private TextureLoader(){
        textures = new ArrayList<>();
    }

    public static TextureLoader getInstance(){
        if (single_instance == null)
            single_instance = new TextureLoader();

        return single_instance;
    }

    public void setContext(Context con){
        context = con;
    }

    public void loadTextures(){
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_block1));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_idle_right));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_idle_left));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_right2));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_right1));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_right0));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_right1));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_left2));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_left1));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_left0));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.soldier_walk_left1));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.shoot_left));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.shoot_right));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.robot_left));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.robot_right));
        textures.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.ammo_pack));
    }

    public Bitmap getTexture(int i){
        return textures.get(i);
    }
}
