package fr.uha.ensisa.jadoma.model;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class DownloadManager {
	
	private List<Download> listDownloads;
	private List<DownloadThread> listDownloadThreads;
	private Scheduler scheduler;
	
	public DownloadManager() {
		this.listDownloads = new ArrayList<Download>();
		this.listDownloadThreads = new ArrayList<DownloadThread>();
		this.scheduler = new Scheduler();
	}
	
	public Download getDownload(int index) {
		return listDownloads.get(index);
	}
	
	public int getDownloadsNumber() {
		return listDownloads.size();
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	
	public void addDownload(SimpleDownloadPanel downloadPanel, Download download) throws MalformedURLException {
		this.listDownloads.add(download);
		this.listDownloadThreads.add(new UniPartDownloadThread(downloadPanel, download));
	}
	
	public void startDownloading(Download download) {
		this.listDownloadThreads.get(this.listDownloads.indexOf(download)).start();
	}
	
	public void stopDownloading(Download download) {
		this.listDownloadThreads.get(this.listDownloads.indexOf(download)).interrupt();
	}
	
	public void startDownloading() {
		for (int i = 0; i < this.listDownloads.size(); i++)
			this.listDownloadThreads.get(i).start();
	}
	
	public void stopDownloading() {
		for (int i = 0; i < this.listDownloads.size(); i++)
			this.listDownloadThreads.get(i).interrupt();
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