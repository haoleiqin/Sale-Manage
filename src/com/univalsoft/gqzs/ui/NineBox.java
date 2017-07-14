package com.univalsoft.gqzs.ui;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.activity.Backup;
import com.univalsoft.gqzs.activity.ExporttoExcel;
import com.univalsoft.gqzs.activity.Operation_Customer;
import com.univalsoft.gqzs.activity.Product;
import com.univalsoft.gqzs.activity.Sales;

public class NineBox extends Activity {
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ȥ������
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activitymain);
		GridView gridview = (GridView) findViewById(R.id.gridview);
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 1; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (i == 1) {
				map.put("ItemImage", R.drawable.g11);
				map.put("ItemText", getResources()
						.getString(R.string.gridview1));
			}
			if (i == 2) {
				map.put("ItemImage", R.drawable.g12);
				map.put("ItemText", getResources()
						.getString(R.string.gridview2));
			}
			if (i == 3) {
				map.put("ItemImage", R.drawable.g13);
				map.put("ItemText", getResources()
						.getString(R.string.gridview3));
			}
			if (i == 4) {
				map.put("ItemImage", R.drawable.g14);
				map.put("ItemText", getResources()
						.getString(R.string.gridview4));
			}
			if (i == 5) {
				map.put("ItemImage", R.drawable.g15);
				map.put("ItemText", getResources()
						.getString(R.string.gridview5));
			}
			if (i == 6) {
				map.put("ItemImage", R.drawable.g16);
				map.put("ItemText", getResources()
						.getString(R.string.gridview6));
			}
			if (i == 7) {
				map.put("ItemImage", R.drawable.g22);
				map.put("ItemText", getResources()
						.getString(R.string.gridview7));
			}
			if (i == 8) {
				map.put("ItemImage", R.drawable.g18);
				map.put("ItemText", getResources()
						.getString(R.string.gridview8));
			}
			if (i == 9) {
				map.put("ItemImage", R.drawable.g19);
				map.put("ItemText", getResources()
						.getString(R.string.gridview9));
			}
			lstImageItem.add(map);

		}

		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.grid_item, new String[] { "ItemImage", "ItemText" },
				new int[] { R.id.ItemImage, R.id.ItemText });

		gridview.setAdapter(saImageItems);
		// ���þŹ���ļ���������˾����
		gridview.setOnItemClickListener(new ItemClickListener());
	}

	class ItemClickListener implements OnItemClickListener {

		@SuppressWarnings("unchecked")
		public void onItemClick(AdapterView<?> arg0,// The AdapterView where the
													// click happened
				View arg1,// The view within the AdapterView that was clicked
				int arg2,// The position of the view in the adapter
				long arg3// The row id of the item that was clicked
		) {

			HashMap<String, Object> item = (HashMap<String, Object>) arg0
					.getItemAtPosition(arg2);

			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview1))) {
				// Toast.makeText(NineBox.this, R.string.gridview1,
				// Toast.LENGTH_LONG).show();

				Intent intent = new Intent(NineBox.this,
						Operation_Customer.class);
				startActivity(intent);
			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview2))) {
				Intent intent = new Intent(NineBox.this, Product.class);
				startActivity(intent);
			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview3))) {
				Intent intent = new Intent(NineBox.this, Sales.class);
				startActivity(intent);
			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview4))) {
				Intent intent = new Intent(NineBox.this, ExporttoExcel.class);
				startActivity(intent);
			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview5))) {
				Intent intent = new Intent(NineBox.this, Backup.class);
				startActivity(intent);
			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview6))) {

				Intent intent = new Intent(NineBox.this, saleKey.class);

				startActivity(intent);

			}

			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview7))) {
				//
				// // TODO Auto-generated method stub
				// AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(
				// NineBox.this);
				// dialogBuilder.setTitle("Backup DataBase");
				// dialogBuilder.setMessage("Do you want to take Backup?");
				// dialogBuilder.setCancelable(true);
				// dialogBuilder.setPositiveButton("Yes",
				// new DialogInterface.OnClickListener() {
				//
				// @Override
				// public void onClick(DialogInterface dialog,
				// int which) {
				// finish();
				//
				// }
				// });
				// dialogBuilder.setNegativeButton("No",
				// new DialogInterface.OnClickListener() {
				//
				// @Override
				// public void onClick(DialogInterface dialog,
				// int which) {
				// finish();
				//
				// }
				// });
				// dialogBuilder.show();

				Intent intent = new Intent();
				try {
					ComponentName componetName = new ComponentName(
							"qo.shaoyou.free.qo",
							"qo.shaoyou.free.qo.activity.Main");
					intent.setComponent(componetName);
					startActivity(intent);		finish();

				} catch (Exception e) {
					Toast.makeText(NineBox.this, "Please install Personal Version", Toast.LENGTH_LONG).show();
				}
		
			}

			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview8))) {

			}
			if (item.get("ItemText").equals(
					getResources().getString(R.string.gridview9))) {

			}
		}
	}
}