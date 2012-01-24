package fr.uha.ensisa.jadoma.model;

import java.net.MalformedURLException;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public abstract class DownloadThread extends Thread {
	
	protected SimpleDownloadPanel downloadPanel;
	protected Download download;
	protected boolean isRunning = false;
	protected boolean isDead = false;
	
	public DownloadThread(SimpleDownloadPanel downloadPanel, Download download) throws MalformedURLException {
		super();
		this.downloadPanel = downloadPanel;
		this.download = download;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public boolean isDead() {
		return isDead;
	}
}