package biblio.view.menu;

import javax.swing.JMenu;

import biblio.controller.Controller;


/**
 * The menu of the system
 * 
 */
public abstract class BiblioMenu extends JMenu {
	
	private Controller controller;
	
	public BiblioMenu(final Controller controller) {
		setController(controller);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
