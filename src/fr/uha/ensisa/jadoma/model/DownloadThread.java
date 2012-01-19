package fr.uha.ensisa.jadoma.model;

import java.net.MalformedURLException;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public abstract class DownloadThread extends Thread {
	
	protected SimpleDownloadPanel downloadPanel;
	protected Download download;
	protected float downloadSpeed;
	
	public DownloadThread(SimpleDownloadPanel downloadPanel, Download download) throws MalformedURLException {
		super();
		this.downloadPanel = downloadPanel;
		this.download = download;
	}
}