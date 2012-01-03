package fr.uha.ensisa.jadoma.view;

public class AppLauncher {
	
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