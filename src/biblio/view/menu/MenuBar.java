package biblio.view.menu;
import javax.swing.JMenuBar;

import biblio.controller.Controller;
import biblio.view.menu.bookMenu.BookMenu;
import biblio.view.menu.clientMenu.ClientMenu;
import biblio.view.menu.fileMenu.FileMenu;
import biblio.view.menu.searchMenu.SearchMenu;

/**
 * the main menu bar
 * just adds the required menus
 * 
 */

public class MenuBar extends JMenuBar {
	
	private FileMenu fileMenu;
	private BookMenu bookMenu;
	private SearchMenu searchMenu;
	private ClientMenu clientMenu;
	
	public MenuBar(final Controller controller) {
		fileMenu = new FileMenu(controller);
		bookMenu = new BookMenu(controller);
		searchMenu = new SearchMenu(controller);
		clientMenu = new ClientMenu(controller);
		add(fileMenu);
		booksMenu();
	}
	
	public void booksMenu() {
		remove(clientMenu);
		add(bookMenu);
		add(searchMenu);
		this.updateUI();
	}
	
	public void clientsMenu() {
		remove(bookMenu);
		remove(searchMenu);
		add(clientMenu);
		this.updateUI();
	}
	
	
}
