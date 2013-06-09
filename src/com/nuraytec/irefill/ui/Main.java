package com.nuraytec.irefill.ui;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuraytec.irefill.R;
import com.nuraytec.irefill.common.BluetoothService;
import com.nuraytec.irefill.common.CommandHelper;
import com.nuraytec.irefill.common.TypeConverterHelper;

public class Main extends BaseActivity {
	// Debugging
	private static final String TAG = "ShellActivity";
	private static final boolean D = true;

	private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
	private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
	private static final int REQUEST_ENABLE_BT = 3;

	private EditText txtDevicStateEditText;
	private EditText txtSendCommandEditText;
	private EditText txtReciveCommandEditText;

	private BluetoothService mChatService;
	// 取得默认的蓝牙适配器
	private BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
	// 存储搜索到的可连接设备
	private ArrayAdapter<String> mNewDevicesArrayAdapter;
	private String currentAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (this.blueAdapter == null) {
			this.showToast("请检查你的设备是否具有蓝牙模块或是否正常");
			this.finish();
		}

		mNewDevicesArrayAdapter = new ArrayAdapter<String>(this,
				R.layout.devicelist_item);
		ListView lvDevices = (ListView) findViewById(R.id.listView1);
		lvDevices.setAdapter(mNewDevicesArrayAdapter);
		lvDevices.setOnItemClickListener(btnItemClickListener);
		((Button) findViewById(R.id.buttonScan))
				.setOnClickListener(btnScanClickListener);
		((Button) findViewById(R.id.buttonConnect))
				.setOnClickListener(btnConnectClickListener);

		txtDevicStateEditText = (EditText) findViewById(R.id.txtDeviceState);
		txtSendCommandEditText = (EditText) findViewById(R.id.txtSendCommand);
		txtReciveCommandEditText = (EditText) findViewById(R.id.txtReceiveCommand);

