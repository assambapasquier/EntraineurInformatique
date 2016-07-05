package ManagerDAO;

import metier.Propositions;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PropositionManager {
	public static final String CREATE_TABLE_BONNEREPONSES = "CREATE TABLE Propositions("+
	"idProposition INTEGER PRIMARY KEY AUTOINCREMENT,"+
	"libelleProposition TEXT,"+
	"imageProposition TEXT"+
");";
	
	private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    
    public PropositionManager(Context context)
    {
    	maBaseSQLite = new MySQLite(context);
    }

	public void open()
	    {
	    	//on ouvre la table en lecture/�criture
	    	db = maBaseSQLite.getWritableDatabase();
	    }
	
	public void close()
	    {
	    	//on ferme l'acc�s � la BDD
	    	db.close();
	    }

	public long addProposition(Propositions p) {
        // Ajout d'un enregistrement dans la table

	        ContentValues values = new ContentValues();
	        values.put("idProposition", p.getIdProposition());
	        values.put("libelleProposition", p.getLibelleProposition());
	        values.put("imageProposition", p.getImageProposition());
	        
	        // insert() retourne l'id du nouvel enregistrement ins�r�, ou -1 en cas d'erreur
	        return db.insert("Propositions",null,values);
        }

    public int modProposition(Propositions p) {
	        // modification d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affect�es par la requ�te
	
    	ContentValues values = new ContentValues();
    	values.put("idProposition", p.getIdProposition());
        values.put("libelleProposition", p.getLibelleProposition());
        values.put("imageProposition", p.getImageProposition());
	        String where = "idProposition = ?";
	        String[] whereArgs = {p.getIdProposition()+""};
	
	        return db.update("Propositions", values, where, whereArgs);
        }

    public int supProposition(Propositions p) {
	        // suppression d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affect�es par la clause WHERE, 0 sinon
	
	        String where = "idProposition = ?";
	        String[] whereArgs = {p.getIdProposition()+""};
	
	        return db.delete("Propositions", where, whereArgs);
        }

    public Propositions getProposition(int idProposition) {
        // Retourne l'animal dont l'id est pass� en param�tre

    	Propositions p = new Propositions();

        Cursor c = db.rawQuery("SELECT * FROM Propositions WHERE idProposition = "+idProposition, null);
        if (c.moveToFirst()) {
        	p.setIdProposition(c.getInt(c.getColumnIndex("idProposition")));
        	p.setLibelleProposition(c.getString(c.getColumnIndex("libelleProposition")));
        	p.setImageProposition(c.getString(c.getColumnIndex("imageProposition")));
            c.close();
            }

        return p;
        }

    public Cursor getProposition() {
        // s�lection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM Propositions", null);
        }
}
