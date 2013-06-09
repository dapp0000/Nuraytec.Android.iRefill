package com.nuraytec.irefill.entity;

public enum DeviceRunMode {
	Mode1(0), // 模式1
	Mode2(1), // 模式2
	Scale(2), // 系统刻度
	Hand(3), // 手动
	Free(4);// 空闲

	private int value;

	DeviceRunMode(int value) {
		this.value = value;
	}

}
