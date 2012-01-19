package fr.uha.ensisa.jadoma.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Download {
	
	private String name;
	private String urlFrom;
	private String fileDestination;
	private List<DownloadPart> listDownloadParts;
	private DownloadState currentState, previousState;
	private int size;
	private Date startDate, endDate;
	
	public Download(String name, String url) {
		this.name = name;
		this.urlFrom = url;
		this.startDate = new Date();
		this.endDate = new Date(Long.MAX_VALUE);
		this.listDownloadParts = new ArrayList<DownloadPart>();
		
		try {
		   HttpURLConnection.setFollowRedirects(true);
		   HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		   con.setRequestMethod("HEAD");
		   
		   con.setConnectTimeout(10000); //set timeout to 10 seconds
		   con.connect();
		   this.size = con.getContentLength();
		} catch (SocketTimeoutException e) {
		   System.out.println(e.getMessage());
		} catch (IOException e) {
		   System.out.println(e.getMessage());
		}
	}
	
	public List<DownloadPart> getListDownloadParts() {
		return listDownloadParts;
	}
	
	public int getSize() {
		return size;
	}
	
	public double getProgress() {
		if (currentState == DownloadState.COMPLETED)
			return 1.0;
		else
		{
			if (this.listDownloadParts.size() != 0)
			{
				double totalProgress = 0.0;
				for (DownloadPart d : this.listDownloadParts)
					totalProgress += d.getPercentCompleted();
				
				return totalProgress / this.listDownloadParts.size();
			}
			else
				return 0.0;
		}
	}
	
	public String getName() {
		return name;
	}

	public String getUrlFrom() {
		return urlFrom;
	}

	public String getFileDestination() {
		return fileDestination;
	}

	public DownloadState getCurrentState() {
		return currentState;
	}

	public DownloadState getPreviousState() {
		return previousState;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	@Override
	public String toString() {
		return this.urlFrom;
	}
}