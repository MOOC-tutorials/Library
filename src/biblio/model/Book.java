package biblio.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Cette classe represente un ouvrage (Book), dont la bibliotheque peut posseder
 * plusieurs exemplaires (BookCopy). La difference entre un ouvrage et un exemplaire
 * est parfois utile, par exemple si un utilisateur veut reserver un livre :
 * il reservera un ouvrage (Book) et pas un exemplaire (BookCopy) car pour l'utilisateur,
 * n'importe quel exemplaire d'un ouvrage conviendra.
 * 
 */
public class Book {

	// Liste de tous les exemplaires correspondant a un ouvrage
	private Collection<BookCopy> copies = new ArrayList<BookCopy>();

	// Liste de toutes les reservations pour cet ouvrage
	private List<Client> reservations = new ArrayList<Client>();

	// Details de l'ouvrage
	private List<String> authors = new ArrayList<String>();	
	private String title;
	private Date publicationDate;
	private String serie;
	private int numberPages;
	private String reference;
	private String editor;

	public Book() {
	}
	
	public Book(String reference) {
		setReference(reference);
	}

	/**
	 * @return la reference, c'est-a-dire l'identificateur univoque 
	 * de l'ouvrage.
	 * @SINF1151 Phase 1 - Cette methode sert comme example pour les autres accesseurs a implementer.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Change la reference de l'ouvrage.
	 * 
	 * @param ref la nouvelle reference de l'ouvrage.
	 * @SINF1151 Phase 1 - Cette methode sert comme example pour les autres mutateurs a implementer.
	 */
	public void setReference(String ref) {
		this.reference = ref;
	}
	
	/**
	 * @return la serie de l'ouvrage.
	 * @SINF1151 Phase 1 - à implémenter
	 */
	public String getSerie() {
		return serie;
	}
	
	/**
	 * Change la serie de l'ouvrage.
	 * 
	 * @param serie la nouvelle serie de l'ouvrage.
	 * @SINF1151 Phase 1 - � impl�menter
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * @return l'editeur de l'ouvrage.
	 * @SINF1151 Phase 1 - � impl�menter
	 */
	public String getEditor() {
		return editor;
	}
	
	/**
	 * Change l'editeur de l'ouvrage.
	 * 
	 * @param editeur le nouvel editeur de l'ouvrage.
	 * @SINF1151 Phase 1 - � impl�menter
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * @return une liste sur l'ensemble de tous les auteurs de l'ouvrage.
	 */
	public List<String> getAuthors() {
		return Collections.unmodifiableList(authors);
	}
	
	/*
	 * Provides a single string representation (a list separated with ,) of all the authors of a book
	 */
	public String getAuthorsString() {
		String authorsString="";
		for(String author : authors)
			authorsString+= author + ", ";
		if(authorsString.length()>0)
			authorsString = authorsString.substring(0, authorsString.length() - 2);
		return authorsString;
	}
	
	public void setAuthors(List<String> authors) {
		this.authors = new ArrayList<String>(authors);
	}
	
	public void setAuthorsString(String authorsString) {
		String authorsArray [] = authorsString.split(",");
		authors = new ArrayList<String>(Arrays.asList(authorsArray));
	}
	
	/**
	 * Ajoute un auteur a l'ouvrage.
	 * 
	 * @param name le nom de l'auteur a ajouter.
	 */
	public void addAuthor(String name) {
		authors.add(name);
	}
	
	/**
	 * @return le titre de l'ouvrage.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Change le titre de l'ouvrage.
	 * 
	 * @param title le nouveau titre de l'ouvrage.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return la date de publication de l'ouvrage.
	 */
	public Date getPublicationDate() {
		return publicationDate;
	}

	/**
	 * Change la date de publication de l'ouvrage.
	 * 
	 * @param la nouvelle date de publication de l'ouvrage.
	 */
	public void setPublicationDate(Date date) {
		this.publicationDate = date;
	}

	/**
	 * @return le nombre de pages de l'ouvrage
	 */
	public int getNumberPages() {
		return numberPages;
	}
	
