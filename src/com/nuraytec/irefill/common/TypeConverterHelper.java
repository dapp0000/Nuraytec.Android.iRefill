package com.nuraytec.irefill.common;

public class TypeConverterHelper {
	

    /**
     * short类型转成byte[]
     * 
     * @param n
     * short参数
     * @return byte[]
     */
    public static byte[] shortToBytes(short n) {
	
	byte[] b = new byte[2];
	
	b[1] = (byte) (n & 0xff);
	
	b[0] = (byte) ((n >> 8) & 0xff);
	
	return b;
	
	}



    /**
     * byte[]类型转成short
     * @param b
     * @return
     */
	public static short bytesToShort(byte[] b) {
		
	return (short) (b[1] & 0xff| (b[0] & 0xff) << 8);
	
	}
	
	
	
	/**
	* int类型转换成byte[]
	*
	* @param num
	* int数
	* @return byte[]
	*/
	public static byte[] intToBytes(int num) {
		
	byte[] b = new byte[4];
	
	for (int i = 0; i < 4; i++) {
		
	b[i] = (byte) (num >>> (24 - i * 8));
	
	}
	
	return b;
	
	}

	/**
	 * byte[]转换成int
	 * @param b
	 * @return
	 */
	public static int bytesToint(byte[] b) {
		
	//byte[] b=new byte[]{1,2,3,4};
	int mask = 0xff;
	int temp = 0;
	int res = 0;
	for (int i = 0; i < 4; i++) {
	res <<= 8;
	temp = b[i] & mask;
	res |= temp;
	}
	
	return res;
	
	}
	
	/**
	 * long转换成byte[]
	 * @param num
	 * @return
	 */
	public static byte[] longToBytes(long num) {
		
	byte[] b = new byte[8];
	for (int i = 0; i < 8; i++) {
	b[i] = (byte) (num >>> (56 - i * 8));
	}
	
	return b;
	
	}
	
	/**
	 * 字符串转换成16进制字符串
	 * @param str
	 * @return
	 */
	public static String stringToHexString(String str) {
		
	char[] chars = "0123456789ABCDEF".toCharArray();
	StringBuilder sb = new StringBuilder("");
	byte[] bs = str.getBytes();
	int bit;
	for (int i = 0; i < bs.length; i++) {
	bit = (bs[i] & 0x0f0) >> 4;
	sb.append(chars[bit]);
	bit = bs[i] & 0x0f;
	sb.append(chars[bit]);
	}
	
	return sb.toString();
	
	}
	
	
	/**
	 * byte[]转换成16进制字符串
	 * @param bArray
	 * @return
	 */
	public static String bytesToHexString(byte[] bArray) {
		
	StringBuffer sb = new StringBuffer(bArray.length);
	String sTemp;
	for (int i = 0; i < bArray.length; i++) {
		
	sTemp = Integer.toHexString(0xFF & bArray[i]);
	if (sTemp.length() < 2) {
	sb.append(0);
	}
	
	sb.append(sTemp.toUpperCase());
	
	}
	
	return sb.toString();	
	}
	
	/**
	 * 16进制字符串转换成byte[]
	 * @param hexString
	 * @return
	 */	
	public static byte[] hexStringToBytes(String hexString) {
		
	String chars = "0123456789ABCDEF";
	
	if (hexString == null || hexString.equals("")) {
	return null;
	}
	
	hexString=hexString.replace("0x", "");
	hexString=hexString.replace("0X", "");
	
	
	hexString = hexString.toUpperCase();
	int length = hexString.length() / 2;
	char[] hexChars = hexString.toCharArray();
	byte[] d = new byte[length];
	for (int i = 0; i < length; i++) {
	int pos = i * 2;
	d[i] = (byte) (chars.indexOf(hexChars[pos]) << 4 | chars.indexOf(hexChars[pos + 1]));
	}
	
	return d;
	
	}

	
	public static String intToHexString(int num,int byteLength){
		String hexString=TypeConverterHelper.bytesToHexString(TypeConverterHelper.intToBytes(num));
		return hexString.substring(hexString.length()-byteLength*2);		
	}
	
	public static int hexStringToInt(String hexString){
		
		return TypeConverterHelper.bytesToint(TypeConverterHelper.hexStringToBytes(hexString));
	}


}
