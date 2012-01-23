package fr.uha.ensisa.jadoma.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import fr.uha.ensisa.jadoma.controller.ControllerLocator;
import fr.uha.ensisa.jadoma.model.Download;

public class SimpleDownloadPanel extends JPanel {
	
	// SWING fields
	private JLabel labelName;
	private JProgressBar progressBar;
	private JButton buttonStart;
	private JButton buttonStop;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6443999510840611020L;
	
	public SimpleDownloadPanel(Download download) {
		super();

		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		
		// Initialization of the associated controller
		ControllerLocator.getInstance().createCtrlSimpleDownloadPanel(this, download);
		
		// Initialization of the values of the SWING components
		this.initComponentValues();
	}

	private void initSwingComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		labelName = new JLabel();
		progressBar = new JProgressBar(0, 100);
		buttonStart = new JButton("Start");
		buttonStop = new JButton("Stop");
		
		labelName.setAlignmentX(LEFT_ALIGNMENT);
		labelName.setAlignmentY(Component.TOP_ALIGNMENT);
		
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setMinimumSize(new Dimension(50, 500));
		progressBar.setAlignmentX(LEFT_ALIGNMENT);
		progressBar.setAlignmentY(Component.TOP_ALIGNMENT);
		
		buttonStart.setAlignmentX(LEFT_ALIGNMENT);
		buttonStart.setAlignmentY(TOP_ALIGNMENT);
		buttonStop.setAlignmentX(LEFT_ALIGNMENT);
		buttonStop.setAlignmentY(TOP_ALIGNMENT);
		
		// Add the SWING components
		this.add(labelName);
		this.add(progressBar);
		this.add(buttonStart);
		this.add(buttonStop);
		
		// Add the listeners
		this.buttonStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(SimpleDownloadPanel.this).handleButtonStartClick();
			}
		});
		
		this.buttonStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(SimpleDownloadPanel.this).handleButtonStopClick();
			}
		});
	}
	
	private void initComponentValues() {
		labelName.setText(ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getDownload().getName());
		progressBar.setValue((int) (ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getDownload().getProgress() * 100));
	}
	
	public void setProgressValue(int value) {
		progressBar.setValue(value);
	}
}
