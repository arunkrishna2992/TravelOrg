package com.example.travelorg;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterDisplaySec extends BaseAdapter{
	
	private Context mContext;
	private ArrayList<String> id;
	private ArrayList<String> place;
	private ArrayList<String> time;
	
	public AdapterDisplaySec(Context c, ArrayList<String> userId,
			ArrayList<String> user_place, ArrayList<String> user_time) {
		// TODO Auto-generated constructor stub
		
		this.mContext = c;
		this.id = userId;
		this.place= user_place;
		this.time = user_time;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return id.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View childSec, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder mHolder;
		LayoutInflater layoutInflater;
		if (childSec == null) {
			layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			childSec = layoutInflater.inflate(R.layout.listcell2, null);
			mHolder = new Holder();
			mHolder.txt_id2 = (TextView) childSec.findViewById(R.id.txt_id2);
			mHolder.txt_place = (TextView) childSec.findViewById(R.id.txt_place);
			mHolder.txt_time = (TextView) childSec.findViewById(R.id.txt_time);
			childSec.setTag(mHolder);
		} else {
			mHolder = (Holder) childSec.getTag();
		}
		mHolder.txt_id2.setText(id.get(position));
		mHolder.txt_place.setText(place.get(position));
		mHolder.txt_time.setText(time.get(position));

		return childSec;
	}
	
	public class Holder {
		TextView txt_id2;
		TextView txt_place;
		TextView txt_time;
	}

}
