package com.nuraytec.irefill.entity;

public enum DeviceRunMode {
	Mode1(0), // ģʽ1
	Mode2(1), // ģʽ2
	Scale(2), // ϵͳ�̶�
	Hand(3), // �ֶ�
	Free(4);// ����

	private int value;

	DeviceRunMode(int value) {
		this.value = value;
	}

}
