package fr.uha.ensisa.jadoma.view;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AppLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Try to set the system Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}

		// Start up the GUI
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FrmMain().setVisible(true);
			}
		});
	}
}