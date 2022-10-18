package biblio.view.menu.fileMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;

public class PrintClientsMenuItem extends BiblioMenuItem {
	
	public PrintClientsMenuItem(final Controller controller) {
		super(controller);
		setAction(new AbstractAction(){
			
			/**
			 * Print all the books in the catalog
			 */
			public void actionPerformed(ActionEvent e) {
				controller.printClients();
			}
			
		});

		setText("Print clients");
		setIcon(new ImageIcon("images/printer.gif"));
	}
}
