package fr.uha.ensisa.jadoma.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.SocketException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import fr.uha.ensisa.jadoma.controller.ControllerLocator;
import fr.uha.ensisa.jadoma.util.NicUtil;

public class FrmMain extends JFrame {

	// SWING fields
	private JTextField textFieldDownloadURL;
	private JButton buttonAct;
	private JButton buttonDisplayNics;
	private JScrollPane scrollDownloads;
	private JPanel scrollPanel;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6344324745845910099L;
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;

	public FrmMain() {
		super();
		
		//Displays the menu bar on the top of the screen (mac style)
        System.setProperty("apple.laf.useScreenMenuBar", "true");
		
		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		
		// Initialization of the associated controller
		ControllerLocator.getInstance().createCtrlFrmMain(this);
		
		// Center the window on the screen
        this.setLocationRelativeTo(null);
		
		// Ask the layout manager to do its work
		this.pack();
	}

	private void initSwingComponents() {
		Container contentPane = this.getContentPane();
		this.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// Create SWING components
		this.buttonAct = new JButton("Action");
		this.buttonDisplayNics = new JButton("Nics");
		this.textFieldDownloadURL = new JTextField("http://test-debit.free.fr/image.iso");
		this.scrollPanel = new JPanel();
		this.scrollPanel.setBackground(Color.white);
		this.scrollPanel.setLayout(new BoxLayout(this.scrollPanel, BoxLayout.Y_AXIS));
		this.scrollDownloads = new JScrollPane();
		this.scrollDownloads.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollDownloads.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollDownloads.setViewportView(this.scrollPanel);
		
		// Add SWING components
		this.textFieldDownloadURL.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.textFieldDownloadURL.setAlignmentY(Component.TOP_ALIGNMENT);
		this.textFieldDownloadURL.setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
		this.buttonAct.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.buttonAct.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.buttonDisplayNics.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.buttonDisplayNics.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.scrollDownloads.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.scrollDownloads.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.setPreferredSize(new Dimension(FrmMain.WINDOW_WIDTH, FrmMain.WINDOW_HEIGHT));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		contentPane.add(this.textFieldDownloadURL);
		contentPane.add(this.buttonAct);
		contentPane.add(this.buttonDisplayNics);
		contentPane.add(this.scrollDownloads);
		
		// Add listeners
		this.buttonAct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlFrmMain().handleButtonActClick();
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
		
		this.textFieldDownloadURL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					ControllerLocator.getInstance().getCtrlFrmMain().handleButtonActClick();
			}
		});
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				setPreferredSize(getSize());
			}
		});
		
		this.scrollPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP)
					ControllerLocator.getInstance().getCtrlFrmMain().handleKeyUpPressedOnScrollPanel();
				else if (e.getKeyCode() == KeyEvent.VK_DOWN)
					ControllerLocator.getInstance().getCtrlFrmMain().handleKeyDownPressedOnScrollPanel();
			}
		});
	}
	
	public String getTextFieldDownloadURL() {
		return textFieldDownloadURL.getText();
	}

	public void addDownloadPanel(SimpleDownloadPanel panel) {
		panel.setAlignmentX(CENTER_ALIGNMENT);
		panel.setAlignmentY(BOTTOM_ALIGNMENT);
		this.scrollPanel.add(panel);
	}

	public void clearURLField() {
		this.textFieldDownloadURL.setText("");
	}
	
	public JPanel getScrollPanel() {
		return scrollPanel;
	}

	public JScrollBar getVerticalScrollBar() {
		return this.scrollDownloads.getVerticalScrollBar();
	}
}