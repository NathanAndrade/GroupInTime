package com.pandoapps.utils;

import com.pandoapps.model.User;

import android.app.Application;

public class App extends Application {

	private static User logedUser;
	//does it have internet connection? yes = true, no = false
	private static boolean hasInternet; 					

	public static boolean isLoged(){
		return logedUser!=null;
	}

	public static void setUser(User u) {
		App.logedUser = u;		
	}
	
	public static User getUser() {
		return App.logedUser;	
	}
	
	public static void setOnline(boolean status){
		App.hasInternet = status;
	}
	
	public static boolean isOnline(){
		return hasInternet;
	}
	
}