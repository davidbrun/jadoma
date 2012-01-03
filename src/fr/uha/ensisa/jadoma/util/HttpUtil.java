package fr.uha.ensisa.jadoma.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;

public class HttpUtil {
	
	private static final String WEB_SITE_TEST_CONNECTION_URL = "www.debian.org";
	
	public static boolean isNicConnectedToInternet(NetworkInterface nic) {
		try {
			Socket s = new Socket();
			s.bind(new InetSocketAddress(getInet4AddressFromNic(nic), 0));
			s.connect(new InetSocketAddress(InetAddress.getByName(WEB_SITE_TEST_CONNECTION_URL), 80));
			
			PrintWriter pw = new PrintWriter(s.getOutputStream(), false);
			pw.print("GET / HTTP/1.1\r\n");
			pw.print("Host: " + WEB_SITE_TEST_CONNECTION_URL + "\r\n");
			pw.print("\r\n");
			pw.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String inputLine, outputCode = "";
			inputLine = br.readLine();
			outputCode = outputCode + inputLine + "\n";
			br.close();
			pw.close();
			if (outputCode.substring(9, 12).equalsIgnoreCase("200"))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static Inet4Address getInet4AddressFromNic(NetworkInterface nic) {
		Inet4Address res = null;
		Enumeration<InetAddress> it = nic.getInetAddresses();
		while (it.hasMoreElements()) {
			InetAddress ia =  it.nextElement();
			if (ia instanceof Inet4Address) {
				res = (Inet4Address)ia;
				break;
			}
		}
		return res;
	}
}