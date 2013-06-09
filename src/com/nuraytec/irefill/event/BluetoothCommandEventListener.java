package com.nuraytec.irefill.event;

import java.util.EventListener;

public interface BluetoothCommandEventListener extends EventListener {
	
	void fireBluetoothCommandEvent(BluetoothCommandEvent e);

}
