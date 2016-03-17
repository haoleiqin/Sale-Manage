package com.univalsoft.gqzs.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sale_dataBase extends SQLiteOpenHelper {
	public static final String name = "sale";
	private static final int version = 1;
	private Context mContext;
	private sale_dataBase sale_dataBase;
	private SQLiteDatabase db;
	public static final String CREATE_BOOK = "create table sale("
			+ "shaoyou_Id integer primary key autoincrement, " + "date  text, "
			+ "custom  text, " + "product  text, " + "notes  text, "
			+ "price text) ";

	public static String create_data = "";

	public sale_dataBase(Context context) {
		super(context, name, null, version);
		mContext=context;
	}

	public sale_dataBase(Context context, String name,
			SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);

		mContext = context;
	}

	public sale_dataBase(Context context, String name,
			SQLiteDatabase.CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/* 创建白表 */
		db.execSQL(CREATE_BOOK);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/* 更新表 */
		// db.execSQL( "DROP TABLE IF EXISTS " + TB_NAME );
		onCreate(db);
	}

	public sale_dataBase open() {
		if (null == sale_dataBase) {
			sale_dataBase = new sale_dataBase(mContext);
		}
		db = sale_dataBase.getWritableDatabase();
		return this;
	}

	/* 关闭数据库 */
	public void close() {
		db.close();
		sale_dataBase.close();
	}
	public Cursor queryAll(String tableName){
		return db.query(tableName, null, null, null, null, null, null);
	}
}
