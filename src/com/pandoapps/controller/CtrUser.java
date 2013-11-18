package com.pandoapps.controller;

import com.pandoapps.database.DBConn;
import com.pandoapps.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CtrUser {

	private static final String table = "user";
	private static final String[] fields = new String[] { "_id","name", "login", "password"};
	
	/**
	 * Add an user in the local database
	 * 
	 * @param User
	 *            This user will be added in the local Database
	 * @param Context
	 *            Which activity is trying to insert the user
	 *            
	 */
	public static void add(User u, Context context) {
		DBConn conn = new DBConn(context);
	    SQLiteDatabase db = conn.openDatabase();
	    ContentValues values = new ContentValues();
	    values.put(fields[0], u.getId()); 
	    values.put(fields[1], u.getName()); 
	    values.put(fields[2], u.getLogin());
	    values.put(fields[3], u.getPass());
	    db.insert(table, null, values);
	    db.close();
	}
	
	
	/**
	 * Extracts an user from a cursor
	 * 
	 * @param Cursor
	 *            Cursor in the current position
	 * @param Context
	 *            Which activity is trying to insert the user
	 * @return User
	 * 		  	  The current user
	 */
	private static User getUser(Cursor cursor){
		User item = new User();
		item.setId(cursor.getInt(0));
		item.setName(cursor.getString(1));
		item.setLogin(cursor.getString(2));
		item.setPass(cursor.getString(3));
    	return item;
	}
	
	/**
	 * Validate the username and password
	 * 
	 * @param Context
	 *            Which activity is trying to validade the user
	 * @param username
	 *            tested username
	 * @param password
	 *            tested password
	 * @return User
	 * 			  return all the information about the user
	 * 			  return null if the user is invalid
	 *            
	 */
	public static User getUserByLoginPass(Context context, String username, String password){
		DBConn conn = new DBConn(context);
	    SQLiteDatabase db = conn.openDatabase();
	    User u = null;
	 
	    Cursor cursor = db.query(
	    		table, 
	    		fields, 
	    		"login = ? and password = ?",
	            new String[] { username , password }, 
	            null, null, null, null);
	    if (cursor != null){
	    	cursor.moveToFirst();
		    if(cursor.getCount()>0){
		    	u = getUser(cursor);
		    }
	    }
	    cursor.close();
	    db.close();
	    return u;
	}
	
	/**
	 * Get all the information from an user based on it's ID
	 * 
	 * @param Context
	 *            Which activity is asking for the user
	 * @param int
	 *            idUser
	 * @return User
	 * 			  return all the information about the user
	 * 			  return null if the user is invalid
	 *            
	 */
	public static User getUserById(Context context, int idUser) {
		DBConn conn = new DBConn(context);
	    SQLiteDatabase db = conn.openDatabase();
	    User u = null;
	 
	    Cursor cursor = db.query(
	    		table, 
	    		fields, 
	    		"_id = ?",
	            new String[] { String.valueOf(idUser)}, 
	            null, null, null, null);
	    if (cursor != null){
	    	cursor.moveToFirst();
		    if(cursor.getCount()>0){
		    	u = getUser(cursor);
		    }
	    } 
	    cursor.close();
	    db.close();
	    return u;
	}

	/**
	 * Delete all the data from the UserTable
	 */
	public static void cleanTable(Context context) {
		DBConn conn = new DBConn(context);
	    SQLiteDatabase db = conn.openDatabase();
	    db.delete(table, null, null);
	    db.close();
	}
	
	
}
