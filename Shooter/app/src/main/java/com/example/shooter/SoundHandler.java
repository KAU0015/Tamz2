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
    private int pistol, aidKit, ammo, startGame, scream, enemyPistol, hail, dance, better, nextLevel;
    private  Context context;
    private int menuStreamID;
    private AudioManager audioManager;
    private  boolean menuPlayed = false;
    private MediaPlayer mediaPlayer;
    private int volume = 1;

    private SoundHandler() {
        this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
    }

    public void setVolume(int volume){
        this.volume = volume;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void loadSounds(){
        this.pistol = this.soundPool.load(context, R.raw.pistol,1);
        this.nextLevel = this.soundPool.load(context, R.raw.hail,1);
        this.startGame = this.soundPool.load(context, R.raw.hell,1);
        this.ammo = this.soundPool.load(context, R.raw.needed,1);
        this.scream = this.soundPool.load(context, R.raw.scream,1);
        this.enemyPistol = this.soundPool.load(context, R.raw.enemypistol,1);
        this.hail = this.soundPool.load(context, R.raw.hail,1);
        this.dance = this.soundPool.load(context, R.raw.dance,1);
        this.aidKit = this.soundPool.load(context, R.raw.better,1);
    }

    public void gunFire(){
       this.soundPool.play(this.pistol,volume, volume, 1, 0, 1f);
    }

    public void enemyPistol(){
        this.soundPool.play(this.enemyPistol,volume*0.35f, volume*0.35f, 1, 0, 1f);
    }

    public void ammoPicked(){
        this.soundPool.play(this.ammo,volume, volume, 1, 0, 1f);
    }

    public void startGame(){
        this.soundPool.play(this.startGame,volume, volume, 1, 0, 1f);
    }

    public void playHail(){
        this.soundPool.play(this.hail,volume, volume, 1, 0, 1f);
    }

    public void aidKitPicked(){
        this.soundPool.play(this.aidKit,volume, volume, 1, 0, 1f);
    }

    public void scream(){
        this.soundPool.play(this.scream,volume*0.5f, volume*0.5f, 1, 0, 1f);
    }

    public void dance(){
        this.soundPool.play(this.dance,volume*2f, volume*2f, 1, 0, 1f);
    }

    public void nextLevel(){this.soundPool.play(this.nextLevel,volume, volume, 1, 0, 1f);}

    public void playBar(){
        mediaPlayer = MediaPlayer.create(context, R.raw.bar);
        mediaPlayer.setVolume(volume*0.5f, volume*0.5f);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    public void stopBar(){
        mediaPlayer.stop();
    }

    public void playMenu(){
        if(!menuPlayed){
            mediaPlayer = MediaPlayer.create(context, R.raw.menu);
            mediaPlayer.setVolume(volume*0.5f, volume*0.5f);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            menuPlayed = true;
        }
    }

    public void stopMenu(){
        mediaPlayer.stop();
        menuPlayed = false;
    }



}
