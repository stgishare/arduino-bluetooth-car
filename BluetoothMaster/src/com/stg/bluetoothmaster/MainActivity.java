package com.stg.bluetoothmaster;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

	Button button;
	BluetoothAdapter bAdapter;
	int REQUEST_CODE = 1;
	Set<BluetoothDevice> pairedDevices;
	String plist[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bAdapter = BluetoothAdapter.getDefaultAdapter();
				if(bAdapter == null){
					Toast.makeText(getBaseContext(), "Device does not support Bluetooth", Toast.LENGTH_LONG).show();
				}else{
					if(!bAdapter.isEnabled()){
						Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
						startActivityForResult(i, REQUEST_CODE);						
					}else{
						getPairedDevice();
					}
					
				}
			}
		});
	}

	
	public void onActivityResult(int requestCode,int resultCode, Intent data){
		if(requestCode == REQUEST_CODE){
			if(resultCode == RESULT_OK){
				Toast.makeText(getBaseContext(), "Bluetooth is enabled", Toast.LENGTH_LONG).show();
				getPairedDevice() ;
			}
		}
	}
	
	protected void getPairedDevice() {
		pairedDevices = bAdapter.getBondedDevices();
		int count = pairedDevices.size();
		int j = 0;
		plist = new String[count];
		for (BluetoothDevice device : pairedDevices) {
			plist[j] = device.getName().toString();
			j++;
		}
		Bundle bn = new Bundle();
		bn.putStringArray("paires", plist);
		Intent in = new Intent("pair_filter");
	    in.putExtras(bn);
	    startActivity(in);
		
	}
	
}
