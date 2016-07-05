package metier;

public class BonneReponses {
	private int idReponse;
	private String libelleReponse;
	private String imageReponse;
	
	public BonneReponses(int idReponse, String libelleReponse,
			String imageReponse) {
		super();
		this.idReponse = idReponse;
		this.libelleReponse = libelleReponse;
		this.imageReponse = imageReponse;
	}
	
	public BonneReponses() {
		super();
	}

	public int getIdReponse() {
		return idReponse;
	}

	public void setIdReponse(int idReponse) {
		this.idReponse = idReponse;
	}

	public String getLibelleReponse() {
		return libelleReponse;
	}

	public void setLibelleReponse(String libelleReponse) {
		this.libelleReponse = libelleReponse;
	}

	public String getImageReponse() {
		return imageReponse;
	}

	public void setImageReponse(String imageReponse) {
		this.imageReponse = imageReponse;
	}
	
	
}
