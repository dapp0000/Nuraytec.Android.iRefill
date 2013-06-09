package com.nuraytec.irefill.event;

import java.util.EventObject;

public class ConnectedEvent  extends EventObject {

	private static final long serialVersionUID = 5106207492889201389L;
	public ConnectedEvent(Object source,boolean connected) {
		super(source);
		// TODO Auto-generated constructor stub
		this.connected=connected;
	}

	private boolean connected;
    public boolean isConnected() {
		return connected;
	}

//	public void setConnected(boolean connected) {
//		this.connected = connected;
//	}

	


}
