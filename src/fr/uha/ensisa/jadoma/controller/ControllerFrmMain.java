package fr.uha.ensisa.jadoma.controller;

import java.net.MalformedURLException;

import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.model.DownloadManager;
import fr.uha.ensisa.jadoma.util.UrlUtil;
import fr.uha.ensisa.jadoma.view.FrmMain;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class ControllerFrmMain {
	
	// Associated view
	private FrmMain frmMain;
	
	// Model fields
	private DownloadManager downloadManager;
	
	public ControllerFrmMain(FrmMain frmMain) {
		this.frmMain = frmMain;
		this.downloadManager = new DownloadManager();
		
		this.updateLabelCaption();
	}

	public void handleButtonActClick() {
		String url = frmMain.getTextFieldDownloadURL();
		if(url.length() == 0)
			return;
		
		Download dl = new Download(UrlUtil.getFileNameFromUrl(url), url);
		SimpleDownloadPanel downloadPanel = new SimpleDownloadPanel(this, dl);
		
		try {
			this.downloadManager.addDownload(downloadPanel, dl);
			this.frmMain.addDownloadPanel(downloadPanel);
			this.frmMain.clearURLField();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
		this.updateLabelCaption();
	}
	
	public DownloadManager getDownloadManager() {
		return downloadManager;
	}
	
	public FrmMain getFrmMain() {
		return frmMain;
	}
	
	private void updateLabelCaption() {
		this.frmMain.pack();
	}
}