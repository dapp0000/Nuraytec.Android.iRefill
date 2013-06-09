package com.nuraytec.irefill.common;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.nuraytec.irefill.event.BluetoothCommandEvent;
import com.nuraytec.irefill.event.BluetoothCommandEventListener;
import com.nuraytec.irefill.event.ConnectedEvent;
import com.nuraytec.irefill.event.ConnectedEventListener;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;



public class BluetoothHelper  {	

	private boolean connected;
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	 private Set<BluetoothCommandEventListener> listenerCommand;
	 private Set<ConnectedEventListener> listenerConnected;	 

	 public void addEventListener(BluetoothCommandEventListener e){   
		      this.listenerCommand.add(e);   
		   } 
	 
	 public void addEventListener(ConnectedEventListener e){   
	      this.listenerConnected.add(e);   
	   } 
	 
	 private void notifiesBluetoothCommandEvent(String result){   
		 BluetoothCommandEventListener cel = null;   
	        Iterator<BluetoothCommandEventListener> iterator = this.listenerCommand.iterator();   
		       while(iterator.hasNext()){ 
		    	   cel = iterator.next();   
		           cel.fireBluetoothCommandEvent(new BluetoothCommandEvent(this,result));   
		     }   
		       
		   } 
	 
	 private void notifiesConnectedEvent(boolean result){
		 ConnectedEventListener cel=null;
		 Iterator<ConnectedEventListener> iterator = this.listenerConnected.iterator();   
		 
		 while(iterator.hasNext()){
			 cel=iterator.next();
			 cel.fireConnectedEvent(new ConnectedEvent(this,result));
		 }
	 }
	 
	 private BluetoothAdapter mBtAdapter;
	 public BluetoothHelper(){
			this.listenerCommand = new HashSet<BluetoothCommandEventListener>();   
			this.listenerConnected = new HashSet<ConnectedEventListener>();   
			
//			
//			 // Register for broadcasts when a device is discovered
//	        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//	        this.registerReceiver(mReceiver, filter);
//
//	        // Register for broadcasts when discovery has finished
//	        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//	        this.registerReceiver(mReceiver, filter);
//
//	        // Get the local Bluetooth adapter
//	        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
//
//	        // Get a set of currently paired devices
//	        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
			
		}

	 
	 public boolean FindDevices(int maxDevices){
		 
		 return false;
	 }
	
	 public boolean ConnectService(String deviceAddress,String pin){
		 
		 return false;
	 }
	 
	 public boolean InitDeviceAdress(String deviceAddress){
		 
		 
		 return false;
		 
	 }

	 public boolean SendMessage(String strSend){
		 
		 return false;
	 }
	 
	 public boolean CloseService(){
		 
		 return false;
	 }
	 
	 
	    // The BroadcastReceiver that listens for discovered devices and
	    // changes the title when discovery is finished
	    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	            String action = intent.getAction();

	            // When discovery finds a device
	            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	                // Get the BluetoothDevice object from the Intent
	                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	                // If it's already paired, skip it, because it's been listed already
	                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
	                	
//	                    mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
	                    
	                }
	            // When discovery is finished, change the Activity title
	            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
//	                setProgressBarIndeterminateVisibility(false);
//	                setTitle(R.string.select_device);
//	                if (mNewDevicesArrayAdapter.getCount() == 0) {
//	                    String noDevices = getResources().getText(R.string.none_found).toString();
//	                    mNewDevicesArrayAdapter.add(noDevices);
//	                }
	            }
	        }
	    };
	 
}
