package biblio.view.menu.clientMenu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;

public class ShowClientMenu extends BiblioMenuItem {

	public ShowClientMenu(final Controller controller) {
		super(controller);
		
		setAction(new AbstractAction(){
			public void actionPerformed(ActionEvent e) {				
				controller.showClient();
			}
		});
		
		setText("Show Client details");
	}
	
}
