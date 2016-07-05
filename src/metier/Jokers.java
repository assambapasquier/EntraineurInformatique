package metier;

public class Jokers {
	private int idJoker;
	private String libelleJoker;
	private String imageJoker;
	
	public Jokers(int idJoker, String libelleJoker, String imageJoker) {
		super();
		this.idJoker = idJoker;
		this.libelleJoker = libelleJoker;
		this.imageJoker = imageJoker;
	}
	public Jokers() {
		super();
	}
	
	public int getIdJoker() {
		return idJoker;
	}
	public void setIdJoker(int idJoker) {
		this.idJoker = idJoker;
	}
	public String getLibelleJoker() {
		return libelleJoker;
	}
	public void setLibelleJoker(String libelleJoker) {
		this.libelleJoker = libelleJoker;
	}
	public String getImageJoker() {
		return imageJoker;
	}
	public void setImageJoker(String imageJoker) {
		this.imageJoker = imageJoker;
	}
	
	

}
