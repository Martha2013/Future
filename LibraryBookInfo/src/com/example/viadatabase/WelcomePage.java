package com.example.viadatabase;

/**
 * 
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author martha
 *
 */
public class WelcomePage extends Activity {

	protected boolean _active = true;

	// time to display the splash screen in ms
	protected int _splashTime = 5000; 

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_page);

		// thread for displaying the SplashScreen
		Thread splashThread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while(_active && (waited < _splashTime)) {
						sleep(100);
						if(_active) {
							waited += 100;
						}
					}
				} catch(InterruptedException e) {
					// do nothing
				} finally {
					finish();
					startActivity(new Intent(WelcomePage.this, Login.class));
					//stop();
				}
			}
		};
		splashThread.start();
	}

	public void onDestroy(){
		super.onDestroy();
	}

}
