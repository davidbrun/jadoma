package fr.uha.ensisa.jadoma.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.SocketException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import fr.uha.ensisa.jadoma.controller.ControllerFrmMain;
import fr.uha.ensisa.jadoma.util.NicUtil;

public class FrmMain extends JFrame {

	// SWING fields
	private JTextPane labelDisplayDownloadManagerText;
	private JTextField textFieldDownloadURL;
	private JButton buttonAct;
	private JButton buttonDisplayNics;
	
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
		this.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// Create SWING components
		this.labelDisplayDownloadManagerText = new JTextPane();
		this.labelDisplayDownloadManagerText.setBackground(null);
		this.labelDisplayDownloadManagerText.setEditable(false);
		this.labelDisplayDownloadManagerText.setBorder(null);
		this.buttonAct = new JButton("Action");
		this.buttonDisplayNics = new JButton("Nics");
		this.textFieldDownloadURL = new JTextField("http://download.services.openoffice.org/files/localized/fr/3.3.0/OOo_3.3.0_MacOS_x86_install_fr.dmg");

		// Add SWING components
		this.textFieldDownloadURL.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.textFieldDownloadURL.setAlignmentY(Component.TOP_ALIGNMENT);
		this.buttonAct.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.buttonAct.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.buttonDisplayNics.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.buttonDisplayNics.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.labelDisplayDownloadManagerText.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.labelDisplayDownloadManagerText.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.add(this.textFieldDownloadURL);
		contentPane.add(this.buttonAct);
		contentPane.add(this.buttonDisplayNics);
		contentPane.add(this.labelDisplayDownloadManagerText);
		
		// Add listeners
		this.buttonAct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ctrlFrmMain.handleButtonActClick();
			}
		});
		
		this.buttonDisplayNics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JOptionPane.showMessageDialog(FrmMain.this, NicUtil.getNics().toString());
				} catch (SocketException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void setLabelDisplayDownloadManagerText(String textToDisplay) {
		this.labelDisplayDownloadManagerText.setText(textToDisplay);
	}
	
	public String getTextFieldDownloadURL() {
		return textFieldDownloadURL.getText();
	}
}