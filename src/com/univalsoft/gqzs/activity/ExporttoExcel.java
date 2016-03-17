package com.univalsoft.gqzs.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.backup.CommonUtil;
import com.univalsoft.gqzs.bean.Customers;
import com.univalsoft.gqzs.bean.ProuctsBean;
import com.univalsoft.gqzs.bean.SaleBean;
import com.univalsoft.gqzs.data.ExcelUtils;
import com.univalsoft.gqzs.data.ProExcelUtils;
import com.univalsoft.gqzs.data.SaleExcelUtils;
import com.univalsoft.gqzs.data.product_dataBase;
import com.univalsoft.gqzs.data.sale_dataBase;
import com.univalsoft.gqzs.data.trueproduct_dataBase;

public class ExporttoExcel extends Activity implements OnClickListener {
	private TextView back;
	private RelativeLayout customer, product, sales;
	private product_dataBase cusData;
	private SQLiteDatabase cusDB;
	private SQLiteDatabase proDB;
	private SQLiteDatabase saleDB;
	private sale_dataBase saleData;
	private trueproduct_dataBase proData;
	private List<Customers> cusList = null;
	private List<ProuctsBean> proList = null;
	private List<SaleBean> saleList = null;
	private File file;
	private String[] Custitle = { "id", "name", "addrss", "mobile", "phone",
			"e-mail", "what'sapp", "opening" };
	private String[] Protitle = { "id", "name", "unit", "purchase", "sale",
			"category" };
	private String[] Saletitle = { "id", "date", "custom", "product", "notes",
			"price" };

