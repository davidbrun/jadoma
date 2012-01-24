package fr.uha.ensisa.jadoma.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import fr.uha.ensisa.jadoma.controller.ControllerLocator;
import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.util.ResourcesUtil;
import fr.uha.ensisa.jadoma.util.SizeUtil;

public class SimpleDownloadPanel extends JPanel {
	
	// SWING fields
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel rightSubPanel;
	private JPanel detailsPanel;
	private JPanel statusPanel;
	private JLabel labelName;
	private JLabel labelURL;
	private JLabel labelDestination;
	private JLabel labelState;
	private JLabel labelStatusSeparator1;
	private JLabel labelProgression;
	private JLabel labelStatusSeparator2;
	private JLabel labelSpeed;
	private JLabel labelEndDate;
	private JProgressBar progressBar;
	private JButton buttonStartPause;
	private JButton buttonStop;
	
	// Fields
	private boolean isExtended;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6443999510840611020L;
	
	private static final int FOLD_HEIGHT = 67;
	private static final int EXTENDED_HEIGHT = 103;
	
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
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// Instantiation
		this.leftPanel = new JPanel();
		this.leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		this.rightPanel = new JPanel();
		this.rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		this.rightSubPanel = new JPanel();
		this.rightSubPanel.setLayout(new BoxLayout(rightSubPanel, BoxLayout.X_AXIS));
		this.detailsPanel = new JPanel();
		this.detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		this.statusPanel = new JPanel();
		this.statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		
		labelName = new JLabel();
		labelURL = new JLabel();
		labelDestination = new JLabel();
		progressBar = new JProgressBar(0, 100);
		labelState = new JLabel();
		labelStatusSeparator1 = new JLabel();
		labelProgression = new JLabel();
		labelStatusSeparator2 = new JLabel();
		labelSpeed = new JLabel();
		labelEndDate = new JLabel();
//		buttonStartPause = new JButton("Start");
//		buttonStop = new JButton("Stop");
		buttonStartPause = new JButton(ResourcesUtil.START_BUTTON_IMAGE_ICON);
		buttonStop = new JButton(ResourcesUtil.STOP_BUTTON_IMAGE_ICON);
		
		// Place the components
		leftPanel.setAlignmentX(LEFT_ALIGNMENT);
		leftPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		rightPanel.setAlignmentX(RIGHT_ALIGNMENT);
		rightPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		rightPanel.setMinimumSize(new Dimension(40, 0));
		rightSubPanel.setAlignmentX(RIGHT_ALIGNMENT);
		rightSubPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		statusPanel.setAlignmentX(LEFT_ALIGNMENT);
		statusPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		labelName.setAlignmentX(LEFT_ALIGNMENT);
		labelName.setAlignmentY(Component.TOP_ALIGNMENT);
		
		detailsPanel.setAlignmentX(LEFT_ALIGNMENT);
		detailsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		labelURL.setAlignmentX(LEFT_ALIGNMENT);
		labelURL.setAlignmentY(Component.TOP_ALIGNMENT);
		labelDestination.setAlignmentX(LEFT_ALIGNMENT);
		labelDestination.setAlignmentY(Component.TOP_ALIGNMENT);
		
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setAlignmentX(LEFT_ALIGNMENT);
		progressBar.setAlignmentY(Component.TOP_ALIGNMENT);
		
		labelState.setAlignmentX(LEFT_ALIGNMENT);
		labelState.setAlignmentY(Component.TOP_ALIGNMENT);
		labelStatusSeparator1.setAlignmentX(LEFT_ALIGNMENT);
		labelStatusSeparator1.setAlignmentY(Component.TOP_ALIGNMENT);
		labelProgression.setAlignmentX(LEFT_ALIGNMENT);
		labelProgression.setAlignmentY(Component.TOP_ALIGNMENT);
		labelStatusSeparator2.setAlignmentX(LEFT_ALIGNMENT);
		labelStatusSeparator2.setAlignmentY(Component.TOP_ALIGNMENT);
		labelSpeed.setAlignmentX(LEFT_ALIGNMENT);
		labelSpeed.setAlignmentY(Component.TOP_ALIGNMENT);
		
