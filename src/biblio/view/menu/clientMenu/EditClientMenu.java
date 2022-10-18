package biblio.view.menu.clientMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;


/**
 * Edit borrower Menu
 * 
 */
public class EditClientMenu extends BiblioMenuItem {

	public EditClientMenu(final Controller controller) {
		super(controller);
		
		setAction(new AbstractAction(){
			public void actionPerformed(ActionEvent e) {				
				controller.editClient();
			}
		});
		
		setText("Edit Client");
	}
}
