package biblio.model;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import biblio.BiblioSystem;
import biblio.model.searchFilters.FilterByBookField;
import biblio.util.CatalogFileReader;


/**
 * Cette classe BookRepository represente un ensemble d'ouvrages qui peuvent etre
 * manipules par les clients (les emprunteurs) d'une bibliotheque.
 * Elle implemente les operations qui peuvent etre
 * effectuees sur ce repertoire (comme la recherche d'un livre).
 * 
 */
public class BookRepository {

	/**
	 * En interne l'ensemble de tous les ouvrages de la bibliotheque est 
	 * repesente par une liste. 
	 */
	protected List<Book> booksList = new ArrayList<Book>();

	/**
	 * Constructeur de la classe BookRepository � partir d'un fichier par d�faut.
	 *
	 */
	public BookRepository() {
		this(BiblioSystem.DEFAULT_CATALOGUE_FILE_NAME);
	}

	/**
	 * Constructeur de la classe BookRepository.
	 * 
	 * @param fileName est le nom du fichier contenant les informations permettant de creer une nouvelle instance de BookRepository
	 * @pre Le parametre nomFichier represente un nom de fichier existant et valide et contenant des informations sous le bon format.
	 * @post Une nouvelle instance de la classe Library est creee, contenant les informations decrites dans ce fichier.
	 */
	public BookRepository(String fileName) {
		booksList = new ArrayList<Book>();
		CatalogFileReader catalogReader = new CatalogFileReader(fileName);
		List<Book> donnees = null;
		try {
			donnees = readBooks(catalogReader.getBooksInFile());
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		if(donnees != null) {
			setBooks(donnees);
		}
	}

	public Collection<Book> getBooks() {
		return Collections.unmodifiableCollection(booksList);
	}



	/**
	 * Ajoute un book a l'ensemble des books que represente le catalogue
	 * 
	 * @param book est le book a ajouter a l'ensemble des books connus par le catalogue
	 * 
	 * @pre Le parametre book ne vaut pas null
	 * @post Le book represente par le parametre book a ete ajoute a l'ensemble des 
	 * 		 books connus par le catalogue si le book ne faisait pas encore partie de
	 * 		 l'ensemble des books connus par le catalogue.
	 *   	 Sinon, pas de modifications au catalogue.
	 */
	public void addBook(Book book) {
		if (this.canAdd(book))
			this.basicAdd(book);
	}

	/**
	 * Ajoute un book a l'ensemble des books que represente le catalogue
	 * 
	 * @param book est le book a ajouter a l'ensemble des books connus par le catalogue
	 * 
	 * @pre Le parametre book ne vaut pas null
	 * @pre Le book passe en parametre ne fait pas encore partie de
	 * 		l'ensemble des books connus par le catalogue.
	 * @post Le book represente par le parametre book a ete ajoute a l'ensemble des 
	 * 		 books connus par le catalogue.
	 * @sinf1151 ne pas montrer aux etudiants; methode auxiliaire pour addBook(Book)
	 */
	protected void basicAdd(Book book) {
		booksList.add(book);
	}

	/**
	 * Determine si on peut ajouter un book au catalog
	 *
	 * @param book est le book a ajouter a l'ensemble des books connus par le catalogue
	 * 
	 * @pre Le parametre book ne vaut pas null
	 * @post Pas de modifications au catalogue
	 * @return Renvoie true si le book ne se trouve pas encore dans l'ensemble des books connus par le catalogue,
	 * 	sinon renvoie false.
	 * @sinf1151 ne pas montrer aux etudiants; methode auxiliaire pour addBook(Book)
	 **/
	public boolean canAdd(Book book) {
		return !(hasBook(book));
	}

	/**
	 * Retire un book a l'ensemble des books que represente le catalogue
	 * 
	 * @param book est le book a retirer a l'ensemble des books connus par le catalogue
	 * 
	 * @pre Le parametre book ne vaut pas null
	 * @post Le book represente par le parametre book a ete retire a la liste des books connus par le catalogue,
	 * 	 si le book faisait deja partie de l'ensemble des books connus par le catalogue.
	 *   Sinon, pas de modifications au catalogue.
	 */
	public void removeBook(Book book) {
		if (this.canRemove(book))
			booksList.remove(book);	
	}

	/**
	 * Determine si on peut ajouter un book au catalog
	 *
	 * @param book est le book a supprimer de l'ensemble des books connus par le catalogue
	 * 
	 * @pre Le parametre book ne vaut pas null
	 * @post Pas de modifications au catalogue
	 * @return Renvoie true si le book se trouve deja dans l'ensemble des books connus par le catalogue,
	 * 	sinon renvoie false.
	 * @sinf1151 ne pas montrer aux etudiants; methode auxiliaire pour removeBook(Book)
	 **/
	public boolean canRemove(Book book) {
		return (this.hasBook(book));
	}

	/**
	 * Determine si un book fait partie de l'ensemble des books connus par le book manager
	 * 
	 * @param book est le book dont il faut verifier l'appartenance a l'ensemble des books connus par le book manager
	 * 
	 * @pre Le parametre book ne vaut pas null
	 * @post Aucune modification n'est effectuee
	 * @return true si le book represente par le parametre book fait partie de la liste de books connus par le book manager
	 * @return false sinon
	 */	
	public boolean hasBook(Book book) {
		return booksList.contains(book);
	}

	public void setBooks(Collection<Book> booksList) {
		this.booksList = new ArrayList<Book>(booksList);
	}

	/*
	 * Return a book in base of its unique id
	 */
	public Book findById(String ref) {
		for (Book b : booksList) {
			if(b.getReference().equals(ref))
				return b;
		}
		return null;
	}

	/*
	 * A simple search. This method uses a field code and a string value for finding all the books that include that value in its name
	 * The type of the object must be considered.
	 * Pay attention to the following examples:
	 * 	- Strings
	 * 		A search string "wild" will match "A wild expedition" or "WilDDD" or even ""xwiLdx"
	 *  - Numbers
	 *  	A search string "3" will match only that string. The strings "43", "36" nor "432" will not be matched
	 *  - Dates
	 *  - For simplification, the dates will be considered as string. The string "2000" will match "JAN 04, 2000"
	 *  
	 *  Give concrete example of how to call this method for a given field code.
	 */
	public Collection<Book> search(int bookFieldCode, String value) {
		return new FilterByBookField(this).applyFilter(bookFieldCode, value);
	}

	/* 
	 * Print information about the books in the specified PrintStream
	 */
	public void print(PrintStream out) {
		if(booksList == null || booksList.size() == 0)
			out.println("The catalog is empty");
		else {
			for(Book book : booksList) {
				out.println("---------------------------------------");
				out.println("Reference: " + book.getReference());
				out.println("Title: " + book.getTitle());
			}
		}
	}

	/*
	 * Answer all the books borrowed by a client
	 */
	public Collection<Book> hasBorrowedWhat(Client client) {
		Collection<Book> borrowedBooks = new ArrayList<Book>();
		for(Book book: booksList) {
			if(book.borrowedBy(client))
				borrowedBooks.add(book);
		}
		return borrowedBooks;
	}

	/*
	 * Answer all the books reserved by a client
	 */
	public Collection<Book> hasReservedWhat(Client client) {
		Collection<Book> reservedBooks = new ArrayList<Book>();
		for(Book book: booksList) {
			if(book.reservedBy(client))
				reservedBooks.add(book);
		}
		return reservedBooks;
	}

	private List<Book> readBooks(List<List<String>> rawList) {
		List<Book> booksList = new ArrayList<Book>();
		for(List<String> rawFields : rawList) {
				Book book = new Book();
				for(String rawField : rawFields) {
					if (rawField.startsWith("%Author"))
						book.addAuthor(rawField.substring(rawField.indexOf(" "), rawField.length()).trim());
					else if (rawField.startsWith("%Title"))
						book.setTitle(rawField.substring(rawField.indexOf(" "), rawField.length()).trim());
					else if (rawField.startsWith("%Series"))
						book.setSerie(rawField.substring(rawField.indexOf(" "), rawField.length()).trim());
					else if (rawField.startsWith("%Date")) 
						book.setPublicationDate(parseStringToDate(rawField.substring(rawField.indexOf(" "), rawField.length()).trim()));
					else if (rawField.startsWith("%Editor"))
						book.setEditor(rawField.substring(rawField.indexOf(" "), rawField.length()).trim());
					else if (rawField.startsWith("%Reference"))
						book.setReference(rawField.substring(rawField.indexOf(" "), rawField.length()).trim());
					else if (rawField.startsWith("%Pages"))
						book.setNumberPages(Integer.parseInt(rawField.substring(rawField.indexOf(" "), rawField.length()).trim()));
					else if (rawField.startsWith("%Copies"))
						book.addCopies(Integer.parseInt(rawField.substring(rawField.indexOf(" "), rawField.length()).trim()));
				}
				booksList.add(book);
		}
		return booksList;
	}

	//in the example file, dates can be written using one of these two formats:
	//the "MMM yyyy" or "yyyy".
	//for this reason there are defined two DateFormat for reading dates:
	private final String defaultFormat = "MMM yyyy";
	private final String alternativeFormat = "yyyy";
	private final DateFormat defaultDateFormat = new SimpleDateFormat(defaultFormat, Locale.US);
	private final DateFormat alternativeDateFormat = new SimpleDateFormat(alternativeFormat, Locale.US);


	private Date parseStringToDate(String s) {
		Date d = null;
		try {
			d = defaultDateFormat.parse(s);
		} catch (ParseException e) {
			try {
				d = alternativeDateFormat.parse(s);
			} catch (ParseException e1) {
				throw new RuntimeException(e1);
			}
		}
		return d;
	}
}
