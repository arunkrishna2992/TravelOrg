package com.example.travelorg;

import java.util.ArrayList;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	private SQLiteDatabase dataBase;
	Button btn_add;
	private DataBaseHelper mHelper;
	
	private ArrayList<String> userId = new ArrayList<String>();
	private ArrayList<String> user_loc = new ArrayList<String>();
	private ArrayList<String> user_date = new ArrayList<String>();
	
	private ListView userList;
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        btn_add = (Button)findViewById(R.id.btnAdd);
        userList = (ListView) findViewById(R.id.List);
        userList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						AddTimeActivity.class);
				i.putExtra("Location", user_loc.get(position));
				i.putExtra("Date", user_date.get(position));
				i.putExtra("ID", userId.get(position));
				//i.putExtra("update", true);
				startActivity(i);
				Toast.makeText(getApplicationContext(), user_loc.get(position), Toast.LENGTH_LONG).show();

			}
		});

		mHelper = new DataBaseHelper(this);
        btn_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent in = new Intent(getApplicationContext(), AddDateActivity.class);
				startActivity(in);
				
			}
		});
    }
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
	protected void onResume() {
		displayData();
		super.onResume();
	}

	/**
	 * displays data from SQLite
	 */
	private void displayData() {
		dataBase = mHelper.getWritableDatabase();
		Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
				+ DataBaseHelper.TABLE_NAME, null);

		userId.clear();
		user_loc.clear();
		user_date.clear();
		if (mCursor.moveToFirst()) {
			do {
				userId.add(mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.KEY_ID)));
				user_loc.add(mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.KEY_LOC)));
				user_date.add(mCursor.getString(mCursor.getColumnIndex(DataBaseHelper.KEY_DATE)));

			} while (mCursor.moveToNext());
		}
		AdapterDisplay adpDis = new AdapterDisplay(MainActivity.this,userId,user_loc,user_date);
		userList.setAdapter(adpDis);
		mCursor.close();
	}

	


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
