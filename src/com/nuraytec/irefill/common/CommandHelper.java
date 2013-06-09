package com.nuraytec.irefill.common;

import com.nuraytec.irefill.entity.DeviceState;

public class CommandHelper {
	private static final String EscString = "0xFF1B";
	private static final String IDString = "4E3030303032";

	private int ByteLength;// 16进制1个字节的字符串长度，如FF
	private int NdataStringStartIndex;// 命令中带的数据在整个命令中的位置

	public CommandHelper() {
		ByteLength = 2;
		NdataStringStartIndex = GetNormalCommand("60", "").length()
				- ByteLength;
	}

	public static String GetNormalCommand(String commandCode,
			String insertNdataString) {
		// String commandString
		// =TypeConverterHelper.intToHexString(commandCode);

		// String commandString=String.valueOf(commandCode);

		String commandString = commandCode;

		String dataString = insertNdataString;

		String ndataString = TypeConverterHelper.intToHexString(
				dataString.length() / 2, 1);

		String xorString = GetXORString(commandString + IDString + ndataString
				+ dataString);

		String result = EscString + commandString + IDString + ndataString
				+ dataString + xorString;

		return result;
	}

	public static String GetXORString(String hexString) {
		byte[] arr = TypeConverterHelper.hexStringToBytes(hexString);

		byte[] xor = new byte[1];
		xor[0] = 0;
		int count = arr.length;
		for (int i = 0; i < count; i++) {
			xor[0] ^= arr[i];
		}

		return TypeConverterHelper.bytesToHexString(xor);
	}

	public DeviceState AnalysisGetDeviceStateResult(String hexString) {
		if (hexString.length() < NdataStringStartIndex)
			return null;

		String ndataString = hexString.substring(NdataStringStartIndex);
		int index = 0;

		DeviceState result = new DeviceState();
		if (ndataString.length() > ByteLength * 26)// 26字节长度
		{
			// int t1 = StringToInt(ndataString.substring(index, ByteLength *
			// 4));
			// int t2 = StringToInt(ndataString.Substring(index += ByteLength *
			// 4,ByteLength * 4));
			// result.DeviceTime = StringToDateTime(ndataString.Substring(index
			// += ByteLength * 4, ByteLength * 7));
			// result.MyDeviceRunMode =
			// (DeviceRunMode)Enum.Parse(typeof(DeviceRunMode),
			// StringToInt(ndataString.Substring(index += ByteLength * 7,
			// ByteLength)).ToString());
			// result.TotalValve = StringToBool(ndataString.Substring(index +=
			// ByteLength, ByteLength));
			// result.ExhaustValve = StringToBool(ndataString.Substring(index +=
			// ByteLength, ByteLength));
			// result.RefillValve = StringToBool(ndataString.Substring(index +=
			// ByteLength, ByteLength));
			// result.SourceTankWeight = StringToInt(ndataString.Substring(index
			// += ByteLength, ByteLength));
			// result.TargetTankWeight = StringToInt(ndataString.Substring(index
			// += ByteLength, ByteLength));
			// result.MyDeviceRunState =
			// (DeviceRunState)Enum.Parse(typeof(DeviceRunState),
			// StringToInt(ndataString.Substring(index += ByteLength,
			// ByteLength)).ToString());
			// result.RemainInterval =
			// StringToTimeSpan(ndataString.Substring(index +=
			// ByteLength,ByteLength*3));
			// result.RemainRefillNumbers =
			// HexStringHelper.HexStringToInt(ndataString.Substring(index +=
			// ByteLength*3, ByteLength));
			//
			// //增加返回请求的命令类型
			// result.RequestCommandType=(CommandType)Enum.Parse(typeof(CommandType),
			// HexStringHelper.HexStringToByteArray(ndataString.Substring(index
			// += ByteLength, ByteLength))[0].ToString(), true);
			//
			// try
			// {
			// result.ExhaustTemperature =
			// Convert.ToInt32(TemperatureHelper.GetTemperature(t1));
			// result.TargetTankTemperature =
			// Convert.ToInt32(TemperatureHelper.GetTemperature(t2));
			// }
			// catch
			// {
			//
			// }

		}

		return result;
	}
}
