package fr.uha.ensisa.jadoma.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public abstract class Download {
	
	protected String name;
	protected String urlFrom;
	protected String fileDestination;
	protected List<DownloadPart> listDownloadParts;
	protected DownloadState currentState, previousState;
	protected int size;
	protected Date startDate, endDate;
	
	public Download(String name, String url) {
		this.name = name;
		this.urlFrom = url;
		this.startDate = new Date();
		this.endDate = new Date(Long.MAX_VALUE);
		this.listDownloadParts = new ArrayList<DownloadPart>();
		//TODO: get size with HEAD request
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
			//return 0.0;
			//TODO: remove random value
			return new Random().nextDouble();
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