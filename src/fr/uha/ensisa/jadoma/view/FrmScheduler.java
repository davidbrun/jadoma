package fr.uha.ensisa.jadoma.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import fr.uha.ensisa.jadoma.controller.ControllerLocator;

public class FrmScheduler extends JDialog {
	
	// Constants
	private static final long serialVersionUID = 4873179765938349395L;
	private static final int WINDOW_MINIMUM_WIDTH = 400;
	private static final int WINDOW_MINIMUM_HEIGHT = 200;
	
	// SWING components
	private JPanel panelContent;
	private JPanel panelButtons;
	private JLabel labelStartDate;
	private JTextField textFieldStartDate;
	private JLabel labelEndDate;
	private JTextField textFieldEndDate;
	private JButton buttonSave;
	private JButton buttonCancel;
	
	public FrmScheduler(FrmMain owner, boolean modality) {
		super(owner, modality);
		
		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		
		// Initialization of the associated controller
		ControllerLocator.getInstance().createCtrlFrmScheduler(this);
	    
		// Ask the layout manager to do its work
		this.pack();
		
		// Center the window in the parent
	    this.setLocationRelativeTo(owner);
		this.centerFrameInParent(owner);
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
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		this.panelButtons = new JPanel();
		this.panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		this.labelStartDate = new JLabel(" Début de la période sans téléchargement (format hh:mm:ss) :");
		this.textFieldStartDate = new JTextField(dateFormat.format(ControllerLocator.getInstance().getScheduler().getStartDate()));
		this.labelEndDate = new JLabel(" Fin de la période sans téléchargement (format hh:mm:ss) :");
		this.textFieldEndDate = new JTextField(dateFormat.format(ControllerLocator.getInstance().getScheduler().getEndDate()));
		this.buttonSave = new JButton("Sauvegarder");
		this.buttonCancel = new JButton("Annuler");
		Dimension dimension = new Dimension(140, 30);
		setComponentSize(this.buttonSave, dimension);
		setComponentSize(this.buttonCancel, dimension);
		
		// Add SWING components
		this.labelStartDate.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.labelStartDate.setAlignmentY(Component.TOP_ALIGNMENT);
		this.textFieldStartDate.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.textFieldStartDate.setAlignmentY(Component.TOP_ALIGNMENT);
		this.textFieldStartDate.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
		this.labelEndDate.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.labelEndDate.setAlignmentY(Component.TOP_ALIGNMENT);
		this.textFieldEndDate.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.textFieldEndDate.setAlignmentY(Component.TOP_ALIGNMENT);
		this.panelButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.panelButtons.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.buttonSave.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.buttonSave.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.buttonCancel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.buttonCancel.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		panelButtons.add(Box.createHorizontalGlue());
		panelButtons.add(this.buttonSave);
		panelButtons.add(this.buttonCancel);
		panelButtons.add(Box.createHorizontalGlue());
		panelContent.add(Box.createVerticalStrut(30));
		panelContent.add(this.labelStartDate);
		panelContent.add(this.textFieldStartDate);
		panelContent.add(Box.createVerticalStrut(30));
		panelContent.add(this.labelEndDate);
		panelContent.add(this.textFieldEndDate);
		panelContent.add(Box.createVerticalStrut(50));
		panelContent.add(panelButtons);
		panelContent.add(Box.createVerticalStrut(15));
		
		contentPane.add(Box.createHorizontalStrut(15));
		contentPane.add(panelContent);
		contentPane.add(Box.createHorizontalStrut(15));
		
		this.setTitle("Planification des téléchargements");
		this.setMinimumSize(new Dimension(WINDOW_MINIMUM_WIDTH, WINDOW_MINIMUM_HEIGHT));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Add listeners
		this.buttonSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlFrmScheduler().handleButtonSaveClick();
			}
		});
		
		this.buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlFrmScheduler().handleButtonCancelClick();
			}
		});
		
        this.panelContent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Exit");
        this.panelContent.getActionMap().put("Exit", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	ControllerLocator.getInstance().getCtrlFrmScheduler().handleButtonCancelClick();
            }
        });
        
        this.panelContent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Validate");
        this.panelContent.getActionMap().put("Validate", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            	ControllerLocator.getInstance().getCtrlFrmScheduler().handleButtonSaveClick();
            }
        });
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

	public String getEndDate() {
		return this.textFieldEndDate.getText();
	}

	public String getStartDate() {
		return this.textFieldStartDate.getText();
	}
}