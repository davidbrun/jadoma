package fr.uha.ensisa.jadoma.model;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import fr.uha.ensisa.jadoma.controller.ControllerLocator;
import fr.uha.ensisa.jadoma.factory.DownloadFactory;

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
	
	public void addDownload(Download download) throws MalformedURLException {
		this.listDownloads.add(download);
		this.listDownloadThreads.add(new UniPartDownloadThread(
				ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(download).getSimpleDownloadPanel(),
				download));
	}
	
	public void startDownloading(Download download) throws MalformedURLException {
		int downloadIndex = this.listDownloads.indexOf(download);
		DownloadThread tmp = this.listDownloadThreads.get(downloadIndex);
		
		if (tmp.isDead())
		{
			this.listDownloadThreads.remove(tmp);
			tmp = null;
			tmp = DownloadFactory.createDownloadThread(
					ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(download).getSimpleDownloadPanel(),
					download);
			this.listDownloadThreads.add(downloadIndex, (DownloadThread) tmp);
		}
		if (!tmp.isRunning)
			tmp.start();
	}
	
	public void stopDownloading(Download download) {
		DownloadThread tmp = this.listDownloadThreads.get(this.listDownloads.indexOf(download));
		if (tmp.isRunning)
			tmp.interrupt();
	}
	
	public void startDownloading() throws MalformedURLException {
		for (Download d : this.listDownloads)
			this.startDownloading(d);
	}
	
	public void stopDownloading() {
		for (Download d : this.listDownloads)
			this.stopDownloading(d);
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