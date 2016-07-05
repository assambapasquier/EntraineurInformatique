package metier;

public class Proposition_Questions {
	private int proposition;
	private int question;
	
	public Proposition_Questions(int proposition, int question) {
		super();
		this.proposition = proposition;
		this.question = question;
	}
	
	public Proposition_Questions() {
		super();
	}
	
	public int getProposition() {
		return proposition;
	}
	public void setProposition(int proposition) {
		this.proposition = proposition;
	}
	public int getQuestion() {
		return question;
	}
	public void setQuestion(int question) {
		this.question = question;
	}
	
	

}
