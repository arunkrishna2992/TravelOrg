package com.example.travelorg;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class AdapterDisplay extends BaseAdapter
{
	private Context mContext;
	private ArrayList<String> id;
	private ArrayList<String> location;
	private ArrayList<String> date;

	

	public AdapterDisplay(Context c, ArrayList<String> userId,
			ArrayList<String> user_loc, ArrayList<String> user_date) {
		// TODO Auto-generated constructor stub
		
		this.mContext = c;
		this.id = userId;
		this.location= user_loc;
		this.date = user_date;
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
	public View getView(int position, View child, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder mHolder;
		LayoutInflater layoutInflater;
		if (child == null) {
			layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			child = layoutInflater.inflate(R.layout.listcell, null);
			mHolder = new Holder();
			mHolder.txt_id = (TextView) child.findViewById(R.id.txt_id);
			mHolder.txt_loc = (TextView) child.findViewById(R.id.txt_loc);
			mHolder.txt_date = (TextView) child.findViewById(R.id.txt_date);
			child.setTag(mHolder);
		} else {
			mHolder = (Holder) child.getTag();
		}
		mHolder.txt_id.setText(id.get(position));
		mHolder.txt_loc.setText(location.get(position));
		mHolder.txt_date.setText(date.get(position));

		return child;
	}
	public class Holder {
		TextView txt_id;
		TextView txt_loc;
		TextView txt_date;
	}

}
