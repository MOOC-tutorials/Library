package biblio.view.menu.fileMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;

/**
* Shows the catalog in the console
*/
public class PrintBooksMenuItem extends BiblioMenuItem {
	
	public PrintBooksMenuItem(final Controller controller) {
		super(controller);
		setAction(new AbstractAction(){
			
			/**
			 * Print all the books in the catalog
			 */
			public void actionPerformed(ActionEvent e) {
				controller.printCatalog();
			}
			
		});

		setText("Print catalog");
		setIcon(new ImageIcon("images/printer.gif"));
	}
}
