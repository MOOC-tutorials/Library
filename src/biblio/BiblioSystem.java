package biblio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import biblio.controller.Controller;
import biblio.view.AdministratorGUI;
import biblio.view.GUI;

/**
 * Ceci est la classe la plus importante du systeme de gestion d'une bibliotheque.
 * Elle contient la methode 'main' qui lance l'application et qui ouvre l'interface
 * utilisateur avec lequel un utilisateur (emprunteur ou bibliothecaire) peut
 * interagir avec le systeme de gestion de la bibliotheque.
 * 
 * @auteur       Sergio Castro
 * @creation     2007-09-01
 * @modification 2008-01-08 Kim Mens
 * @SINF1151	 Le code complet de cette classe vous sera fourni
 *   au début du projet, mais vous ne devez PAS modifier cette classe.
 */
public class BiblioSystem {

	// Format de date a utiliser par l'application
	private static final String DEFAULT_FORMAT = "dd/MM/yyyy";
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(DEFAULT_FORMAT, Locale.US);

	// Nom du fichier contenant la description de tous les ouvrages de la bilbiotheque
	private static final String LARGE_CATALOGUE_FILE_NAME   = "data/bib.trn.txt";
	// Nom du petit fichier contenant la description de quelques ouvrages de la bilbiotheque
	private static final String SMALL_CATALOGUE_FILE_NAME   = "data/bib_small.trn.txt";
	// Fichier contenant les ouvrages de la bilbiotheque
	public static final String DEFAULT_CATALOGUE_FILE_NAME = LARGE_CATALOGUE_FILE_NAME;

	private BiblioSystem() {
		// Create an administrator user interface for the library with a controller to handle its actions
		GUI userInterface = new AdministratorGUI(new Controller());
		// Launch the user interface
		userInterface.launch();
	}
	
	/**
	 * Affiche l'interface graphique de l'application BiblioSystem
	 */
	public static void main(String[] args) {
		new BiblioSystem();
	}
	
}
