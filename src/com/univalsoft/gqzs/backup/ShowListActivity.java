package com.univalsoft.gqzs.backup;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.univalsoft.gqzs.R;

public class ShowListActivity extends Activity{
   
	MySqliteHelper helper;
	Button query;
	TextView name,age;
	ListView listview;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        helper=new MySqliteHelper(getApplicationContext());
        query();
        helper.close();
    }
    
 
   public void query() {
			ListView listView = (ListView) findViewById(R.id.listview);
		    SQLiteDatabase db=helper.getWritableDatabase();
			Cursor cursor=db.query("student", new String[] {"_id","name", "score" }, null, null, null, null, null);
			SimpleCursorAdapter sca = new SimpleCursorAdapter(this,
					R.layout.list_item, cursor,
					new String[] {"name", "score" }, new int[] { R.id.name,
							R.id.score});
			Log.d("mylog", "success");
			listView.setAdapter(sca);
			db.close();
   }
  
	 
	 

}