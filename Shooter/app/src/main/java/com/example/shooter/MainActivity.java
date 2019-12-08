package com.example.shooter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {


    private static Button continueGame;
    private Button startGame, settings, highScore;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SoundHandler.getInstance().setContext(getApplicationContext());
        SoundHandler.getInstance().loadSounds();

        SoundHandler.getInstance().playMenu();


        startGame = findViewById(R.id.start_game);
        continueGame = findViewById(R.id.continue_game);
        settings = findViewById(R.id.settings);
        highScore = findViewById(R.id.high_score);

        String sharedPrefFile = "prefFile";
        final SharedPreferences preferences = getApplicationContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        GameSettings gs = GameSettings.getInstance();
        gs.setStrDifficulty(preferences.getString("difficulty", gs.getDefaultDifficulty()));
        gs.setSound(preferences.getBoolean("sound", gs.getDefaultSound()));

        if(preferences.getInt("lives", 0) <= 0){
            continueGame.setEnabled(false);
        }

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundHandler.getInstance().stopMenu();
                SoundHandler.getInstance().startGame();
                SharedPreferences.Editor preferencesEditor = preferences.edit();
                preferencesEditor.putInt("lives", PlayerUI.getInstance().getNewGameLives());
                preferencesEditor.putInt("level", PlayerUI.getInstance().getNewGameLevel());
                preferencesEditor.putInt("score", PlayerUI.getInstance().getNewGameScore());
                preferencesEditor.apply();
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
            }
        });


        continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundHandler.getInstance().stopMenu();
                SoundHandler.getInstance().startGame();
                    startActivity(new Intent(getApplicationContext(), GameActivity.class));
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HighScoreActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public static void setButtonState(boolean enabled){
        continueGame.setEnabled(enabled);
    }
}
