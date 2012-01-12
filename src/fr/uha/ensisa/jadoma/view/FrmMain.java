package fr.uha.ensisa.jadoma.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.net.SocketException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import fr.uha.ensisa.jadoma.controller.ControllerFrmMain;
import fr.uha.ensisa.jadoma.util.NicUtil;

public class FrmMain extends JFrame {

	// SWING fields
	private JTextField textFieldDownloadURL;
	private JButton buttonAct;
	private JButton buttonDisplayNics;
	private JScrollPane scrollDownloads;
	private JPanel scrollPanel;
	
	// Model fields
	
	
	// Controller fields
	private ControllerFrmMain ctrlFrmMain;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6344324745845910099L;
	private int last_width = 500;
	private int last_height = 200;

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
		this.buttonAct = new JButton("Action");
		this.buttonDisplayNics = new JButton("Nics");
		this.textFieldDownloadURL = new JTextField("http://test-debit.free.fr/image.iso");
		this.scrollPanel = new JPanel();
		this.scrollPanel.setBackground(Color.black);
		this.scrollPanel.setLayout(new BoxLayout(this.scrollPanel, BoxLayout.Y_AXIS));
		this.scrollDownloads = new JScrollPane();
		this.scrollDownloads.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollDownloads.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollDownloads.setViewportBorder(new LineBorder(Color.red));
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
		
		this.setPreferredSize(new Dimension(this.last_width, this.last_height));
		
		contentPane.add(this.textFieldDownloadURL);
		contentPane.add(this.buttonAct);
		contentPane.add(this.buttonDisplayNics);
		contentPane.add(this.scrollDownloads);
		
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
		
		this.textFieldDownloadURL.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {	
					ctrlFrmMain.handleButtonActClick();
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {}
		});
		
		this.addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent arg0) {}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				setPreferredSize(getSize());
			}
			
			@Override
			public void componentMoved(ComponentEvent arg0) {}
			@Override
			public void componentHidden(ComponentEvent arg0) {}
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
}