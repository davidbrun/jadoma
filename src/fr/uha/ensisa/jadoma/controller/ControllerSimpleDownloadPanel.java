package fr.uha.ensisa.jadoma.controller;

import javax.swing.JOptionPane;

import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class ControllerSimpleDownloadPanel {
	
	// Main controller
	private ControllerFrmMain ctrlFrmMain;
	
	// Associated view
	private SimpleDownloadPanel downloadPanel;
	
	// Model fields
	private Download download;

	public ControllerSimpleDownloadPanel(ControllerFrmMain ctrlFrmMain, SimpleDownloadPanel downloadPanel, Download download) {
		this.ctrlFrmMain = ctrlFrmMain;
		this.downloadPanel = downloadPanel;
		this.download = download;
	}

	public Download getDownload() {
		return download;
	}
	
	public void handleButtonStartClick() {
		JOptionPane.showMessageDialog(ctrlFrmMain.getFrmMain(), "Début du download");
		ctrlFrmMain.getDownloadManager().startDownloading(download);
	}
}