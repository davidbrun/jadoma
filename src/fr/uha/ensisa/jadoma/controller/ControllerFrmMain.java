package fr.uha.ensisa.jadoma.controller;

import java.net.MalformedURLException;

import fr.uha.ensisa.jadoma.model.DownloadManager;
import fr.uha.ensisa.jadoma.view.FrmAddDownload;
import fr.uha.ensisa.jadoma.view.FrmAddListDownloads;
import fr.uha.ensisa.jadoma.view.FrmMain;
import fr.uha.ensisa.jadoma.view.FrmPreferences;

public class ControllerFrmMain {
	
	// Associated view
	private FrmMain frmMain;
	
	// Model fields
	private DownloadManager downloadManager;
	
	public ControllerFrmMain(FrmMain frmMain) {
		this.frmMain = frmMain;
		this.downloadManager = new DownloadManager();
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
	
	public void handleButtonAddDownloadClick() {
		new FrmAddDownload(frmMain, true).setVisible(true);
	}
	
	public void handleButtonClearOldDownloadsClick() {
		ControllerLocator.getInstance().clearOldDownloads();
		frmMain.getScrollPanel().repaint(frmMain.getScrollPanel().getVisibleRect());
		frmMain.pack();
	}
	
	public DownloadManager getDownloadManager() {
		return downloadManager;
	}
	
	public FrmMain getFrmMain() {
		return frmMain;
	}

	public void handleButtonStartDownloadClick() {
		try {
			downloadManager.startDownloading(ControllerLocator.getInstance().getSelectedDownload());
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void handleButtonPauseDownloadClick() {
		downloadManager.stopDownloading(ControllerLocator.getInstance().getSelectedDownload());
	}

	public void handleButtonCancelDownloadClick() {
		downloadManager.cancelDownloading(ControllerLocator.getInstance().getSelectedDownload());
	}

	public void handleButtonAddDownloadsClick() {
		new FrmAddListDownloads(frmMain, true).setVisible(true);
	}

	public void handleButtonStartDownloadsClick() {
		try {
			downloadManager.startDownloading();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void handleButtonPauseDownloadsClick() {
		downloadManager.stopDownloading();
	}

	public void handleButtonCancelDownloadsClick() {
		downloadManager.cancelDownloading();
	}

	public void handleButtonSchedulerClick() {
		
	}

	public void handleButtonPreferencesClick() {
		new FrmPreferences(frmMain, true).setVisible(true);
	}
}