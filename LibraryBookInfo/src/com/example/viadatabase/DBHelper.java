package com.example.viadatabase;

/**
 * 
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author martha
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "Library.db";
	private static final int DATABASE_VERSION = 1;
    public static final String LIBRARY_TABLE_NAME = "login";
	private static final String LIBRARY_TABLE_CREATE =
	                "CREATE TABLE " + LIBRARY_TABLE_NAME + "(" +
	                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
	                "Name TEXT NOT NULL, LibraryID TEXT NOT NULL, Work TEXT NOT NULL);";
	private static final String LIBRARY_DB_ADMIN = "INSERT INTO "+LIBRARY_TABLE_NAME+"values(1, admin, LibAdmin, Creator);";
	
	
	public DBHelper(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		System.out.println("In constructor");
		
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		try{
			//Create Database
			db.execSQL(LIBRARY_TABLE_CREATE);
			
			//create admin account
			db.execSQL(LIBRARY_DB_ADMIN);
			System.out.println("In onCreate");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		

	}

}
