package com.pandoapps.abas;


import com.pandoapps.projetodefault.R;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class AbasController extends TabActivity {

	private TabHost mTabHost;

	private OnClickListener tabListener = new OnClickListener() {

		public void onClick(View v) {
			updateFields();
		}
	};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.conteudo_tab_view);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

		updateFields();
	}

	private void updateFields() {

		Intent intent = null;
		int index = mTabHost.getCurrentTab();
		mTabHost.setCurrentTab(0);
		mTabHost.clearAllTabs();

		for (int i = 1; i <= 20; ++i) {
			intent = new Intent().setClass(AbasController.this, ConteudoController.class);
			intent.putExtra("i", i);
			View tabview = createTabView(mTabHost.getContext(), "" + i);
			tabview.setOnClickListener(tabListener);

			tabview.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_bg_selector));

			TabSpec setContent = mTabHost.newTabSpec("" + i).setIndicator(tabview)
					.setContent(intent);
			mTabHost.addTab(setContent);

		}
		mTabHost.setCurrentTab(index);
	}

	private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg_view, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

}