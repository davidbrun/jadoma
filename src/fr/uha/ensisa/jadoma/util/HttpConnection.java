package fr.uha.ensisa.jadoma.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpConnection {
    
    private URL url;
    private NetworkInterface networkInterface;
    private Map<String, String> mapHttpHeaders;
    private Socket socket;
    
    /**
     * Initialize an HTTP connection from a URL
     * @param url url of the desired file
     */
    public HttpConnection(URL url) {
        this.url = url;
        this.mapHttpHeaders = new HashMap<String, String>();
        this.mapHttpHeaders.put("Host", this.url.getHost());
    }
    
    /**
     * Initialize an HTTP connection from a URL
     * @param url url of the desired file
     * @throws MalformedURLException if the url is not correct
     */
    public HttpConnection(String url) throws MalformedURLException {
    	this(new URL(url));
    }
    
    /**
     * Initialize an HTTP connection from a URL
     * @param url url of the desired file
     * @param networkInterface the desired network interface for the connection
     * @throws SocketException if the network interface is not up
     */
    public HttpConnection(URL url, NetworkInterface networkInterface) throws SocketException {
        this(url);
    	
        if (networkInterface != null && networkInterface.isUp())
            this.networkInterface = networkInterface;
    }

    /**
     * Initialize an HTTP connection from a URL
     * @param url url of the desired file
     * @param networkInterface the desired network interface for the connection
     * @throws MalformedURLException if the url is not correct
     * @throws SocketException if the network interface is not up
     */
    public HttpConnection(String url, NetworkInterface networkInterface) throws MalformedURLException, SocketException {
        this(new URL(url), networkInterface);
    }
    
    /**
     * Add an HTTP header to the request
     * @param key key word of the header
     * @param value value of the header
     */
    public void addRequestProperty(String key, String value) {
		this.mapHttpHeaders.put(key, value);
    }
    
    /**
     * Establish the connection to the URL
     * @throws IOException if there is a stream error
     */
    private void openConnection() throws IOException {
        // Create the socket
    	this.socket = new Socket();
    	
    	if (this.networkInterface != null)
    		this.socket.bind(new InetSocketAddress(HttpUtil.getInet4AddressFromNic(this.networkInterface), 0));
    		
    	this.socket.connect(new InetSocketAddress(InetAddress.getByName(this.url.getHost()), (this.url.getPort() != -1 ? this.url.getPort() : 80)));
    	
    	// Use the socket to make the request
        OutputStream out = this.socket.getOutputStream();
        PrintWriter pw = new PrintWriter(out, false);
        pw.print("GET /" + this.url.getFile().replaceFirst("/", "") + " HTTP/1.1\r\n");
        for (int i=0; i < this.mapHttpHeaders.size(); i++)
            pw.print(this.mapHttpHeaders.get(i) + "\r\n");
        
        pw.print("\r\n");
        pw.flush();
    }
    
    /**
     * To specify the range of the request: the interval of desired bytes to download
     * @param startByte first byte to download (if null then 0)
     * @param endByte last byte to download (if null then end)
     */
    public void setRange(int startByte, int endByte) {
        this.addRequestProperty("Range", "bytes=" + String.valueOf(startByte) + "-" + String.valueOf(endByte));
    }

    /**
     * To specify the user agent which makes the request
     * @param userAgent code of the user agent
     */
    public void setUserAgent(String userAgent) {
        this.addRequestProperty("User-Agent", userAgent);
    }

    /**
     * Return the input stream of this connection
     * @return the input stream of this connection
     * @throws IOException if there is a stream error
     */
    public InputStream getInputStream() throws IOException {
        if (this.socket == null)
        	this.openConnection();
        
    	return this.socket.getInputStream();
    }
}