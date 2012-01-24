package fr.uha.ensisa.jadoma.util;

import java.text.DecimalFormat;

public class SizeUtil {
	
	public static String[] getFormattedSize(int sizeInBytes) {
		float value;
		String unity;
		
		float valueO, valueKo, valueMo, valueGo, valueTo;
		valueO = (float) sizeInBytes;
		valueKo = valueO / 1024;
		valueMo = valueKo / 1024;
		valueGo = valueMo / 1024;
		valueTo = valueGo / 1024;
		
		if (valueKo < 1.0)
		{
			unity = "octets";
			value = valueO;
		}
		else if (valueKo >= 1.0 && valueMo < 1.0)
		{
			unity = "Ko";
			value = valueKo;
		}
		else if (valueMo >= 1.0 && valueGo < 1.0)
		{
			unity = "Mo";
			value = valueMo;
		}
		else if (valueGo >= 1.0 && valueTo < 1.0)
		{
			unity = "Go";
			value = valueGo;
		}
		else
		{
			unity = "To";
			value = valueTo;
		}
		
		DecimalFormat df = new DecimalFormat("0.0");
		return new String[] { df.format(value), unity };
	}
	
	public static String[] getFormattedSize(float sizeInBytes) {
		return getFormattedSize((int)sizeInBytes);
	}
}