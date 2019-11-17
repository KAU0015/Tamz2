package com.example.shooter;

import android.content.Context;
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

public class GameView extends SurfaceView implements Runnable {
    volatile boolean playing;
    private Thread gameThread = null;
    private Block block;
    private Level level;
    private ArrayList<Block> blocks;
    private GameObjectContainer container = null;
    private Camera camera = null;
    private int fps = 0;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private int width, height;

    public GameView(Context context, int width, int height) {
        super(context);

        //block = new Block(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        level = new Level();
        camera = new Camera(0,0);
        container = new GameObjectContainer(camera, width, height);
        blocks = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        ArrayList<String> lvl = level.loadLevel(getContext());

        int row = 0;

        for(String s: lvl){

            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '1'){
                    container.addObject(new Block(getContext(), i*158, row*158));
                }
                else if(s.charAt(i) == 'P'){
                    container.addObject(new Player(getContext(), i*158, row*158+1));
                }
            }
            row++;
        }

      /*  long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / 80;
        long averageFPS;

        while (playing) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    update();
                    invalidate();
                }
            } catch (Exception e) {       }
            finally {
                if (canvas != null)            {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                gameThread.sleep(waitTime);
            } catch (Exception e) {}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == 80)        {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }*/

        long now;
        long updateTime;
        long wait;

        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        while (playing) {
            now = System.nanoTime();

            update();
           invalidate();

            updateTime = System.nanoTime() - now;
            wait = (OPTIMAL_TIME - updateTime) / 1000000;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


      /*  while (playing) {

            fps++;

            if(fps == 60){
                System.out.println(fps);
                update();
                //draw();
                invalidate();
                fps = 0;

            }


        }*/
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setWillNotDraw(false);
    }

    private void update() {
       // block.update();
        container.moveObject();
        container.checkObjectCollision();
    }

   /* private void draw() {
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
    }*/

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

    @Override
    protected void onDraw(Canvas canvas) {

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
}

