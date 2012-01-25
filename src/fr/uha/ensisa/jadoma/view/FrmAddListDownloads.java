package fr.uha.ensisa.jadoma.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import fr.uha.ensisa.jadoma.controller.ControllerLocator;

public class FrmAddListDownloads extends FrmAddDownload {
	
	// Constants
	private static final long serialVersionUID = -5542945451662637593L;
	
	public FrmAddListDownloads(JFrame owner, boolean modality) {
		super(owner, modality);
		
		initComponents();
		
		super.centerFrameInParent(owner);
	}
	
	private void initComponents() {
		int width = super.textFieldDownloadURL.getWidth();
		
		super.panelContent.remove(super.textFieldDownloadURL);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.textFieldDownloadURL = new JTextPane();
		scrollPane.setViewportView(textFieldDownloadURL);
		setComponentSize(this.textFieldDownloadURL, new Dimension(width, 100));
		setComponentSize(scrollPane, new Dimension(width, 100));
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		
		super.panelContent.add(scrollPane, 2);
		
		super.setTitle("Téléchargement par lot");
		super.labelTextFieldUrl.setText("URL des téléchargements :");
		super.checkBoxStartAuto.setText("Démarrer les téléchargements automatiquement");
		
		// Modify the listener
		this.buttonDownload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ControllerLocator.getInstance().getCtrlFrmAddDownload().handleButtonDownloadAllClick();
			}
		});
		
		super.setSize(new Dimension(super.getWidth(), super.getHeight() + 70));
	}
}