package biblio.view.menu.fileMenu;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import biblio.controller.Controller;
import biblio.view.menu.BiblioMenuItem;

	/**
	* MenuItem: File / Load a Catalog
	* Opens a file chooser and instantiates a new catalog corresponding to the data file
	* chosen by the user.
	*/
	public class OpenFileMenuItem extends BiblioMenuItem {
		
		public OpenFileMenuItem(final Controller controller) {
			super(controller);
			setAction(new AbstractAction(){
				
				/**
				 * Open a new library catalog
				 */
				public void actionPerformed(ActionEvent e) {
					controller.loadCatalog();
				}
				
			});

			setText("Load a Catalog");
			setIcon(new ImageIcon("images/Ouvrir.gif"));
		}

	}
	