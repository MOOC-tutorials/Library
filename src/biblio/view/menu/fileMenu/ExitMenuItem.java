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
 * MenuItem: File / Exit
 */
public class ExitMenuItem extends BiblioMenuItem {
	
	public ExitMenuItem(final Controller controller) {
		super(controller);
		setAction(new AbstractAction(){
			
			/**
			 * Just quit the program
			 */
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
//		must go after the setAction that overrides the name and icon
		setText("Exit");
		setIcon(new ImageIcon("images/Quitter.gif"));
	}

}
