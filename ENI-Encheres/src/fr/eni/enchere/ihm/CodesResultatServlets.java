package fr.eni.enchere.ihm;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {
	
	public static final Integer FORMULAIRE_INSCIPTION_SAISIE_OBLIGATOIRE = 30000;
	public static final Integer EMAIL_INSCIPTION_SAISIE_OBLIGATOIRE = 30001;
	public static final Integer TELEPHONE_INSCIPTION_SAISIE_OBLIGATOIRE = 30002;
	public static final Integer CODE_POSTAL_INSCIPTION_SAISIE_OBLIGATOIRE = 30003;
	public static final Integer MOT_DE_PASSE_INSCIPTION_SAISIE_OBLIGATOIRE = 30004;
	public static final Integer PSEUDO_INSCIPTION_EXIST = 30009;
	
	// Article
	
	public static final Integer FORMULAIRE_AJOUT_SAISIE_OBLIGATOIRE = 30005;
	public static final Integer PRIX_NON_NEGATIF = 30006;
	public static final Integer EMPECHER_JAVASCRIPT = 30007;
	public static final Integer DATE_INCOHERANTE = 30008;
	
	// Enchere
	
	public static final Integer PRIX_COMPRIS  = 30200;
	public static final Integer PRIX_COMPRIS_DE_LENCHERE  = 30201;
}