package fr.uha.ensisa.jadoma.viewmodel;

import fr.uha.ensisa.jadoma.controller.ControllerLocator;
import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.model.DownloadState;

public class DownloadViewModel extends Download {
	
	// Constants
	private static final long serialVersionUID = 2988173960004590105L;

	public DownloadViewModel(String name, String url) {
		super(name, url);
	}
	
	@Override
	public void setCurrentState(DownloadState currentState) {
		super.setCurrentState(currentState);
		ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getSimpleDownloadPanel().toogleDownloadState();
		
		if (currentState == DownloadState.DOWNLOADING)
			ControllerLocator.getInstance().getCtrlFrmMain().getDownloadManager().incrNbrOfSimultaneousDownloads();
		else
			ControllerLocator.getInstance().getCtrlFrmMain().getDownloadManager().decrNbrOfSimultaneousDownloads();
		
		ControllerLocator.getInstance().getCtrlFrmMain().saveDownloads();
	}
	
	@Override
	public void setFileDestination(String fileDestination) {
		super.setFileDestination(fileDestination);
		ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getSimpleDownloadPanel().updateComponentValues(this);
	}
}