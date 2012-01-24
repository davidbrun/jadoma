package fr.uha.ensisa.jadoma.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.model.DownloadState;
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
	
	public void handleLabelDestinationClick() {
		this.browseOutputFolder();
	}
	
	public void handleLabelNameClick() {
		if (download.getCurrentState() == DownloadState.COMPLETED)
			this.openFile();
	}
	
	private void openFile() {
		if (Desktop.isDesktopSupported()) {
	        Desktop desktop = Desktop.getDesktop();
	        try {
				desktop.open(new File(download.getFileDestination()));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private void browseOutputFolder() {
		if (Desktop.isDesktopSupported()) {
	        Desktop desktop = Desktop.getDesktop();
	        try {
				desktop.open(new File(download.getFileDestination()).getParentFile());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}