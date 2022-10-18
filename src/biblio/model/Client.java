package biblio.model;

import java.io.PrintStream;

/**
 * Contient les donn�es relatives aux clients de la bibliotheque, c'est a dire aux emprunteurs de livres.
 * 
 */
public class Client {

	// Identifiant du client
	private String id;
	
	// Nom de l'emprunteur
	private String name;

	// Montant des amendes a payer
	private double fine = 0;
	
	public Client() {
	}
	
	/**
	 * Initialise un nouvel emprunteur en specifiant son nom.
	 * 
	 * @param nom est le nom de l'emprunteur
	 */
	public Client(String id, String name) {
		setId(id);
		setName(name);
	}

	/**
	 * @return the fine
	 */
	public double getFine() {
		return fine;
	}

	/**
	 * @param fine the fine to set
	 */
	public void setFine(double fine) {
		this.fine = fine;
	}
	
	/*
	 * Add some value to the current amount the client had as a fine
	 */
	public void addFine(double fine) {
		this.fine += fine;
	}

	/**
	 * @return le nom de l'emprunteur
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
 	 * @see Object.equals()
	 */
	public boolean equals(Object obj) {
		return obj instanceof Client &&
			this.id.equals(((Client)obj).getId());
	}
	
	/**
	 * @see Object.hashCode() 
	 */
	public int hashCode() {
		return (getName() == null ? 0 : getId().hashCode());
	}

	public Object getFieldValue(int clientFieldCode) {
		switch (clientFieldCode) {
			case ClientField.ID: return getId();
			case ClientField.NAME: return getName();
			case ClientField.FINE: return getFine();
			default: throw new RuntimeException("Client code no recognized");
		}
	}
	
	public String toString() {
		return name;
	}
	
	public void print(PrintStream out) {
		
	}
}
