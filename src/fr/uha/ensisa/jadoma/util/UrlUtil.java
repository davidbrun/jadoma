package fr.uha.ensisa.jadoma.util;

public class UrlUtil {
	
	public static String getFileNameFromUrl(String url) {
		String[] tab = url.split("/");
		if (tab.length != 0)
			return tab[tab.length - 1];
		else
			return "";
	}
	
	public static String getFileNameWithoutExtension(String name) {
		String[] tab = name.split("\\.");
		if (tab.length != 0)
		{
			StringBuilder result = new StringBuilder();
			
			for (int i = 0; i < tab.length - 1; i++)
			{
				if (i != 0)
					result.append(".");
				result.append(tab[i]);
			}
			
			return result.toString();
		}
		else
			return "";
	}
}