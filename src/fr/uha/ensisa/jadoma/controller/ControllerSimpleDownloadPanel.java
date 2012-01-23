package fr.uha.ensisa.jadoma.controller;

import java.net.MalformedURLException;

import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class ControllerSimpleDownloadPanel {
	
	// Associated view
	private SimpleDownloadPanel downloadPanel;
	
	// Model fields
	private Download download;

	public ControllerSimpleDownloadPanel(SimpleDownloadPanel downloadPanel, Download download) {
		this.downloadPanel = downloadPanel;
		this.download = download;
	}

	public Download getDownload() {
		return download;
	}
	
	public SimpleDownloadPanel getSimpleDownloadPanel() {
		return downloadPanel;
	}
	
	public void handleButtonStartClick() {
		try {
			ControllerLocator.getInstance().getCtrlFrmMain().getDownloadManager().startDownloading(download);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void handleButtonStopClick() {
		ControllerLocator.getInstance().getCtrlFrmMain().getDownloadManager().stopDownloading(download);
	}
}