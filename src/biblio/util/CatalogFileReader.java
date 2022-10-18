package biblio.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Lit un fichier de texte avec donnees bibliographiques et construit des objets
 * representant ces donnees.
 * 
 * NOTE : cette classe est un outil INTERNE.
 * Les etudiants ne sont pas censes de comprendre le fonctionnement interne
 * de cette classe mais seulement le resultat renvoye.
 */
public class CatalogFileReader {

	// fichier texte subyacent
	private BufferedReader fichier;
	private String fileName;
	// attributs utilis�s pour l'algoritme de lecture d'ouvrages
	private 	String ligne = ""; // derni�re ligne lue
	private boolean ouvrageLu = false; // vrai si la lecture d'un ouvrage a �t� complet�e

	
	
	/**
	 * Cr�e un lecteur d'ouvrages qui utilise comme entr�e un fichier.
	 * Le nom du fichier est donn�e dans le parametre 'nomFichier'.
	 */
	public CatalogFileReader(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Renvoi la liste compl�te d'ouvrages contenus dans le
	 * fichier texte.
	 */
	public List<List<String>> getBooksInFile() {
		// liste ordonnée d'ouvrages
		
		List<List<String>> rawList = null;
//		InputStream is = null;
		System.out.println("Ouverture du fichier " + fileName + ".");
		try {
			fichier = new BufferedReader(new FileReader(fileName));
//			is = getClass().getResourceAsStream(fileName);
	//		fichier = new BufferedReader(new InputStreamReader(is));	

			rawList = basicReadBooks();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Le fichier avec nom " + fileName
					+ " n'a pas été trouvé.", e);
		} finally { 
		if(fichier != null)
				try {
					fichier.close();
			//		is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
		}
		return rawList;
	}
	
	
	/**
	 * Remplit le catalogue avec les donnees provenant d'un fichier
	 * 
	 * booksList est une structures de donnees contenant des informations sur les books.
	 *        Il s'agit en fait d'une "liste de liste de tableaux".
	 *                  +-+-+               +-+-+    +-+-+
	 *      booksList = |.|.+---   ...  --->|.|.+--->|.|.|
	 *                  +++-+               +++-+    +++-+
	 *                   |                   |        |
	 *                   V                   V        V
	 *       _____________________________       _____________________________
	 *      |           Block 1           |     |     Block N                 |
	 *      |_____________________________|     |_____________________________| 
     *
	 *       booksList est une liste contenant d'autres listes.
	 *       Appellons ces dernieres listes des 'fields'.
	 *       Un object fields contient des informations sur un book.
	 *       
	 *                 +-+-+              +-+-+            +-+-+
	 *      fields i = |.|.+------------->|.|.+----------->|.|.+-----> ...
	 *                 +++-+              +++-+            +++-+
	 *                  |                  |       
	 *                  V                  V       
	 *             +--------+-----+    +----+-----------+  +------+--------+
	 *             |%Reference 010-54-01  +    |%Autor Bauer, F. L. +  |%Pages 213         |
	 *             +--------+-----+    +----+-----------+  +------+--------+
     *
	 *       Un object fields est une liste qui contient des tableaux. Appellons ces tableaux des elements.
	 *       Chaque element est un string. Le primer et deuxieme characters sont un code, le trocieme est un space,
	 *       et aprest il y a le valeur d' un champ.
	 *       Le code represente le nom du champ:
	 *       %Author : author
	 *       %Title : title
	 *       %Series : serie
	 *       %Date : publication date
	 *       %Editor : editor
	 *       %Reference : reference
	 *       %Pages : number of pages
	 *       %Copies : number of copies
	 *       
	 * @post Pour chaque list 'fields' un neuveau book sera instancie
	 *       Ces instances de Book ont ete ajoutees a la liste des books connus par la BookManager.      
	 */
	public List<List<String>> basicReadBooks() {
		List<List<String>> booksList = new ArrayList<List<String>>();
		for(List<String> fields = basicReadFields(); fields != null; fields = basicReadFields()) {
			booksList.add(fields);
		}
		return booksList;
		
	}
	private List<String> basicReadFields() {
		List<String> fields = new ArrayList<String>();
		for(String champ = lisChamp(); champ != null; champ = lisChamp()) {
			fields.add(champ);
		}
		if(fields.size() == 0)
			return null;
		return fields;
	}
	
	/**
	 * Retourne le champ suivante d'un ouvrage, ou null
	 * si l'ouvrage n'a plus de champs.
	 */
	private String lisChamp() {
		/*
		 NOTE: cette impl�mentation est plus compliqu�e que n�cessaire,
		 parce que le format du fichier texte original
		 est plus complexe que le format du fichier fourni aux �tudiants.
		 -> Les �tudiants n'ont pas besoin d'impl�menter un
		    algoritme come celui ci-dessous.
		*/

		if(ouvrageLu) {
			ouvrageLu = false;
			return null;
		}
		
		try {
			// Eviter le space blanc
			while(ligne != null && ligne.equals(""))
				ligne = fichier.readLine();

			// Un champ peut comporter plusieures lignes
			String champ = ligne, accum = "";
			boolean finPossible = false;
			for(ligne = fichier.readLine();
				ligne != null && !ligne.startsWith("%");
				ligne = fichier.readLine())
			{		
				if(ligne.equals(""))
					finPossible = true;
				else if(finPossible) {
					champ += accum;
					finPossible = false;
				}
				if(finPossible)
					accum += "\n" + ligne;
				else
					champ += "\n" + ligne;
			}
			if(finPossible)
				ouvrageLu = true;
			return champ;
		}
		catch (IOException e) {
			System.out.println("Erreur pendant la lecture du fichier");
			System.exit(1);
		}
		return null;
	}
	
}
