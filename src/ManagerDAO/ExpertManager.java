package ManagerDAO;

import metier.Experts;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ExpertManager {
	public static final String CREATE_TABLE_BONNEREPONSES = "CREATE TABLE Experts("+
	"idExpert INTEGER PRIMARY KEY AUTOINCREMENT,"+
	"nomExpert TEXT,"+
	"niveau INTEGER,"+
	"FOREIGN KEY(niveau) REFERENCES Niveaux(niveau)"+
	");";
	
	private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    
    public ExpertManager(Context context)
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

	public long addExpert(Experts exp) {
        // Ajout d'un enregistrement dans la table

	        ContentValues values = new ContentValues();
	        values.put("idExpert", exp.getIdExpert());
	        values.put("nomExpert", exp.getNomExpert());
	        values.put("niveau", exp.getNiveau());
	        
	        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
	        return db.insert("Experts",null,values);
        }

    public int modExpert(Experts exp) {
	        // modification d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la requête
	
    	ContentValues values = new ContentValues();
    	values.put("idExpert", exp.getIdExpert());
        values.put("nomExpert", exp.getNomExpert());
        values.put("niveau", exp.getNiveau());
	        String where = "idExpert = ?";
	        String[] whereArgs = {exp.getIdExpert()+""};
	
	        return db.update("Experts", values, where, whereArgs);
        }

    public int supExpert(Experts exp) {
	        // suppression d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon
	
	        String where = "idExpert = ?";
	        String[] whereArgs = {exp.getIdExpert()+""};
	
	        return db.delete("Experts", where, whereArgs);
        }

    public Experts getExpert(int idExpert) {
        // Retourne l'animal dont l'id est passé en paramètre

    	Experts exp = new Experts();

        Cursor c = db.rawQuery("SELECT * FROM Experts WHERE idReponse = "+idExpert, null);
        if (c.moveToFirst()) {
        	exp.setIdExpert(c.getInt(c.getColumnIndex("idExpert")));
        	exp.setNomExpert(c.getString(c.getColumnIndex("nomExpert")));
        	exp.setNiveau(c.getInt(c.getColumnIndex("niveau")));
            c.close();
            }

        return exp;
        }

    public Cursor getExpert() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM Experts", null);
        }
}

