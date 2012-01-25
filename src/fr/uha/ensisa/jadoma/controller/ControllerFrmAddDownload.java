package fr.uha.ensisa.jadoma.controller;

import java.net.MalformedURLException;
import fr.uha.ensisa.jadoma.factory.DownloadFactory;
import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.util.UrlUtil;
import fr.uha.ensisa.jadoma.view.FrmAddDownload;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class ControllerFrmAddDownload {
	
	// Associated view
	private FrmAddDownload frmAddDownload;
	
	public ControllerFrmAddDownload(FrmAddDownload frmAddDownload) {
		this.frmAddDownload = frmAddDownload;
	}

	public void handleButtonDownloadClick() {
		String url = frmAddDownload.getTextFieldDownloadURL();
		if(url.length() == 0)
			return;
		
		Download dl = DownloadFactory.createDownload(UrlUtil.getFileNameFromUrl(url), url);
		SimpleDownloadPanel downloadPanel = new SimpleDownloadPanel(dl);
		
		try {
			ControllerLocator.getInstance().getCtrlFrmMain().getDownloadManager().addDownload(dl);
			ControllerLocator.getInstance().getCtrlFrmMain().getFrmMain().addDownloadPanel(downloadPanel);
			downloadPanel.updateBackgroundColor();
			frmAddDownload.clearURLField();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
		ControllerLocator.getInstance().getCtrlFrmMain().getFrmMain().pack();
	}

	public void handleButtonCancelClick() {
		frmAddDownload.setVisible(false);
	}
}