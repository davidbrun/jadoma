package fr.uha.ensisa.jadoma.view;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import fr.uha.ensisa.jadoma.controller.ControllerSimpleDownloadPanel;
import fr.uha.ensisa.jadoma.model.Download;

public class SimpleDownloadPanel extends JPanel {
	
	// SWING fields
	private JLabel labelName;
	private JProgressBar progressBar;
	private JButton buttonStop;

	// Controller fields
	private ControllerSimpleDownloadPanel ctrlDownloadPanel;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6443999510840611020L;
	
	public SimpleDownloadPanel(Download download) {
		super();

		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		
		// Initialization of the associated controller
		this.ctrlDownloadPanel = new ControllerSimpleDownloadPanel(this, download);
		
		// Initialization of the values of the SWING components
		this.initComponentValues();
	}

	private void initSwingComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		labelName = new JLabel();
		progressBar = new JProgressBar(0, 100);
		buttonStop = new JButton("Stop");
		
		labelName.setAlignmentX(LEFT_ALIGNMENT);
		labelName.setAlignmentY(Component.TOP_ALIGNMENT);
		
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setMinimumSize(new Dimension(50, 500));
		progressBar.setAlignmentX(LEFT_ALIGNMENT);
		progressBar.setAlignmentY(Component.TOP_ALIGNMENT);
		
		buttonStop.setAlignmentX(LEFT_ALIGNMENT);
		buttonStop.setAlignmentY(TOP_ALIGNMENT);
		
		this.add(labelName);
		this.add(progressBar);
		this.add(buttonStop);
	}
	
	private void initComponentValues() {
		labelName.setText(ctrlDownloadPanel.getDownload().getName());
		progressBar.setValue((int) (ctrlDownloadPanel.getDownload().getProgress() * 100));
	}
	
	public void setProgressValue(int value) {
		progressBar.setValue(value);
	}
}
