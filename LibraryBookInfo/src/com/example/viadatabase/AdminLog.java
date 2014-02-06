package com.example.viadatabase;

/**
 * 
 */


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author martha
 *
 */
public class AdminLog extends Activity implements OnClickListener {

	Button AdminButton;
	Button AdminCancel;
	EditText Username;
	EditText mLibId;
	DBHelper Library = new DBHelper(AdminLog.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_log);
		
		AdminButton = (Button)findViewById(R.id.buttonLogin);
		AdminButton.setOnClickListener(this);
		
		AdminCancel = (Button)findViewById(R.id.buttonCancel);
		AdminCancel.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.buttonLogin:
			mLibId = (EditText)findViewById(R.id.editLibId);
			
			String LId = mLibId.getText().toString();
			if(LId.equals("") || LId == null){
				
				Toast.makeText(getApplicationContext(), "Password Missing", Toast.LENGTH_SHORT).show();
				
			}else{
				
				if(LId.equals("LibAdmin")){
					Toast.makeText(getApplicationContext(), "Retrieving Data", Toast.LENGTH_SHORT).show();
					retrieveEntries();
				}else{
					Toast.makeText(getApplicationContext(), "Admin Login Failed", Toast.LENGTH_SHORT).show();
				}
			}
			
			break;
			
		case R.id.buttonCancel:
			Intent i = new Intent(AdminLog.this, Login.class);
			startActivity(i);
			finish();
			break;
		}
	}
	
	public void retrieveEntries(){
		try{
			
			SQLiteDatabase db = Library.getReadableDatabase();
			
			String[] columns = {"Name","Work","LibraryId"};
			
			Cursor cursor = db.query(DBHelper.LIBRARY_TABLE_NAME, columns, null, null, null, null, null);
			if(cursor != null){
				System.out.println("database showing");
				
				showDatabase(cursor);
			}
			System.out.println("Cursor NuLL");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void showDatabase(Cursor cursor) {
		StringBuilder ret = new StringBuilder("Database Values\n\n");
	    ret.append("\nName \t Work  \t LibraryID\n");
		while (cursor.moveToNext()) {
	      
	      String uname = cursor.getString(0);
	      
	      String work = cursor.getString(1);
	      String libid=cursor.getString(2);
	      ret.append(uname+"\t\t\t"+work + "\t\t\t" + libid +"\n");
	    }
		
		TextView result = new TextView(this);
		result.setText(ret);
		setContentView(result);
	}
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		Intent i = new Intent(AdminLog.this, Login.class);
		startActivity(i);
		finish();
	}
}
