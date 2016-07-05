package ManagerDAO;

import metier.Proposition_Questions;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Proposition_QuestionManager {
	public static final String CREATE_TABLE_BONNEREPONSES = "CREATE TABLE Propositions_Questions("+
	"proposition INTEGER,"+
	"question INTEGER,"+
	"PRIMARY KEY (proposition, question),"+
	"FOREIGN KEY(proposition) REFERENCES Propositions(idProposition),"+
	"FOREIGN KEY(question) REFERENCES Questions(idQuestion)"+
");";
	
	private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    
    public Proposition_QuestionManager(Context context)
    {
    	maBaseSQLite = new MySQLite(context);
    }

	public void open()
	    {
	    	//on ouvre la table en lecture/écriture
	    	db = maBaseSQLite.getWritableDatabase();
	    }
	
	public void close()
	    {
	    	//on ferme l'accès à la BDD
	    	db.close();
	    }

	public long addProposition_Question(Proposition_Questions p_q) {
        // Ajout d'un enregistrement dans la table

	        ContentValues values = new ContentValues();
	        values.put("proposition", p_q.getProposition());
	        values.put("question", p_q.getQuestion());
	        
	        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
	        return db.insert("Proposition_Questions",null,values);
        }

    public int modProposition_Question(Proposition_Questions p_q) {
	        // modification d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la requête
	
    	ContentValues values = new ContentValues();
    	values.put("proposition", p_q.getProposition());
        values.put("question", p_q.getQuestion());
	        String where = "proposition = ? AND question = ?";
	        String[] whereArgs = {p_q.getProposition()+"", p_q.getQuestion()+""};
	
	        return db.update("Proposition_Questions", values, where, whereArgs);
        }

    public int supProposition_Question(Proposition_Questions p_q) {
	        // suppression d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon
	
	        String where = "proposition = ? AND question = ?";
	        String[] whereArgs = {p_q.getProposition()+"", p_q.getQuestion()+""};
	
	        return db.delete("Proposition_Questions", where, whereArgs);
        }

    public Proposition_Questions getProposition_Question(int proposition, int question) {
        // Retourne l'animal dont l'id est passé en paramètre

    	Proposition_Questions p_q = new Proposition_Questions();

        Cursor c = db.rawQuery("SELECT * FROM Proposition_Questions WHERE proposition = "+proposition+"AND question = "+question, null);
        if (c.moveToFirst()) {
        	p_q.setProposition(c.getInt(c.getColumnIndex("proposition")));
        	p_q.setQuestion(c.getInt(c.getColumnIndex("question")));
            c.close();
            }

        return p_q;
        }

    public Cursor getProposition_Question() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM Proposition_Questions", null);
        }
}
