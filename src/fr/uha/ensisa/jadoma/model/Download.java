package fr.uha.ensisa.jadoma.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Download {
	
	protected List<DownloadPart> listDownloadParts;
	
	public Download() {
		this.listDownloadParts = new ArrayList<DownloadPart>();
	}
	
	public List<DownloadPart> getListDownloadParts() {
		return listDownloadParts;
	}
}