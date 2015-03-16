package com.meigwilym.ycodwrcanu;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mei on 10/03/2015.
 */
public class MyDbHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "codwrcanu.db";
    private static final String TABLE_SONGS = "songs";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_LYRICS = "lyrics";
    public static final String COLUMN_MP3 = "mp3";
    public static final String COLUMN_TON = "ton";

    public static final String[] ALL_COLUMNS = new String[] {"_id", "title", "lyrics", "mp3", "ton"};

    public MyDbHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion)
    {
        /*
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        onCreate(db);
        */
    }

    public void addSong(Song song)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, song.getTitle());
        values.put(COLUMN_LYRICS, song.getLyrics());
        values.put(COLUMN_MP3, song.getMp3());
        values.put(COLUMN_TON, song.getTon());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SONGS, null, values);
        db.close();

        Log.d("Codwr", "New song inserted");
    }

    /**
     * Find a song from the db
     * @param songid
     * @return
     */
    public Song findSong(int songid)
    {
        String query = "Select * FROM " + TABLE_SONGS + " WHERE " + COLUMN_ID + " =  " + songid + " LIMIT 1;";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Song song = new Song();

        if (cursor.moveToFirst())
        {
            cursor.moveToFirst();
            song.setId(Integer.parseInt(cursor.getString(0)));
            song.setTitle(cursor.getString(1));
            song.setLyrics(cursor.getString(2));
            song.setMp3(cursor.getString(3));
            song.setTon(cursor.getString(4));

            cursor.close();

            Log.w("Codwr", "Song has been found");
        }
        else
        {
            song = null;
            Log.w("Codwr", "No song found");
        }
        db.close();
        return song;
    }

    public Cursor getAllSongs()
    {
        String where = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(false, TABLE_SONGS, ALL_COLUMNS, where, null, null, null, null, null);

        if(cursor != null)
        {
            cursor.moveToFirst();

            Log.d("Codwr", cursor.getString(1));
        }
        else
        {
            Log.w("Codwr", "no songs at all");
        }

        return cursor;
    }

}