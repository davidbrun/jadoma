package fr.uha.ensisa.jadoma.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import fr.uha.ensisa.jadoma.controller.ControllerLocator;
import fr.uha.ensisa.jadoma.util.OSUtil;
import fr.uha.ensisa.jadoma.util.ResourcesUtil;

public class FrmMain extends JFrame {
	
	// Constants
	private static final long serialVersionUID = 6344324745845910099L;
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final int SPACE_BETWEEN_TOOLBAR_BUTTONS = 3;
	private static final Color BOTTOM_ROW_BACKGROUND_COLOR = new Color(176, 176, 176);
	
	// SWING fields
	private JToolBar toolBar;
	private JButton buttonAddDownload;
	private JButton buttonStartDownload;
	private JButton buttonPauseDownload;
	private JButton buttonCancelDownload;
	private JButton buttonAddDownloads;
	private JButton buttonStartDownloads;
	private JButton buttonPauseDownloads;
	private JButton buttonCancelDownloads;
	private JButton buttonPreferences;
	private JButton buttonScheduler;
	private JScrollPane scrollDownloads;
	private JPanel scrollPanel;
	private JPanel bottomPanel;
	private JButton buttonClearOldDownloads;
	private JPanel hSeparator1;
	private JPanel hSeparator2;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuOptions;
	private JMenu menuHelp;
	private JMenuItem menuFile_AddDownload;
	private JMenuItem menuFile_AddDownloads;
	private JMenuItem menuFile_Quit;
	private JMenuItem menuOptions_Scheduler;
	private JMenuItem menuOptions_Params;
	private JMenuItem menuHelp_About;

	public FrmMain() {
		super();
		
		//Displays the menu bar on the top of the screen (mac style)
        System.setProperty("apple.laf.useScreenMenuBar", "true");
		
		// Initialization of all the SWING components of the frame
		this.initSwingComponents();
		
		// Initialization of the associated controller
		ControllerLocator.getInstance().createCtrlFrmMain(this);
		
		// Ask the layout manager to do its work
		this.pack();
		
		// Center the window on the screen
        this.setLocationRelativeTo(null);
	}

