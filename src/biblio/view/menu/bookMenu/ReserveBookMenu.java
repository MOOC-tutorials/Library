package biblio.view.menu.bookMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;


/**
 * Reserve Book Menu
 * 
 */
public class ReserveBookMenu extends BiblioMenuItem {

	public ReserveBookMenu(final Controller controller) {
		super(controller);
		
		setAction(new AbstractAction(){
			public void actionPerformed(ActionEvent e) {				
				controller.reserveBook();
			}
		});
		
		setText("Reserve");
	}
}
