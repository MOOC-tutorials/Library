package biblio.view.menu.bookMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;


/**
 * Details Book Menu
 * 
 */
public class DetailsBookMenu extends BiblioMenuItem {

	public DetailsBookMenu(final Controller controller) {
		super(controller);
		
		setAction(new AbstractAction(){
			public void actionPerformed(ActionEvent e) {				
				controller.showBook();
			}
		});
		
		setText("Book details");
	}
}
