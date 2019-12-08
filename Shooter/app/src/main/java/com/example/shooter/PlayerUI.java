package com.example.shooter;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.shooter.objects.GameObject;

public class PlayerUI extends GameObject {
    private static PlayerUI ourInstance = new PlayerUI();

    private int life = 100;
    private int ammo = 20;
    private int level;
    private int lives;
    private int score;

    public static PlayerUI getInstance() {
        return ourInstance;
    }

    private PlayerUI() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.FILL);
        p.setTextSize(40);
        canvas.drawText("LIFE: " + life, x+80, y + 65, p);
        canvas.drawText("AMMO: " + ammo, x+280, y + 65, p);
        canvas.drawText("LEVEL: " + level, x+500, y + 65, p);
        canvas.drawText("LIVES: " + lives, x+700, y + 65, p);
        canvas.drawText("SCORE: " + score, x+900, y + 65, p);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawText("LIFE: " + life, x+80, y + 65, p);
        canvas.drawText("AMMO: " + ammo, x+280, y + 65, p);
        canvas.drawText("LEVEL: " + level, x+500, y + 65, p);
        canvas.drawText("LIVES: " + lives, x+700, y + 65, p);
        canvas.drawText("SCORE: " + score, x+900, y + 65, p);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life += life;
        if(this.life > 100){
            this.life = 100;
        }
    }

    public void lostLives(){
        this.lives--;
    }

    public int getNewGameLevel(){
        return 1;
    }

    public int getNewGameLives(){
        return 6/GameSettings.getInstance().getDifficulty();
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public int getLives(){
        return  this.lives;
    }

    public void nextLevel(){
        this.ammo = 20;
        this.life = 100;
        this.level++;
    }

    public int getLevel(){
        return this.level;
    }

    public void setStartLife(int life){
        this.life = life;
    }

    public void setStartAmmo(int ammo){
        this.ammo = ammo;
    }

    public void restart(){
        this.ammo = 20;
        this.life = 100;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo += ammo;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public void addScore(int score){
        this.score+=score;
    }

    public int getNewGameScore(){
        return 0;
    }
}
