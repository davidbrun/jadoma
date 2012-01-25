package fr.uha.ensisa.jadoma.util;

public class TimeUtil {
	
	public static String getFormattedTime(long seconds) {
		StringBuilder result = new StringBuilder();
		
		int days, hours, minutes, secs;
		
		days = (int) Math.floor((seconds / (60 * 60 * 24)));
		seconds -= days * (60 * 60 * 24);
		hours = (int) Math.floor((seconds / (60 * 60)));
		seconds -= hours * (60 * 60);
		minutes = (int) Math.floor(seconds / 60);
		seconds -= minutes * 60;
		secs = (int) seconds;
		
		if (days > 0)
			result.append(days).append(" jours");
		
		if (hours > 0 || result.length() > 0)
			result.append((result.length() > 0 ? ", " : "")).append(hours).append(" h");
		
		if (minutes > 0 || result.length() > 0)
			result.append((result.length() > 0 ? ", " : "")).append(minutes).append(" min");
		
		if (secs > 0 || result.length() > 0)
			result.append((result.length() > 0 ? ", " : "")).append(secs).append(" secondes");
		
		if (result.length() == 0)
			result.append("Quelques instants restant");
		
		return result.toString();
	}
}