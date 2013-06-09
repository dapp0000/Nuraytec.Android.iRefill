package com.nuraytec.irefill.common;

public class TemperatureHelper {

	/**
	 * 获取温度值
	 * @param resistanceValue
	 * @return
	 */
    public static double GetTemperature(double resistanceValue)
    {
        double t;      //温度值 
        double Rt = resistanceValue;     //电阻值

        double A = 3.9083E-3;
        double B = -5.802E-7;
        //double C = -4.2735E-12;

    
        //输入电阻值求温度值
        t = (Math.sqrt(Math.pow(A * 1000, 2) - 4 * B * 1000 * (1000 - Rt)) - A * 1000) / 2 / B / 1000;

        return t;
    }

 
    /**
     * 获取电阻值
     * @param temperatureValue
     * @return
     */
    public static double GetResistance(double temperatureValue)
    {
        double t = temperatureValue;      //温度值 
        double Rt;     //电阻值

        double A = 3.9083E-3;
        double B = -5.802E-7;
        //double C = -4.2735E-12;

        //输入温度值求电阻值
        Rt = 1000 - (Math.pow(A * 1000, 2) - Math.pow(t * 1000 * B * 2 + A * 1000, 2)) / 4 / B / 1000;

        return Rt;
    }

}
