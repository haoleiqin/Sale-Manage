package com.univalsoft.gqzs.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 少游 on 2015/12/28.
 */
public class product_dataBase extends SQLiteOpenHelper {
	public static final String name = "custom";
	private static final int version = 1;
	private Context mContext;
	private product_dataBase product_dataBase=null;
	private SQLiteDatabase db;

	public static final String CREATE_BOOK = "create table custom("
			+ "shaoyou_Id integer primary key autoincrement, " + "name text, "
			+ "address text, " + "mobile text, " + "phone text, "
			+ "email text, " + "whatsapp text, " + "opening text) ";

	public static String create_data = "";

	public product_dataBase(Context context) {
		super(context, name, null, version);
		mContext=context;
		Log.d("shaoyou", "我的数据库构造器");

	}

	public product_dataBase(Context context, String name,
			SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);

		mContext = context;
	}

	public product_dataBase(Context context, String name,
			SQLiteDatabase.CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/* 创建白表 */
		db.execSQL(CREATE_BOOK);

		Log.d("shaoyou", "创建表乘公共");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("shaoyou", "更新表");
		onCreate(db);
	}

	public product_dataBase open() {
		if (null == product_dataBase) {
			product_dataBase = new product_dataBase(mContext);
		}
		db = product_dataBase.getWritableDatabase();
		return this;
	}

	/* 关闭数据库 */
	public void close() {
		db.close();
		product_dataBase.close();
	}
	public Cursor queryAll(String tableName){
		return db.query("custom", null, null, null, null, null, null);
	}
}
