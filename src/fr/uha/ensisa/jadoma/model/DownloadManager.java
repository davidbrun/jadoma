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
		StringBuilder res = new StringBuilder("[scheduler: ");
		res.append(scheduler.toString()).append(", ").append(System.getProperty("line.separator"));
		res.append("downloads: ");
		
		for (Download d : listDownloads)
			res.append(d.toString()).append(System.getProperty("line.separator"));
		
		res.append("]");
		
		return res.toString();
	}
}