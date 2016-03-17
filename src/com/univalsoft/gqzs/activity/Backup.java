package com.univalsoft.gqzs.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.univalsoft.gqzs.R;
import com.univalsoft.gqzs.backup.CommonUtil;
import com.univalsoft.gqzs.backup.FileManager;

public class Backup extends Activity {

	private LayoutInflater inflater;
	private AlertDialog dialog;
	private Context context;
	private FileManager flieMgr;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch ((Integer) msg.obj) {
			case -1:
				dialog.dismiss();
				CommonUtil.Toast(context, "Some bug~ Please try again");
				break;
			case 0:
				dialog.dismiss();
				CommonUtil.Toast(context, "Backup Data Success");
				break;
			case 1:
				dialog.dismiss();
				CommonUtil.Toast(context, "Restore Data Success");
				break;

			}

		}
	};
	private com.univalsoft.gqzs.backup.MySqliteHelper helper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.backup);
		ZhuCe();
		context = this;
		inflater = LayoutInflater.from(context);
	}

	private void ZhuCe() {
		
		TextView textView2=(TextView)findViewById(R.id.textView2);
		textView2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		flieMgr = new FileManager(context);

		Button btn_backup = (Button) findViewById(R.id.btn_backup);
		btn_backup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				View view0 = inflater.inflate(R.layout.progress_dialog, null);
				((TextView) view0.findViewById(R.id.progress_msg))
						.setText(" 备 份 中 . . .");
				dialog = new AlertDialog.Builder(context).setView(view0)
						.create();
				dialog.show();
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(999);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
//						int flag = flieMgr.dbFileOperation("backup", "contact");
						// Log.e(" MainActivity ","flag==========="+flag);

						Message msg = new Message();
						msg.obj = 0;
						handler.sendMessage(msg);
					}
				}).start();

			}
		});

		Button btn_restore = (Button) findViewById(R.id.btn_restore);
		btn_restore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				View view0 = inflater.inflate(R.layout.progress_dialog, null);
				((TextView) view0.findViewById(R.id.progress_msg))
						.setText(" 备 份 中 . . .");
				dialog = new AlertDialog.Builder(context).setView(view0)
						.create();
				dialog.show();
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(999);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
//						int flag = flieMgr.dbFileOperation("backup", "contact");
						// Log.e(" MainActivity ","flag==========="+flag);

						Message msg = new Message();
						msg.obj = 1;
						handler.sendMessage(msg);
					}
				}).start();

			}
		});

	}

}
