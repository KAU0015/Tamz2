package com.example.shooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.shooter.objects.GameObject;
import com.example.shooter.objects.GameObjectContainer;
import com.example.shooter.objects.blocks.Block;
import com.example.shooter.objects.players.player.Player;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    volatile boolean playing;
    private Block block;
    private Level level;
    private ArrayList<Block> blocks;
    private GameObjectContainer container = null;
    private Camera camera = null;
    private int fps = 0;

    private Paint paint;
    private Canvas canvas;
    private int width, height;


    private GameThread gameThread;

    public GameView(Context context, int width, int height) {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
        paint = new Paint();
        level = new Level();
        camera = new Camera(0,0);
        container = new GameObjectContainer(camera, width, height);
        blocks = new ArrayList<>();
        this.width = width;
        this.height = height;

        init();
    }

    public void init() {
        ArrayList<String> lvl = level.loadLevel(getContext());

        int row = 0;

        for (String s : lvl) {

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    Bitmap b = TextureLoader.getInstance().getTexture(0);
                    container.addObject(new Block(getContext(), i * b.getHeight(), row * b.getWidth()));
                } else if (s.charAt(i) == 'P') {
                    Bitmap b = TextureLoader.getInstance().getTexture(1);
                    container.addObject(new Player(getContext(), i * b.getHeight(), row * b.getWidth() + 1));
                }
            }
            row++;
        }
    }

    public void update() {
        container.moveObject();
        container.checkObjectCollision();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.translate(camera.getX(), camera.getY());
        container.drawObject(canvas);
        canvas.translate(-camera.getX(), -camera.getY());
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                for (GameObject o : container.getObjectList()){
                    if(o instanceof Player){
                        ((Player) o).setXVel(0);
                        //((Player) o).setToRight(true);
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                for(GameObject o : container.getObjectList()){
                    if(o instanceof Player){
                        float eventX = motionEvent.getX();
                        if(eventX >= width/2){
                            ((Player) o).setXVel(8);
                            ((Player) o).setToRight(true);
                        }
                        else if(eventX < width/2){
                            ((Player) o).setXVel(-8);
                            ((Player) o).setToRight(false);
                        }

                    }
                }
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.gameThread = new GameThread(this,holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry= true;
        while(retry) {
            try {
                this.gameThread.setRunning(false);
                this.gameThread.join();
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
            retry= true;
        }
    }
}

