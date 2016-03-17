package com.univalsoft.gqzs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.univalsoft.gqzs.R;

public class Setting extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		ZhuCe();
	}

	private void ZhuCe() {
	ImageView back_imageView1_product=(ImageView)findViewById(R.id.back_imageView1_product);
	back_imageView1_product.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
finish();
			
		}
	});
		
	}

}
