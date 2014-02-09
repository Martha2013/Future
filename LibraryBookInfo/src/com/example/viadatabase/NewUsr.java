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

	private Button Register;
	private Button Cancel;
	private EditText Name;
	private EditText LibraryId;
	private EditText Work;
	private DBHelper Library = new DBHelper(NewUsr.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_usr);
		
		
		Register = (Button)findViewById(R.id.buttonRegister);
		Register.setOnClickListener(this);
		
		Cancel = (Button)findViewById(R.id.buttonCancel);
		Cancel.setOnClickListener(this);
	}
	
	public void onClick(View v) {
	
		switch(v.getId()){
			
			case R.id.buttonCancel:
				Intent i = new Intent(NewUsr.this,Login.class);
				startActivity(i);
				finish();
				break;
				
			case R.id.buttonRegister:
				
				Name = (EditText)findViewById(R.id.editName);
				LibraryId = (EditText)findViewById(R.id.editLibraryId);
				Work = (EditText)findViewById(R.id.editWork);
				
				String uname = Name.getText().toString();
				String LId = LibraryId.getText().toString();
				String work = Work.getText().toString();
				boolean invalid = false;
				
				if(uname.equals("")){
					invalid = true;
					Toast.makeText(getApplicationContext(), "Username Missing", Toast.LENGTH_SHORT).show();
				}else if(LId.equals("")){
					invalid = true;
					Toast.makeText(getApplicationContext(), "LibraryId Missing", Toast.LENGTH_SHORT).show();
				}else if(work.equals("")){
					invalid = true;
					Toast.makeText(getApplicationContext(), "Work Missing", Toast.LENGTH_SHORT).show();
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
		Library.close();
	}
	
	public void addEntry(String uname, String LId, String work){
		
		SQLiteDatabase db = Library.getWritableDatabase();
		
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
