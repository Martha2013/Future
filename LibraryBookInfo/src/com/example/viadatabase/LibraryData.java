package com.example.viadatabase;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LibraryData extends ListActivity {
	String[] splittedresult;
	InputStream inputStream;
	HttpClient httpclient;
	HttpPost httppost;
    List<NameValuePair> nameValuePairs;
    StringBuffer sb;
    String line, result, number;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Toast.makeText(
						getApplicationContext(),
						"you clicked " + ((TextView) view).getText()
								+ " at position: " + (position + 1),
						Toast.LENGTH_SHORT).show();
				if (position == 0) {
					Toast.makeText(
							getApplicationContext(),
							"You selected "
									+ parent.getItemAtPosition(position) + " "
									+ position, Toast.LENGTH_SHORT).show();
				} else if (position == 1) {
					// call another activity

				}

			}
		});
	

		
		
		try {
	           httpclient = new DefaultHttpClient();
	           
	           httppost = new HttpPost("http://10.0.2.2/LibProject/retrieve.php");
	          
	             
	              // Execute HTTP Post Request
	             HttpResponse response = httpclient.execute(httppost);
	              
	              inputStream = response.getEntity().getContent();
	              
	              BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream), 4096);
	              String line;
	              StringBuilder sb =  new StringBuilder();
	              
	              while ((line = rd.readLine()) != null) {
	              		sb.append(line);
	              }
	              rd.close();
	              result = sb.toString();
	              
	              inputStream.close();
	              Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
	             splittedresult = result.split("#");

	              ArrayAdapter<String> aa= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, splittedresult);// provide a particular design for the drop-down lines
	              setListAdapter(aa);
	   	
	   			
	       }
	              catch (Exception e)
	              {
	                  Toast.makeText(getApplicationContext(), "Error inside set:"+e.toString(), Toast.LENGTH_LONG).show();
	              }
		
	}


}
