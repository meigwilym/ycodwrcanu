package com.meigwilym.ycodwrcanu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


public class SongActivity extends ActionBarActivity {

    private CodwrCanuDatabase db;

    int id;
    String[] title;
    String[] lyrics;
    TextView songTitle;
    TextView songLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        db = new CodwrCanuDatabase(this);
        Song song = db.findSong(id);

        setTitle(song.getTitle());

        songLyrics = (TextView) findViewById(R.id.textViewLyrics);
        songLyrics.setText(song.getLyrics());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_song, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent launchNewIntent = new Intent(SongActivity.this, CreditActivity.class);
            startActivityForResult(launchNewIntent, 0);
            startActivity(launchNewIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
