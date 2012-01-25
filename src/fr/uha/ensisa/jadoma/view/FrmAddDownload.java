package fr.uha.ensisa.jadoma.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import fr.uha.ensisa.jadoma.controller.ControllerLocator;

public class FrmAddDownload extends JDialog {

	// Constants
	private static final long serialVersionUID = 5065872976301103539L;
	private static final int WINDOW_MINIMUM_WIDTH = 400;
	private static final int WINDOW_MINIMUM_HEIGHT = 200;
	
	// SWING components
	private JPanel panelContent;
	private JPanel panelButtons;
	private JLabel labelTextFieldUrl;
	private JTextField textFieldDownloadURL;
	private JLabel labelTextFieldDestination;
	private JPanel panelDestinationFile;
	private JButton buttonBrowse;
	private JTextField textFieldDestination;
	private JCheckBox checkBoxStartAuto;
	private JButton buttonDownload;
	private JButton buttonCancel;
	
	public FrmAddDownload(JFrame owner, boolean modality) {
		super(owner, modality);
		
		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		
		// Initialization of the associated controller
		ControllerLocator.getInstance().createCtrlFrmAddDownload(this);
		
		// Center the window in the parent
	    this.setLocationRelativeTo(owner);
		this.centerFrameInParent(owner);
	    
		// Ask the layout manager to do its work
		this.pack();
	}

	@SuppressWarnings("serial")
	private void initSwingComponents() {
		Container contentPane = this.getContentPane();
		this.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		this.panelContent = new JPanel();
		panelContent.setLayout(new BoxLayout(panelContent, BoxLayout.Y_AXIS));
		panelContent.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelContent.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		// Create SWING components
		this.panelButtons = new JPanel();
		this.panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		this.labelTextFieldUrl = new JLabel(" URL du téléchargement :");
		this.textFieldDownloadURL = new JTextField("http://test-debit.free.fr/image.iso");
		this.labelTextFieldDestination = new JLabel(" Enregistrer dans le dossier :");
		this.panelDestinationFile = new JPanel();
		this.panelDestinationFile.setLayout(new BoxLayout(panelDestinationFile, BoxLayout.X_AXIS));
		this.textFieldDestination = new JTextField("");
		this.buttonBrowse = new JButton("...");
		this.checkBoxStartAuto = new JCheckBox("Démarrer le téléchargement automatiquement");
		//TODO: initialize with the stored parameter
		this.buttonDownload = new JButton("Télécharger");
		this.buttonCancel = new JButton("Annuler");
		Dimension dimension = new Dimension(140, 30);
		setComponentSize(this.buttonDownload, dimension);
		setComponentSize(this.buttonCancel, dimension);
		
		// Add SWING components
		this.labelTextFieldUrl.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.labelTextFieldUrl.setAlignmentY(Component.TOP_ALIGNMENT);
		this.textFieldDownloadURL.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.textFieldDownloadURL.setAlignmentY(Component.TOP_ALIGNMENT);
		this.textFieldDownloadURL.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
		this.labelTextFieldDestination.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.labelTextFieldDestination.setAlignmentY(Component.TOP_ALIGNMENT);
		this.textFieldDestination.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.textFieldDestination.setAlignmentY(Component.TOP_ALIGNMENT);
		this.buttonBrowse.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.buttonBrowse.setAlignmentY(Component.TOP_ALIGNMENT);
		this.panelDestinationFile.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panelDestinationFile.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.checkBoxStartAuto.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.checkBoxStartAuto.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.panelButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panelButtons.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.buttonDownload.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.buttonDownload.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.buttonCancel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.buttonCancel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		panelButtons.add(Box.createHorizontalGlue());
		panelButtons.add(this.buttonDownload);
		panelButtons.add(this.buttonCancel);
		panelButtons.add(Box.createHorizontalGlue());
		panelDestinationFile.add(this.textFieldDestination);
		panelDestinationFile.add(buttonBrowse);
		panelContent.add(Box.createVerticalStrut(15));
		panelContent.add(this.labelTextFieldUrl);
		panelContent.add(this.textFieldDownloadURL);
		panelContent.add(Box.createVerticalStrut(30));
		panelContent.add(this.labelTextFieldDestination);
		panelContent.add(this.panelDestinationFile);
		panelContent.add(Box.createVerticalStrut(30));
		panelContent.add(this.checkBoxStartAuto);
		panelContent.add(Box.createVerticalStrut(50));
		panelContent.add(panelButtons);
		panelContent.add(Box.createVerticalStrut(15));
		
		contentPane.add(Box.createHorizontalStrut(15));
		contentPane.add(panelContent);
		contentPane.add(Box.createHorizontalStrut(15));
		
		this.setTitle("Ajouter un téléchargement");
		this.setMinimumSize(new Dimension(WINDOW_MINIMUM_WIDTH, WINDOW_MINIMUM_HEIGHT));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
        this.panelContent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Exit");
        this.panelContent.getActionMap().put("Exit", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	ControllerLocator.getInstance().getCtrlFrmAddDownload().handleButtonCancelClick();
            }
        });
        
        this.panelContent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Validate");
        this.panelContent.getActionMap().put("Validate", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
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
	
	private void setComponentSize(Component component, Dimension size) {
		component.setMinimumSize(size);
		component.setMaximumSize(size);
		component.setPreferredSize(size);
		component.setSize(size);
	}
}