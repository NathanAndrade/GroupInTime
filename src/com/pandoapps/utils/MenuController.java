package com.pandoapps.utils;


import com.pandoapps.Pando;

import com.pandoapps.projetodefault.R;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MenuController extends Activity{
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pandoapps, menu);
		
		/*
		int panda = getResources().getIdentifier("@drawable/pando_ico", null, getPackageName());
		ImageView iv = (ImageView) findViewById(R.id.pandoMenu);
		Drawable res = getResources().getDrawable(panda);
		iv.setImageDrawable(res);
		*/
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	      case R.id.pandoMenu:
	         showAboutScreen();
	         return true;
	      default:
	         return super.onOptionsItemSelected(item);
	   }
	}
	
	private void showAboutScreen() {
		Intent i = new Intent(this, Pando.class);
		this.startActivity(i);
		return;
	}
}
