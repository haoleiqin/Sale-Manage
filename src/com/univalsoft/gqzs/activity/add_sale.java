package com.univalsoft.gqzs.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.TimeDateselect.DateTimePickDialogUtil;
import com.univalsoft.gqzs.data.sale_dataBase;

public class add_sale extends Activity {
	private TextView EditText02;
	private Calendar c = null;
	private SQLiteDatabase db;
	private sale_dataBase sale_dbhelper;
	private TextView date;
	private EditText custom;
	private EditText product;
	private EditText notes;
	private EditText price;
	private TextView startDateTime;
	private TextView Timetextview;
	private String initStartDateTime = "2013年9月3日 14:44"; // 初始化开始时间
	private String initEndDateTime = "2014年8月23日 17:44"; // 初始化结束时间
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_saleorder);
		Mainfunction();
		startDateTime = (TextView) findViewById(R.id.edit_date);
		startDateTime.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
						add_sale.this, initEndDateTime);
				dateTimePicKDialog.dateTimePicKDialog(startDateTime);

			}
		});
		
		
		ImageView add_product_back_imageView1 = (ImageView) findViewById(R.id.add_product_back_imageView1);
		add_product_back_imageView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		EditText02 = (TextView) findViewById(R.id.EditText022222);
		EditText02.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(0);
				// Toast.makeText(add_product.this, "dd",0).show();

			}
		});

	}// ONcreate end

	private void Mainfunction() {
		Datamain();
		Zhuce();

	}

	private void Zhuce() {
		Button ti_jiao222222 = (Button) findViewById(R.id.ti_jiao2222345345345343422);

		date = (TextView) findViewById(R.id.edit_date);
		custom = (EditText) findViewById(R.id.edit_custom);
		product = (EditText) findViewById(R.id.edit_product);
		notes = (EditText) findViewById(R.id.edit_notes);
		price = (EditText) findViewById(R.id.edit_price);

		ti_jiao222222.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (date.getText().length() > 0
						&& custom.getText().length() > 0
						&& product.getText().length() > 0
						&& notes.getText().length() > 0
						&& price.getText().length() > 0) {
					ContentValues values = new ContentValues();

					values.put("date ", date.getText().toString());

					values.put("custom ", custom.getText().toString());

					values.put("product ", product.getText().toString());

					values.put("notes ", notes.getText().toString());

					values.put("price", price.getText().toString());
					db.insert("sale", null, values);
					finish();

				} else {
					Toast.makeText(add_sale.this, "Please Complete input ",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private void Datamain() {
		sale_dbhelper = new sale_dataBase(this, "sale.db", null, 1);

		db = sale_dbhelper.getWritableDatabase();

	}

	/**
	 * 创建日期及时间选择对话框
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch (id) {
		case 0:
			c = Calendar.getInstance();
			dialog = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {
						public void onDateSet(DatePicker dp, int year,
								int month, int dayOfMonth) {
							EditText02.setText("" + year + "." + (month + 1)
									+ "." + dayOfMonth + "");
						}
					}, c.get(Calendar.YEAR), // 传入年份
					c.get(Calendar.MONTH), // 传入月份
					c.get(Calendar.DAY_OF_MONTH) // 传入天数
			);
			break;
		case 1:
			c = Calendar.getInstance();
			dialog = new TimePickerDialog(this,
					new TimePickerDialog.OnTimeSetListener() {
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							EditText02.setText("您选择了：" + hourOfDay + "h"
									+ minute + "m");
						}
					}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
					false);
			break;
		}
		return dialog;
	}
}
