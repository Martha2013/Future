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

	Button mAdminButton;
	Button mAdminCancel;
	EditText mUsername;
	EditText mLibId;
	DBHelper myDb = new DBHelper(AdminLog.this);
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_log);
		
		mAdminButton = (Button)findViewById(R.id.buttonLogin);
		mAdminButton.setOnClickListener(this);
		
		mAdminCancel = (Button)findViewById(R.id.buttonCancel);
		mAdminCancel.setOnClickListener(this);
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
			
			SQLiteDatabase db = myDb.getReadableDatabase();
			
			String[] columns = {"Name","Work","LibraryId"};
			
			Cursor cursor = db.query(DBHelper.LIBRARY_TABLE_NAME, columns, null, null, null, null, null);
			if(cursor != null){
				System.out.println("database showing");
				//startManagingCursor(cursor);
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
	
	/*public void onDestroy(){
		super.onDestroy();
		myDb.close();
		finish();
	}
	*/

	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		Intent i = new Intent(AdminLog.this, Login.class);
		startActivity(i);
		finish();
	}
}
