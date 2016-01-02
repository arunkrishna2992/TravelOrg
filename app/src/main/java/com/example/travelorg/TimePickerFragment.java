package com.example.travelorg;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class TimePickerFragment extends DialogFragment {
	
	OnTimeSetListener ontimeset;
	
	public TimePickerFragment(){
			
	}
	
	private int hour;
	private int minute;	
	
	public void setArguments(Bundle args) {
		// TODO Auto-generated method stub
		 super.setArguments(args);
		  hour = args.getInt("hour");
		  minute = args.getInt("minute");
		  
	}
	
	public void setCallBack(OnTimeSetListener ontime){
		
		ontimeset = ontime;
	}
	
	@Override
	 public Dialog onCreateDialog(Bundle savedInstanceState) {
	  return new TimePickerDialog(getActivity(), STYLE_NORMAL, ontimeset, hour, minute, true);
	 }

	

	


}
