package fr.uha.ensisa.jadoma.controller;

import java.util.ArrayList;
import java.util.List;
import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.view.FrmMain;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class ControllerLocator {
	
	// Static instance
    private static ControllerLocator thisInstance;
    private ControllerFrmMain ctrlFrmMainInstance;
    private List<ControllerSimpleDownloadPanel> listCtrlSimpleDownloadPanels;
    
    // Just to synchronize
    private static Object synchronousObject = new Object();
    
    // Private constructor
    private ControllerLocator() {
    	listCtrlSimpleDownloadPanels = new ArrayList<ControllerSimpleDownloadPanel>();
    }
    
	public static ControllerLocator getInstance() {
        if (thisInstance == null) // First call
            synchronized(synchronousObject) {
                if (thisInstance == null)
                    thisInstance = new ControllerLocator();
            }
        
        return thisInstance;
    }
	
	public void createCtrlFrmMain(FrmMain frmMain) {
    	ctrlFrmMainInstance = new ControllerFrmMain(frmMain);
	}
	
	public ControllerFrmMain getCtrlFrmMain() {
        return ctrlFrmMainInstance;
	}
    
	public void createCtrlSimpleDownloadPanel(SimpleDownloadPanel downloadPanel, Download download) {
		listCtrlSimpleDownloadPanels.add(new ControllerSimpleDownloadPanel(downloadPanel, download));
	}
	
	public ControllerSimpleDownloadPanel getCtrlSimpleDownloadPanel(Download download) {
		ControllerSimpleDownloadPanel result = null;
		
		for (ControllerSimpleDownloadPanel ctrl : listCtrlSimpleDownloadPanels)
			if (ctrl.getDownload().equals(download))
			{
				result = ctrl;
				break;
			}
		
		return result;
	}
	
	public ControllerSimpleDownloadPanel getCtrlSimpleDownloadPanel(SimpleDownloadPanel simpleDownloadPanel) {
		ControllerSimpleDownloadPanel result = null;
		
		for (ControllerSimpleDownloadPanel ctrl : listCtrlSimpleDownloadPanels)
			if (ctrl.getSimpleDownloadPanel().equals(simpleDownloadPanel))
			{
				result = ctrl;
				break;
			}
		
		return result;
	}
}