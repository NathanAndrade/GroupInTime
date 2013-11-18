package com.pandoapps;

import com.pandoapps.projetodefault.R;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends Activity implements Runnable {
	
	Animation animShowLogo;
	ImageView pandoLogo;
	private final int TIME_DELAY = 1000;
	
	@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        
        loadViews();
        loadAnimations();
       
        Handler handler = new Handler();
        handler.postDelayed(this, TIME_DELAY);
        
        pandoLogo.startAnimation(animShowLogo);
       
    }
	
	@Override
    public void run() {
            startActivity(new Intent(this, Login.class));
            finish();
    }
	
	/**
	 * Load all the views in the activity
	 */
	private void loadViews(){
		pandoLogo = (ImageView)findViewById(R.id.logoPando);
	}

	/**
	 * Load all the animations in the activity
	 */
	public void loadAnimations(){
		animShowLogo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.showlogo);
	}
}