	private void initSwingComponents() {
		Container contentPane = this.getContentPane();
		this.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// Create SWING components
		this.scrollPanel = new JPanel();
		this.scrollPanel.setBackground(Color.white);
		this.scrollPanel.setLayout(new BoxLayout(this.scrollPanel, BoxLayout.Y_AXIS));
		this.scrollDownloads = new JScrollPane();
		this.scrollDownloads.setBorder(null);
		this.scrollDownloads.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollDownloads.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.scrollDownloads.setViewportView(this.scrollPanel);
		
		// The toolbar
		this.toolBar = new JToolBar("Java Download Manager");
		this.toolBar.setFloatable(false);
		this.toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.toolBar.setAlignmentY(Component.TOP_ALIGNMENT);
		setComponentSize(this.toolBar, new Dimension(Integer.MAX_VALUE, 50));
		this.buttonAddDownload = new JButton(ResourcesUtil.TOOLBAR_ADD_BUTTON_IMAGE_ICON);
		this.buttonAddDownloads = new JButton(ResourcesUtil.TOOLBAR_ADD_LIST_BUTTON_IMAGE_ICON);
		this.buttonStartDownload = new JButton(ResourcesUtil.TOOLBAR_START_BUTTON_IMAGE_ICON);
		this.buttonStartDownloads = new JButton(ResourcesUtil.TOOLBAR_START_ALL_BUTTON_IMAGE_ICON);
		this.buttonPauseDownload = new JButton(ResourcesUtil.TOOLBAR_PAUSE_BUTTON_IMAGE_ICON);
		this.buttonPauseDownloads = new JButton(ResourcesUtil.TOOLBAR_PAUSE_ALL_BUTTON_IMAGE_ICON);
		this.buttonCancelDownload = new JButton(ResourcesUtil.TOOLBAR_CANCEL_BUTTON_IMAGE_ICON);
		this.buttonCancelDownloads = new JButton(ResourcesUtil.TOOLBAR_CANCEL_ALL_BUTTON_IMAGE_ICON);
		this.buttonPreferences = new JButton(ResourcesUtil.TOOLBAR_PREFERENCES_BUTTON_IMAGE_ICON);
		this.buttonScheduler = new JButton(ResourcesUtil.TOOLBAR_SCHEDULER_BUTTON_IMAGE_ICON);
		// Add the items
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonAddDownload);
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonStartDownload);
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonPauseDownload);
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonCancelDownload);
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.addSeparator();
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonAddDownloads);
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonStartDownloads);
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonPauseDownloads);
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonCancelDownloads);
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.addSeparator();
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonScheduler);
		this.toolBar.add(Box.createHorizontalStrut(SPACE_BETWEEN_TOOLBAR_BUTTONS));
		this.toolBar.add(buttonPreferences);
		
		// Add tooltips
		this.buttonAddDownload.setToolTipText("Ajouter un téléchargement");
		this.buttonAddDownloads.setToolTipText("Ajouter une liste de téléchargements");
		this.buttonStartDownload.setToolTipText("Démarrer le téléchargement");
		this.buttonStartDownloads.setToolTipText("Démarrer tous les téléchargements");
		this.buttonPauseDownload.setToolTipText("Suspendre le téléchargement");
		this.buttonPauseDownloads.setToolTipText("Suspendre tous les téléchargements");
		this.buttonCancelDownload.setToolTipText("Annuler le téléchargement");
		this.buttonCancelDownloads.setToolTipText("Annuler tous les téléchargements");
		this.buttonPreferences.setToolTipText("Paramètres");
		this.buttonScheduler.setToolTipText("Planifier les téléchargements");
		
		// Add listeners
		this.buttonAddDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlFrmMain().handleButtonAddDownloadClick();
			}
		});
		
		// The menu
		this.menuBar = new JMenuBar();
		this.menuFile = new JMenu("Fichier");
		this.menuOptions = new JMenu("Options");
		this.menuHelp = new JMenu("Aide");
		this.menuFile_AddDownload = new JMenuItem("Télécharger un nouveau fichier");
		this.menuFile_AddDownloads = new JMenuItem("Télécharger par lot");
		this.menuFile_Quit = new JMenuItem("Quitter");
		this.menuOptions_Scheduler = new JMenuItem("Planifier les téléchargements");
		this.menuOptions_Params = new JMenuItem("Paramètres");
		this.menuHelp_About = new JMenuItem("À propos de Jadoma");
		// Add
		this.menuBar.add(menuFile);
		this.menuBar.add(menuOptions);
		this.menuBar.add(menuHelp);
		this.menuFile.add(menuFile_AddDownload);
		this.menuFile.add(menuFile_AddDownloads);
		if (!OSUtil.IS_MAC)
		{
			this.menuFile.addSeparator();
			this.menuFile.add(menuFile_Quit);
		}
		this.menuOptions.add(menuOptions_Scheduler);
		this.menuOptions.addSeparator();
		this.menuOptions.add(menuOptions_Params);
		this.menuHelp.add(menuHelp_About);
		// Shortcuts
		int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		this.menuFile_Quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, mask));
		this.menuFile_AddDownload.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, mask));
		// Listeners
		this.menuFile_Quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.menuFile_AddDownload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ControllerLocator.getInstance().getCtrlFrmMain().handleButtonAddDownloadClick();
			}
		});
		
		this.setJMenuBar(this.menuBar);
		
		// The bottom row
		this.bottomPanel = new JPanel();
		this.bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		this.bottomPanel.setBackground(BOTTOM_ROW_BACKGROUND_COLOR);
		this.bottomPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.bottomPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		setComponentSize(bottomPanel, new Dimension(Integer.MAX_VALUE, 40));
		this.buttonClearOldDownloads = new JButton("Effacer l'historique");
		this.buttonClearOldDownloads.setToolTipText("Retirer les téléchargements terminés, annulés et ayant échoué de la liste");
		this.buttonClearOldDownloads.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.buttonClearOldDownloads.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.bottomPanel.add(buttonClearOldDownloads);
		
		// The separators
		this.hSeparator1 = new JPanel();
		this.hSeparator1.setBackground(Color.DARK_GRAY);
		setComponentSize(hSeparator1, new Dimension(Integer.MAX_VALUE, 1));
		this.hSeparator1.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.hSeparator1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.hSeparator2 = new JPanel();
		this.hSeparator2.setBackground(Color.DARK_GRAY);
		setComponentSize(hSeparator2, new Dimension(Integer.MAX_VALUE, 1));
		this.hSeparator2.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.hSeparator2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		// Add SWING components
		this.scrollDownloads.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.scrollDownloads.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.setPreferredSize(new Dimension(FrmMain.WINDOW_WIDTH, FrmMain.WINDOW_HEIGHT));
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Java Download Manager");
		
		contentPane.add(this.toolBar);
		contentPane.add(this.hSeparator1);
		contentPane.add(this.scrollDownloads);
		contentPane.add(this.hSeparator2);
		contentPane.add(this.bottomPanel);
		
		// Add listeners
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
	
	public void addDownloadPanel(SimpleDownloadPanel panel) {
		panel.setAlignmentX(CENTER_ALIGNMENT);
		panel.setAlignmentY(BOTTOM_ALIGNMENT);
		this.scrollPanel.add(panel);
	}
	
	public JPanel getScrollPanel() {
		return scrollPanel;
	}

	public JScrollBar getVerticalScrollBar() {
		return this.scrollDownloads.getVerticalScrollBar();
	}
	
	private void setComponentSize(Component component, Dimension size) {
		component.setMinimumSize(size);
		component.setMaximumSize(size);
		component.setPreferredSize(size);
		component.setSize(size);
	}
}