package com.univalsoft.gqzs.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.adapter.CommonAdapter;
import com.univalsoft.gqzs.adapter.ViewHolder;
import com.univalsoft.gqzs.bean.Customers;
import com.univalsoft.gqzs.data.product_dataBase;
import com.univalsoft.gqzs.ui.NineBox;

public class Operation_Customer extends Activity implements OnClickListener,
		TextWatcher, OnEditorActionListener {
	// private Button add,back;
	private ImageView add;
	private TextView back;
	private Button delete;
	private EditText editCustomer;
	private SQLiteDatabase db;
	private product_dataBase product_dbhelper;
	private String customer;// 从输入框中获取的客户信息
	private List<Customers> dataList = null;
	private ListView listView;// 查询列表
	private CommonAdapter<Customers> commonAdapter;
	private Handler handler = new Handler();
private TextView  aaa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.operation_customer);
		initData();
		queryCustomer();
		initView();

	}

	@Override
	protected void onStart() {
		super.onStart();
		handler.post(rChange);
	}

	private void initView() {
		add = (ImageView) findViewById(R.id.btn_add_customer);
		back = (TextView) findViewById(R.id.btn_back);
		add.setOnClickListener(this);
		back.setOnClickListener(this);
		delete = (Button) findViewById(R.id.imgbtn_delete);
		delete.setOnClickListener(this);
		editCustomer = (EditText) findViewById(R.id.edit_customer);
		editCustomer.setFocusable(true);
		editCustomer.addTextChangedListener(this);
		editCustomer.setOnEditorActionListener(this);
		listView = (ListView) findViewById(R.id.listview_customer);
		commonAdapter = new CommonAdapter<Customers>(getApplicationContext(),
				dataList, R.layout.item_constomer) {

			@Override
			public void convert(ViewHolder helper, Customers item) {
				helper.setText(R.id.txt_name, "" + item.getName() + "");
				helper.setText(R.id.txt_address, "(" + item.getAddress() + ")");
				helper.setText(R.id.txt_mobile, "" + item.getMobile() + "");
				helper.setText(R.id.txt_mobile2, "Rs. " + item.getOpening()
						+ ".00 Db.");

			}
		};
		listView.setAdapter(commonAdapter);
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * android.widget.AdapterView.OnItemClickListener#onItemClick(android
			 * .widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				  aaa = (TextView) arg1.findViewById(R.id.txt_name);

				// Toast.makeText(Operation_Customer.this,
				// aaa.getText().toString(),
				// Toast.LENGTH_LONG).show();

				aaa.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						menu.add(0, 1, 0, "  View");

						menu.add(0, 2, 0, "  MODIFY");

//						menu.add(0, 3, 0, "  DELETE!");
						menu.add(0, 3, 0, "  CANCEL");
					}
				});
				aaa.showContextMenu();
				

			}
		});

	}
	
	
	
	
	// 选择上下文菜单

		@Override
		public boolean onContextItemSelected(MenuItem item) {

			switch (item.getGroupId()) {

			case 0:

			{

				switch (item.getItemId()) {

				case 1:

				{

					// 点击选项1
//					Intent ins = new Intent(Operation_Customer.this, EditSale.class);
//					ins.putExtra("name", aaa.getText().toString());
//					
//					ins.putExtra("puanduan", "view");
//					startActivity(ins);
//					
//					
					
					
					
					
					Intent ins = new Intent(Operation_Customer.this,
							Editcostomer.class);

					String str = aaa.getText().toString();

					// String resultString = str.substring(6);

					ins.putExtra("name", aaa.getText().toString());
					ins.putExtra("puanduan", "view");
					startActivity(ins);


				}
					break;

				case 2:

				{

					// 点击选项2

					Intent ins = new Intent(Operation_Customer.this,
							Editcostomer.class);

					String str = aaa.getText().toString();

					// String resultString = str.substring(6);

					ins.putExtra("name", aaa.getText().toString());
					ins.putExtra("puanduan", "edit");
					startActivity(ins);

					

				}
					break;

				// end get item id
				case 3:

				{

					// 点击选项1
//					db.delete("sale", "date=?", new String[] { aaa.getText().toString() });

				}
					break;
				case 4:

				{

				}
					break;

				}
			}

			default:
				break;

			}

			return true;

		}
	
	

	/**
	 * 按钮点击事件监听
	 */
	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_add_customer:
			intent = new Intent(Operation_Customer.this, add_product.class);
			break;
		case R.id.btn_back:
			finish();
			break;
		case R.id.imgbtn_delete:
			editCustomer.setText("");

			break;
		default:
			break;
		}
		if (intent != null)
			startActivity(intent);
	}

	/**
	 * 输入框输入用户监听
	 */
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// Toast.makeText(this, "count"+count+"start"+start+"before"+before,
		// 0).show();
		if (start == 0) {
		}

	}

	@Override
	public void afterTextChanged(Editable s) {
		if (s.length() == 0) {
			handler.post(rChange);
		}
	}

	/**
	 * 数据库初始化
	 */
	private void initData() {
		product_dbhelper = new product_dataBase(this, "custom.db", null, 1);

		db = product_dbhelper.getWritableDatabase();
	}

	/**
	 * 整体查询
	 * 
	 * @return
	 */
	public List<Customers> queryCustomer() {
		dataList = new ArrayList<Customers>();
		Customers customers = null;
		Cursor cursor = db.query("custom", null, null, null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				customers = new Customers();
				customers.setName(cursor.getString(cursor
						.getColumnIndex("name")));
				customers.setAddress(cursor.getString(cursor
						.getColumnIndex("address")));
				customers.setMobile(cursor.getString(cursor
						.getColumnIndex("mobile")));

				customers.setOpening(cursor.getString(cursor
						.getColumnIndex("opening")));

				dataList.add(customers);
			} while (cursor.moveToNext());
		}
		// Toast.makeText(Operation_Customer.this,
		// dataList.get(0).getName()+"好几个",
		// 0).show();
		cursor.close();
		return dataList;
	}

	/**
	 * 条件查询 title
	 * 
	 * @return
	 */
	public List<Customers> querycjCustomer() {
		// String titleString = editCustomer.getText().toString().trim();

		String titleString = "%" + editCustomer.getText().toString().trim()
				+ "%";
		dataList = new ArrayList<Customers>();
		Customers customers = null;
		// Cursor cursor = db.query("custom", null, "name=?",
		// new String[] { titleString }, null, null, null);
		//

		Cursor cursor = db.query("custom", null, "name like ?",
				new String[] { titleString }, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				customers = new Customers();
				customers = new Customers();
				customers.setName(cursor.getString(cursor
						.getColumnIndex("name")));
				customers.setAddress(cursor.getString(cursor
						.getColumnIndex("address")));
				customers.setMobile(cursor.getString(cursor
						.getColumnIndex("mobile")));
				dataList.add(customers);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return dataList;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			// 隐藏键盘
			InputMethodManager imm = (InputMethodManager) this
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
			// Toast.makeText(Operation_Customer.this, "输完了", 0).show();
			dataList.clear();
			querycjCustomer();
			if (dataList.size() != 0) {
				// 列表显示
				// Toast.makeText(this, "有的", 0).show();
				handler.post(rtjChange);

			} else {
				Toast.makeText(this, "没有数据", 0).show();
				handler.post(rtjChange);
			}
			return true;
		}
		return false;
	}

	/**
	 * 条件查询同步刷新列表
	 */
	private Runnable rtjChange = new Runnable() {

		@Override
		public void run() {
			commonAdapter.setData(querycjCustomer());
			commonAdapter.notifyDataSetChanged();
		}
	};
	/**
	 * 全部查询
	 */
	private Runnable rChange = new Runnable() {

		@Override
		public void run() {
			commonAdapter.setData(queryCustomer());
			commonAdapter.notifyDataSetChanged();
		}
	};
}
