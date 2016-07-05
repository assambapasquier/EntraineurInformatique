package metier;

public class Questions {
	private int idQuestion;
	private String libelleQuestion;
	private String prop1;
	private String prop2;
	private String prop3;
	private String bonneRep;
	private int classe;
	private int niveau;
	
	public Questions(int idQuestion, String libelleQuestion, String prop1,
			String prop2, String prop3, String bonneRep, int classe, int niveau) {
		super();
		this.idQuestion = idQuestion;
		this.libelleQuestion = libelleQuestion;
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
		this.bonneRep = bonneRep;
		this.classe = classe;
		this.niveau = niveau;
	}
	
	public Questions() {
		super();
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getLibelleQuestion() {
		return libelleQuestion;
	}

	public void setLibelleQuestion(String libelleQuestion) {
		this.libelleQuestion = libelleQuestion;
	}

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public String getProp3() {
		return prop3;
	}

	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}

	public String getBonneRep() {
		return bonneRep;
	}

	public void setBonneRep(String bonneRep) {
		this.bonneRep = bonneRep;
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
