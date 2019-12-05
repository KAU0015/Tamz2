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
import com.example.shooter.objects.buttons.Exit;
import com.example.shooter.objects.packs.AidKit;
import com.example.shooter.objects.packs.AmmoPack;
import com.example.shooter.objects.players.enemies.RobotEnemy;
import com.example.shooter.objects.players.player.Player;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Level level;
    private ArrayList<Block> blocks;
    private GameObjectContainer container = null;
    private Camera camera = null;
    private int width, height;
    public static boolean exitLevel = false;
    private int levelNum = 1;


    private GameThread gameThread;

    public GameView(Context context, int width, int height) {
        super(context);
        this.width = width;
        this.height = height;
        this.getHolder().addCallback(this);
       // this.gameThread = new GameThread(this, getHolder());
        this.setFocusable(true);
    }

    public void init() {
        level = new Level();
        camera = new Camera(0,0);
        container = new GameObjectContainer(camera, width, height);
        blocks = new ArrayList<>();

        ArrayList<String> lvl = level.loadLevel(getContext(), levelNum);

        int row = 0;
        Bitmap b = TextureLoader.getInstance().getTexture(0);

        for (String s : lvl) {

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    container.addObject(new Block(getContext(), i * b.getHeight(), row * b.getWidth()));
                }
                else if (s.charAt(i) == 'P') {
                    container.addObject(new Player(getContext(), i * b.getHeight(), row * b.getWidth() + 1));
                }
                else if(s.charAt(i) == 'R'){
                    container.addObject(new RobotEnemy(i * b.getHeight(), row * b.getWidth() + 1));
                }
                else if(s.charAt(i) == 'A'){
                    Bitmap bmp = TextureLoader.getInstance().getTexture(15);
                    container.addObject(new AmmoPack(i * b.getHeight() + b.getHeight()/4, row * b.getWidth() +b.getWidth()-bmp.getHeight()+ 1, 20));
                }
                else if(s.charAt(i) == 'M'){
                    Bitmap bmp = TextureLoader.getInstance().getTexture(16);
                    container.addObject(new AidKit(i * b.getHeight() + b.getHeight()/4, row * b.getWidth() +b.getWidth()-bmp.getHeight()+ 1, 20));
                }
                else if(s.charAt(i) == 'E'){
                    Bitmap bmp = TextureLoader.getInstance().getTexture(16);
                    container.addObject(new Exit(i * b.getHeight() + b.getHeight()/4, row * b.getWidth() +bmp.getHeight()));
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
                        ((Player) o).setFire(false);
                        //((Player) o).setToRight(true);
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                for(GameObject o : container.getObjectList()){
                    if(o instanceof Player){
                        float eventX = motionEvent.getX();
                        if(eventX <= width/2 && eventX > width/4){
                            ((Player) o).setXVel(8);
                            ((Player) o).setToRight(true);
                        }
                        else if(eventX < width/4){
                            ((Player) o).setXVel(-8);
                            ((Player) o).setToRight(false);
                        }
                        else if(eventX > (width/4)*3){
                            if(!((Player) o).isJumping()){
                                ((Player) o).setYVel(-10);
                                ((Player) o).setJumping(true);
                            }
                        }
                        else if(eventX > width/2 && eventX <= (width/4)*3){
                            ((Player) o).setFire(true);
                        }

                        if(((Player) o).isNearExit()){
                            nextLevel();
                        }

                    }
                }
                break;
        }
        return true;
    }

    private void nextLevel(){
        levelNum++;
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.gameThread = new GameThread(this,holder);
        init();
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry= true;
        this.gameThread.setRunning(false);
        while(retry) {
            try {
                this.gameThread.join();
                retry = false;
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
          //  retry= true;
        }
    }
}

