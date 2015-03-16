package com.meigwilym.ycodwrcanu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.SyncStateContract;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Mei on 11/03/2015.
 */
public class CodwrCanuDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "codwrcanu.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONGS = "songs";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_LYRICS = "lyrics";
    public static final String COLUMN_MP3 = "mp3";
    public static final String COLUMN_TON = "ton";

    public static final String[] ALL_COLUMNS = new String[] {"_id", "title", "lyrics", "mp3", "ton"};

    public CodwrCanuDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getSongs()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"_id","title","mp3", "ton"};

        qb.setTables(TABLE_SONGS);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, (String) "title"); // order by title

        c.moveToFirst();
        return c;
    }

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

            int lyricsIndex = cursor.getColumnIndex(COLUMN_LYRICS);

            byte[] blobs = cursor.getBlob(2);
            String blobString = new String(blobs);
            song.setLyrics(blobString);

            song.setMp3(cursor.getString(3));
            song.setTon(cursor.getString(4));

            cursor.close();
        }
        else
        {
            song = null;
        }
        db.close();
        return song;
    }
}
