package fr.uha.ensisa.jadoma.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import fr.uha.ensisa.jadoma.controller.ControllerLocator;
import fr.uha.ensisa.jadoma.model.Download;
import fr.uha.ensisa.jadoma.model.DownloadState;
import fr.uha.ensisa.jadoma.util.ResourcesUtil;
import fr.uha.ensisa.jadoma.util.SizeUtil;
import fr.uha.ensisa.jadoma.util.TimeUtil;

public class SimpleDownloadPanel extends JPanel {
	
	// Constants
	private static final long serialVersionUID = -6443999510840611020L;
	private static final int FOLD_HEIGHT = 73;
	private static final int EXTENDED_HEIGHT = 103;
	private static final int RIGHT_PANEL_WIDTH = 90;
	private static final int SPACE_BETWEEN_MAIN_PANELS = 10;
	private static final Color DOWNLOAD_ODD_BACKGROUND_COLOR = new Color(237, 241, 244);
	private static final Color DOWNLOAD_EVEN_BACKGROUND_COLOR = new Color(255, 255, 255);
	private static final Color DOWNLOAD_SELECTED_BACKGROUND_COLOR = new Color(132, 198, 244);
	
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
	private JPanel endDatePanel;
	private JLabel labelEndDateDay;
	private JLabel labelEndDateHour;
	private JProgressBar progressBar;
	private JButton buttonStartPause;
	private JButton buttonStop;
	
	// Fields
	private boolean isExtended;
	private boolean isSelected;
	
