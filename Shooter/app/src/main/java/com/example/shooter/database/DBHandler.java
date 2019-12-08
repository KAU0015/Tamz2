package com.example.shooter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DBGAME.db";
    public static final String TABLE_NAME = "players";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT,"
                + COLUMN_SCORE + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete(TABLE_NAME, null, null);
        if(deleted == -1) return  false;
        return true;
    }

    public boolean insert(String name, int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SCORE, score);
        long insertedId = db.insert(TABLE_NAME, null, contentValues);
        if (insertedId == -1) return false;
        return true;
    }

    public List<PlayerTable> selectTop10(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery("select * from " + TABLE_NAME +" order by " + COLUMN_SCORE + " desc "+ " limit 10 ", null);
        List<PlayerTable> players = new ArrayList<>();

        try {
            if (res.moveToFirst()) {
                do {
                    PlayerTable p = new PlayerTable();
                    p.setId(res.getInt(0));
                    p.setName(res.getString(1));
                    p.setScore(res.getInt(2));
                    players.add(p);
                } while (res.moveToNext());
            }

        } finally {
            try { res.close(); } catch (Exception ignore) {}
        }

        return players;
    }
}
