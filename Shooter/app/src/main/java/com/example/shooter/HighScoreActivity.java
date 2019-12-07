package com.example.shooter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.shooter.database.DBHandler;
import com.example.shooter.database.PlayerTable;

import java.util.ArrayList;
import java.util.List;

public class HighScoreActivity extends AppCompatActivity {

    CustomAdapter customAdapter;
    private DBHandler dbHandler;
    private ListView listView;
    private Button restart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        listView = findViewById(R.id.players);
        restart = findViewById(R.id.restart);

        dbHandler = new DBHandler(this);
        customAdapter = new CustomAdapter(getApplicationContext(), dbHandler.selectTop10());
        listView.setAdapter(customAdapter);





    }
}
