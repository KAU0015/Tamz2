package com.example.shooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.shooter.objects.GameObjectContainer;
import com.example.shooter.objects.blocks.Block;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {
    volatile boolean playing;
    private Thread gameThread = null;
    private Block block;
    private Level level;
    private ArrayList<Block> blocks;
    private GameObjectContainer container = null;
    private Camera camera = null;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    public GameView(Context context) {
        super(context);

        //block = new Block(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        level = new Level();
        camera = new Camera(0,0);
        container = new GameObjectContainer(camera);
        blocks = new ArrayList<>();
    }

    @Override
    public void run() {
        ArrayList<String> lvl = level.loadLevel(getContext());

        int row = 0;

        for(String s: lvl){

            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '1'){
                    container.addObject(new Block(getContext(), i*122, row*122));
                }
            }
            row++;
        }


        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {
       // block.update();
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            //drawing a background color for canvas
            canvas.drawColor(Color.BLACK);
            //Drawing the player
            canvas.translate(camera.getX(), camera.getY());
            container.drawObject(canvas);
            canvas.translate(-camera.getX(), -camera.getY());

            //Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}

