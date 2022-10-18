package biblio.view.menu.bookMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;


/**
 * Borrowing book menu
 * 
 */
public class BorrowBookMenu extends BiblioMenuItem {

	public BorrowBookMenu(final Controller controller) {
		super(controller);
		
		setAction(new AbstractAction(){
			public void actionPerformed(ActionEvent e) {				
				controller.borrowBook();
			}
		});
		
		setText("Borrow");
	}
}
