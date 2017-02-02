package com.example.aggnimodule1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.VO.UserDetails;
import com.example.aggnimodule1.beans.LoginResponseBean;
import com.example.aggnimodule1.utilities.JSONParser;
import com.google.gson.Gson;

public class LoginActivity extends Activity {

	private Button mSignUp;
	private Button mSignIn;
	private EditText mMobile;
	private EditText mPass;
	LoginResponseBean loginResponse;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_login);
		mSignUp = (Button) findViewById(R.id.signup);
		mSignIn = (Button) findViewById(R.id.login);
		mMobile = (EditText) findViewById(R.id.email_id);
		mPass = (EditText) findViewById(R.id.userpassword);
		mProgressDialog = new ProgressDialog(this);
		mSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this,
						SignUpActivity.class);
				startActivity(intent);
			}
		});
		mSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginProcess login = new LoginProcess();
				login.execute("Login");

			}
		});
	}

	class LoginProcess extends AsyncTask<String, String, String> {

		String username = mMobile.getText().toString();
		String password = mPass.getText().toString();

		
		@Override
		protected void onPreExecute() {
			mProgressDialog.setMessage("Authenticating, Please wait");
			mProgressDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			JSONParser jsonParser = new JSONParser();

			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("MobileNumber", username));
			params.add(new BasicNameValuePair("Password", password));

			JSONObject json = jsonParser
					.makeHttpRequest(
							"http://aggniapp.aggni.org/aggniappscripts/getLoginDetailsTest.php",
							"POST", params);

			// Log.i("Vaibhavs",json.toString());
			Gson gson = new Gson();

			try {
				loginResponse = gson.fromJson(json.toString(),
						LoginResponseBean.class);
				return "success";

			} catch (Exception e) {
				Log.i("Vaibhavs", "catch");
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(String result) {

			mProgressDialog.dismiss();
			if (result!=null && result.equals("success") && loginResponse != null
					&& loginResponse.getPhoneNumber() != null) {
				
				UserDetails.getSingletonInstance().setEmailId(loginResponse.getEmailId());
				UserDetails.getSingletonInstance().setFirstName(loginResponse.getFirstName());
				UserDetails.getSingletonInstance().setLastName(loginResponse.getLastName());
				UserDetails.getSingletonInstance().setPassword(loginResponse.getPassword());
				UserDetails.getSingletonInstance().setPhoneNumber(loginResponse.getPhoneNumber());
				UserDetails.getSingletonInstance().setRole(loginResponse.getRole());
				UserDetails.getSingletonInstance().setUserId(loginResponse.getUserId());
				
				SharedPreferences.Editor editor = getSharedPreferences("AGGNI_PREFERENCES", MODE_PRIVATE).edit();
				 editor.putString("MobileNumber", username);
				 editor.putString("Password", password);
				 editor.commit();
				 
				finish();
				Intent intent = new Intent(LoginActivity.this,
						EventListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}

			else

			{
				Toast.makeText(LoginActivity.this, "Login Failed, Try Again!",
						Toast.LENGTH_LONG).show();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
