package fr.uha.ensisa.jadoma.controller;

import fr.uha.ensisa.jadoma.model.DownloadManager;
import fr.uha.ensisa.jadoma.model.UniPartDownload;
import fr.uha.ensisa.jadoma.view.FrmMain;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class ControllerFrmMain {
	
	private FrmMain frmMain;
	private DownloadManager downloadManager;
	
	public ControllerFrmMain(FrmMain frmMain) {
		this.frmMain = frmMain;
		this.downloadManager = new DownloadManager();
		
		this.updateLabelCaption();
	}

	public void handleButtonActClick() {
		UniPartDownload dl = new UniPartDownload(frmMain.getTextFieldDownloadURL());
		this.downloadManager.getListDownloads().add(dl);
		this.frmMain.addDownloadPanel(new SimpleDownloadPanel(dl.toString()));

		this.updateLabelCaption();
	}
	
	private void updateLabelCaption() {
		this.frmMain.setLabelDisplayDownloadManagerText(this.downloadManager.toString());
		
		
		this.frmMain.pack();
	}
}