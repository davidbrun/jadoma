package fr.uha.ensisa.jadoma.model;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import fr.uha.ensisa.jadoma.controller.ControllerLocator;
import fr.uha.ensisa.jadoma.factory.DownloadFactory;

public class DownloadManager {
	
	// Fields
	private List<Download> listDownloads;
	private List<DownloadThread> listDownloadThreads;
	private Scheduler scheduler;
	private int nbrOfSimultaneousDownloads;
	
	public DownloadManager() {
		this.listDownloads = new ArrayList<Download>();
		this.listDownloadThreads = new ArrayList<DownloadThread>();
		this.scheduler = ControllerLocator.getInstance().getScheduler();
		this.nbrOfSimultaneousDownloads = 0;
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
	
	public void removeDownload(Download download) {
		this.listDownloadThreads.remove(this.listDownloads.indexOf(download));
		this.listDownloads.remove(download);
	}
	
	public void startDownloading(Download download) throws MalformedURLException {
		if (download != null)
		{
			if (this.nbrOfSimultaneousDownloads >= ControllerLocator.getInstance().getUserPreferences().getNbrOfSimultaneousDownloads()
					&& ControllerLocator.getInstance().getUserPreferences().getNbrOfSimultaneousDownloads() >= 0)
			{
				JOptionPane.showMessageDialog(ControllerLocator.getInstance().getCtrlFrmMain().getFrmMain(),
						"La limite de téléchargements simultanés est atteinte",
						"Téléchargement non lancé", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			if (!scheduler.canStartDownloading())
			{
				JOptionPane.showMessageDialog(ControllerLocator.getInstance().getCtrlFrmMain().getFrmMain(),
						"La planification empêche le téléchargement de commencer",
						"Téléchargement non lancé", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
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
	}
	
	public void stopDownloading(Download download) {
		if (download != null)
		{
			DownloadThread tmp = this.listDownloadThreads.get(this.listDownloads.indexOf(download));
			if (tmp.isRunning)
				tmp.interrupt();
		}
	}
	
	public void cancelDownloading(Download download) {
		if (download != null)
		{
			DownloadThread tmp = this.listDownloadThreads.get(this.listDownloads.indexOf(download));
			if (tmp.isRunning)
				tmp.interrupt();
			
			new File(download.getFileDestination()).delete();
			download.setEndDate(new Date());
			download.setCurrentState(DownloadState.CANCELED);
		}
	}
	
	public void startDownloading() throws MalformedURLException {
		for (Download d : this.listDownloads)
			this.startDownloading(d);
	}
	
	public void stopDownloading() {
		for (Download d : this.listDownloads)
			this.stopDownloading(d);
	}
	
	public void cancelDownloading() {
		for (Download d : this.listDownloads)
			this.cancelDownloading(d);
	}
	
	public synchronized void incrNbrOfSimultaneousDownloads() {
		this.nbrOfSimultaneousDownloads++;
	}
	
	public synchronized void decrNbrOfSimultaneousDownloads() {
		this.nbrOfSimultaneousDownloads--;
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

	public List<Download> getListDownloads() {
		return this.listDownloads;
	}
}