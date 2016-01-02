package com.example.travelorg;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

@SuppressLint("NewApi") public class DatePickerFragment extends DialogFragment {
	
	OnDateSetListener ondateset;
	
	public DatePickerFragment() {
	
	 }
	
	
	private int year, month, day;

	public void setArguments(Bundle args) {
		// TODO Auto-generated method stub
		 super.setArguments(args);
		  year = args.getInt("year");
		  month = args.getInt("month");
		  day = args.getInt("day");
	}

	public void setCallBack(OnDateSetListener ondate) {
		// TODO Auto-generated method stub
		ondateset = ondate;
	}
	
	@Override
	 public Dialog onCreateDialog(Bundle savedInstanceState) {
	  return new DatePickerDialog(getActivity(), ondateset, year, month, day);
	 }

	

}
