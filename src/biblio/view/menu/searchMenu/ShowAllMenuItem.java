package biblio.view.menu.searchMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;

/**
* Show all the available books
*/
public class ShowAllMenuItem extends BiblioMenuItem {
	
	public ShowAllMenuItem(final Controller controller) {
		super(controller);
		
		setAction(new AbstractAction(){
			
			/**
			 * Refresh the table with the books
			 */
			public void actionPerformed(ActionEvent e) {
				controller.updateBookList();
			}
			
		});

		setText("Show all books");
	}
}
