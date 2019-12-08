package com.example.shooter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundHandler {
    private static final SoundHandler ourInstance = new SoundHandler();

    public static SoundHandler getInstance() {
        return ourInstance;
    }

    private static final int MAX_STREAMS = 5;
    private SoundPool soundPool;
    private int pistol, aidKit, difficultyEasy, difficultyHard, ammo, startGame, scream, enemyPistol, hail;
    private  Context context;
    private int menuStreamID;
    private AudioManager audioManager;
    private  boolean loaded = false;
    private MediaPlayer mediaPlayer;

    private SoundHandler() {
        this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void loadSounds(){
        this.pistol = this.soundPool.load(context, R.raw.pistol,1);
        this.ammo = this.soundPool.load(context, R.raw.hail,1);
        this.startGame = this.soundPool.load(context, R.raw.hell,1);
        this.aidKit = this.soundPool.load(context, R.raw.needed,1);
        this.scream = this.soundPool.load(context, R.raw.scream,1);
        this.enemyPistol = this.soundPool.load(context, R.raw.enemypistol,1);
        this.hail = this.soundPool.load(context, R.raw.hail,1);
    }

    public void gunFire(){
       this.soundPool.play(this.pistol,1, 1, 1, 0, 1f);
    }

    public void enemyPistol(){
        this.soundPool.play(this.enemyPistol,1, 1, 1, 0, 1f);
    }

    public void ammoPicked(){
        this.soundPool.play(this.ammo,1, 1, 1, 0, 1f);
    }

    public void startGame(){
        this.soundPool.play(this.startGame,1, 1, 1, 0, 1f);
    }

    public void playHail(){
        this.soundPool.play(this.hail,1, 1, 1, 0, 1f);
    }

    public void aidKitPicked(){
        this.soundPool.play(this.aidKit,1, 1, 1, 0, 1f);
    }

    public void scream(){
        this.soundPool.play(this.scream,1, 1, 1, 0, 1f);
    }

    public void playMenu(){
        mediaPlayer = MediaPlayer.create(context, R.raw.menu);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    public void stopMenu(){
        mediaPlayer.stop();
    }

}
