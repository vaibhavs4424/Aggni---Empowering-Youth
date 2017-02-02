package com.example.aggnimodule1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.LoginActivity.LoginProcess;
import com.example.aggnimodule1.VO.UserDetails;
import com.example.aggnimodule1.beans.LoginResponseBean;
import com.example.aggnimodule1.utilities.JSONParser;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashActivity extends Activity {

	private ImageView mSPlashImage;
	private ProgressBar mProgress;
	private String mobileNumber;
	private String password;
private LoginResponseBean loginResponse;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_splash);
		mProgress = (ProgressBar) findViewById(R.id.splash_progress);
		mSPlashImage = (ImageView) findViewById(R.id.splash_image);
		ScaleAnimation fade_in = new ScaleAnimation(0f, 1f, 0f, 1f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		fade_in.setDuration(2000); // animation duration in milliseconds
		fade_in.setFillAfter(true); // If fillAfter is true, the transformation
									// that this animation performed will
									// persist when it is finished.
		mSPlashImage.startAnimation(fade_in);
		mProgress.getIndeterminateDrawable().setColorFilter(0xFF000000,
				android.graphics.PorterDuff.Mode.MULTIPLY);

		fade_in.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				SharedPreferences prefs = getSharedPreferences(
						"AGGNI_PREFERENCES", MODE_PRIVATE);
				mobileNumber=prefs.getString("MobileNumber", "MobileNumber");				
				password=prefs.getString("Password", "Password");
				
				
				if(mobileNumber.equals("MobileNumber") && password.equals("Password"))
				{				
				Intent intent = new Intent(SplashActivity.this,
						LoginActivity.class);
				finish();
				startActivity(intent);
				}
				else 
				{
					LoginProcess login = new LoginProcess();
					login.execute("Login");

					
				}
				
			}
		});

	}

	
	
	class LoginProcess extends AsyncTask<String, String, String> {

	
		
		@Override
		protected void onPreExecute() {
			

		}

		@Override
		protected String doInBackground(String... args) {
			JSONParser jsonParser = new JSONParser();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("MobileNumber", mobileNumber));
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

		
			if (result!=null && result.equals("success") && loginResponse != null
					&& loginResponse.getPhoneNumber() != null) {
				UserDetails.getSingletonInstance().setEmailId(loginResponse.getEmailId());
				UserDetails.getSingletonInstance().setFirstName(loginResponse.getFirstName());
				UserDetails.getSingletonInstance().setLastName(loginResponse.getLastName());
				UserDetails.getSingletonInstance().setPassword(loginResponse.getPassword());
				UserDetails.getSingletonInstance().setPhoneNumber(loginResponse.getPhoneNumber());
				UserDetails.getSingletonInstance().setRole(loginResponse.getRole());
				UserDetails.getSingletonInstance().setUserId(loginResponse.getUserId());
				
				finish();
				Intent intent = new Intent(SplashActivity.this,
						EventListActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}

			else

			{
				Intent intent = new Intent(SplashActivity.this,
						LoginActivity.class);
				finish();
				startActivity(intent);
			}
		}

	}

	
}
