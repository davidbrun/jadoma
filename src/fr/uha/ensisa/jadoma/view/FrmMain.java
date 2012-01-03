package fr.uha.ensisa.jadoma.view;

import javax.swing.JFrame;

import fr.uha.ensisa.jadoma.controller.ControllerFrmMain;

public class FrmMain extends JFrame {
	
	private ControllerFrmMain ctrlFrmMain;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6344324745845910099L;
	
	public FrmMain() {
		super();
		this.ctrlFrmMain = new ControllerFrmMain(this);
	}
}