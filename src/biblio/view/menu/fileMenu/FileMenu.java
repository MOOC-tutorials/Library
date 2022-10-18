
package biblio.view.menu.fileMenu;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenu;



/**
 * Menu: File
 * just adds to the File menu all the menu items
 */
public class FileMenu extends BiblioMenu {
	
	public FileMenu(final Controller controller) {
		super(controller);
		add(new OpenFileMenuItem(controller));
		add(new PrintBooksMenuItem(controller));
		add(new PrintClientsMenuItem(controller));
		add(new ExitMenuItem(controller));
		setText("File");
	}

}

