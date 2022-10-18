package biblio.model;

import java.util.Date;

public class BookField {

	/** Un ouvrage consiste de plusieurs champs (auteur, titre, date de publication, ...)
 	 * On peut rechercher un ouvrage sur base d'un de ces champs.
	 * A chaque champ correspond un identifiant unique comme d�clar� pas les variables
	 * statiques suivants: 
	 */
	public static final int AUTHORS = 0;
	public static final int TITLE = 1;
	public static final int PUBLICATION_DATE = 2;
	public static final int SERIE = 3;
	public static final int NUMBER_PAGES = 4;
	public static final int REFERENCE = 5;
	public static final int EDITOR = 6;
	public static final int NUM_BORROWED_COPIES = 7;
	public static final int NUM_AVAILABLE_COPIES = 8;
	public static final int NUM_RESERVED_COPIES = 9;
	
	/** Transforme le code d'un champ (une des valeurs �num�r�es ci-dessus)
	 *  en un string repr�sentant le type de champ.
	 *  Par example la valeur 0 (le champ AUTHORS) est traduit en "authors".
	 */
	public static String getFieldName(int bookdFieldCode) {
		switch(bookdFieldCode) {
			case AUTHORS: return "Authors";
			case TITLE: return "Title";
			case PUBLICATION_DATE: return "Publication date";
			case SERIE: return "Series";
			case NUMBER_PAGES: return "Number of pages";
			case REFERENCE: return "Reference";
			case EDITOR: return "Editor";
			case NUM_BORROWED_COPIES: return "number of borrowed copies";
			case NUM_AVAILABLE_COPIES: return "number of available copies";	
			case NUM_RESERVED_COPIES: return "number of reserved copies";
			default: throw new RuntimeException("BIBLIO - Book code unrecognized");
		}	
	}

	/** Transforme le code d'un champ (une des valeurs �num�r�es ci-dessus)
	 *  au type de valeurs correspondant � ce champ.
	 *  Par example la valeur 0 (le champ AUTHORS) est traduit en la classe String,
	 *  car les auteurs sont repr�sent�s par des strings.
	 *  La valeur 4 (le champ NUMBER_PAGES) est traduite en la classe Integer
	 *  car le num�ro de pages d'un livres est respr�sent� par un entier.
	 */
	public static Class getFieldType(int fieldCode) {
		switch (fieldCode) {
		case AUTHORS: return String.class;
		case TITLE: return String.class;
		case PUBLICATION_DATE: return Date.class;
		case SERIE: return String.class;
		case NUMBER_PAGES: return Integer.class;
		case REFERENCE: return String.class;
		case EDITOR: return String.class;
		case NUM_BORROWED_COPIES: return Integer.class;
		case NUM_AVAILABLE_COPIES: return Integer.class;
		case NUM_RESERVED_COPIES: return Integer.class;
		default: throw new RuntimeException("BIBLIO - Book code unrecognized");
		}
	}

}
