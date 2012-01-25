package fr.uha.ensisa.jadoma.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
		if (url.length() == 0)
			return;
		
		if (!url.startsWith("http://"))
			JOptionPane.showMessageDialog(frmAddDownload, "Seul le protocol HTTP est supporté actuellement",
					"Erreur de protocol", JOptionPane.WARNING_MESSAGE);
			
		
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
		
		if (frmAddDownload.isCheckBoxStartAutoChecked())
			; //TODO: launch automatically the download
		
		frmAddDownload.setVisible(false);
	}

	public void handleButtonCancelClick() {
		frmAddDownload.setVisible(false);
	}
	
	public void handleButtonBrowseClick() {
		 JFileChooser fileChooser = new JFileChooser();
	     //TODO: start at the stored folder
		 fileChooser.setCurrentDirectory(new java.io.File("."));
		 fileChooser.setDialogTitle("Rechercher un dossier");
		 fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 fileChooser.setAcceptAllFileFilterUsed(false);
		 if (fileChooser.showOpenDialog(frmAddDownload) == JFileChooser.APPROVE_OPTION) { 
			try {
				frmAddDownload.setDestinationFileText(fileChooser.getSelectedFile().getCanonicalPath());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		 }
	}
}