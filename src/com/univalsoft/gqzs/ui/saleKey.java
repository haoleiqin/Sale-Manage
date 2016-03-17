package com.univalsoft.gqzs.ui;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.univalsoft.gqzs.R;

public class saleKey extends Activity {
	private EditText editText1;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.salekey);
		editText1 = (EditText) findViewById(R.id.editText1);
		zhuce();
	}

	private void zhuce() {

		TextView textView2 = (TextView) findViewById(R.id.textView2);
		textView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Myradom();

		button5 = (Button) findViewById(R.id.Button04);
		button4 = (Button) findViewById(R.id.Button03);
		button3 = (Button) findViewById(R.id.Button02);
		button2 = (Button) findViewById(R.id.Button01);
		button1 = (Button) findViewById(R.id.button1);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(saleKey.this, "Erro License Key", 0).show();
			}
		});

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = "+919825388710";
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ number));
				startActivity(intent);

			}
		});

		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String phoneNumber = "+919825388710";
				String listengss = editText1.getText().toString();
				String message = "Request for Mobile Sale Registration:IMEI Number:"
						+ listengss;
				Intent intent = new Intent(Intent.ACTION_SENDTO, Uri
						.parse("smsto:" + phoneNumber));
				intent.putExtra("sms_body", message);
				startActivity(intent);

			}
		});

		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {String listengss = editText1.getText().toString();
				Intent data=new Intent(Intent.ACTION_SENDTO); 
				data.setData(Uri.parse("mailto:info@eleganceinfoways.com")); 
				data.putExtra(Intent.EXTRA_SUBJECT, "Request for Mobile Sale"); 
				data.putExtra(Intent.EXTRA_TEXT, listengss ); 
				startActivity(data); 
			}
		});

		button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	private void Myradom() {

		// Set<Integer> set = new HashSet<Integer>();
		// Random random = new Random();
		// while (set.size() < 16) {
		// set.add(random.nextInt());//
		// }

		int random = (int) (Math.random() * 100000000);

		int random2 = (int) (Math.random() * 100000000);

		String aaaaString = random + "" + random2;
		editText1.setText(aaaaString);

		editText1.setEnabled(false);

	}

}
