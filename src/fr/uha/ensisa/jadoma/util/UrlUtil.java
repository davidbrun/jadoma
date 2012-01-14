package fr.uha.ensisa.jadoma.util;

public class UrlUtil {
	
	public static String getFileNameFromUrl(String url) {
		String[] tab = url.split("/");
		if (tab.length != 0)
			return tab[tab.length - 1];
		else
			return "";
	}
}