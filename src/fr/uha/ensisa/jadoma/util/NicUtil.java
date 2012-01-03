package fr.uha.ensisa.jadoma.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NicUtil {

	public static List<NetworkInterface> getNics() throws SocketException {
		List<NetworkInterface> res = new ArrayList<NetworkInterface>();
		
		Enumeration<NetworkInterface> scanNics = NetworkInterface.getNetworkInterfaces();
		while (scanNics.hasMoreElements()) {
			NetworkInterface nic = scanNics.nextElement();
			if (HttpUtil.isNicConnectedToInternet(nic))
				res.add(nic);
		}
		
		return res;
	}
}