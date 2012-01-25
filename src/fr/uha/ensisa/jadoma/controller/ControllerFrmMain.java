package fr.uha.ensisa.jadoma.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.model.DownloadManager;
import fr.uha.ensisa.jadoma.model.DownloadState;
import fr.uha.ensisa.jadoma.view.FrmAddDownload;
import fr.uha.ensisa.jadoma.view.FrmAddListDownloads;
import fr.uha.ensisa.jadoma.view.FrmMain;
import fr.uha.ensisa.jadoma.view.FrmPreferences;
import fr.uha.ensisa.jadoma.view.FrmScheduler;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class ControllerFrmMain {
	
	// Associated view
	private FrmMain frmMain;
	
	// Model fields
	private DownloadManager downloadManager;
	
	public ControllerFrmMain(FrmMain frmMain) {
		this.frmMain = frmMain;
		this.downloadManager = new DownloadManager();
	}
	
	@SuppressWarnings("unchecked")
	public void importDownloads() {
		List<Download> listDownloads;
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("history.dat"));
			listDownloads = (List<Download>) ois.readObject();
		} catch (Exception e) {
			listDownloads = new ArrayList<Download>();
		}
		
		for (int i = 0; i < listDownloads.size(); i++)
		{
			Download dl = listDownloads.get(i);
			SimpleDownloadPanel downloadPanel = new SimpleDownloadPanel(dl);
			
			try {
				downloadManager.addDownload(dl);
				frmMain.addDownloadPanel(downloadPanel);
				downloadPanel.updateBackgroundColor();
			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
			
			frmMain.pack();
			
			if (dl.getCurrentState() == DownloadState.DOWNLOADING)
				try {
					ControllerLocator.getInstance().getCtrlFrmMain().getDownloadManager().startDownloading(dl);
				} catch (MalformedURLException e) {
					System.out.println(e.getMessage());
				}
		}
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
		saveDownloads();
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
		new FrmScheduler(frmMain, true).setVisible(true);
	}

	public void handleButtonPreferencesClick() {
		new FrmPreferences(frmMain, true).setVisible(true);
	}

	public void saveDownloads() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("history.dat"));
			oos.writeObject(downloadManager.getListDownloads());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}