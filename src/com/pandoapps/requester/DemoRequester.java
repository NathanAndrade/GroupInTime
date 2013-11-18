package com.pandoapps.requester;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pandoapps.controller.CtrUser;
import com.pandoapps.model.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


public class DemoRequester extends Activity{
	
Context context;
final String JSON_URL =  "http://www.pandoapps.com.br/users.json";
final String JSON_ARRAYNAME =  "itens";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = this;
		sync();
	}
	
	/**
	 * Instanciate a new DownloadJsonAsyncRequester with the JSON URL
	 */
	private void sync() {
		new DownloadJsonAsyncTask().execute(JSON_URL);
	}

	class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<User>> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = ProgressDialog.show(DemoRequester.this, "Aguarde",
					"Baixando JSON, Por Favor Aguarde...");
		}

		@Override
        protected List<User> doInBackground(String... params) {
			String urlString = params[0];

			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(urlString);

			try {
				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					String json = toString(instream);
					instream.close();
					return getList(json);
				}
				
			} catch (Exception e) {
				Log.e("CallRequester", "Falha ao acessar Web service", e);
			}
			
			return null;
		}

		/**
		 * Extracts from the string all the itens. Requires the configuration of JSON_ARRAYNAME
		 * 
		 * @param String
		 *            JSON String
		 */
		private List<User> getList(String jsonString) {
			List<User> list = new ArrayList<User>();

			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				JSONArray jResult = jsonObject.getJSONArray(JSON_ARRAYNAME);
				
				for (int i = 0; i < jResult.length(); i++) {
					JSONObject f = jResult.getJSONObject(i);
					User item = new User();
					item.setId(f.getInt("id"));
					item.setName(f.getString("name"));
					item.setLogin(f.getString("login"));
					item.setPass(f.getString("password"));
					// adding in the list
					list.add(item);
				}
				CtrUser.cleanTable(context);
				for (User item : list) {
					// adding in the localDatabase
					CtrUser.add(item, context);
				}
			} catch (JSONException e) {
				Log.e("CallRequester", "Erro no parsing do JSON", e);
			}
			
			return list;
		}

		@Override
		protected void onPostExecute(List<User> result) {
			super.onPostExecute(result);
			dialog.dismiss();
			finish();
		}

		/**
		 * Transform the InputStream in a String
		 * 
		 * @param InputStream
		 * @return String
		 */
		private String toString(InputStream is) throws IOException {
			byte[] bytes = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int lidos;
			while ((lidos = is.read(bytes)) > 0) {
				baos.write(bytes, 0, lidos);
			}
			return new String(baos.toByteArray());
		}
	
	}

}
