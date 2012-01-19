package fr.uha.ensisa.jadoma.model;

import java.net.MalformedURLException;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class MultiPartDownloadThread extends DownloadThread {

	public MultiPartDownloadThread(SimpleDownloadPanel downloadPanel, Download download) throws MalformedURLException {
		super(downloadPanel, download);
	}
}