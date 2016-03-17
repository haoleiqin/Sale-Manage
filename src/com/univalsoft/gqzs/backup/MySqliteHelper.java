package com.univalsoft.gqzs.backup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {

	public static String DBNAME = "sale.db";

	public MySqliteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DBNAME, factory, version);
	}

	public MySqliteHelper(Context context) {
		super(context, DBNAME, null, 1);
	}

	public void onCreate(SQLiteDatabase db) {
		String sql = "create table sale("
				+ "shaoyou_Id integer primary key autoincrement, "
				+ "date  text, "
				+ "custom  text, " 
				+ "product  text, "
				+ "notes  text, "
				+ "price text) ";
		db.execSQL(sql);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "drop  table if exists sale";
		db.execSQL(sql);
	}

}
