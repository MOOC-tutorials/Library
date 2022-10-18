package biblio.view.menu.bookMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;


/**
 * Return Book Menu
 * 
 */
public class ReturnBookMenu extends BiblioMenuItem {

	public ReturnBookMenu(final Controller controller) {
		super(controller);
		
		setAction(new AbstractAction(){
			public void actionPerformed(ActionEvent e) {				
				controller.returnBook();
			}
		});
		
		setText("Return");
	}
}
