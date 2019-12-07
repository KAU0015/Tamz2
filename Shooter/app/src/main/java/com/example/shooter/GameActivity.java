package com.example.shooter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        TextureLoader textureLoader = TextureLoader.getInstance();
        textureLoader.setContext(this);
        textureLoader.loadTextures();
        this.setContentView(new GameView(this, width, height));
    }

    @Override
    public void onBackPressed() {
        GameActivity.this.finish();
    }
}
