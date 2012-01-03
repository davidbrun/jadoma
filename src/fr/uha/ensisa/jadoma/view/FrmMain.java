package fr.uha.ensisa.jadoma.view;

import javax.swing.JFrame;

public class FrmMain extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6344324745845910099L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Launch the main frame of the application
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrmMain().setVisible(true);
			}
		});
	}
}