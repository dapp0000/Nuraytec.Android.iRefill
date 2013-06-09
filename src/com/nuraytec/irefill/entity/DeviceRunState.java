package com.nuraytec.irefill.entity;

public enum DeviceRunState {

	/**
	 * 初次延时补充
	 */
	FirstDelay(0),
	/**
	 * 正在排气
	 */
	Exhaust(1),
	/**
	 * 正在补充
	 */
	Refill(2),
	/**
	 * 等待下次补充
	 */
	WaitNextTime(3);

	private int value;

	DeviceRunState(int value) {
		this.value = value;
	}

}
