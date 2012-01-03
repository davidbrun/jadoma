package fr.uha.ensisa.jadoma.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Download {
	
	protected String url;
	protected List<DownloadPart> listDownloadParts;
	
	public Download(String url) {
		this.url = url;
		this.listDownloadParts = new ArrayList<DownloadPart>();
	}
	
	public List<DownloadPart> getListDownloadParts() {
		return listDownloadParts;
	}
	
	@Override
	public String toString() {
		return this.url;
	}
}