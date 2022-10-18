package biblio.view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


import biblio.controller.Controller;
import biblio.util.SwingUtility;

/**
 * Classe abstraite pour toutes les classes représentant un interface graphique swing.
 * La vraie interface graphique heerite de cette classe GUI (acronyme pour Graphical User Interface).
 * Cette classe abstrait peut servir dans le futur pour implémenter d'autres interfaces utilisateurs. 
 * Par exemple une interface restreinte dédiée aux emprunteurs. 
 * 
 */
public abstract class GUI {

	private Controller controller;
	private JFrame mainFrame;
	
	
	/** 
	 * Constructeur de la classe.
	 * @param Controller
	 * @SINF1151 Phase 1 - à implementer
	 */
	public GUI(Controller controller) {
		// Chaque interfaçe utilisateur a besoin d'un contrôleur qui gére les événements
		// dans cette interfaçe. Le contrôleur a besoin d'être passe à l'interface lors de son creation
		setController(controller);
	}
	
	/**
	 * Affiche l'interface utilisateur 
	 */
	public void launch() {
		mainFrame.setVisible(true);
	}

	/**
	 * Retourne le côntroleur 
	 * @return Controller
	 */
	public Controller getController() {
		return controller;
	}

	/**
	 * Modifie le côntroleur
	 * @param Controller
	 */
	private void setController(Controller controller) {
		this.controller = controller;
	}
		
	/**
	 * Affiche un JFileChoose pour choisir un nouveau fichier
	 * @return File
	 */
	public File selectFile() {
		return SwingUtility.selectFile(JFileChooser.OPEN_DIALOG);
	}
	
	/**
	 * Affiche un message à l'utilisateur 
	 * @param message
	 */
	public void showMessage(String message) {
		SwingUtility.showMessage(message);
	}
	
	/**
	 * Affiche un message d'erreur à l'utilisateur
	 * @param message
	 */
	public void showErrorMessage(String message) {
		SwingUtility.showErrorMessage(message);
	}
	
	/**
	 * Affiche un message d'avertissement à l'uti;isateur
	 * @param message
	 */
	public void showWarningMessage(String message) {
		SwingUtility.showWarningMessage(message);
	}
	
	/**
	 * Affiche un message qui demande une valeur de l'utilisateur 
	 * @param message
	 * @return String
	 */
	public String askValue(String message) {
		return SwingUtility.askValue(message);
	}	
	
	/**
	 * Retorune le frame principale
	 * @return JFrame
	 */
	public JFrame getMainFrame() {
		return mainFrame;
	}
	
	/**
	 * Modifie le fram principale
	 * @param JFrame
	 */
	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
