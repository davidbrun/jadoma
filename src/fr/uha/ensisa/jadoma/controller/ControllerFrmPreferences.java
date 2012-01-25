package fr.uha.ensisa.jadoma.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import fr.uha.ensisa.jadoma.view.FrmPreferences;

public class ControllerFrmPreferences {
	
	// Associated view
	private FrmPreferences frmPreferences;
	
	public ControllerFrmPreferences(FrmPreferences frmPreferences) {
		this.frmPreferences = frmPreferences;
	}

	public void handleButtonSaveClick() {
		int nbr;
		try {
			nbr = Integer.parseInt(frmPreferences.getNbrOfSimultaneousDownloads());
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frmPreferences, "Veuillez entrer une valeur valide",
					"Erreur", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ControllerLocator.getInstance().getUserPreferences().setDestinationFolder(frmPreferences.getDestinationFolder());
		ControllerLocator.getInstance().getUserPreferences().setNbrOfSimultaneousDownloads(nbr);
		ControllerLocator.getInstance().getUserPreferences().setStartDownloadAuto(frmPreferences.getStartDownloadAuto());
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("preferences.dat"));
			oos.writeObject(ControllerLocator.getInstance().getUserPreferences());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		frmPreferences.setVisible(false);
	}
	
	public void handleButtonBrowseClick() {
		 JFileChooser fileChooser = new JFileChooser();
		 fileChooser.setCurrentDirectory(new java.io.File(ControllerLocator.getInstance().getUserPreferences().getDestinationFolder()));
		 fileChooser.setDialogTitle("Rechercher un dossier");
		 fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 fileChooser.setAcceptAllFileFilterUsed(false);
		 if (fileChooser.showOpenDialog(frmPreferences) == JFileChooser.APPROVE_OPTION) { 
			try {
				frmPreferences.setDestinationFileText(fileChooser.getSelectedFile().getCanonicalPath());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		 }
	}
	
	public void handleButtonCancelClick() {
		frmPreferences.setVisible(false);
	}
}