package biblio.view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import biblio.view.dataTable.BooksDataTableImpl;
import biblio.view.dataTable.ClientDataTableImpl;
import biblio.view.menu.MenuBar;

/**
 * Cette classe a l'implementation à construire l'interfaçe d'administration
 * 
 */
public class AdministratorGuiBuilder {
	private AdministratorGUI gui;
	private JFrame mainFrame;
	private BooksDataTableImpl booksDataTable;
	private ClientDataTableImpl clientsDataTable;
	
	/**
	 * Modifie l'interfaçe d'administriation
	 * @param administratorInterface the administratorInterface to set
	 */
	public void setAdministratorInterface(
			AdministratorGUI administratorInterface) {
		this.gui = administratorInterface;
	}

	/**
	 * Crée l'interfaçe utilisateur
	 * @param administratorInterface
	 */
	public void build(AdministratorGUI administratorInterface) {
		setAdministratorInterface(administratorInterface);
		createInterfaceObjects();
		buildAdministratorInterface();
	}

	/**
	 * Crée l'interfaçe d'administration
	 */
	private void buildAdministratorInterface() {
		gui.setMainFrame(mainFrame);
		gui.setBooksDataTable(booksDataTable);
		gui.setClientsDataTable(clientsDataTable);
	}

	/**
	 * Crée les objets de l'interfaçe utilisateur comme les menus, panels, boutons ou tables
	 */
	private void createInterfaceObjects() {
		// Crée le frame principale
		mainFrame = new JFrame("BiblioSystem");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Crée les composants de l'application
		final MenuBar menuBar = new MenuBar(gui.getController());
		mainFrame.setJMenuBar(menuBar);
		
		final JTabbedPane tabbedPane = new JTabbedPane();
		
		booksDataTable = new BooksDataTableImpl(gui);
		booksDataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JComponent panelBooks = new JScrollPane(booksDataTable);
		tabbedPane.addTab("Books", null, panelBooks,
		                  "Management of Books");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		clientsDataTable = new ClientDataTableImpl(gui);
		clientsDataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JComponent panelClients = new JScrollPane(clientsDataTable);
		tabbedPane.addTab("Clients", null, panelClients,
		                  "Management of Clients");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);		
		
		tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent e) {
				switch ( tabbedPane.getSelectedIndex() ) {
				case 0:
					menuBar.booksMenu();
					break;
				case 1:
					menuBar.clientsMenu();
					break;
				}
			}
		});
		mainFrame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		mainFrame.pack();
	}
}