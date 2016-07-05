package metier;

public class Experts {
	private int idExpert;
	private String nomExpert;
	private int niveau;
	
	public Experts(int idExpert, String nomExpert, int niveau) {
		super();
		this.idExpert = idExpert;
		this.nomExpert = nomExpert;
		this.niveau = niveau;
	}
	public Experts() {
		super();
	}

	public int getIdExpert() {
		return idExpert;
	}

	public void setIdExpert(int idExpert) {
		this.idExpert = idExpert;
	}

	public String getNomExpert() {
		return nomExpert;
	}

	public void setNomExpert(String nomExpert) {
		this.nomExpert = nomExpert;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
}