	public SimpleDownloadPanel(Download download) {
		super();

		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		
		// Initialization of the associated controller
		ControllerLocator.getInstance().createCtrlSimpleDownloadPanel(this, download);
		
		// Initialization of the values of the SWING components
		this.initComponentValues(download);
		
		// Initialization of the display
		this.toogleDownloadState();
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
		this.endDatePanel = new JPanel();
		this.endDatePanel.setLayout(new BoxLayout(endDatePanel, BoxLayout.Y_AXIS));
		
		labelName = new JLabel();
		labelURL = new JLabel();
		labelDestination = new JLabel();
		progressBar = new JProgressBar(0, 100);
		labelState = new JLabel();
		labelStatusSeparator1 = new JLabel();
		labelProgression = new JLabel();
		labelStatusSeparator2 = new JLabel();
		labelSpeed = new JLabel();
		labelEndDateDay = new JLabel();
		labelEndDateHour = new JLabel();
		buttonStartPause = new JButton(ResourcesUtil.START_BUTTON_IMAGE_ICON);
		buttonStop = new JButton(ResourcesUtil.STOP_BUTTON_IMAGE_ICON);
		
		// Place the components
		leftPanel.setAlignmentX(LEFT_ALIGNMENT);
		leftPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		rightPanel.setAlignmentX(RIGHT_ALIGNMENT);
		rightPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		rightSubPanel.setAlignmentX(CENTER_ALIGNMENT);
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
		
		labelEndDateDay.setAlignmentX(CENTER_ALIGNMENT);
		labelEndDateDay.setAlignmentY(CENTER_ALIGNMENT);
		labelEndDateHour.setAlignmentX(CENTER_ALIGNMENT);
		labelEndDateHour.setAlignmentY(Component.CENTER_ALIGNMENT);
		endDatePanel.setAlignmentX(LEFT_ALIGNMENT);
		endDatePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		buttonStartPause.setAlignmentX(LEFT_ALIGNMENT);
		buttonStartPause.setAlignmentY(TOP_ALIGNMENT);
		buttonStop.setAlignmentX(LEFT_ALIGNMENT);
		buttonStop.setAlignmentY(TOP_ALIGNMENT);
		
		// Change the display
		String defaultFontName = this.labelName.getFont().getName();
		int defaultFontStyle = this.labelName.getFont().getStyle();
		int defaultFontSize = this.labelName.getFont().getSize();
		
		this.labelURL.setFont(new Font(defaultFontName, defaultFontStyle, defaultFontSize - 2));
		this.labelDestination.setFont(new Font(defaultFontName, defaultFontStyle, defaultFontSize - 2));
		this.labelState.setFont(new Font(defaultFontName, defaultFontStyle, defaultFontSize - 2));
		this.labelSpeed.setFont(new Font(defaultFontName, defaultFontStyle, defaultFontSize - 2));
		this.labelStatusSeparator1.setFont(new Font(defaultFontName, defaultFontStyle, defaultFontSize - 2));
		this.labelStatusSeparator2.setFont(new Font(defaultFontName, defaultFontStyle, defaultFontSize - 2));
		this.labelProgression.setFont(new Font(defaultFontName, defaultFontStyle, defaultFontSize - 2));
		this.labelEndDateDay.setFont(new Font(defaultFontName, defaultFontStyle, defaultFontSize - 2));
		this.labelEndDateHour.setFont(new Font(defaultFontName, defaultFontStyle, defaultFontSize - 2));
		Color dark = Color.DARK_GRAY.darker();
		this.labelURL.setForeground(dark);
		this.labelDestination.setForeground(dark);
		this.labelState.setForeground(dark);
		this.labelSpeed.setForeground(dark);
		this.labelStatusSeparator1.setForeground(dark);
		this.labelStatusSeparator2.setForeground(dark);
		this.labelProgression.setForeground(dark);
		this.labelEndDateDay.setForeground(dark);
		this.labelEndDateHour.setForeground(dark);
		
		// Add the SWING components
		this.detailsPanel.add(labelURL);
		this.detailsPanel.add(Box.createVerticalStrut(1));
		this.detailsPanel.add(labelDestination);
		
		this.statusPanel.add(labelState);
		this.statusPanel.add(labelStatusSeparator1);
		this.statusPanel.add(labelProgression);
		this.statusPanel.add(labelStatusSeparator2);
		this.statusPanel.add(labelSpeed);
		
		this.leftPanel.add(Box.createVerticalStrut(8));
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
		this.leftPanel.add(Box.createVerticalStrut(2));
		this.rightSubPanel.add(Box.createHorizontalGlue());
		this.rightSubPanel.add(buttonStartPause);
		this.rightSubPanel.add(Box.createHorizontalGlue());
		this.rightSubPanel.add(Box.createHorizontalGlue());
		this.rightSubPanel.add(buttonStop);
		this.endDatePanel.add(labelEndDateDay);
		this.endDatePanel.add(labelEndDateHour);
		this.rightSubPanel.add(endDatePanel);
		this.rightPanel.add(Box.createVerticalGlue());
		this.rightPanel.add(rightSubPanel);
		this.rightPanel.add(Box.createVerticalGlue());
		this.add(Box.createHorizontalStrut(SPACE_BETWEEN_MAIN_PANELS));
		this.add(leftPanel);
		this.add(Box.createHorizontalStrut(SPACE_BETWEEN_MAIN_PANELS));
		this.add(rightPanel);
		this.add(Box.createHorizontalStrut(SPACE_BETWEEN_MAIN_PANELS));
		
		// Initialize the state of the components
		this.endDatePanel.setVisible(false);
		this.isExtended = true;
		this.tooglePanelState();
		this.isSelected = false;
		
		// Add the listeners
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
				{
					if (SimpleDownloadPanel.this.isSelected)
						tooglePanelState();
					
					ControllerLocator.getInstance().deselectAllDownloadPanel();
					
					SimpleDownloadPanel.this.isSelected = true;
					updateBackgroundColor();
				}
			}
		});
		
		this.buttonStartPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(SimpleDownloadPanel.this).handleButtonStartPauseClick();
			}
		});
		
		this.buttonStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(SimpleDownloadPanel.this).handleButtonCancelClick();
			}
		});
		
		// Add actions on particular labels
		this.labelName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.labelName.setToolTipText("Ouvrir le fichier");
		this.labelName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(SimpleDownloadPanel.this).handleLabelNameClick();
			}
		});
		this.labelDestination.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.labelDestination.setToolTipText("Ouvrir l'emplacement du fichier");
		this.labelDestination.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(SimpleDownloadPanel.this).handleLabelDestinationClick();
			}
		});
	}
	
	private void initComponentValues(Download download) {
		updateComponentValues(download);
		
		labelState.setText("En pause");
		labelStatusSeparator1.setText("  -  ");
		String[] tmp = SizeUtil.getFormattedSize(download.getNbrOfCompletedBytes());
		String[] tmp2 = SizeUtil.getFormattedSize(download.getSize());
		labelProgression.setText(tmp[0] + " " + tmp[1] + " sur " + tmp2[0] + " " + tmp2[1]);
		labelStatusSeparator2.setText("  ");
		DecimalFormat df = new DecimalFormat("0.0");
		labelSpeed.setText("-  " + df.format(download.getProgress() * 100) + " %" + "  " + "(0 o/s)");
		
		buttonStartPause.setToolTipText("Télécharger");
		buttonStop.setToolTipText("Annuler");
	}
	
	public void updateComponentValues(Download download) {
		labelName.setText(download.getName());
		progressBar.setValue((int) (download.getProgress() * 100));
		labelDestination.setText(download.getFileDestination());
		labelURL.setText(download.getUrlFrom());
	}
	
	public void setProgressValue(int value) {
		progressBar.setValue(value);
	}
	
	public void setSpeedLabel(float downloadSpeed) {
		String[] tmp = SizeUtil.getFormattedSize(downloadSpeed);
		DecimalFormat df = new DecimalFormat("0.0");
		labelSpeed.setText("-  " + df.format(ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getDownload().getProgress() * 100) +
				" %" + "  " + "(" + tmp[0] + " " + tmp[1] + "/s)");
	}
	
	public void updateStateLabel() {
		Download download = ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getDownload();
		labelState.setText("Temps restant : " + TimeUtil.getFormattedTime((long) ((download.getSize() - download.getNbrOfCompletedBytes()) / download.getLastKnownSpeed())));
	}
	
	public void updateProgressionLabel() {
		Download download = ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getDownload();
		String[] tmp = SizeUtil.getFormattedSize(download.getNbrOfCompletedBytes());
		String current = tmp[0] + " " + tmp[1];
		tmp = SizeUtil.getFormattedSize(download.getSize());
		String total = tmp[0] + " " + tmp[1];
		labelProgression.setText(current + " sur " + total);
	}
	
	public void setExtended(boolean isExtended) {
		this.isExtended = isExtended;
	}
	
	public void tooglePanelState() {
		updateBackgroundColor();
		this.isExtended = !this.isExtended;
		
		this.detailsPanel.setVisible(isExtended);
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, (isExtended ? EXTENDED_HEIGHT : FOLD_HEIGHT)));
		setComponentSize(rightPanel, new Dimension(RIGHT_PANEL_WIDTH, (isExtended ? EXTENDED_HEIGHT : FOLD_HEIGHT)));
		setComponentSize(leftPanel, new Dimension(
				ControllerLocator.getInstance().getCtrlFrmMain().getFrmMain().getScrollPanel().getWidth() - RIGHT_PANEL_WIDTH - SPACE_BETWEEN_MAIN_PANELS * 3,
				(isExtended ? EXTENDED_HEIGHT : FOLD_HEIGHT)));
	}
	
	public void toogleDownloadState() {
		Download download = ControllerLocator.getInstance().getCtrlSimpleDownloadPanel(this).getDownload();
		
		if (download.getCurrentState() == DownloadState.PAUSED)
		{
			buttonStartPause.setIcon(ResourcesUtil.START_BUTTON_IMAGE_ICON);
			labelState.setText("En pause");
			String[] tmp = SizeUtil.getFormattedSize(download.getNbrOfCompletedBytes());
			String[] tmp2 = SizeUtil.getFormattedSize(download.getSize());
			labelProgression.setText(tmp[0] + " " + tmp[1] + " sur " + tmp2[0] + " " + tmp2[1]);
			buttonStartPause.setToolTipText("Télécharger");
		}
		else if (download.getCurrentState() == DownloadState.DOWNLOADING)
		{
			buttonStartPause.setIcon(ResourcesUtil.PAUSE_BUTTON_IMAGE_ICON);
			buttonStartPause.setToolTipText("Suspendre");
			labelState.setText("Temps restant : ∞");
		}
		else if (download.getCurrentState() == DownloadState.COMPLETED)
		{
			String[] tmp = SizeUtil.getFormattedSize(download.getNbrOfCompletedBytes());
			labelState.setText(tmp[0] + " " + tmp[1]);
			labelProgression.setText(download.getHost());
			labelSpeed.setVisible(false);
			labelStatusSeparator2.setVisible(false);
			buttonStartPause.setVisible(false);
			buttonStop.setVisible(false);
			endDatePanel.setVisible(true);
			updateLabelEndDateText(download);
		}
		else if (download.getCurrentState() == DownloadState.CANCELED)
		{
			labelName.setForeground(Color.red);
			labelState.setText("Téléchargement annulé");
			labelProgression.setVisible(false);
			labelSpeed.setVisible(false);
			labelStatusSeparator1.setVisible(false);
			labelStatusSeparator2.setVisible(false);
			buttonStartPause.setVisible(false);
			buttonStop.setVisible(false);
			endDatePanel.setVisible(true);
			updateLabelEndDateText(download);
		}
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
		ControllerLocator.getInstance().getCtrlFrmMain().getFrmMain().getScrollPanel().requestFocus();
		
		updateBackgroundColor();
	}
	
	public void updateBackgroundColor() {
		if (this.isSelected)
		{
			if (this.getBackground() != DOWNLOAD_SELECTED_BACKGROUND_COLOR)
				this.setBackground(DOWNLOAD_SELECTED_BACKGROUND_COLOR);
		}
		else
			if (ControllerLocator.getInstance().getPositionOfSimpleDownloadPanel(this) % 2 != 0)
			{
				if (this.getBackground() != DOWNLOAD_EVEN_BACKGROUND_COLOR)
					this.setBackground(DOWNLOAD_EVEN_BACKGROUND_COLOR);
			}
			else
			{
				if (this.getBackground() != DOWNLOAD_ODD_BACKGROUND_COLOR)
					this.setBackground(DOWNLOAD_ODD_BACKGROUND_COLOR);
			}
	}

	private void updateLabelEndDateText(Download download) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		labelEndDateDay.setText(dateFormat.format(download.getEndDate()));
		dateFormat = new SimpleDateFormat("HH:mm");
		labelEndDateHour.setText(dateFormat.format(download.getEndDate()));
	}
	
	private void setComponentSize(Component component, Dimension size) {
		component.setMinimumSize(size);
		component.setMaximumSize(size);
		component.setPreferredSize(size);
		component.setSize(size);
	}
	
	@Override
	public void setBackground(Color background) {
		super.setBackground(background);
		
		for (Component c : this.getComponents())
			setBackground(c, background);
	}
	
	private void setBackground(Component component, Color background) {
		if (component instanceof JPanel)
			for (Component child : ((JPanel)component).getComponents())
				setBackground(child, background);
		
		component.setBackground(background);
	}
}
