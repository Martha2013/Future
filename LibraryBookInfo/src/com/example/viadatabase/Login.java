package com.example.viadatabase;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {
    
	Button Login;
	Button NewUser;
	Button ShowAll;
	EditText Name;
	EditText mLibId;
	DBHelper Library = null;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        NewUser = (Button)findViewById(R.id.buttonNewUser);
        NewUser.setOnClickListener(this);
        
        Login = (Button)findViewById(R.id.buttonLogin);
        Login.setOnClickListener(this);
        
        ShowAll = (Button)findViewById(R.id.buttonShowAll);
        ShowAll.setOnClickListener(this);
    }
    
	public void onClick(View v) {
	
		switch(v.getId()){
		
		case R.id.buttonLogin:
			Name = (EditText)findViewById(R.id.editName);
			mLibId = (EditText)findViewById(R.id.editId);
			
			String uname = Name.getText().toString();
			String LibId = mLibId.getText().toString();
			
			if(uname.equals("") || uname == null){
				Toast.makeText(getApplicationContext(), "Username Empty", Toast.LENGTH_SHORT).show();
			}else if(LibId.equals("") || LibId == null){
				Toast.makeText(getApplicationContext(), "LibraryId Empty", Toast.LENGTH_SHORT).show();
			}else{
				boolean validLogin = validateLogin(uname, LibId, Login.this);
				if(validLogin){
					System.out.println("In Valid");
					Intent i = new Intent(Login.this, UserLog.class);
					startActivity(i);
					finish();
				}
			}
			break;
			
		case R.id.buttonNewUser:
			Intent i = new Intent(Login.this, NewUsr.class);
			startActivity(i);
			finish();
			break;
			
		case R.id.buttonShowAll:
			Intent i_admin = new Intent(Login.this, AdminLog.class);
			startActivity(i_admin);
			finish();
			break;
		}
	}

	public boolean validateLogin(String uname, String LibId, Context context) {
		
		Library = new DBHelper(context);
		SQLiteDatabase db = Library.getReadableDatabase();
		//SELECT
		String[] columns = {"Name"};
		
		//WHERE clause
		String selection = "Name=? AND LibraryID=?";
		
		//WHERE clause arguments
		String[] selectionArgs = {uname,LibId};
		
		Cursor cursor = null;
		try{
		//SELECT Name FROM members WHERE username=uname AND LibraryId=LibId
		cursor = db.query(DBHelper.LIBRARY_TABLE_NAME, columns, selection, selectionArgs, null, null, null);
		
		//startManagingCursor(cursor);
		}catch(Exception e){
			e.printStackTrace();
		}
		int numberOfRows = cursor.getCount();
		
		if(numberOfRows <= 0){
		
			Toast.makeText(getApplicationContext(), "Login Failed.. + \n + Try Again", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		
		return true;
	}
	
	
}