	/**
	 * Change le nombre de pages de l'ouvrage.
	 * 
	 * @param le nouveau nombre de pages de l'ouvrage.
	 */
	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}

	/**
	 * @return the reservations
	 */
	public List<Client> getReservations() {
		return Collections.unmodifiableList(reservations);
	}
	
	/**
	 * Compare une instance de la classe Book avec un autre objet donne
	 * et retourne vrai si le objet est un Ouvrage avec la meme
	 * reference.
	 */
	public boolean equals(Object obj) {
		return
			obj != null &&
			obj instanceof Book &&
			this.getReference() != null &&
			this.getReference().equals(((Book)obj).getReference());
	}
	
	/**
	 * Quand on reecrit la methode Object.equals(), il faut reecrire
	 * Object.hashCode() pour respecter le contract entre ces deux methodes.
	 * 
	 * @see methode Object.hashCode() 
	 */
	public int hashCode() {
		return (null == getReference() ? 0 : getReference().hashCode());
	}
	

	/**
	 * Ajoute des nouveaux exemplaires a l'ouvrage.
	 * 
	 * @param quantite quantite d'exemplaires a ajouter.
	 */
	public void addCopies(int quantite) {
		for(; quantite > 0; quantite--)
			copies.add(new BookCopy(this));
	}

	/*
	 * method for borrowing a copy of the book to a client.
	 * the start date of the borrowing and the number of days requested must be specified
	 * the method returns true if the borrowing operation was successful, otherwise it returns false
	 * it is only possible to borrow a copy of the book to a client if:
	 * - the client has not yet borrowed it
	 * - there are copies availables of the book
	 * - for phase 3, in the case that the book has been reserved, also the following conditions must be true
	 * 		- either the client made one of the existing reservations, or there are more available books than reservations
	 * 		- even if the client has made a reservation, she can only borrow the book if the number of available copies is greater or equal
	 * 		than her position in the reservation queue.
	 */
	public boolean borrow(Client client, Date startDate, int days) {
		BookCopy bookCopy = copyFor(client);
		if(bookCopy == null)
			return false;
		else 
			return bookCopy.borrow(client, startDate, days);
	}
	
	/*
	 * This operation return a copy of the book that has been previously borrowed by a client
	 * If the specified return date is greater than the deadline specified when borrowing the book, a fine must be calculated
	 * In case of fine, this should be added to the client with its method "addFine"
	 * If the operation success, it must return true, otherwise 
	 * (i.e. in case of an attempt of returning a book that has not been borrowed by the specified client) 
	 * , the operation must return false
	 */
	public boolean returnBook(Client client, Date realReturnDate) {
		BookCopy bookCopy = getBorrowedCopy(client);
		if(bookCopy != null) {
			client.addFine(bookCopy.calculateFine(realReturnDate));
			bookCopy.returnCopy();
			return true;
		} else
			return false;
	}
	
	/*
	 * return the copy borrowed for a client, null if no copy has been borrowed by her
	 */
	public BookCopy getBorrowedCopy(Client client) {
		for(BookCopy bookCopy : copies) {
			if( bookCopy.getBorrower() != null
				&& bookCopy.getBorrower().equals(client))
				return bookCopy;
		}
		return null;
	}
	
	/*
	 * answer true or false according to if the client has borrowed the book or not
	 */
	public boolean borrowedBy(Client client) {
		return getBorrowedCopy(client) != null;
	}
	
	/*
	 * answer true or false according to if the client has reserved the book or not
	 */
	public boolean reservedBy(Client client) {
		for(Client c : reservations) {
			if(c.equals(client))
				return true;
		}
		return false;
	}
	
	/*
	 * answer all the clients that have borrowed the book
	 */
	public Collection<Client> borrowedBy() {
		Collection<Client> clients = new ArrayList<Client>();
		for(BookCopy bookCopy : borrowedCopies())
			clients.add(bookCopy.getBorrower());
		return clients;
	}
	
	/**
	 * Encode une reservation pour le client passe en parametre.
	 * On peut reserver un ouvrage seulement s'il n'y a plus d'exemplaires
	 * disponibles.
	 * Si le client possede deja une reservation, la methode retounera false
	 */
	public boolean reserveFor(Client client) {

		if(reservations.contains(client)) // reserve deja faite
			return false;
		
		// essaie de trouver un exemplaire disponible pour le client
		BookCopy exemplaire = null;
		exemplaire = copyFor(client);
		
		/* on peut reserver seulement s'il n'y a pas des exemplaires
		   disponibles */
		if(exemplaire == null) {
			reservations.add(client);
			return true;
		}
		else //"It is not possible to reserve if the book is available"
			return false;
	}
	
	/**
	 * @return une collection sur l'ensemble de tous les exemplaires de cet ouvrage.
	 */
	public Collection<BookCopy> getCopies() {
		return Collections.unmodifiableCollection(copies);
	}


	/**
 	 * @return une collection sur l'ensemble de tous les exemplaires non empruntes a ce moment.
	 */
	public Collection<BookCopy> availableCopies() {
		Collection<BookCopy> result = new ArrayList<BookCopy>();	
		for(BookCopy bookCopy : copies)
			if(bookCopy.isAvailable())
				result.add(bookCopy);
		return result;
	}

	/**
	 * Renvoie un exemplaire pour que le client donnee l'emprunte, tenant
	 * compte des reservations placees sur cet ouvrage.
	 *
	 * S'il n'y a pas des exemplaires disponibles, la methode renvoie null. Ceci
	 * inclut le cas ou il y a des exemplaires mais il y a une reservation pour
	 * un autre client.
	 * 
	 * Si le client possede une reservation, il est possible de lui donner un
	 * exemplaire disponible, et la reservation sur l'ouvrage est retiree.
	 * 
	 * Si l'ouvrage n'a pas de reservation a ce moment, la methode renvoie un
	 * exemplaire disponible quelconque.
	 * 
	 * @return Un exemplaire a emprunter par le client passe en parametre, ou null
	 * s'il n'y a aucun exemplaire disponible pour ce client a ce moment.
	 */
	public BookCopy copyFor(Client client) {
		if(borrowedBy(client))
			return null;
		Collection<BookCopy> availables = availableCopies();
		if(availables.size() == 0)
			return null;
		else {
			if(reservations.size() > 0) { // verifier reserves...
				int pos = reservations.indexOf(client); // position du client dans la queue d'attente
				if( pos != -1 ) { // le client a une reserve...
					if(pos<availables.size()) {
						reservations.remove(pos);
						return availables.iterator().next();
					} else
						return null;
				} else if(availables.size() > reservations.size()) {
					return availables.iterator().next();
				} else
					return null;
			} else  //there are copies available and not reserved
				return availables.iterator().next();
		}
	}

	/**
	 * @return une collection sur l'ensemble de tous les exemplaires empruntes a ce moment.
	 */
	public Collection<BookCopy> borrowedCopies() {
		Collection<BookCopy> result = new ArrayList<BookCopy>();	
		for(BookCopy bookCopy : copies)
			if(!bookCopy.isAvailable())
				result.add(bookCopy);
		return result;
	}

	/**
	 * @return le nombre d'exemplaires de cet ouvrage.
	 */
	public int numCopies() {
		return copies.size();
	}
	
	/**
	 * @return le nombre d'exemplaires that have been borrowed
	 */
	public int numBorrowedCopies() {
		return numCopies() - numAvailableCopies();
	}
	
	/**
	 * @return le nombre d'exemplaires disponibles
	 */
	public int numAvailableCopies() {
		return availableCopies().size();
	}
	
	/**
	 * @return le nombre d'exemplaires avec une reservation
	 */
	public int numReservedCopies() {
		return reservations.size();
	}
	
	
	/**
	 * Retourne la valeur d'un champ en fonction du numero de ce champ.
	 * 
	 * @param fieldCode est le code du champ dont on veut obtenir la valeur.
	 * 
	 * @pre 0 <= pos < NOMBRE_CHAMPS
	 * @post Aucune modification n'est effectuee
 	 * @return 	Le list de authors si pos vaut 0
	 *          le title du livre si pos vaut 1
	 *          la date de publication du livre si pos vaut 2
	 *          la serie si pos vaut 3
	 *          le numero du pages si pos vaut 4
	 *          la reference si pos vaut 5
	 *          l' editor si pos vaut 6
	 *          le numero de copies empreste si pos vaut 7
	 *          le numero de copies disponible si pos vaut 8
	 *          le numero de copies reservu si pos vaut 9
	 */
	public Object getFieldValue(int fieldCode) {
		switch (fieldCode) {
		case BookField.AUTHORS: return getAuthorsString();
		case BookField.TITLE: return getTitle();
		case BookField.PUBLICATION_DATE: return getPublicationDate();
		case BookField.SERIE: return getSerie();
		case BookField.NUMBER_PAGES: return getNumberPages();
		case BookField.REFERENCE: return getReference();
		case BookField.EDITOR: return getEditor();
		case BookField.NUM_BORROWED_COPIES: return numBorrowedCopies();
		case BookField.NUM_AVAILABLE_COPIES: return numAvailableCopies();
		case BookField.NUM_RESERVED_COPIES: return numReservedCopies();
		default: throw new RuntimeException("Book code no recognized");
		}
	}
	
	/** 
	 * Renvoie une chaine de caracteres caracterisant une instance de la classe Book.
	 * @pre      -
	 * @post	 -
	 * @return   Une chaine comme
		%W 010-93-04
		%A White Ron
		%T How computers work
		%I Ziff-Davis Press
		%D 1993
		%P 202
		%S PC Computing
		%F 1
	*/
	@Override
	public String toString() {
		String nl = "\n";
		String result = "%W " + getReference()+nl;
		result += "%A " + getAuthorsString()+nl;
		result += "%T " + getTitle()+nl;
		result += "%I " + getEditor()+nl;
		result += "%D " + getPublicationDate()+nl;
		result += "%P " + getNumberPages()+nl;
		result += "%S " + getSerie()+nl;
		result += "%F " + getCopies().size();
		return result;
	}

	/**
	 * @param clients is a ClientRepository that knows about who has borrowed what books
	 * @return true if a given book can be borrowed (taking into account what borrowers
	 * 		   have already reserved or borrowed what books);
	 *         false otherwise.
	 */
	public boolean canBeBorrowed(ClientRepository clients) {
		return clients.whoCanBorrow(this).size() > 0;
	}

	/**
	 * @param clients is a ClientRepository that knows about who has borrowed what books
	 * @return true if a given book can be reserved (taking into account what borrowers
	 * 		   have already reserved or borrowed what books);
	 *         false otherwise.
	 */
	public boolean canBeReserved(ClientRepository clients) {
		return clients.whoCanReserve(this).size() > 0;
	}

	/**
	 * @param / (returning a book is independent of other clients, so no
	 * client repository needs to be passed as parameters here)
	 * @return true if a given book can be returned (if there still remain
	 *   exemplars that were not yet returned);
	 *   false otherwise.
	 */
	public boolean canBeReturned() {
		return borrowedBy().size() > 0;
	}

}
