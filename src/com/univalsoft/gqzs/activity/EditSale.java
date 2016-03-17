package com.univalsoft.gqzs.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.data.sale_dataBase;

public class EditSale extends Activity {
	private SQLiteDatabase db;
	private sale_dataBase sale_dbhelper;
	private Button ti_jiao222222;
	private EditText date;
	private EditText custom;
	private EditText product;
	private EditText notes;
	private EditText price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_saleorder);

		Zhuce();

	}

	private void Zhuce() {
		ti_jiao222222 = (Button) findViewById(R.id.ti_jiao222222);
		date = (EditText) findViewById(R.id.edit_date);
		custom = (EditText) findViewById(R.id.edit_custom);
		product = (EditText) findViewById(R.id.edit_product);
		notes = (EditText) findViewById(R.id.edit_notes);
		price = (EditText) findViewById(R.id.edit_price);

		Intent aa = getIntent();
		String aaaaString = aa.getStringExtra("puanduan");
		if (aaaaString.equals("view")) {

			date.setEnabled(false);
			custom.setEnabled(false);
			product.setEnabled(false);
			notes.setEnabled(false);
			price.setEnabled(false);

		}else if(aaaaString.equals("edit")){

			date.setEnabled(true);
			custom.setEnabled(true);
			product.setEnabled(true);
			notes.setEnabled(true);
			price.setEnabled(true);
		}

		Database();

		Button deleteButton01 = (Button) findViewById(R.id.deleteButton01);
		deleteButton01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent ssIntent = getIntent();
				String nimaString = ssIntent.getStringExtra("name");
				db.delete("sale", "date=?", new String[] { nimaString });
				finish();

			}
		});
		ti_jiao222222.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent ssIntent = getIntent();
				String nimaString = ssIntent.getStringExtra("name");
				ContentValues values = new ContentValues();
				if (nimaString.equals(date.getText().toString())) {
					values.put("custom", custom.getText().toString());

					values.put("product", product.getText().toString());

					values.put("notes", notes.getText().toString());

					values.put("price", price.getText().toString());

					db.update("sale", values, "date=?",
							new String[] { nimaString });

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
		sale_dbhelper = new sale_dataBase(this, "sale.db", null, 1);

		db = sale_dbhelper.getWritableDatabase();
		loadProduct();
	}

	public void loadProduct() {
		Intent ssIntent = getIntent();

		Cursor cursor = db.query("sale", null, null, null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				String nameaaaaa = cursor.getString(cursor
						.getColumnIndex("date"));
				if (nameaaaaa.equals(ssIntent.getStringExtra("name"))) {
					Log.d("lucheng", "到了");
					date.setText(nameaaaaa);
					custom.setText(cursor.getString(cursor
							.getColumnIndex("custom")));
					product.setText(cursor.getString(cursor
							.getColumnIndex("product")));
					notes.setText(cursor.getString(cursor
							.getColumnIndex("notes")));
					price.setText(cursor.getString(cursor
							.getColumnIndex("price")));

				}
			} while (cursor.moveToNext());
		}

	}
}
