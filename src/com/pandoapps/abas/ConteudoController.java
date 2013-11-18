package com.pandoapps.abas;

import com.pandoapps.projetodefault.R;
import android.app.Activity;
import android.os.Bundle;

public class ConteudoController extends Activity {

	int dia ;
	int mes = 10;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conteudo_view);
		dia = getIntent().getIntExtra("i", 0);
	}
}
