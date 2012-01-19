package fr.uha.ensisa.jadoma.controller;

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
}