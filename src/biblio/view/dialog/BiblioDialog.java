package biblio.view.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import biblio.controller.Controller;
import biblio.view.GUI;

/**
 * An abstract class for the classes representing dialogs
 * 
 */
public abstract class BiblioDialog extends JDialog {

	protected Controller controller;
	protected JPanel mainPanel;
	protected GUI gUI;
	protected JPanel buttonsPanel;
	
	public BiblioDialog(GUI gUI, String title, boolean modal) {
		super(gUI.getMainFrame(), title, modal);
		setGui(gUI);
		setController(gUI.getController());
	}
	
	public BiblioDialog(GUI gUI, String title, boolean modal, JPanel mainPanel, JPanel buttonsPanel) {
		this(gUI, title, modal);
		build(mainPanel, buttonsPanel);
	}

	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}	
	
	protected void build(JPanel mainPanel, JPanel buttonsPanel) {
		setMainPanel(mainPanel);
		setButtonsPanel(buttonsPanel);
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		pack();
	}




	public JPanel getButtonsPanel() {
		return buttonsPanel;
	}


	public void setButtonsPanel(JPanel buttonsPanel) {
		this.buttonsPanel = buttonsPanel;
	}
	
	public void onClose() {
		close();
	}
	
	public void close() {
		setVisible(false);
		dispose();
	}

	/**
	 * @param gUI the gui to set
	 */
	public void setGui(GUI gUI) {
		this.gUI = gUI;
	}
	

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
}
