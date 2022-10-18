package biblio.view.menu;

import javax.swing.JMenuItem;

import biblio.controller.Controller;


/**
 * A single menu item
 * 
 */
public abstract class BiblioMenuItem extends JMenuItem {

	private Controller controller;
	
	public BiblioMenuItem(final Controller controller) {
		setController(controller);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
