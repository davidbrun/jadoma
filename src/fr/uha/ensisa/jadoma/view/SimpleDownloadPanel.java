package fr.uha.ensisa.jadoma.view;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	private JButton buttonStop;

	
	public SimpleDownloadPanel(String name) {
		super();

		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		labelName.setText(name);
		Random r = new Random();
		progressBar.setValue(r.nextInt(100));
	}

	private void initSwingComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		labelName = new JLabel();
		progressBar = new JProgressBar(0, 100);
		buttonStop = new JButton("Stop");
		
		labelName.setAlignmentX(LEFT_ALIGNMENT);
		labelName.setAlignmentY(Component.TOP_ALIGNMENT);
		
		progressBar.setMinimumSize(new Dimension(50, 500));
		progressBar.setAlignmentX(LEFT_ALIGNMENT);
		progressBar.setAlignmentY(Component.TOP_ALIGNMENT);
		
		buttonStop.setAlignmentX(LEFT_ALIGNMENT);
		buttonStop.setAlignmentY(TOP_ALIGNMENT);
		
		this.add(labelName);
		this.add(progressBar);
		this.add(buttonStop);
	}
	
	public void setProgressValue(int value) {
		progressBar.setValue(value);
	}
}
