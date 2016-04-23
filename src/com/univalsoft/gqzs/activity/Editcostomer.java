package com.univalsoft.gqzs.activity;

import java.util.ArrayList;
import java.util.List;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.data.product_dataBase;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Editcostomer extends Activity {
	private EditText product_name;
	private EditText p_price;
	private EditText p_id;
	private EditText ptype;
	private EditText sale_rate;

	private TextView Timetextview;
	private ListView listView;
	private SQLiteDatabase db;
	private product_dataBase product_dbhelper;
	private ArrayAdapter<String> adapter;
	private Button ti_jiao222222;
	private List<String> dataList = new ArrayList<String>();
	private EditText name;
	private EditText address;
	private EditText mobile;
	private EditText phone;
	private EditText email;
	private EditText whatsapp;
	private EditText opening;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_product);

		Zhuce();

	}

	private void Zhuce() {
		ti_jiao222222 = (Button) findViewById(R.id.ti_jiao22222343422);
		name = (EditText) findViewById(R.id.product_name_editText1);
		address = (EditText) findViewById(R.id.EditText011);
		mobile = (EditText) findViewById(R.id.EditText01);
		phone = (EditText) findViewById(R.id.EditTextMobile);
		email = (EditText) findViewById(R.id.EditText03333);
		whatsapp = (EditText) findViewById(R.id.EditText033332);
		opening = (EditText) findViewById(R.id.EditText03);
		

		Intent aa = getIntent();
		String aaaaString = aa.getStringExtra("puanduan");
		
		if (aaaaString.equals("view")) {

			name.setEnabled(false);
			address.setEnabled(false);
			mobile.setEnabled(false);
			phone.setEnabled(false);
			email.setEnabled(false);
			whatsapp.setEnabled(false);
			opening.setEnabled(false);

		}else if(aaaaString.equals("edit")){

			name.setEnabled(true);
			address.setEnabled(true);
			mobile.setEnabled(true);
			phone.setEnabled(true);
			email.setEnabled(true);
			whatsapp.setEnabled(true);
			opening.setEnabled(true);
		}
		
		

		Database();

		Button deleteButton01 = (Button) findViewById(R.id.deleteButton01);
		deleteButton01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent ssIntent = getIntent();
				String nimaString = ssIntent.getStringExtra("name");
				db.delete("custom", "name=?", new String[] { nimaString });
				finish();

			}
		});
		ti_jiao222222.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent ssIntent = getIntent();
				String nimaString = ssIntent.getStringExtra("name");
				ContentValues values = new ContentValues();
				if (nimaString.equals(name.getText().toString())) {
					values.put("address", address.getText().toString());

					values.put("mobile", mobile.getText().toString());

					values.put("phone", phone.getText().toString());

					values.put("email", email.getText().toString());

					values.put("whatsapp", whatsapp.getText().toString());

					values.put("opening", opening.getText().toString());

					db.update("custom", values, "name=?",
							new String[] { nimaString });

					finish();
				} else {
					values.put("name", name.getText().toString());
					values.put("address", address.getText().toString());
					values.put("phone", phone.getText().toString());

					values.put("email", email.getText().toString());

					values.put("whatsapp", whatsapp.getText().toString());

					values.put("opening", opening.getText().toString());

					db.update("custom", values, "mobile=?",
							new String[] { mobile.getText().toString() });

					finish();

				}

			}
		});

		ImageView backButton = (ImageView) findViewById(R.id.add_product_back_imageView1);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
	}

	private void Database() {
		product_dbhelper = new product_dataBase(this, "custom.db", null, 1);

		db = product_dbhelper.getWritableDatabase();
		loadProduct();
	}

	public void loadProduct() {
		Intent ssIntent = getIntent();
		String nimaString = ssIntent.getStringExtra("name");
		List<String> list = new ArrayList<String>();

		Cursor cursor = db.query("custom", null, null, null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				String nameaaaaa = cursor.getString(cursor
						.getColumnIndex("name"));
				if (nameaaaaa.equals(ssIntent.getStringExtra("name"))) {

					String addressstring = cursor.getString(cursor
							.getColumnIndex("address"));

					String mobilestring = cursor.getString(cursor
							.getColumnIndex("mobile"));

					String phonestring = cursor.getString(cursor
							.getColumnIndex("phone"));

					String emailstring = cursor.getString(cursor
							.getColumnIndex("email"));

					String whatsappstring = cursor.getString(cursor
							.getColumnIndex("whatsapp"));

					String openingstring = cursor.getString(cursor
							.getColumnIndex("opening"));

					name.setText(nimaString);
					address.setText(addressstring);
					mobile.setText(mobilestring);
					phone.setText(phonestring);
					email.setText(emailstring);
					whatsapp.setText(whatsappstring);
					opening.setText(openingstring);

				}
			} while (cursor.moveToNext());
		}
		cursor.close();
	}
}
