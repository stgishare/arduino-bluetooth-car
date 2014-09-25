package com.stg.bluetoothmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PairlistActivity extends Activity {
	    ListView lview;
	    String[] paries; 
	    ArrayAdapter<String> adapter;
	    
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.parilist_layout);
			lview = (ListView) findViewById(R.id.listviewid);
			Bundle bn = getIntent().getExtras();
			paries = bn.getStringArray("paires");
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,paries);
			lview.setAdapter(adapter);
			
			

			lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String selectedDeviceName =(String) (lview.getItemAtPosition(position));
					Toast.makeText(getBaseContext(), "Name = "+selectedDeviceName, Toast.LENGTH_LONG).show();
					
					Bundle bn = new Bundle();
					bn.putString("selectedDeviceName", selectedDeviceName);
					Intent in = new Intent("motor_filter");
				    in.putExtras(bn);
				    startActivity(in);
				    
				}                 
			});
		}
}
