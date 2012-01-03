package fr.uha.ensisa.jadoma.model;

import java.util.ArrayList;
import java.util.List;

public class DownloadManager {
	
	private List<Download> listDownloads;
	
	public DownloadManager() {
		this.listDownloads = new ArrayList<Download>();
	}
	
	public List<Download> getListDownloads() {
		return listDownloads;
	}
}