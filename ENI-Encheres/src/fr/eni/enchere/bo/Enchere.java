package fr.eni.enchere.bo;

import java.sql.Timestamp;

public class Enchere {

	private Utilisateur encherit;
	private Timestamp dateEnchere;
	private int montant_enchere;
	
	public Enchere(Utilisateur encherit, Timestamp dateEnchere, int montant_enchere) {
		super();
		this.encherit = encherit;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	public Utilisateur getEncherit() {
		return encherit;
	}

	public void setEncherit(Utilisateur encherit) {
		this.encherit = encherit;
	}

	public Timestamp getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Timestamp dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	
	
}
