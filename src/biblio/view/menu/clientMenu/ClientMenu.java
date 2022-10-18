package biblio.view.menu.clientMenu;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenu;

/**
 * Main Borrower Menu
 * 
 */
public class ClientMenu extends BiblioMenu {

	public ClientMenu(final Controller controller) {
		super(controller);
		add(new AddClientMenu(controller));
		add(new EditClientMenu(controller));
		add(new RemoveClientMenu(controller));
		add(new ShowClientMenu(controller));
		
		setText("Client");
	}
}
