package com.example.travelorg;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

@SuppressLint("NewApi") public class AddTimePage extends FragmentActivity {
	
	private Button btnTimePick,btnPointPick,btnSave;
	private EditText et_time,et_place;
	private String ctime,cloc,place,cdate;
	private DataBaseHelperSec mHelperSec;
	private SQLiteDatabase databasesec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_time_page);
		
		cdate=getIntent().getExtras().getString("Date");
		cloc = getIntent().getExtras().getString("Location");
		String[] items1 = cdate.split("-");
		final String year=items1[0];
		final String month=items1[1];
		final String day=items1[2];
		//Toast.makeText(getApplicationContext(), day, Toast.LENGTH_LONG).show();
		btnSave = (Button)findViewById(R.id.btn_Timesave);
		et_time = (EditText)findViewById(R.id.et_stime);
		et_place = (EditText)findViewById(R.id.et_point);
		btnTimePick = (Button)findViewById(R.id.btn_time_pick);
		btnTimePick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showTimePicker();
			}
		});
		
		mHelperSec = new DataBaseHelperSec(this);
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				ctime = et_time.getText().toString().trim();
				place = et_place.getText().toString().trim();
				String[] items2 = ctime.split(":");
				String hour = items2[0];
				String minute = items2[1];
				int ihour = Integer.parseInt(hour);
				int iminute = Integer.parseInt(minute);
				int iyear = Integer.parseInt(year);
				int imonth = Integer.parseInt(month);
				int iday = Integer.parseInt(day);
				
				//Toast.makeText(getApplicationContext(), cdate, Toast.LENGTH_SHORT).show();
				
				if(ctime.length() > 0 && place.length() > 0)
				{
					Calendar c = Calendar.getInstance();
					c.set(iyear, imonth, iday, ihour, iminute);
					
					saveAllDate();
				}
				else
				{
					AlertDialog.Builder alertBuilder2 = new AlertDialog.Builder(AddTimePage.this);
					alertBuilder2.setTitle("Invalid Data");
					alertBuilder2.setMessage("Enter Valid Time and Place");
					alertBuilder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					}); 
						
						alertBuilder2.create().show();
				}
			
			}
		});
	}

	protected void saveAllDate() {
		// TODO Auto-generated method stub
		databasesec = mHelperSec.getWritableDatabase();
		ContentValues val = new ContentValues();
		
		val.put(DataBaseHelperSec.KEY_DATE, cdate);
		val.put(DataBaseHelperSec.KEY_LOC, cloc);
		val.put(DataBaseHelperSec.KEY_POINT,place );
		val.put(DataBaseHelperSec.KEY_STIME, ctime);
		
		System.out.println("");
		
		databasesec.insert(DataBaseHelperSec.TABLE_NAME, null, val);
		databasesec.close();
		finish();
	}

	protected void showTimePicker() {
		// TODO Auto-generated method stub
		TimePickerFragment time = new TimePickerFragment();
		
		Calendar calender = Calendar.getInstance();
		  Bundle args = new Bundle();
		  args.putInt("hour", calender.get(Calendar.HOUR_OF_DAY));
		  args.putInt("minute", calender.get(Calendar.MINUTE));
		 // args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		  time.setArguments(args);
		  /**
		   * Set Call back to capture selected date
		   */
		  time.setCallBack(ontime);
		  time.show(getSupportFragmentManager(), "time");
	}
	
	OnTimeSetListener ontime = new OnTimeSetListener() {
		  
//		   et_date.setText( String.valueOf(year) + ":" + String.valueOf(monthOfYear)
//		       + "-" + String.valueOf(dayOfMonth));
//		  
		  

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			
			et_time.setText(String.valueOf(hourOfDay)+":"+String.valueOf(minute));
			//Toast.makeText(getApplicationContext(), String.valueOf(hourOfDay)+":"+String.valueOf(minute), Toast.LENGTH_LONG).show();
			
		}
		 };
	
}
	


