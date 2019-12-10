package com.example.shooter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shooter.database.PlayerTable;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private List<PlayerTable> players;
    LayoutInflater infliter;
    Context context;

    public CustomAdapter(Context context, List<PlayerTable> players){
        this.context = context;
        this.players = players;
        infliter = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        try{
            return players.size();
        }catch (Exception e){

        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return players.get(i);
    }

    @Override
    public long getItemId(int i) {
        return players.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = infliter.inflate(R.layout.list_item, null);
        TextView position = view.findViewById(R.id.position);
        position.setText(i+1 + ".");

        TextView name = view.findViewById(R.id.name);
        name.setText(players.get(i).getName());

        TextView score = view.findViewById(R.id.score);
        score.setText(players.get(i).getScore() + "");
        return view;
    }
}
