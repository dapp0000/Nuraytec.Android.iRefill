package com.nuraytec.irefill.entity;

public enum DeviceRunState {

	/**
	 * ������ʱ����
	 */
	FirstDelay(0),
	/**
	 * ��������
	 */
	Exhaust(1),
	/**
	 * ���ڲ���
	 */
	Refill(2),
	/**
	 * �ȴ��´β���
	 */
	WaitNextTime(3);

	private int value;

	DeviceRunState(int value) {
		this.value = value;
	}

}
