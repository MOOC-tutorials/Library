package biblio.view.menu.clientMenu;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;


/**
 * Add borrower Menu
 * 
 */
public class AddClientMenu extends BiblioMenuItem {
	
	public AddClientMenu(final Controller controller) {
		super(controller);
		setAction(new AbstractAction(){
			public void actionPerformed(ActionEvent e) {				
				controller.addClient();
			}
		});
		setText("Add Client");
	}
	
}
