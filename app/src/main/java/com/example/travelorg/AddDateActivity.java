package com.example.travelorg;

import java.util.Calendar;





import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi") public class AddDateActivity extends FragmentActivity {
	
	private Button btn_save,btn_pick;
	private EditText et_loc,et_date;
	private DataBaseHelper mHelper;
	private SQLiteDatabase dataBase;
	private String id,location,date;
	final int Date_Dialog_ID=0;
	int cDay,cMonth,cYear; // this is the instances of the current date
	Calendar cDate;
	int sDay,sMonth,sYear; // this is the instances of the entered date
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_activity);
		btn_save=(Button)findViewById(R.id.save_btn);
        et_loc=(EditText)findViewById(R.id.et_location);
        et_date=(EditText)findViewById(R.id.et_date);
        btn_pick=(Button)findViewById(R.id.date_pick);
        btn_pick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDatePicker();		
				
			}
		});
        mHelper = new DataBaseHelper(this);
        btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				location=et_loc.getText().toString().trim();
				date=et_date.getText().toString().trim();
				if(location.length()>0 && date.length()>0)
				{
					saveData();
				}
				else
				{
					AlertDialog.Builder alertBuilder=new AlertDialog.Builder(AddDateActivity.this);
					alertBuilder.setTitle("Invalid Data");
					alertBuilder.setMessage("Please, Enter valid data");
					alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
							
						}
					});
					alertBuilder.create().show();
				}
			}	});
        }
	
	protected void saveData() {
		// TODO Auto-generated method stub
		dataBase=mHelper.getWritableDatabase();
		ContentValues values=new ContentValues();
		
		values.put(DataBaseHelper.KEY_DATE, date);
		values.put(DataBaseHelper.KEY_LOC,location);
		
		
		System.out.println("");
		
			//insert data into database
			dataBase.insert(DataBaseHelper.TABLE_NAME, null, values);
		
		//close database
		dataBase.close();
		finish();
	}

	protected void showDatePicker() {
		// TODO Auto-generated method stub
		DatePickerFragment date = new DatePickerFragment();
		  /**
		   * Set Up Current Date Into dialog
		   */
		  Calendar calender = Calendar.getInstance();
		  Bundle args = new Bundle();
		  args.putInt("year", calender.get(Calendar.YEAR));
		  args.putInt("month", calender.get(Calendar.MONTH));
		  args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
		  date.setArguments(args);
		  /**
		   * Set Call back to capture selected date
		   */
		  date.setCallBack(ondate);
		  date.show(getFragmentManager(), "Date");
		
	}
	
	 OnDateSetListener ondate = new OnDateSetListener() {
		  @Override
		  public void onDateSet(DatePicker view, int year, int monthOfYear,
		    int dayOfMonth) {
//		   Toast.makeText(
//		    AddDateActivity.this, String.valueOf(year) + "-" + String.valueOf(monthOfYear)
//		       + "-" + String.valueOf(dayOfMonth),
//		     Toast.LENGTH_LONG).show();
		   et_date.setText( String.valueOf(year) + "-" + String.valueOf(monthOfYear)
		       + "-" + String.valueOf(dayOfMonth));
		  }
		 };

}
