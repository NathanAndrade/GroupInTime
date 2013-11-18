package com.pandoapps;

import com.pandoapps.controller.CtrUser;
import com.pandoapps.model.User;
import com.pandoapps.utils.App;
import com.pandoapps.utils.MenuController;
import com.pandoapps.utils.MessageManager;

import com.pandoapps.projetodefault.R;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class Login extends MenuController {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

	}

    @Override
    protected void onResume(){
        super.onResume();
        App.setUser(null);
    }
	
    /**
	 * BtnLogin OnClick
	 * 
	 * @param View
	 *            Activity Screen
	 */
	public void checkLogin(View v){
		String username = ((EditText) findViewById(R.id.edtLogin)).getText().toString();
		String pass = ((EditText) findViewById(R.id.edtPass)).getText().toString();
		
		User u = CtrUser.getUserByLoginPass(this, username, pass);
		
		if(u!=null){
			App.setUser(u);
			Intent i = new Intent(this,Projeto.class);
			Login.this.startActivity(i);
		}else{
			MessageManager.showErrorMessage(this, getString(R.string.Erro_UserNotFoundTitle), getString(R.string.Erro_UserNotFoundMessage));
		}
		return;
	}
}
