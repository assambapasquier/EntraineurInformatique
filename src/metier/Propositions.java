package metier;

public class Propositions {
	private int idProposition;
	private String libelleProposition;
	private String imageProposition;
	
	public Propositions(int idProposition, String libelleProposition,
			String imageProposition) {
		super();
		this.idProposition = idProposition;
		this.libelleProposition = libelleProposition;
		this.imageProposition = imageProposition;
	}
	
	public Propositions() {
		super();
	}

	public int getIdProposition() {
		return idProposition;
	}

	public void setIdProposition(int idProposition) {
		this.idProposition = idProposition;
	}

	public String getLibelleProposition() {
		return libelleProposition;
	}

	public void setLibelleProposition(String libelleProposition) {
		this.libelleProposition = libelleProposition;
	}

	public String getImageProposition() {
		return imageProposition;
	}

	public void setImageProposition(String imageProposition) {
		this.imageProposition = imageProposition;
	}
	
	
}
