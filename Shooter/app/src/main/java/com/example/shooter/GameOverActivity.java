package com.example.shooter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends Activity {

    private Button submit;
    private TextView score;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        PlayerUI.getInstance().restart();
        submit = findViewById(R.id.submit);
        score = findViewById(R.id.score);
        name = findViewById(R.id.user_name);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}
