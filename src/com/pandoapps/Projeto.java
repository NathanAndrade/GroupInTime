package com.pandoapps;

import com.pandoapps.abas.AbasController;
import com.pandoapps.requester.DemoRequester;
import com.pandoapps.utils.MenuController;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.pandoapps.projetodefault.R;

public class Projeto extends MenuController {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.projeto);
	}
	
	/**
	 * BtnRemote OnClick
	 * 
	 * @param View
	 *            Activity Screen
	 */
	public void getRemoteUsers(View v){
		Intent i = new Intent(this, DemoRequester.class);
		this.startActivity(i);
		return;
	}
	
	public void openAbas(View v){
		Intent i = new Intent(this, AbasController.class);
		this.startActivity(i);
		return;
	}
}