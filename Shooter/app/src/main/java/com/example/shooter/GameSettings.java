package com.example.shooter;

public class GameSettings {
    private static GameSettings ourInstance = new GameSettings();

    private int difficulty = 2;
    private String strDifficulty = "HARD";
    private boolean sound = false;

    public static GameSettings getInstance() {
        return ourInstance;
    }

    private GameSettings() {

    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDefaultDifficulty(){
        return "EASY";
    }

    public boolean getDefaultSound(){
        return false;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {

        this.sound = sound;
        if(sound)
            SoundHandler.getInstance().setVolume(1);
        else
            SoundHandler.getInstance().setVolume(0);
    }

    public String getStrDifficulty() {
        return strDifficulty;
    }

    public void setStrDifficulty(String strDifficulty) {
        if(strDifficulty.equals("EASY"))
            this.difficulty = 1;
        else
            this.difficulty = 2;
        this.strDifficulty = strDifficulty;
    }
}
