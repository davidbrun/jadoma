package fr.uha.ensisa.jadoma.controller;

import java.io.File;
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
		
		if (!new File(frmAddDownload.getDestinationFileText()).canWrite())
			JOptionPane.showMessageDialog(frmAddDownload, "Impossible d'écrire à l'emplacement spécifié !",
					"Erreur", JOptionPane.WARNING_MESSAGE);
		
		this.addDownload(url);
		
		frmAddDownload.setVisible(false);
	}

	public void handleButtonCancelClick() {
		frmAddDownload.setVisible(false);
	}
	
	public void handleButtonBrowseClick() {
		 JFileChooser fileChooser = new JFileChooser();
		 fileChooser.setCurrentDirectory(new java.io.File(ControllerLocator.getInstance().getUserPreferences().getDestinationFolder()));
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
	
	public void handleButtonDownloadAllClick() {
		String urls = frmAddDownload.getTextFieldDownloadURL();
		String[] splitedUrl = urls.split("\n");
		
		for (String s : splitedUrl)
			if (s.length() != 0 && s.startsWith("http://") && new File(frmAddDownload.getDestinationFileText()).canWrite())
				addDownload(s);
		
		frmAddDownload.setVisible(false);
	}
	
	private void addDownload(String url) {
		Download dl = DownloadFactory.createDownload(UrlUtil.getFileNameFromUrl(url), url);
		SimpleDownloadPanel downloadPanel = new SimpleDownloadPanel(dl);
		dl.setFileDestination(frmAddDownload.getDestinationFileText() +
				(frmAddDownload.getDestinationFileText().endsWith("/") ? "" : "/") +
				dl.getName());
		
		int i = 0;
		String originalName = dl.getName();
		String originalNameWithoutExtension = UrlUtil.getFileNameWithoutExtension(originalName);
		String extension = originalName.replaceAll(originalNameWithoutExtension, "");
		while (new File(dl.getFileDestination()).exists())
		{
			dl.setName(originalNameWithoutExtension + "_" + i + extension);
			dl.setFileDestination(frmAddDownload.getDestinationFileText() +
					(frmAddDownload.getDestinationFileText().endsWith("/") ? "" : "/") +
					dl.getName());
			i++;
		}
		
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
			try {
				ControllerLocator.getInstance().getCtrlFrmMain().getDownloadManager().startDownloading(dl);
			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
	}
}