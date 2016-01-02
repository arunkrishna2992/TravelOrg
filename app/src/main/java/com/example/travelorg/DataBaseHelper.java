package com.example.travelorg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	
	static String DATABASE_NAME="usermaindata";
	public static final String TABLE_NAME="details";
	public static final String KEY_DATE="date";
	public static final String KEY_LOC="location";
	public static final String KEY_ID="id";

	public DataBaseHelper(Context context) {
		super(context,DATABASE_NAME, null, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+
				" INTEGER PRIMARY KEY, "+KEY_DATE+" TEXT, "+KEY_LOC+" TEXT) ";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
		onCreate(db);

	}

}
