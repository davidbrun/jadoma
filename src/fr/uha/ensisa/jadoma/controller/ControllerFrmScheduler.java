package fr.uha.ensisa.jadoma.controller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.JOptionPane;

import fr.uha.ensisa.jadoma.util.TimeUtil;
import fr.uha.ensisa.jadoma.view.FrmScheduler;

public class ControllerFrmScheduler {
	
	// Associated view
	private FrmScheduler frmScheduler;
	
	public ControllerFrmScheduler(FrmScheduler frmScheduler) {
		this.frmScheduler = frmScheduler;
	}

	public void handleButtonSaveClick() {
		Date start, end;
		
		start = TimeUtil.getTimeFromString(frmScheduler.getStartDate());
		end = TimeUtil.getTimeFromString(frmScheduler.getEndDate());
		
		if (start == null || end == null)
		{
			JOptionPane.showMessageDialog(frmScheduler, "Veuillez entrer des heures valides",
					"Erreur", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ControllerLocator.getInstance().getScheduler().setStartDate(start);
		ControllerLocator.getInstance().getScheduler().setEndDate(end);
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("scheduler.dat"));
			oos.writeObject(ControllerLocator.getInstance().getScheduler());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		frmScheduler.setVisible(false);
	}
	
	public void handleButtonCancelClick() {
		frmScheduler.setVisible(false);
	}
}