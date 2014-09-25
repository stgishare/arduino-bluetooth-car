package com.stg.bluetoothmaster;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MotorControlActivity extends Activity {
	
	String MY_UUID = "00001101-0000-1000-8000-00805f9b34fb";

	Button forward ;
	Button left ;
	Button stop ;
	Button right ;
	Button back ;
	SeekBar seekBar;
	TextView seekBarValue;
	
	String selectedDeviceName;
	BluetoothAdapter bAdapter;
	BluetoothDevice bluetoothDevice;
	BluetoothSocket bluetoothSocket;
	OutputStream blueOutputStream;
	InputStream blueInputStream;
	Set<BluetoothDevice> pairedDevices;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.motor_control_layout);
		
		forward  = (Button) findViewById(R.id.button1);
		left = (Button) findViewById(R.id.button2); // left			
		
		stop = (Button) findViewById(R.id.button3); // stop
		
		right = (Button) findViewById(R.id.button4); // right
		
		back = (Button) findViewById(R.id.button5); // back
		
		
		Bundle bn = getIntent().getExtras();
		selectedDeviceName = bn.getString("selectedDeviceName");
		bAdapter = BluetoothAdapter.getDefaultAdapter();
		pairedDevices = bAdapter.getBondedDevices();
		 if(pairedDevices.size() > 0) {
			for(BluetoothDevice device : pairedDevices) {
			 if(device.getName().equals(selectedDeviceName)) {
				 	bluetoothDevice = device;
				 	break;
			 }
		   }
		 }
		 
		 UUID uuid = UUID.fromString(MY_UUID); //Standard SerialPortService ID
		 try {
			bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
			bluetoothSocket.connect();
			blueOutputStream = bluetoothSocket.getOutputStream();
			blueInputStream = bluetoothSocket.getInputStream();
		} catch (IOException e) {
			Toast.makeText(getBaseContext(), "IOException could not establish bluetooth connection.", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		 
		 forward.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						blueOutputStream.write("1".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
				}
		 });
		 
		 left.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						blueOutputStream.write("2".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
				}
		 });
		 
		 
		 stop.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						blueOutputStream.write("3".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
				}
		 });
		 
		 right.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						blueOutputStream.write("4".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
				}
		 });
		 
		 back.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						blueOutputStream.write("5".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
				}
		 });
		 
		 seekBar = (SeekBar)findViewById(R.id.seekBar1); 
		 seekBar.setProgress(5);
	     seekBarValue = (TextView)findViewById(R.id.textView3);
	     seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){ 
	    	 
			 @Override 
			 public void onProgressChanged(SeekBar seekBar, int progress, 
			 boolean fromUser) { 
			    // TODO Auto-generated method stub 
			    seekBarValue.setText(String.valueOf(progress)); 
			    
			    if(progress > 0 && progress <= 51){
			    	try {
						blueOutputStream.write("a".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
			    }
			    else if(progress > 51 && progress <= 102){
			    	try {
						blueOutputStream.write("b".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
			    }
				else if(progress > 102 && progress <= 153){
					try {
						blueOutputStream.write("c".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}	    	
				}
				else if(progress > 153 && progress <= 204){
					try {
						blueOutputStream.write("d".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
				}
				else if(progress > 153 && progress <= 204){
					try {
						blueOutputStream.write("e".getBytes());
					} catch (IOException e) {
						Toast.makeText(getBaseContext(), "IOException could not sent dat", Toast.LENGTH_LONG).show();
						e.printStackTrace();
					}
				}			    
			    
			 } 

		    @Override 
		    public void onStartTrackingTouch(SeekBar seekBar) { 
		     // TODO Auto-generated method stub 
		    } 

		    @Override 
		    public void onStopTrackingTouch(SeekBar seekBar) { 
		     // TODO Auto-generated method stub 
		    } 
		    
	   }); 
	       

	}
}
