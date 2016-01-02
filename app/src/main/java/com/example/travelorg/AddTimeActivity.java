package com.example.travelorg;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddTimeActivity extends Activity{
	
	private SQLiteDatabase databasesec;
	private DataBaseHelperSec mHelperSec;
	private TextView tv_date,tv_loc;
	private String location,date;
	Button btn_add;
	
	private ArrayList<String> user_id = new ArrayList<String>();
	private ArrayList<String> user_place = new ArrayList<String>();
	private ArrayList<String> user_time = new ArrayList<String>();
	
	private ListView user_list;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_time_activity);
		location = getIntent().getExtras().getString("Location");
		date = getIntent().getExtras().getString("Date");
		tv_loc = (TextView)findViewById(R.id.tv_time_loc);
		tv_date = (TextView)findViewById(R.id.tv_time_date);
		btn_add = (Button)findViewById(R.id.add_timeact_add);
		
		tv_loc.setText(location);
		tv_date.setText(date);
		
		user_list = (ListView)findViewById(R.id.List2);
		user_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), user_place.get(position)+"@"+user_time.get(position), Toast.LENGTH_SHORT).show();
			}
		});
		
		//System.out.println(location);
		mHelperSec = new DataBaseHelperSec(this);
		btn_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),AddTimePage.class);
				i.putExtra("Location", location);
				i.putExtra("Date", date);
				startActivity(i);			}
		});
		
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		displayTimeData();
		super.onResume();
	}


	private void displayTimeData() {
		// TODO Auto-generated method stub
		databasesec = mHelperSec.getWritableDatabase();
		String [] SelectionArg = {String.valueOf(location)};
		Cursor mCursor = databasesec.rawQuery("SELECT * FROM "+DataBaseHelperSec.TABLE_NAME +" WHERE "+DataBaseHelperSec.KEY_LOC+" = ? "+" ORDER BY "+DataBaseHelperSec.KEY_STIME+ " DESC ", SelectionArg);
		user_id.clear();
		user_place.clear();
		user_time.clear();
		if(mCursor.moveToFirst() ){
			
			do {
				
				user_id.add(mCursor.getString(mCursor.getColumnIndex(DataBaseHelperSec.KEY_ID)));
				user_place.add(mCursor.getString(mCursor.getColumnIndex(DataBaseHelperSec.KEY_POINT)));
				user_time.add(mCursor.getString(mCursor.getColumnIndex(DataBaseHelperSec.KEY_STIME)));
				
			} while (mCursor.moveToNext());
			
			
		}
		
		AdapterDisplaySec adpDisec = new AdapterDisplaySec(AddTimeActivity.this, user_id, user_place, user_time);
		user_list.setAdapter(adpDisec);
		mCursor.close();
	}
	
	
	

}
