package com.meigwilym.ycodwrcanu;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends ActionBarActivity {

    private CodwrCanuDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // populate list view with rows from db
        db = new CodwrCanuDatabase(this);
        Cursor cursor = db.getSongs();

        String[] fromFieldNames = new String[] {db.COLUMN_ID, db.COLUMN_TITLE};
        int[] toViewIDs = new int[] {R.id.textViewId, R.id.textViewItemTitle};

        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_item_layout, cursor, fromFieldNames, toViewIDs, 0);

        final ListView listView = (ListView) findViewById(R.id.listSongs);
        listView.setAdapter(myCursorAdapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> myAdapter, View view, int position, long id) {

                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                /*
                TextView txtName = (TextView) view.findViewById(R.id.textViewId);
                */
                Intent intent = new Intent(ListActivity.this, SongActivity.class);
                // Pass all data rank
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndexOrThrow(db.COLUMN_ID)));
                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
            Intent launchNewIntent = new Intent(ListActivity.this, CreditActivity.class);
            startActivityForResult(launchNewIntent, 0);
            startActivity(launchNewIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
