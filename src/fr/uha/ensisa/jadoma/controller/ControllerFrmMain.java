package fr.uha.ensisa.jadoma.controller;

import fr.uha.ensisa.jadoma.model.DownloadManager;
import fr.uha.ensisa.jadoma.view.FrmMain;

public class ControllerFrmMain {
	
	// Associated view
	private FrmMain frmMain;
	
	// Model fields
	private DownloadManager downloadManager;
	
	public ControllerFrmMain(FrmMain frmMain) {
		this.frmMain = frmMain;
		this.downloadManager = new DownloadManager();
		
		//this.frmMain.pack();
	}
	
	public void handleKeyUpPressedOnScrollPanel() {
		int currentPosition = ControllerLocator.getInstance().getPositionOfSelectedSimpleDownloadPanel();
		ControllerLocator.getInstance().deselectAllDownloadPanel();
		ControllerLocator.getInstance().selectPreviousSimpleDownloadPanel(currentPosition);
		frmMain.getVerticalScrollBar().setValue(ControllerLocator.getInstance().getPositionOfSelectedSimpleDownloadPanel() * frmMain.getVerticalScrollBar().getMaximum() / ControllerLocator.getInstance().getNbrOfDownloadPanels());
	}
	
	public void handleKeyDownPressedOnScrollPanel() {
		int currentPosition = ControllerLocator.getInstance().getPositionOfSelectedSimpleDownloadPanel();
		ControllerLocator.getInstance().deselectAllDownloadPanel();
		ControllerLocator.getInstance().selectNextSimpleDownloadPanel(currentPosition);
		frmMain.getVerticalScrollBar().setValue(ControllerLocator.getInstance().getPositionOfSelectedSimpleDownloadPanel() * frmMain.getVerticalScrollBar().getMaximum() / ControllerLocator.getInstance().getNbrOfDownloadPanels());
	}
	
	public DownloadManager getDownloadManager() {
		return downloadManager;
	}
	
	public FrmMain getFrmMain() {
		return frmMain;
	}
}