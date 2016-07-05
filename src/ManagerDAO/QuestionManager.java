package ManagerDAO;

import metier.Questions;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QuestionManager {
	
	private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    
    public QuestionManager(Context context)
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

	public long addQuestion(Questions q) {
        // Ajout d'un enregistrement dans la table

	        ContentValues values = new ContentValues();
	        values.put("idQuestion", q.getIdQuestion());
	        values.put("libelleQuestion", q.getLibelleQuestion());
	        values.put("prop1", q.getProp1());
	        values.put("prop2", q.getProp2());
	        values.put("prop3", q.getProp3());
	        values.put("bonneRep", q.getBonneRep());
	        values.put("classe", q.getClasse());
	        values.put("niveau", q.getNiveau());
	        
	        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
	        return db.insert("Questions",null,values);
        }

    public int modQuestion(Questions q) {
	        // modification d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la requête
	
    	ContentValues values = new ContentValues();
    	values.put("idQuestion", q.getIdQuestion());
        values.put("libelleQuestion", q.getLibelleQuestion());
        values.put("prop1", q.getProp1());
        values.put("prop2", q.getProp2());
        values.put("prop3", q.getProp3());
        values.put("bonneRep", q.getBonneRep());
        values.put("classe", q.getClasse());
        values.put("niveau", q.getNiveau());
	        String where = "idQuestion = ?";
	        String[] whereArgs = {q.getIdQuestion()+""};
	
	        return db.update("Questions", values, where, whereArgs);
        }

    public int supQuestion(Questions q) {
	        // suppression d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon
	
	        String where = "idQuestion = ?";
	        String[] whereArgs = {q.getIdQuestion()+""};
	
	        return db.delete("Questions", where, whereArgs);
        }

    public Questions getQuestion(int idQuestion) {
        // Retourne l'animal dont l'id est passé en paramètre

    	Questions q = new Questions();

        Cursor c = db.rawQuery("SELECT * FROM Questions WHERE idQuestion = "+idQuestion, null);
        if (c.moveToFirst()) {
        	q.setIdQuestion(c.getInt(c.getColumnIndex("idQuestion")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("libelleQuestion")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("prop1")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("prop2")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("prop3")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("bonneRep")));
        	q.setClasse(c.getInt(c.getColumnIndex("classe")));
        	q.setNiveau(c.getInt(c.getColumnIndex("niveau")));
 
            c.close();
            }

        return q;
        }
    
    public Cursor getQuestionClasseNiv(int classe, int niveau) {
        
    	/*if (c.moveToFirst()) {
        	q.setIdQuestion(c.getInt(c.getColumnIndex("idQuestion")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("libelleQuestion")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("prop1")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("prop2")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("prop3")));
        	q.setLibelleQuestion(c.getString(c.getColumnIndex("bonneRep")));
        	q.setClasse(c.getInt(c.getColumnIndex("classe")));
        	q.setNiveau(c.getInt(c.getColumnIndex("niveau")));
 
            c.close();
            }*/
    	return db.rawQuery("SELECT * FROM Questions WHERE classe = "+classe+" AND niveau ="+niveau, null);
        }

    public Cursor getQuestion() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM Questions", null);
        }
}
