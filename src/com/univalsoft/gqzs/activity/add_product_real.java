package com.univalsoft.gqzs.activity;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.data.trueproduct_dataBase;

public class add_product_real extends Activity {
	private TextView EditText02;
	private Calendar c = null;
	private SQLiteDatabase db;
	private trueproduct_dataBase product_dbhelper;
	private EditText product_nameEditText;
	private EditText product_name;
	private EditText p_price;
	private EditText p_id;
	private EditText ptype;
	private EditText sale_rate;
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

		setContentView(R.layout.add_product_true);

		Datamain();

		zhuce();

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

	private void zhuce() {
		Button ti_jiao222222 = (Button) findViewById(R.id.ti_jiao222222);

		// /////////
		name = (EditText) findViewById(R.id.product_name_editText1);
		address = (EditText) findViewById(R.id.EditText011);
		mobile = (EditText) findViewById(R.id.EditText01);
		phone = (EditText) findViewById(R.id.EditTextMobile);
		email = (EditText) findViewById(R.id.EditText03333);
		// whatsapp = (EditText) findViewById(R.id.EditText033332);
		// opening = (EditText) findViewById(R.id.EditText03);
		//

		ti_jiao222222.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (name.getText().length() > 0
						&& mobile.getText().length() > 0
						&& address.getText().length() > 0
						&& phone.getText().length() > 0
						&& email.getText().length() > 0) {
					ContentValues values = new ContentValues();

					values.put("name", name.getText().toString());

					values.put("unit", address.getText().toString());

					values.put("purchase", mobile.getText().toString());

					values.put("sale", phone.getText().toString());

					values.put("category", email.getText().toString());
					db.insert("product", null, values);
					Toast.makeText(add_product_real.this, "Please",
							Toast.LENGTH_SHORT).show();
					finish();

				} else {
					Toast.makeText(add_product_real.this,
							"Please Complete input ", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
	}

	private void Datamain() {
		product_dbhelper = new trueproduct_dataBase(this, "product.db", null, 1);

		db = product_dbhelper.getWritableDatabase();

	}
}
