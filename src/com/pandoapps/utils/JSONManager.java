package com.pandoapps.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONManager {

	//method to send json url to the webservice
	public static void sendJSONurl(String path) throws IOException {
		URL url = new URL(path);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream()));

		String line = null;

		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}

		in.close();
		urlConnection.disconnect();
	}
	
}