	private AlertDialog dialog;
	private LayoutInflater inflater;
	private Context context;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch ((Integer) msg.obj) {
			case -1:
				dialog.dismiss();
				CommonUtil.Toast(context, "Export Custom Success");
				break;
			case 0:
				dialog.dismiss();
				CommonUtil.Toast(context, "Export Sales Success");
				break;
			case 1:
				dialog.dismiss();
				CommonUtil.Toast(context, "Export Product Success");
				break;

			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.export);
		initView();
		openData();context=this;
		inflater = LayoutInflater.from(context);
	}

	@SuppressLint("CutPasteId")
	private void initView() {

		back = (TextView) findViewById(R.id.textView2);
		back.setOnClickListener(this);
		customer = (RelativeLayout) findViewById(R.id.relativeLayout1);
		customer.setOnClickListener(this);
		product = (RelativeLayout) findViewById(R.id.RelativeLayout02);
		product.setOnClickListener(this);
		sales = (RelativeLayout) findViewById(R.id.RelativeLayout01);
		sales.setOnClickListener(this);

	}

	/**
	 * 开启数据库
	 */
	private void openData() {
		cusData = new product_dataBase(this, "custom.db", null, 1);
		cusDB = cusData.getWritableDatabase();
		proData = new trueproduct_dataBase(this, "product.db", null, 1);
		proDB = proData.getWritableDatabase();
		saleData = new sale_dataBase(this, "sale.db", null, 1);
		saleDB = saleData.getWritableDatabase();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textView2:
			finish();
			break;
		case R.id.relativeLayout1:

			View view0 = inflater.inflate(R.layout.progress_dialog, null);
			((TextView) view0.findViewById(R.id.progress_msg))
					.setText(" 备 份 中 . . .");
			dialog = new AlertDialog.Builder(context).setView(view0).create();
			dialog.show();
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(699);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// int flag = flieMgr.dbFileOperation("backup", "contact");
					// Log.e(" MainActivity ","flag==========="+flag);

					Message msg = new Message();
					msg.obj = -1;
					handler.sendMessage(msg);
				}
			}).start();

			excelCustomer();
			break;
		case R.id.RelativeLayout02:

			View view0111 = inflater.inflate(R.layout.progress_dialog, null);
			((TextView) view0111.findViewById(R.id.progress_msg))
					.setText(" 备 份 中 . . .");
			dialog = new AlertDialog.Builder(context).setView(view0111)
					.create();
			dialog.show();
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(699);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// int flag = flieMgr.dbFileOperation("backup", "contact");
					// Log.e(" MainActivity ","flag==========="+flag);

					Message msg = new Message();
					msg.obj = 0;
					handler.sendMessage(msg);
				}
			}).start();

			excelProduct();
			break;
		case R.id.RelativeLayout01:

			View view011 = inflater.inflate(R.layout.progress_dialog, null);
			((TextView) view011.findViewById(R.id.progress_msg))
					.setText(" 备 份 中 . . .");
			dialog = new AlertDialog.Builder(context).setView(view011).create();
			dialog.show();
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(699);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// int flag = flieMgr.dbFileOperation("backup", "contact");
					// Log.e(" MainActivity ","flag==========="+flag);

					Message msg = new Message();
					msg.obj = 1;
					handler.sendMessage(msg);
				}
			}).start();

			excelSale();
			break;
		default:
			break;
		}
	}

	private void excelCustomer() {

		if (getProData() == null) {
			Toast.makeText(ExporttoExcel.this, "Data is null", 0).show();
		} else {

			List<Customers> sibiCustomers = getCusData();

			if (sibiCustomers.size() > 0) {

				file = new File(getSDPath() + "/Family");
				makeDir(file);
				ExcelUtils.setExcelName("Customer");
				ExcelUtils.initExcel(file.toString() + "/customer.xls",
						Custitle);
				ExcelUtils.writeObjListToExcel(getCusData(), getSDPath()
						+ "/Family/customer.xls", this);
			} else {
				Toast.makeText(this, "Data is null", 0).show();
			}
		}

	}

	private void excelProduct() {
		List<ProuctsBean> sibiCustomers = getProData();
		if (sibiCustomers.size() > 0) {
			file = new File(getSDPath() + "/Family");
			makeDir(file);
			ProExcelUtils.setExcelName("Product");
			ProExcelUtils.initExcel(file.toString() + "/product.xls", Protitle);
			ProExcelUtils.writeObjListToExcel(sibiCustomers, getSDPath()
					+ "/Family/product.xls", this);
		} else {
			Toast.makeText(this, "Data is null", 0).show();
		}
	}

	private void excelSale() {
		List<SaleBean> sibiCustomers = getSaleData();
		if (sibiCustomers.size() > 0) {

			file = new File(getSDPath() + "/Family");
			makeDir(file);
			SaleExcelUtils.setExcelName("Sale");
			SaleExcelUtils.initExcel(file.toString() + "/sale.xls", Saletitle);
			SaleExcelUtils.writeObjListToExcel(sibiCustomers, getSDPath()
					+ "/Family/sale.xls", this);
		} else {
			Toast.makeText(this, "Data is null", 0).show();
		}
	}

	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdirs();
	}

	public String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
		}
		String dir = sdDir.toString();
		return dir;

	}

	@Override
	protected void onStop() {
		super.onStop();
		// cusData.close();
		// proData.close();
		// saleData.close();
	}

	private List<Customers> getCusData() {
		cusList = new ArrayList<Customers>();
		Customers customers = null;
		Cursor cursor = cusDB.query("custom", null, null, null, null, null,
				null);

		if (cursor.moveToFirst()) {
			do {
				customers = new Customers();
				customers.setId(cursor.getString(cursor
						.getColumnIndex("shaoyou_Id")));
				customers.setName(cursor.getString(cursor
						.getColumnIndex("name")));
				customers.setAddress(cursor.getString(cursor
						.getColumnIndex("address")));
				customers.setMobile(cursor.getString(cursor
						.getColumnIndex("mobile")));
				customers.setPhone(cursor.getString(cursor
						.getColumnIndex("phone")));
				customers.seteMail(cursor.getString(cursor
						.getColumnIndex("email")));
				customers.setWhatapp(cursor.getString(cursor
						.getColumnIndex("whatsapp")));

				customers.setOpening(cursor.getString(cursor
						.getColumnIndex("opening")));

				cusList.add(customers);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return cusList;
	}

	private List<ProuctsBean> getProData() {
		proList = new ArrayList<ProuctsBean>();
		ProuctsBean prouctsBean = null;
		Cursor cursor = proDB.query("product", null, null, null, null, null,
				null);

		if (cursor.moveToFirst()) {
			do {
				prouctsBean = new ProuctsBean();
				prouctsBean.setId(cursor.getString(cursor
						.getColumnIndex("shaoyou_Id")));
				prouctsBean.setName(cursor.getString(cursor
						.getColumnIndex("name")));
				prouctsBean.setUnit(cursor.getString(cursor
						.getColumnIndex("unit")));
				prouctsBean.setPurcharse(cursor.getString(cursor
						.getColumnIndex("purchase")));
				prouctsBean.setSale(cursor.getString(cursor
						.getColumnIndex("sale")));
				prouctsBean.setCategory(cursor.getString(cursor
						.getColumnIndex("category")));

				proList.add(prouctsBean);
			} while (cursor.moveToNext());
		}
		cursor.close();
//		Log.d("lucheng", "有数据" + proList.get(0).getCategory());
		return proList;
	}

	private List<SaleBean> getSaleData() {
		saleList = new ArrayList<SaleBean>();
		SaleBean sales = null;
		Cursor cursor = saleDB
				.query("sale", null, null, null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				sales = new SaleBean();
				sales.setId(cursor.getString(cursor
						.getColumnIndex("shaoyou_Id")));
				sales.setDate(cursor.getString(cursor.getColumnIndex("date")));
				sales.setCustom(cursor.getString(cursor
						.getColumnIndex("custom")));
				sales.setProduct(cursor.getString(cursor
						.getColumnIndex("product")));
				sales.setNotes(cursor.getString(cursor.getColumnIndex("notes")));
				sales.setPrice(cursor.getString(cursor.getColumnIndex("price")));

				saleList.add(sales);
			} while (cursor.moveToNext());
		}
//		Log.d("lucheng", "有数据");
		cursor.close();
		return saleList;
	}
}
