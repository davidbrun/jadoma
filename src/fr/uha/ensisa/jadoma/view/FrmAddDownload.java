package fr.uha.ensisa.jadoma.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import fr.uha.ensisa.jadoma.controller.ControllerLocator;

public class FrmAddDownload extends JFrame {

	// Constants
	private static final long serialVersionUID = 5065872976301103539L;
	
	// SWING components
	private JPanel panelButtons;
	private JLabel labelTextFieldUrl;
	private JTextField textFieldDownloadURL;
	private JButton buttonDownload;
	private JButton buttonCancel;
	
	public FrmAddDownload() {
		super();
		
		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		
		// Initialization of the associated controller
		ControllerLocator.getInstance().createCtrlFrmAddDownload(this);
		
		// Center the window in the parent
	    this.setLocationRelativeTo(ControllerLocator.getInstance().getCtrlFrmMain().getFrmMain());
		this.centerFrameInParent(ControllerLocator.getInstance().getCtrlFrmMain().getFrmMain());
	    
		// Ask the layout manager to do its work
		this.pack();
	}

	private void initSwingComponents() {
		Container contentPane = this.getContentPane();
		this.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Create SWING components
		this.panelButtons = new JPanel();
		this.panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		this.labelTextFieldUrl = new JLabel("Veuillez entrer l'url du téléchargement");
		this.textFieldDownloadURL = new JTextField("http://test-debit.free.fr/image.iso");
		this.buttonDownload = new JButton("Télécharger");
		this.buttonCancel = new JButton("Annuler");
		
		// Add SWING components
		this.labelTextFieldUrl.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.labelTextFieldUrl.setAlignmentY(Component.TOP_ALIGNMENT);
		this.textFieldDownloadURL.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.textFieldDownloadURL.setAlignmentY(Component.TOP_ALIGNMENT);
		this.textFieldDownloadURL.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
		this.panelButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.panelButtons.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.buttonDownload.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.buttonDownload.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.buttonCancel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.buttonCancel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		panelButtons.add(this.buttonDownload);
		panelButtons.add(this.buttonCancel);
		contentPane.add(labelTextFieldUrl);
		contentPane.add(this.textFieldDownloadURL);
		contentPane.add(panelButtons);
		
		// Add listeners
		this.buttonDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlFrmAddDownload().handleButtonDownloadClick();
			}
		});
		
		this.buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlFrmAddDownload().handleButtonCancelClick();
			}
		});
		
		this.textFieldDownloadURL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					ControllerLocator.getInstance().getCtrlFrmAddDownload().handleButtonDownloadClick();
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					ControllerLocator.getInstance().getCtrlFrmAddDownload().handleButtonCancelClick();
				else if (e.getKeyCode() == KeyEvent.VK_ENTER)
					ControllerLocator.getInstance().getCtrlFrmAddDownload().handleButtonDownloadClick();
			}
		});
	}
	
	public void clearURLField() {
		this.textFieldDownloadURL.setText("");
	}
	
	public String getTextFieldDownloadURL() {
		return textFieldDownloadURL.getText();
	}
	
	private void centerFrameInParent(JFrame parent)
    {
        // Works also if the about frame is larger than the main frame
        int x = parent.getWidth() / 2 - this.getWidth() / 2 + parent.getX();
        int y = parent.getHeight() / 2 - this.getHeight() / 2 + parent.getY();

        this.setLocation(x, y);
    }
}