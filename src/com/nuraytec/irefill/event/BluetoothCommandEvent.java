package com.nuraytec.irefill.event;

import java.util.EventObject;


public class BluetoothCommandEvent extends EventObject {

	private static final long serialVersionUID = 7900347518702841005L;
	private String result="";
	
	public String getResult() {
		return result;
	}

//	public void setResult(String result) {
//		this.result = result;
//	}

	
	public BluetoothCommandEvent(Object source,String result) {
		super(source);
		// TODO Auto-generated constructor stub
		this.result=result;
	}

}
