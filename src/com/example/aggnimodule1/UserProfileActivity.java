package com.example.aggnimodule1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.VO.EventDetailVO;
import com.example.aggnimodule1.VO.EventList;
import com.example.aggnimodule1.VO.RegisteredEventsList;
import com.example.aggnimodule1.VO.UserDetails;
import com.example.aggnimodule1.beans.EventsListResponse;
import com.example.aggnimodule1.beans.EventsListResponse.Event;
import com.example.aggnimodule1.utilities.JSONParser;
import com.google.gson.Gson;

public class UserProfileActivity extends Activity {

	private TextView mFullName, mEmailId, mMobile;
	private ImageView mChangepassword;
	private ListView mEventListView;
	private ProgressDialog mProgressDialog;
	private JSONArray mRegisteredEventListResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_profile_details);
		mFullName = (TextView) findViewById(R.id.full_name);
		mEmailId = (TextView) findViewById(R.id.email);
		mMobile = (TextView) findViewById(R.id.mobile_number);
		mChangepassword = (ImageView) findViewById(R.id.change_password);
		mEventListView = (ListView) findViewById(R.id.event_list);
		mFullName.setText(UserDetails.getSingletonInstance().getFirstName()
				+ " " + UserDetails.getSingletonInstance().getLastName());
		mEmailId.setText(UserDetails.getSingletonInstance().getEmailId());
		mMobile.setText(UserDetails.getSingletonInstance().getPhoneNumber());
		mProgressDialog = new ProgressDialog(this);
		FetchRegisteredEvents events =new FetchRegisteredEvents();
		events.execute("Execute");
		mChangepassword.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePasswordDialog();
			}
		});
		
	}

	public void changePasswordDialog() {
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.change_password_dialog);
		dialog.setTitle("Change Password");
		dialog.show();

	}

	class FetchRegisteredEvents extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			mProgressDialog.setMessage("Fetching Registered Events...");
			mProgressDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			JSONParser jsonParser = new JSONParser();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("UserId", String
					.valueOf(UserDetails.getSingletonInstance().getUserId())));

			JSONObject json = jsonParser
					.makeHttpRequest(
							"http://aggniapp.aggni.org/aggniappscripts/RegisteredEventIdList.php",
							"POST", params);

			// Log.i("Vaibhavs",json.toString());
			Gson gson = new Gson();

			try {
				mRegisteredEventListResponse = json.getJSONArray("Events");
				
				return "success";

			} catch (Exception e) {
				Log.i("Vaibhavs", "catch");
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(String result) {

			if (result != null && result.equals("success")
					&& mRegisteredEventListResponse != null
					) {

				ArrayList<String> registeredEvents =new ArrayList<String>();
				mProgressDialog.dismiss();
				for(int i = 0;i<mRegisteredEventListResponse.length();i++)
				{
					try {
					registeredEvents.add(mRegisteredEventListResponse.getString(i));
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				RegisteredEventsList.getSingletonInstance().setEventId(registeredEvents);
				EventListAdapter adapter = new EventListAdapter(
						UserProfileActivity.this,"RegisteredEvents");
				mEventListView.setAdapter(adapter);	
			}

			else

			{
				mProgressDialog.dismiss();
				Toast.makeText(UserProfileActivity.this,
						"Fetching Events Failed, Try Again!", Toast.LENGTH_LONG)
						.show();
			}
		
	
		}

	}

}
