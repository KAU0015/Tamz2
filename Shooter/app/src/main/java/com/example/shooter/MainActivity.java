package com.example.shooter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {


    private static Button continueGame;
    private Button startGame, settings, highScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame = findViewById(R.id.start_game);
        continueGame = findViewById(R.id.continue_game);
        settings = findViewById(R.id.settings);
        highScore = findViewById(R.id.high_score);

        String sharedPrefFile = "prefFile";
        final SharedPreferences preferences = getApplicationContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        if(preferences.getInt("lives", 0) <= 0){
            continueGame.setEnabled(false);
        }

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor preferencesEditor = preferences.edit();
                preferencesEditor.putInt("lives", PlayerUI.getInstance().getNewGameLives());
                preferencesEditor.putInt("level", PlayerUI.getInstance().getNewGameLevel());
                preferencesEditor.apply();
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
            }
        });


        continueGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
