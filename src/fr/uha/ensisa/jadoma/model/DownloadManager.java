package fr.uha.ensisa.jadoma.model;

import java.util.ArrayList;
import java.util.List;

public class DownloadManager {
	
	private List<Download> listDownloads;
	private Scheduler scheduler;
	
	public DownloadManager() {
		this.listDownloads = new ArrayList<Download>();
		this.scheduler = new Scheduler();
	}
	
	public List<Download> getListDownloads() {
		return listDownloads;
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	
	@Override
	public String toString() {
		return "[scheduler: " + scheduler.toString() + ", downloads: " + listDownloads.toString() + "]";
	}
}