		labelEndDate.setAlignmentX(LEFT_ALIGNMENT);
		labelEndDate.setAlignmentY(Component.TOP_ALIGNMENT);
		
		buttonStartPause.setAlignmentX(LEFT_ALIGNMENT);
		buttonStartPause.setAlignmentY(TOP_ALIGNMENT);
		buttonStop.setAlignmentX(LEFT_ALIGNMENT);
		buttonStop.setAlignmentY(TOP_ALIGNMENT);
		
		// Add the SWING components
		this.detailsPanel.add(labelURL);
		this.leftPanel.add(Box.createVerticalStrut(3));
		this.detailsPanel.add(labelDestination);
		
		this.statusPanel.add(labelState);
		this.statusPanel.add(labelStatusSeparator1);
		this.statusPanel.add(labelProgression);
		this.statusPanel.add(labelStatusSeparator2);
		this.statusPanel.add(labelSpeed);
		
		this.leftPanel.add(Box.createVerticalGlue());
		this.leftPanel.add(labelName);
		this.leftPanel.add(Box.createVerticalStrut(3));
		this.leftPanel.add(Box.createVerticalGlue());
		this.leftPanel.add(detailsPanel);
		this.leftPanel.add(Box.createVerticalGlue());
		this.leftPanel.add(Box.createVerticalStrut(4));
		this.leftPanel.add(progressBar);
		this.leftPanel.add(Box.createVerticalStrut(4));
		this.leftPanel.add(Box.createVerticalGlue());
		this.leftPanel.add(statusPanel);
		this.leftPanel.add(Box.createVerticalGlue());
		this.rightSubPanel.add(buttonStartPause);
		this.rightSubPanel.add(buttonStop);
		this.rightSubPanel.add(labelEndDate);
		this.rightPanel.add(Box.createVerticalGlue());
		this.rightPanel.add(rightSubPanel);
		this.rightPanel.add(Box.createVerticalGlue());
		this.add(Box.createHorizontalStrut(5));
		this.add(leftPanel);
		this.add(Box.createHorizontalStrut(5));
		this.add(rightPanel);
		
		// Initialize the state of the components
		this.labelEndDate.setVisible(false);
		this.isExtended = true;
		this.tooglePanelState();
		
		// Add the listeners
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tooglePanelState();
			}
		});
		
		this.buttonStartPause.addMouseListener(new MouseAdapter() {
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
		Download download = ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getDownload();
		labelName.setText(download.getName());
		progressBar.setValue((int) (download.getProgress() * 100));
		labelDestination.setText(download.getFileDestination());
		labelURL.setText(download.getUrlFrom());
		
		// TODO: add real values
		labelState.setText("En pause");
		labelStatusSeparator1.setText("  -  ");
		labelProgression.setText("X sur Y Mo");
		labelStatusSeparator2.setText("  ");
		labelSpeed.setText("(Z Ko/s)");
		labelEndDate.setText("date");
		
		buttonStartPause.setToolTipText("Démarrer");
		buttonStop.setToolTipText("Annuler le téléchargement");
	}
	
	public void setProgressValue(int value) {
		progressBar.setValue(value);
	}
	
	public void setSpeedLabel(float downloadSpeed) {
		String[] tmp = SizeUtil.getFormattedSize(downloadSpeed);
		labelSpeed.setText("(" + tmp[0] + " " + tmp[1] + "/s)");
	}
	
	public void updateProgressionLabel() {
		Download download = ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getDownload();
		String[] tmp = SizeUtil.getFormattedSize(download.getNbrOfCompletedBytes());
		String current = tmp[0] + " " + tmp[1];
		tmp = SizeUtil.getFormattedSize(download.getSize());
		String total = tmp[0] + " " + tmp[1];
		labelProgression.setText(current + " sur " + total);
	}
	
	public void tooglePanelState() {
		this.isExtended = !this.isExtended;
		
		this.detailsPanel.setVisible(isExtended);
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, (isExtended ? EXTENDED_HEIGHT : FOLD_HEIGHT)));
	}
}
