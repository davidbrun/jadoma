package fr.uha.ensisa.jadoma.model;

import java.io.Serializable;

public class UserPreferences implements Serializable {

	// Constants
	private static final long serialVersionUID = -3360418721439469588L;
	
	// Fields
	private String destinationFolder;
	private boolean startDownloadAuto;
	private int nbrOfSimultaneousDownloads;
	
	public UserPreferences() {
		this.destinationFolder = System.getProperty("user.home") + "/";
		this.startDownloadAuto = false;
		this.nbrOfSimultaneousDownloads = -1;
	}

	public String getDestinationFolder() {
		return destinationFolder;
	}

	public void setDestinationFolder(String destinationFolder) {
		this.destinationFolder = destinationFolder;
	}

	public boolean isStartDownloadAuto() {
		return startDownloadAuto;
	}

	public void setStartDownloadAuto(boolean startDownloadAuto) {
		this.startDownloadAuto = startDownloadAuto;
	}

	public int getNbrOfSimultaneousDownloads() {
		return nbrOfSimultaneousDownloads;
	}

	public void setNbrOfSimultaneousDownloads(int nbrOfSimultaneousDownloads) {
		this.nbrOfSimultaneousDownloads = nbrOfSimultaneousDownloads;
	}
}