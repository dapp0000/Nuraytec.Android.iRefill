package com.nuraytec.irefill.entity;

import java.util.Date;

import android.text.format.Time;

public class DeviceState {

	private int requestCommandType;
	private int exhaustTemperature;
	private int targetTankTemperature;
	private Date deviceTime;
	private DeviceRunMode myDeviceRunMode;
	private boolean totalValve;
	private boolean exhaustValve;
	private boolean refillValve;
	private int sourceTankWeight;
	private int targetTankWeight;
	private DeviceRunState myDeviceRunState;
	private Time remainInterval;
	private int remainRefillNumbers;

	public String ToString() {
		String result = "";
		String str1 = "";
		String str2 = "";
		String str3 = "";
		String span = "\n";

		switch (this.myDeviceRunMode) {
		case Mode1:
			str1 = GetRunStateString(this.myDeviceRunState) + span;
			str2 = "剩余次数:" + this.remainRefillNumbers + "次" + span;
			str3 = "剩余时间:" + (this.remainInterval.toMillis(true) / 60000) + "分";
			break;
		case Mode2:
			str1 = GetRunStateString(this.myDeviceRunState) + span;
			str2 = "剩余时间:" + (this.remainInterval.toMillis(true) / 60000) + "分";
			break;
		case Free:
			str1 = "空闲中" + span;
			str2 = "............";
			break;
		case Hand:
			str1 = "手动中" + span;
			str2 = "............";
			break;
		case Scale:
			str1 = "刻度中" + span;
			str2 = "............";
			break;
		}

		result = String.format("{0}{1}{2}", str1, str2, str3);
		return result;
	}

	private String GetRunStateString(DeviceRunState state) {
		String result = "";
		switch (state) {
		case FirstDelay:
			result = "初次延时补充";
			break;
		case Exhaust:
			result = "正在排气";
			break;
		case Refill:
			result = "正在补充";
			break;
		case WaitNextTime:
			result = "等待下次补充";
			break;

		}

		return result;
	}

	/**
	 * @return the requestCommandType
	 */
	public int getRequestCommandType() {
		return requestCommandType;
	}

	/**
	 * @param requestCommandType
	 *            the requestCommandType to set
	 */
	public void setRequestCommandType(int requestCommandType) {
		this.requestCommandType = requestCommandType;
	}

	/**
	 * @return the exhaustTemperature
	 */
	public int getExhaustTemperature() {
		return exhaustTemperature;
	}

	/**
	 * @param exhaustTemperature
	 *            the exhaustTemperature to set
	 */
	public void setExhaustTemperature(int exhaustTemperature) {
		this.exhaustTemperature = exhaustTemperature;
	}

	/**
	 * @return the targetTankTemperature
	 */
	public int getTargetTankTemperature() {
		return targetTankTemperature;
	}

	/**
	 * @param targetTankTemperature
	 *            the targetTankTemperature to set
	 */
	public void setTargetTankTemperature(int targetTankTemperature) {
		this.targetTankTemperature = targetTankTemperature;
	}

	/**
	 * @return the deviceTime
	 */
	public Date getDeviceTime() {
		return deviceTime;
	}

	/**
	 * @param deviceTime
	 *            the deviceTime to set
	 */
	public void setDeviceTime(Date deviceTime) {
		this.deviceTime = deviceTime;
	}

	/**
	 * @return the myDeviceRunMode
	 */
	public DeviceRunMode getMyDeviceRunMode() {
		return myDeviceRunMode;
	}

	/**
	 * @param myDeviceRunMode
	 *            the myDeviceRunMode to set
	 */
	public void setMyDeviceRunMode(DeviceRunMode myDeviceRunMode) {
		this.myDeviceRunMode = myDeviceRunMode;
	}

	/**
	 * @return the totalValve
	 */
	public boolean isTotalValve() {
		return totalValve;
	}

	/**
	 * @param totalValve
	 *            the totalValve to set
	 */
	public void setTotalValve(boolean totalValve) {
		this.totalValve = totalValve;
	}

	/**
	 * @return the exhaustValve
	 */
	public boolean isExhaustValve() {
		return exhaustValve;
	}

	/**
	 * @param exhaustValve
	 *            the exhaustValve to set
	 */
	public void setExhaustValve(boolean exhaustValve) {
		this.exhaustValve = exhaustValve;
	}

	/**
	 * @return the refillValve
	 */
	public boolean isRefillValve() {
		return refillValve;
	}

	/**
	 * @param refillValve
	 *            the refillValve to set
	 */
	public void setRefillValve(boolean refillValve) {
		this.refillValve = refillValve;
	}

	/**
	 * @return the sourceTankWeight
	 */
	public int getSourceTankWeight() {
		return sourceTankWeight;
	}

	/**
	 * @param sourceTankWeight
	 *            the sourceTankWeight to set
	 */
	public void setSourceTankWeight(int sourceTankWeight) {
		this.sourceTankWeight = sourceTankWeight;
	}

	/**
	 * @return the targetTankWeight
	 */
	public int getTargetTankWeight() {
		return targetTankWeight;
	}

	/**
	 * @param targetTankWeight
	 *            the targetTankWeight to set
	 */
	public void setTargetTankWeight(int targetTankWeight) {
		this.targetTankWeight = targetTankWeight;
	}

	/**
	 * @return the myDeviceRunState
	 */
	public DeviceRunState getMyDeviceRunState() {
		return myDeviceRunState;
	}

	/**
	 * @param myDeviceRunState
	 *            the myDeviceRunState to set
	 */
	public void setMyDeviceRunState(DeviceRunState myDeviceRunState) {
		this.myDeviceRunState = myDeviceRunState;
	}

	/**
	 * @return the remainInterval
	 */
	public Time getRemainInterval() {
		return remainInterval;
	}

	/**
	 * @param remainInterval
	 *            the remainInterval to set
	 */
	public void setRemainInterval(Time remainInterval) {
		this.remainInterval = remainInterval;
	}

	/**
	 * @return the remainRefillNumbers
	 */
	public int getRemainRefillNumbers() {
		return remainRefillNumbers;
	}

	/**
	 * @param remainRefillNumbers
	 *            the remainRefillNumbers to set
	 */
	public void setRemainRefillNumbers(int remainRefillNumbers) {
		this.remainRefillNumbers = remainRefillNumbers;
	}

}
