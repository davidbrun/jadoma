package fr.uha.ensisa.jadoma.controller;

import java.util.ArrayList;
import java.util.List;
import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.model.DownloadState;
import fr.uha.ensisa.jadoma.view.FrmAddDownload;
import fr.uha.ensisa.jadoma.view.FrmMain;
import fr.uha.ensisa.jadoma.view.SimpleDownloadPanel;

public class ControllerLocator {
	
	// Static instance
    private static ControllerLocator thisInstance;
    private ControllerFrmMain ctrlFrmMainInstance;
    private List<ControllerSimpleDownloadPanel> listCtrlSimpleDownloadPanels;
	private ControllerFrmAddDownload ctrlFrmAddDownloadInstance;
    
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
	
	public void createCtrlFrmAddDownload(FrmAddDownload frmAddDownload) {
        ctrlFrmAddDownloadInstance = new ControllerFrmAddDownload(frmAddDownload);
	}
	
	public ControllerFrmAddDownload getCtrlFrmAddDownload() {
        return ctrlFrmAddDownloadInstance;
	}
	
	public int getPositionOfSimpleDownloadPanel(SimpleDownloadPanel simpleDownloadPanel) {
		int result = -1;
		
		for (int i = 0; i < listCtrlSimpleDownloadPanels.size(); i++)
			if (listCtrlSimpleDownloadPanels.get(i).getSimpleDownloadPanel().equals(simpleDownloadPanel))
			{
				result = i;
				break;
			}
		
		return result;
	}
	
	public int getPositionOfSelectedSimpleDownloadPanel() {
		int result = -1;
		
		for (int i = 0; i < listCtrlSimpleDownloadPanels.size(); i++)
			if (listCtrlSimpleDownloadPanels.get(i).getSimpleDownloadPanel().isSelected())
			{
				result = i;
				break;
			}
		
		return result;
	}
	
	public void deselectAllDownloadPanel() {
		for (ControllerSimpleDownloadPanel c : listCtrlSimpleDownloadPanels)
			c.getSimpleDownloadPanel().setSelected(false);
	}
	
	public void selectNextSimpleDownloadPanel(int currentDownloadPanelPosition) {
		if (currentDownloadPanelPosition < listCtrlSimpleDownloadPanels.size() - 1)
			if (currentDownloadPanelPosition == -1)
				listCtrlSimpleDownloadPanels.get(0).getSimpleDownloadPanel().setSelected(true);
			else 
				listCtrlSimpleDownloadPanels.get(currentDownloadPanelPosition + 1).getSimpleDownloadPanel().setSelected(true);
		else
			listCtrlSimpleDownloadPanels.get(currentDownloadPanelPosition).getSimpleDownloadPanel().setSelected(true);
	}
	
	public void selectPreviousSimpleDownloadPanel(int currentDownloadPanelPosition) {
		if (currentDownloadPanelPosition > 0)
			if (currentDownloadPanelPosition == -1)
				listCtrlSimpleDownloadPanels.get(0).getSimpleDownloadPanel().setSelected(true);
			else 
				listCtrlSimpleDownloadPanels.get(currentDownloadPanelPosition - 1).getSimpleDownloadPanel().setSelected(true);
		else
			listCtrlSimpleDownloadPanels.get(currentDownloadPanelPosition).getSimpleDownloadPanel().setSelected(true);
	}
	
	public int getNbrOfDownloadPanels() {
		return listCtrlSimpleDownloadPanels.size();
	}

	public void clearOldDownloads() {
		for (int i = 0; i < listCtrlSimpleDownloadPanels.size(); i++)
		{
			ControllerSimpleDownloadPanel ctrl = listCtrlSimpleDownloadPanels.get(i);
			if (ctrl.getDownload().getCurrentState() == DownloadState.CANCELED ||
					ctrl.getDownload().getCurrentState() == DownloadState.COMPLETED)
			{
				listCtrlSimpleDownloadPanels.remove(ctrl);
				Download tmp = ctrl.getDownload();
				ctrlFrmMainInstance.getDownloadManager().removeDownload(tmp);
				tmp = null;
				SimpleDownloadPanel tmp2 = ctrl.getSimpleDownloadPanel();
				ctrlFrmMainInstance.getFrmMain().getScrollPanel().remove(tmp2);
				tmp2 = null;
				ctrl = null;
			}
		}
	}

	public Download getSelectedDownload() {
		Download result = null;
		
		for (ControllerSimpleDownloadPanel d : listCtrlSimpleDownloadPanels)
			if (d.getSimpleDownloadPanel().isSelected())
			{
				result = d.getDownload();
				break;
			}
		
		return result;
	}
}