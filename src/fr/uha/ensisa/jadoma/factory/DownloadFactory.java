package fr.uha.ensisa.jadoma.factory;

import java.net.MalformedURLException;

import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.model.DownloadThread;
import fr.uha.ensisa.jadoma.model.UniPartDownloadThread;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;
import fr.uha.ensisa.jadoma.viewmodel.DownloadViewModel;

public class DownloadFactory {
	
	public static Download createDownload(String name, String url) {
		return new DownloadViewModel(name, url);	
	}
	
	public static DownloadThread createDownloadThread(SimpleDownloadPanel downloadPanel, Download download) throws MalformedURLException {
		return new UniPartDownloadThread(downloadPanel, download);
	}
}