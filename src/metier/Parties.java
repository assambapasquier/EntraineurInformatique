package metier;

import java.io.Serializable;

public class Parties implements Serializable{
	private int idPartie;
	private String nomJoueur;
	private String date;
	private int points;
	private int somme;
	private int rang;
	private int classe;
	private int niveau;
	
	public Parties(int idPartie, String nomJoueur, String date, int points,
			int somme, int rang, int classe, int niveau) {
		super();
		this.idPartie = idPartie;
		this.nomJoueur = nomJoueur;
		this.date = date;
		this.points = points;
		this.somme = somme;
		this.rang = rang;
		this.classe = classe;
		this.niveau = niveau;
	}
	
	public Parties() {
		super();
	}

	public int getIdPartie() {
		return idPartie;
	}

	public void setIdPartie(int idPartie) {
		this.idPartie = idPartie;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getSomme() {
		return somme;
	}

	public void setSomme(int somme) {
		this.somme = somme;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	
	
}
