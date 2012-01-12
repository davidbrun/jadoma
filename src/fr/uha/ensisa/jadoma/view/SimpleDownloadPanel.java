package fr.uha.ensisa.jadoma.view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class SimpleDownloadPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6443999510840611020L;
	private JLabel labelName;
	private JProgressBar progressBar;
	

	
	public SimpleDownloadPanel(String name) {
		super();

		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		labelName.setText(name);
	}

	private void initSwingComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		
		labelName = new JLabel();
		progressBar = new JProgressBar(0, 100);
		
		labelName.setAlignmentX(RIGHT_ALIGNMENT);
		labelName.setAlignmentY(Component.TOP_ALIGNMENT);
		
		progressBar.setMinimumSize(new Dimension(50, 500));
		progressBar.setAlignmentX(RIGHT_ALIGNMENT);
		progressBar.setAlignmentY(Component.TOP_ALIGNMENT);
		this.add(labelName);
		this.add(progressBar);
		this.setVisible(true);
		this.labelName.setVisible(true);
	}
	
	public void setProgressValue(int value) {
		progressBar.setValue(value);
	}
}
