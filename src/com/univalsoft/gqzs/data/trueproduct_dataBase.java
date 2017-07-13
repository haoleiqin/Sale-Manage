package com.univalsoft.gqzs.data;

import com.univalsoft.gqzs.R;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by 少游 on 2015/12/28.
 */
public class trueproduct_dataBase extends SQLiteOpenHelper {
	private static final String tag = "DBSQLiteHelper";
	public static final String name = "product";
	private static final String ID = "produc_Id";
	private static final String title = "title";
	private static final String neirong = "neirong";
	private static final String shijian1 = "shijian1";
	private static final String shijian2 = "shijian2";
	private static final String shijian_yuji = "shijian_yuji";
	private static final String didian = "didian";
	private static final String jindu = "jindu";
	private static final int version = 1;
	private Context mContext;
	private trueproduct_dataBase trueproduct_dataBase;
	private SQLiteDatabase db;
	/**
	 * name=title p_price =neirong p_id = shijian1 ptype = shijian2 sale_rate =
	 * shijian_yuji
	 * 
	 * Timetextview didian
	 */

	public static final String CREATE_BOOK = "create table product("
			+ "shaoyou_Id integer primary key autoincrement, " +
			"name text, "
			+ "unit text, "
			+ "purchase text, " 
			+ "sale text, "
			+ "category text) ";

	public static String create_data = "";

	public trueproduct_dataBase(Context context) {
		super(context, name, null, version);
		mContext=context;
		Log.d("shaoyou", "我的数据库构造器");

	}

	public trueproduct_dataBase(Context context, String name,
			SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);

		mContext = context;
	}

	public trueproduct_dataBase(Context context, String name,
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
		/* 更新表 */
		// db.execSQL( "DROP TABLE IF EXISTS " + TB_NAME );
		Log.d("shaoyou", "更新表");
		onCreate(db);
	}

	public trueproduct_dataBase open() {
		if (null == trueproduct_dataBase) {
			trueproduct_dataBase = new trueproduct_dataBase(mContext);
		}
		db = trueproduct_dataBase.getWritableDatabase();
		return this;
	}

	/* 关闭数据库 */
	public void close() {
		db.close();
		trueproduct_dataBase.close();
	}
	public Cursor queryAll(String tableName){
		return db.query(tableName, null, null, null, null, null, null);
	}
}
