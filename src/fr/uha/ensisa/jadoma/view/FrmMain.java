package fr.uha.ensisa.jadoma.view;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import fr.uha.ensisa.jadoma.controller.ControllerFrmMain;

public class FrmMain extends JFrame {

	// SWING fields
	private JLabel labelDisplayDownloadManagerText;

	// Model fields
	
	
	// Controller fields
	private ControllerFrmMain ctrlFrmMain;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6344324745845910099L;

	public FrmMain() {
		super();

		// Initialization of all the SWING components of the frame
		this.initSwingComponents();

		// Initialization of the associated controller
		this.ctrlFrmMain = new ControllerFrmMain(this);
		
		// Ask the layout manager to do its work
		this.pack();
	}

	private void initSwingComponents() {
		Container contentPane = this.getContentPane();
		
		// Create SWING components
		this.labelDisplayDownloadManagerText = new JLabel();
		
		// Add SWING components
		contentPane.add(this.labelDisplayDownloadManagerText);
	}
	
	public void setLabelDisplayDownloadManagerText(String textToDisplay) {
		this.labelDisplayDownloadManagerText.setText(textToDisplay);
	}
}