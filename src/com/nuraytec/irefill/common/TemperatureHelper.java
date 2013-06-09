package com.nuraytec.irefill.common;

public class TemperatureHelper {

	/**
	 * ��ȡ�¶�ֵ
	 * @param resistanceValue
	 * @return
	 */
    public static double GetTemperature(double resistanceValue)
    {
        double t;      //�¶�ֵ 
        double Rt = resistanceValue;     //����ֵ

        double A = 3.9083E-3;
        double B = -5.802E-7;
        //double C = -4.2735E-12;

    
        //�������ֵ���¶�ֵ
        t = (Math.sqrt(Math.pow(A * 1000, 2) - 4 * B * 1000 * (1000 - Rt)) - A * 1000) / 2 / B / 1000;

        return t;
    }

 
    /**
     * ��ȡ����ֵ
     * @param temperatureValue
     * @return
     */
    public static double GetResistance(double temperatureValue)
    {
        double t = temperatureValue;      //�¶�ֵ 
        double Rt;     //����ֵ

        double A = 3.9083E-3;
        double B = -5.802E-7;
        //double C = -4.2735E-12;

        //�����¶�ֵ�����ֵ
        Rt = 1000 - (Math.pow(A * 1000, 2) - Math.pow(t * 1000 * B * 2 + A * 1000, 2)) / 4 / B / 1000;

        return Rt;
    }

}
