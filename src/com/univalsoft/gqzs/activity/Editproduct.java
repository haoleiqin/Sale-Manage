package com.univalsoft.gqzs.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.data.trueproduct_dataBase;

public class Editproduct extends Activity {

	private SQLiteDatabase db;
	private trueproduct_dataBase product_dbhelper;
	private Button ti_jiao222222;
	private EditText name;
	private EditText unit;
	private EditText purchase;
	private EditText sale;
	private EditText category;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_product_true);

		Zhuce();

	}

	private void Zhuce() {
		ti_jiao222222 = (Button) findViewById(R.id.ti_jiao222222);
		name = (EditText) findViewById(R.id.product_name_editText1);
		unit = (EditText) findViewById(R.id.EditText011);
		purchase = (EditText) findViewById(R.id.EditText01);
		sale = (EditText) findViewById(R.id.EditTextMobile);
		category = (EditText) findViewById(R.id.EditText03333);

		
		Intent aa = getIntent();
		String aaaaString = aa.getStringExtra("puanduan");
		if (aaaaString.equals("view")) {

			name.setEnabled(false);
			unit.setEnabled(false);
			purchase.setEnabled(false);
			sale.setEnabled(false);
			category.setEnabled(false);

		}else if(aaaaString.equals("edit")){

			name.setEnabled(true);
			unit.setEnabled(true);
			purchase.setEnabled(true);
			sale.setEnabled(true);
			category.setEnabled(true);
		}

		
		
		Database();

		Button deleteButton01 = (Button) findViewById(R.id.deleteButton01);
		deleteButton01.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent ssIntent = getIntent();
				String nimaString = ssIntent.getStringExtra("name");
				db.delete("product", "name=?", new String[] { nimaString });
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
					values.put("name", name.getText().toString());

					values.put("purchase", purchase.getText().toString());

					values.put("unit", unit.getText().toString());

					values.put("sale", sale.getText().toString());

					values.put("category", category.getText().toString());

					db.update("product", values, "name=?",
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
		product_dbhelper = new trueproduct_dataBase(this, "product.db", null, 1);

		db = product_dbhelper.getWritableDatabase();
		loadProduct();
	}

	public void loadProduct() {
		Intent ssIntent = getIntent();
		Cursor cursor = db.query("product", null, null, null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				String nameaaaaa = cursor.getString(cursor
						.getColumnIndex("name"));
				if (nameaaaaa.equals(ssIntent.getStringExtra("name"))) {

					name.setText(cursor.getString(cursor.getColumnIndex("name")));
					unit.setText(cursor.getString(cursor.getColumnIndex("unit")));
					sale.setText(cursor.getString(cursor.getColumnIndex("sale")));
					category.setText(cursor.getString(cursor
							.getColumnIndex("category")));
					purchase.setText(cursor.getString(cursor
							.getColumnIndex("purchase")));

				}
			} while (cursor.moveToNext());
		}
		cursor.close();
	}
}
