package com.univalsoft.gqzs.export;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity {
	private EditText mFoodEdt;
	private EditText mArticlesEdt;
	private EditText mTrafficEdt;
	private EditText mTravelEdt;
	private EditText mClothesEdt;
	private EditText mDoctorEdt;
	private EditText mRenQingEdt;
	private EditText mBabyEdt;
	private EditText mLiveEdt;
	private EditText mOtherEdt;
	private EditText mRemarkEdt;
	private Button mSaveBtn;
	private File file;
	private String[] title = { "Name", "Address", "Mobile", "Phone", "E-Mail",
			"Whatsapp NO", "Opening" };
	private String[] saveData;
	private DBHelper mDbHelper;
	private ArrayList<ArrayList<String>> bill2List;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initData();

		mDbHelper = new DBHelper(this);
		mDbHelper.open();
		bill2List = new ArrayList<ArrayList<String>>();
	}

	@SuppressLint("SimpleDateFormat")
	public void initData() {
		file = new File(getSDPath() + "/Custom Data");
		makeDir(file);
		ExcelUtils.initExcel(file.toString() + "/CustomData.xls", title);
		ExcelUtils.writeObjListToExcel(getBillData(), getSDPath()
				+ "/SaleData/CustomData.xls", this);
	}

	private ArrayList<ArrayList<String>> getBillData() {
		Cursor mCrusor = mDbHelper.exeSql("select * from custom");
		while (mCrusor.moveToNext()) {
			ArrayList<String> beanList = new ArrayList<String>();
			beanList.add(mCrusor.getString(1));
			beanList.add(mCrusor.getString(2));
			beanList.add(mCrusor.getString(3));
			beanList.add(mCrusor.getString(4));
			beanList.add(mCrusor.getString(5));
			beanList.add(mCrusor.getString(6));
			beanList.add(mCrusor.getString(7));
			// beanList.add(mCrusor.getString(8));
			// beanList.add(mCrusor.getString(9));
			// beanList.add(mCrusor.getString(10));
			// beanList.add(mCrusor.getString(11));
			// beanList.add(mCrusor.getString(12));
			bill2List.add(beanList);
		}
		mCrusor.close();
		return bill2List;
	}

	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}

	public String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		sdCardExist = true;
		// boolean sdCardExist = Environment.getExternalStorageState().equals(
		// "mnt/sdcard");
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
			// Environment.getExternalStorageDirectory();
		} else {

		}

		String dir = sdDir.toString();
		return dir;

	}

	private boolean canSave(String[] data) {
		boolean isOk = false;
		for (int i = 0; i < data.length; i++) {
			if (i > 0 && i < data.length) {
				if (!TextUtils.isEmpty(data[i])) {
					isOk = true;
				}
			}
		}
		return isOk;
	}
}
