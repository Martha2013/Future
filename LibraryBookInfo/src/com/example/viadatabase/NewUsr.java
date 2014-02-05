package com.example.viadatabase;

/**
 * 
 */


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author martha
 *
 */
public class NewUsr extends Activity implements OnClickListener {

	private Button mRegister;
	private Button mCancel;
	private EditText mName;
	private EditText mLibraryId;
	private EditText mWork;
	private DBHelper myDb = new DBHelper(NewUsr.this);
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_usr);
		
		
		mRegister = (Button)findViewById(R.id.buttonRegister);
		mRegister.setOnClickListener(this);
		
		mCancel = (Button)findViewById(R.id.buttonCancel);
		mCancel.setOnClickListener(this);
	}
	
	public void onClick(View v) {
	
		switch(v.getId()){
			
			case R.id.buttonCancel:
				Intent i = new Intent(NewUsr.this,Login.class);
				startActivity(i);
				finish();
				break;
				
			case R.id.buttonRegister:
				
				mName = (EditText)findViewById(R.id.editName);
				mLibraryId = (EditText)findViewById(R.id.editLibraryId);
				mWork = (EditText)findViewById(R.id.editWork);
				
				String uname = mName.getText().toString();
				String LId = mLibraryId.getText().toString();
				String work = mWork.getText().toString();
				boolean invalid = false;
				
				if(uname.equals("")){
					invalid = true;
					Toast.makeText(getApplicationContext(), "Username Missing", Toast.LENGTH_SHORT).show();
				}else if(LId.equals("")){
					invalid = true;
					Toast.makeText(getApplicationContext(), "Password Missing", Toast.LENGTH_SHORT).show();
				}else if(work.equals("")){
					invalid = true;
					Toast.makeText(getApplicationContext(), "Email ID Missing", Toast.LENGTH_SHORT).show();
				}
				
				if(invalid == false){
					addEntry(uname, LId, work);
					Intent i_register = new Intent(NewUsr.this, Login.class);
					startActivity(i_register);
					finish();
				}
				
				break;
		}
	}
	
	public void onDestroy(){
		super.onDestroy();
		myDb.close();
	}
	
	public void addEntry(String uname, String LId, String work){
		
		SQLiteDatabase db = myDb.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("Name", uname);
		values.put("LibraryId", LId);
		values.put("Work", work);
		
		try{
			db.insert(DBHelper.LIBRARY_TABLE_NAME, null, values);
			Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
