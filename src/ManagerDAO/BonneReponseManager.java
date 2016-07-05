package ManagerDAO;

import metier.BonneReponses;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BonneReponseManager {
	public static final String CREATE_TABLE_BONNEREPONSES = "CREATE TABLE BonneReponses("+
	"idReponse INTEGER PRIMARY KEY AUTOINCREMENT,"+
	"libelleReponse TEXT,"+
	"imageReponse TEXT"+
	");";
	
	private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    
    public BonneReponseManager(Context context)
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

	public long addBonneReponse(BonneReponses br) {
        // Ajout d'un enregistrement dans la table

	        ContentValues values = new ContentValues();
	        values.put("idReponse", br.getIdReponse());
	        values.put("libelleReponse", br.getLibelleReponse());
	        values.put("imageReponse", br.getImageReponse());
	        
	        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
	        return db.insert("BonneReponses",null,values);
        }

    public int modBonneReponse(BonneReponses br) {
	        // modification d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la requête
	
    	ContentValues values = new ContentValues();
    	values.put("idReponse", br.getIdReponse());
        values.put("libelleReponse", br.getLibelleReponse());
        values.put("imageReponse", br.getImageReponse());
	        String where = "idReponse = ?";
	        String[] whereArgs = {br.getIdReponse()+""};
	
	        return db.update("BonneReponses", values, where, whereArgs);
        }

    public int supBonneReponse(BonneReponses br) {
	        // suppression d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon
	
	        String where = "idReponse = ?";
	        String[] whereArgs = {br.getIdReponse()+""};
	
	        return db.delete("BonneReponses", where, whereArgs);
        }

    public BonneReponses getBonneReponse(int idReponse) {
        // Retourne l'animal dont l'id est passé en paramètre

    	BonneReponses br = new BonneReponses();

        Cursor c = db.rawQuery("SELECT * FROM BonneReponses WHERE idReponse = "+idReponse, null);
        if (c.moveToFirst()) {
        	br.setIdReponse(c.getInt(c.getColumnIndex("idReponse")));
        	br.setLibelleReponse(c.getString(c.getColumnIndex("libelleReponse")));
        	br.setImageReponse(c.getString(c.getColumnIndex("imageReponse")));
            c.close();
            }

        return br;
        }

    public Cursor getBonneReponse() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM BonneReponses", null);
        }
}
