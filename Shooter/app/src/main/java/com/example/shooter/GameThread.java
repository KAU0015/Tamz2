package com.example.shooter;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread{
    private boolean running;
    private GameView gameView;
    private SurfaceHolder surfaceHolder;
    private final static int    MAX_FPS = 60;
    private final static int    MAX_FRAME_SKIPS = 5;
    private final static int    FRAME_PERIOD = 1000 / MAX_FPS;

    public GameThread(GameView gameSurface, SurfaceHolder surfaceHolder)  {
        this.gameView= gameSurface;
        this.surfaceHolder= surfaceHolder;
    }

    @Override
    public void run()  {
        long beginTime;
        long timeDiff;
        int sleepTime = 0;
        int framesSkipped;

        while(running)  {
            Canvas canvas= null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    beginTime = System.currentTimeMillis();
                    framesSkipped = 0;
                    this.gameView.update();
                    this.gameView.draw(canvas);
                    timeDiff = System.currentTimeMillis() - beginTime;
                    sleepTime = (int)(FRAME_PERIOD - timeDiff);

                    if (sleepTime > 0) {
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {}
                    }

                    while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                        this.gameView.update();
                        sleepTime += FRAME_PERIOD;
                        framesSkipped++;
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void setRunning(boolean running)  {
        this.running= running;
    }
}

