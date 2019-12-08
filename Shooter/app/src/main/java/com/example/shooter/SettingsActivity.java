package com.example.shooter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

public class SettingsActivity extends Activity {

    private Spinner difficulties;
    private CheckBox sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        String sharedPrefFile = "prefFile";
        final SharedPreferences preferences = getApplicationContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        difficulties = findViewById(R.id.difficulty_spinner);
        sound = findViewById(R.id.sound_option);

        String[] diffs = new String[] {"EASY", "HARD"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, diffs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulties.setAdapter(adapter);

        for(int i = 0; i < adapter.getCount(); i++){
            if(adapter.getItem(i).equals(GameSettings.getInstance().getStrDifficulty())){
                difficulties.setSelection(i);
            }
        }

        if(GameSettings.getInstance().isSound()){
            sound.setText("on");
            sound.setChecked(true);
        }
        else{
            sound.setText("off");
            sound.setChecked(false);
        }

        difficulties.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GameSettings.getInstance().setStrDifficulty(parent.getItemAtPosition(position).toString());
                SharedPreferences.Editor preferencesEditor = preferences.edit();
                preferencesEditor.putString("difficulty", GameSettings.getInstance().getStrDifficulty());
                preferencesEditor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor preferencesEditor = preferences.edit();
                if(isChecked){
                    GameSettings.getInstance().setSound(true);
                    preferencesEditor.putBoolean("sound", true);
                    sound.setText("on");
                }
                else{
                    GameSettings.getInstance().setSound(false);
                    preferencesEditor.putBoolean("sound", false);
                    sound.setText("off");
                }
                preferencesEditor.apply();
            }
        });
    }
}
