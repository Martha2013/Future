package com.example.viadatabase;

/**
 * 
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author martha
 *
 */
public class UserLog extends Activity {
Button Check;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_log);
		Check=(Button)findViewById(R.id.btnView);
		Check.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent next=new Intent(getApplicationContext(),LibraryData.class);
				startActivity(next);
				
			}
		});
	}

	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(UserLog.this, Login.class);
		startActivity(i);
		finish();
	}
	
	
	
}
