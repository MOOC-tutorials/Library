package biblio.model;
import java.util.Date;

import biblio.util.DateUtility;

/**
 * Represente un exemplaire d'un ouvrage qui se trouve
 * dans la bibliotheque.
 * 
 */
public class BookCopy {

	// L'ouvrage dont provient l'exemplaire
	private Book book;
	// Est-ce que l'exemplaire se trouve dans la bibliotheque maintenant?
	private boolean available; // vrai si l'exemplaire n'est pas emprunte

	// Si l'exemplaire est emprunte: debut de la periode d'emprunt
	private Date borrowDate;
	// Si l'exemplaire est emprunte: fin de la periode d'emprunt
	private Date returnDate;
	// Si l'exemplaire est emprunte: personne qui l'a emprunte
	private Client client;

	/**
	 * Instancie un nouvel exemplaire d'un ouvrage. L'exemplaire est 
	 * intialise comme etant disponible, c'est-a-dire non-emprunte.
	 * 
	 * @param book : l'ouvrage dont provient l'exemplaire
	 */
	public BookCopy(Book book) {
		this.book = book;
		available = true;
	}
	
	/**
	 * @pre /
	 * @return l'ouvrage dont provient l'exemplaire.
	 */	
	public Book getBook() {
		return book;
	}
	
	/**
	 * @pre l'exemplaire est emprunte.
	 * @return la date du debut de la periode d'emprunt de l'exemplaire.
	 */
	public Date getBorrowDate() {
		return borrowDate;
	}
	
	/**
	 * @pre l'exemplaire est emprunte.
	 * @return la date de la fin de la periode d'emprunt de l'exemplaire.
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @pre l'exemplaire est emprunte.
	 * @return l'emprunteur de cet exemplaire.
	 */
	public Client getBorrower() {
		return client;
	}

	/**
	 * @return valeur booleenne indiquant si l'exemplaire est disponible en bibliotheque.
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * Enregistre le fait que l'exemplaire ait ete retourne.
	 * 
	 * @post l'exemplaire devient disponible pour etre emprunte.
	 */
	public void returnCopy() {
		borrowDate = returnDate = null;
		client = null;
		available = true;
	}
	

	
	/**
	 * Enregistre l'emprunt de l'exemplaire par un emprunteur pour une
	 * periode de temps donnee.
	 * 
	 * @pre le exemplaire est disponible.
	 * @param emprunteur : l'emprunteur de l'exemplaire
 	 * @param debut : date de debut de la periode d'emprunt
	 * @param jours : duree de la periode d'emprunt
	 */
	public boolean borrow(Client emprunteur, Date debut, int jours) {		
		if(isAvailable()) {
			this.client = emprunteur;
			borrowDate = DateUtility.trunc(debut);
			returnDate = DateUtility.addToDate(this.borrowDate, jours); // debut + "jours" jours
			available = false;
			return true;
		}
		return false;
	}
	
	/*
	 * Calculate the fine that a client must pay in case that he return the book too late
	 */
	public double calculateFine(Date realReturnDate) {
		return calculateFine(returnDate, realReturnDate);
	}
	
	/*
	 * Utility method for calculating the fine
	 */
	public static double calculateFine(Date deadline, Date realReturnDate) {
		double fine = new Double(0);
		if(deadline.before(realReturnDate) ) {
			fine = DateUtility.differenceInDays(deadline, realReturnDate) * 0.20;
		}
		return ((long)(fine*100)/(double)100);
	}


}
