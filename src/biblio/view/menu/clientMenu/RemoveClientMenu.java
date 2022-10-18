package biblio.view.menu.clientMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;


/**
 * Remove borrower Menu
 * 
 */
public class RemoveClientMenu extends BiblioMenuItem {

	public RemoveClientMenu(final Controller controller) {
		super(controller);
		
		setAction(new AbstractAction(){
			public void actionPerformed(ActionEvent e) {				
				controller.deleteClient();
			}
		});
		
		setText("Remove Client");
	}
}
