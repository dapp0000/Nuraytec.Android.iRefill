package com.nuraytec.irefill.event;

import java.util.EventListener;

public interface ConnectedEventListener extends EventListener {
	
	void fireConnectedEvent(ConnectedEvent e);

}
