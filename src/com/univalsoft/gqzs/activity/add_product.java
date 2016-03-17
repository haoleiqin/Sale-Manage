package com.univalsoft.gqzs.activity;

import java.util.Calendar;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.data.product_dataBase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class add_product extends Activity {
	private TextView EditText02;
	private Calendar c = null;
	private SQLiteDatabase db;
	private product_dataBase product_dbhelper;
	private EditText name;
	private EditText address;
	private EditText mobile;
	private EditText phone;
	private EditText email;
	private EditText whatsapp;
	private EditText opening;

	private TextView Timetextview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_product);
		Mainfunction();

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
		// p_price = (EditText) findViewById(R.id.EditText011);
		// p_id = (EditText) findViewById(R.id.EditText01);
		// ptype = (EditText) findViewById(R.id.EditText033332);
		// sale_rate = (EditText) findViewById(R.id.EditText03333);
		//
		// Timetextview = (TextView) findViewById(R.id.EditText022222);
		//
		// product_nameEditText = (EditText)
		// findViewById(R.id.product_name_editText1);
		Button ti_jiao222222 = (Button) findViewById(R.id.ti_jiao222222);

		// /////////
		name = (EditText) findViewById(R.id.product_name_editText1);
		address = (EditText) findViewById(R.id.EditText011);
		mobile = (EditText) findViewById(R.id.EditText01);
		phone = (EditText) findViewById(R.id.EditTextMobile);
		email = (EditText) findViewById(R.id.EditText03333);
		whatsapp = (EditText) findViewById(R.id.EditText033332);
		opening = (EditText) findViewById(R.id.EditText03);

		ti_jiao222222.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (name.getText().length() > 0
						&& mobile.getText().length() > 0
						&& address.getText().length() > 0
						&& phone.getText().length() > 0
						&& email.getText().length() > 0
						&& whatsapp.getText().length() > 0
						&& opening.getText().length() > 0) {
					ContentValues values = new ContentValues();

					values.put("name", name.getText().toString());

					values.put("address", address.getText().toString());

					values.put("mobile", mobile.getText().toString());

					values.put("phone", phone.getText().toString());

					values.put("email", email.getText().toString());

					values.put("whatsapp", whatsapp.getText().toString());

					values.put("opening", opening.getText().toString());

					db.insert("custom", null, values);
					finish();

				} else {
					Toast.makeText(add_product.this, "Please Complete input ",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private void Datamain() {
		product_dbhelper = new product_dataBase(this, "custom.db", null, 1);

		db = product_dbhelper.getWritableDatabase();
		//
		// ContentValues values = new ContentValues();
		// EditText product_nameEditText = (EditText)
		// findViewById(R.id.product_name_editText1);
		// values.put("title", "產品名字");
		// values.put("neirong", "1");
		// values.put("shijian1", "2");
		//
		// db.insert("product", null, values);

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