		((Button) findViewById(R.id.btnGetState))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String strCommandString = CommandHelper
								.GetNormalCommand("60", "");
						sendMessage(strCommandString);
					}
				});
		((Button) findViewById(R.id.btnAuto1))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String strCommandString = CommandHelper
								.GetNormalCommand("66", "");
						sendMessage(strCommandString);
					}
				});
		((Button) findViewById(R.id.btnAuto2))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String strCommandString = CommandHelper
								.GetNormalCommand("67", "");
						sendMessage(strCommandString);
					}
				});
		((Button) findViewById(R.id.btnStopAuto))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String strCommandString = CommandHelper
								.GetNormalCommand("68", "");
						sendMessage(strCommandString);
					}
				});
		((Button) findViewById(R.id.btnStartScale))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String strCommandString = CommandHelper
								.GetNormalCommand("62", "");
						sendMessage(strCommandString);
					}
				});
		((Button) findViewById(R.id.btnStopScale))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String strCommandString = CommandHelper
								.GetNormalCommand("63", "");
						sendMessage(strCommandString);
					}
				});
		((Button) findViewById(R.id.btnTime))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// String
						// strCommandString=CommandHelper.GetNormalCommand("61",
						// "");
						// sendMessage(strCommandString);
					}
				});
		((Button) findViewById(R.id.btnClearAlarm))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String strCommandString = CommandHelper
								.GetNormalCommand("82", "");
						sendMessage(strCommandString);
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnItemClickListener btnItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			blueAdapter.cancelDiscovery();

			String info = ((TextView) arg1).getText().toString();
			String address = info.substring(info.length() - 17);
			currentAddress = address;
		}

	};

	private OnClickListener btnSendClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText txtInput = (EditText) findViewById(R.id.txtReceiveCommand);
			sendMessage(txtInput.getText().toString());
		}
	};

	private OnClickListener btnScanClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			doDiscovery();
		}
	};

	private OnClickListener btnConnectClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			connectDevice(true);
		}
	};

	@Override
	public synchronized void onPause() {
		super.onPause();
		if (D)
			Log.e(TAG, "- ON PAUSE -");
	}

	@Override
	public void onStop() {
		super.onStop();
		if (D)
			Log.e(TAG, "-- ON STOP --");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		if (blueAdapter != null) {

			if (mChatService != null)
				mChatService.stop();
			if (D)
				Log.e(TAG, "--- ON DESTROY ---");

			blueAdapter.cancelDiscovery();
			blueAdapter.disable();

		}
	}

	@Override
	public synchronized void onResume() {
		super.onResume();
		if (D)
			Log.e(TAG, "+ ON RESUME +");

		// Performing this check in onResume() covers the case in which BT was
		// not enabled during onStart(), so we were paused to enable it...
		// onResume() will be called when ACTION_REQUEST_ENABLE activity
		// returns.
		if (mChatService != null) {
			// Only if the state is STATE_NONE, do we know that we haven't
			// started already
			if (mChatService.getState() == BluetoothService.STATE_NONE) {
				// Start the Bluetooth chat services
				mChatService.start();
			}
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		IntentFilter discoverFilter = new IntentFilter(
				BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		IntentFilter foundfFilter = new IntentFilter(
				BluetoothDevice.ACTION_FOUND);
		registerReceiver(discover_R, discoverFilter);
		registerReceiver(found_R, foundfFilter);

		if (!blueAdapter.isEnabled()) {
			blueAdapter.enable();
		} else {
			if (mChatService == null) {
				setupChat();
			}
		}
	}

	public void showToast(String string) {
		Toast.makeText(Main.this, string, Toast.LENGTH_LONG).show();
	}

	/**
	 * 设备扫描
	 */
	private void doDiscovery() {
		blueAdapter.startDiscovery();
	}

	/**
	 * 添加已配对设备
	 */
	private void addpaired() {
		Set<BluetoothDevice> pairedDevices = blueAdapter.getBondedDevices();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				mNewDevicesArrayAdapter.add(device.getName() + "\n"
						+ device.getAddress());
			}
		}
	}

	/**
	 * 扫描到设备通知
	 */
	BroadcastReceiver found_R = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			BluetoothDevice device = intent
					.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			mNewDevicesArrayAdapter.add(device.getName() + "\n"
					+ device.getAddress());
		}
	};

	/**
	 * 扫描完成通知
	 */
	BroadcastReceiver discover_R = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			addpaired();
			unregisterReceiver(discover_R);
			unregisterReceiver(found_R);
		}
	};

	/**
	 * 设置蓝牙功能类
	 */
	private void setupChat() {
		Log.d(TAG, "setupChat()");

		// Initialize the BluetoothChatService to perform bluetooth connections
		mChatService = new BluetoothService(this, mHandler);
	}

	/**
	 * 处理蓝牙功能类返回信息
	 */
	// The Handler that gets information back from the BluetoothChatService
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case BluetoothService.MESSAGE_STATE_CHANGE:
				if (D)
					Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
				switch (msg.arg1) {
				case BluetoothService.STATE_CONNECTED:
					// mTitle.setText(R.string.title_connected_to);
					// mTitle.append(mConnectedDeviceName);
					// mConversationArrayAdapter.clear();
					break;
				case BluetoothService.STATE_CONNECTING:
					// mTitle.setText(R.string.title_connecting);
					break;
				case BluetoothService.STATE_LISTEN:
				case BluetoothService.STATE_NONE:
					// mTitle.setText(R.string.title_not_connected);
					break;
				}
				break;
			case BluetoothService.MESSAGE_WRITE:
				byte[] writeBuf = (byte[]) msg.obj;
				// construct a string from the buffer
				// String writeMessage = new String(writeBuf);
				String writeMessage = TypeConverterHelper
						.bytesToHexString(writeBuf);
				// mSentenceArrayAdapter.add("Me:  " + writeMessage);
				txtSendCommandEditText.setText(writeMessage);
				break;
			case BluetoothService.MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// construct a string from the valid bytes in the buffer
				// String readMessage = new String(readBuf, 0, msg.arg1);

				String readMessage = TypeConverterHelper
						.bytesToHexString(readBuf);
				// mSentenceArrayAdapter.add(currentAddress+":  " +
				// readMessage);
				txtReciveCommandEditText.setText(readMessage);

				break;
			case BluetoothService.MESSAGE_DEVICE_NAME:
				// save the connected device's name
				// mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
				// Toast.makeText(getApplicationContext(), "Connected to "
				// + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
				break;
			case BluetoothService.MESSAGE_TOAST:

				break;
			}
		}
	};

	/**
	 * 设置手机蓝牙为300米内可发现
	 */
	private void ensureDiscoverable() {
		if (D)
			Log.d(TAG, "ensure discoverable");
		if (blueAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Intent discoverableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(
					BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(discoverableIntent);
		}
	}

	/**
	 * 发送蓝牙消息
	 * 
	 * @param message
	 */
	private void sendMessage(String message) {
		// Check that we're actually connected before trying anything
		if (mChatService.getState() != BluetoothService.STATE_CONNECTED) {
			Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT)
					.show();
			return;
		}

		// Check that there's actually something to send
		if (message.length() > 0) {

			byte[] send = TypeConverterHelper.hexStringToBytes(message);
			mChatService.write(send);

		}
	}

	/**
	 * 多个activity之间的传值
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (D)
			Log.d(TAG, "onActivityResult " + resultCode);
		switch (requestCode) {
		case REQUEST_CONNECT_DEVICE_SECURE:
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {
				connectDevice(true);
			}
			break;
		case REQUEST_CONNECT_DEVICE_INSECURE:
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {
				connectDevice(false);
			}
			break;
		case REQUEST_ENABLE_BT:
			// When the request to enable Bluetooth returns
			if (resultCode == Activity.RESULT_OK) {
				// Bluetooth is now enabled, so set up a chat session
				setupChat();
			} else {
				// User did not enable Bluetooth or an error occured
				Log.d(TAG, "BT not enabled");
				Toast.makeText(this, R.string.bt_not_enabled_leaving,
						Toast.LENGTH_SHORT).show();

				finish();
			}
		}
	}

	/**
	 * 连接蓝牙设备
	 * 
	 * @param secure
	 */
	private void connectDevice(boolean secure) {
		if (currentAddress.length() > 0) {
			// Get the device MAC address
			String address = currentAddress;
			// Get the BLuetoothDevice object
			BluetoothDevice device = blueAdapter.getRemoteDevice(address);
			// Attempt to connect to the device
			mChatService.connect(device, secure);

		}

	}

}
