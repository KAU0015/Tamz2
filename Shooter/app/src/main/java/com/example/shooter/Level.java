package com.example.shooter;

import android.app.Application;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Level {

    public Level(){

    }


    public ArrayList<String> loadLevel(Context context, int num){
        ArrayList<String> level = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("level" + num)));
            String line;
            while ((line = reader.readLine()) != null) {
                level.add(line);
            }
        }
        catch (IOException e) {
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
        }

        return level;
    }

}
