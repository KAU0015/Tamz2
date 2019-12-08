package com.example.shooter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shooter.database.DBHandler;

public class GameOverActivity extends Activity {

    private Button submit;
    private TextView score;
    private EditText name;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        dbHandler = new DBHandler(this);
        PlayerUI.getInstance().restart();
        submit = findViewById(R.id.submit);
        score = findViewById(R.id.score);
        name = findViewById(R.id.user_name);

        score.setText("SCORE: " + PlayerUI.getInstance().getScore());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dbHandler.insert(name.getText().toString(), PlayerUI.getInstance().getScore());
              GameOverActivity.this.finish();
            }
        });
    }
}
