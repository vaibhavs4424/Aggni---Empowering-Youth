package com.example.aggnimodule1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.VO.EventDetailVO;
import com.example.aggnimodule1.VO.EventList;
import com.example.aggnimodule1.VO.RegisteredEventsList;
import com.example.aggnimodule1.VO.UserDetails;
import com.example.aggnimodule1.beans.EventsListResponse;
import com.example.aggnimodule1.beans.LoginResponseBean;
import com.example.aggnimodule1.beans.EventsListResponse.Event;
import com.example.aggnimodule1.utilities.JSONParser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;

/**
 * Created by Joydeep on 18-03-2015.
 * 
 */
public class EventListActivity extends Activity {

	// Resgistration Id from GCM
	private static final String PREF_GCM_REG_ID = "PREF_GCM_REG_ID";
	private SharedPreferences prefs;
	// Your project number and web server url. Please change below.
	private static final String GCM_SENDER_ID = "193943141236";
	private static final String WEB_SERVER_URL = "YOUR_WER_SERVER_URL";

	GoogleCloudMessaging gcm;
	Button registerBtn;
	TextView regIdView;

	private static final int ACTION_PLAY_SERVICES_DIALOG = 100;
	protected static final int MSG_REGISTER_WITH_GCM = 101;
	protected static final int MSG_REGISTER_WEB_SERVER = 102;
	protected static final int MSG_REGISTER_WEB_SERVER_SUCCESS = 103;
	protected static final int MSG_REGISTER_WEB_SERVER_FAILURE = 104;
	private String gcmRegId;

	private ListView mEventListView;
	private ProgressDialog mProgressDialog;
	private EventsListResponse mEventListResponse;
	private TextView mUserName;
	private ImageView mLogoutButton;
	private ImageView mCreateEvent;
	private LinearLayout mProfileLayout;
	private JSONArray mRegisteredEventListResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_event_list);
		mEventListView = (ListView) findViewById(R.id.event_list);
		mUserName = (TextView) findViewById(R.id.user_name);
		mLogoutButton = (ImageView) findViewById(R.id.logout_button);

		mProfileLayout = (LinearLayout) findViewById(R.id.profile_layout);
		mCreateEvent = (ImageView) findViewById(R.id.create_event_button);

		if (isGoogelPlayInstalled()) {
			gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
			handler.sendEmptyMessage(MSG_REGISTER_WITH_GCM);
		}

		if (UserDetails.getSingletonInstance().getRole().equals("admin")) {
			mCreateEvent.setVisibility(View.VISIBLE);

		} else {

			mCreateEvent.setVisibility(View.GONE);
		}

		mCreateEvent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(EventListActivity.this,
						CreateEventActivity.class);
				startActivityForResult(intent, 1);
			}
		});

		mProfileLayout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(EventListActivity.this,
						UserProfileActivity.class);
				startActivityForResult(intent, 1);
			}
		});
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("Fetching all the Events...");
		mProgressDialog.show();

		mLogoutButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				SharedPreferences.Editor editor = getSharedPreferences(
						"AGGNI_PREFERENCES", MODE_PRIVATE).edit();
				editor.clear().apply();
				finish();
				Intent intent = new Intent(EventListActivity.this,
						LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		mUserName.setText(UserDetails.getSingletonInstance().getFirstName()
				+ " " + UserDetails.getSingletonInstance().getLastName());
		FetchEvents getEvent = new FetchEvents();
		getEvent.execute("Events");

		mEventListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Bundle bundle = new Bundle();
				bundle.putInt("EventPosition", position);

				if (UserDetails.getSingletonInstance().getRole()
						.equals("admin")) {
					Intent intent = new Intent(EventListActivity.this,
							AdminEventDetails.class);
					intent.putExtras(bundle);
					startActivityForResult(intent, 1);
				} else {
					Intent intent = new Intent(EventListActivity.this,
							EventDetailActivity.class);
					intent.putExtras(bundle);
					startActivityForResult(intent, 1);
				}

			}
		});

	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_REGISTER_WITH_GCM:
				new GCMRegistrationTask().execute();
				break;

			}
		};
	};

	private class GCMRegistrationTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if (gcm == null && isGoogelPlayInstalled()) {
				gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
			}
			try {
				gcmRegId = gcm.register(GCM_SENDER_ID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return gcmRegId;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
			
				if(gcmRegId!=null)
				{
					SaveRegistrationId task = new SaveRegistrationId();
					task.execute("Execute");
				}
				

			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1:
				Toast.makeText(this, "back", Toast.LENGTH_LONG).show();
				break;

			default:
				break;
			}

		}
	}

	private boolean isGoogelPlayInstalled() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(EventListActivity.this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode,
						EventListActivity.this, ACTION_PLAY_SERVICES_DIALOG)
						.show();
			} else {
				Toast.makeText(getApplicationContext(),
						"Google Play Service is not installed",
						Toast.LENGTH_SHORT).show();
				finish();
			}
			return false;
		}
		return true;

	}

	class FetchEvents extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			mProgressDialog.setMessage("Authenticating, Please wait");
			mProgressDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			JSONParser jsonParser = new JSONParser();

			List<NameValuePair> params = new ArrayList<NameValuePair>();

			JSONObject json = jsonParser
					.makeHttpRequest(
							"http://aggniapp.aggni.org/aggniappscripts/getEventsList.php",
							"GET", params);

			// Log.i("Vaibhavs",json.toString());
			Gson gson = new Gson();

			try {
				mEventListResponse = gson.fromJson(json.toString(),
						EventsListResponse.class);
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
					&& mEventListResponse != null
					&& mEventListResponse.getmStatus() == 1) {
				ArrayList<EventDetailVO> tempEventList = new ArrayList<EventDetailVO>();
				for (Event event : mEventListResponse.getmEvents()) {
					EventDetailVO mEvent = new EventDetailVO();
					mEvent.setmEventID(event.getEventId());
					mEvent.setmEventName(event.getEventName());
					mEvent.setmEventDescription(event.getEventDescription());
					mEvent.setmEventDate(event.getEventDate());
					mEvent.setMaxNoOfPeople(event.getEventMaxNoPeople());
					mEvent.setSeatsAvailable(event.getSeatsAvailable());
					mEvent.setSeatsWaiting(event.getSeatsWaiting());
					mEvent.setFacebookLink(event.getFacebook_link());

					String imageUrls[] = event.getEventPhotos().split(",");
					ArrayList<String> imageList = new ArrayList<String>();
					for (String imageUrl : imageUrls) {
						imageList.add(imageUrl);
					}

					mEvent.setmImageList(imageList);

					tempEventList.add(mEvent);

				}

				EventList.getSingletonInstance().setEventDetailList(
						tempEventList);
				EventListAdapter adapter = new EventListAdapter(
						EventListActivity.this, "AllEvents");
				mEventListView.setAdapter(adapter);
				// mProgressDialog.dismiss();

			}

			else

			{
				// mProgressDialog.dismiss();
				Toast.makeText(EventListActivity.this,
						"Fetching Events Failed, Try Again!", Toast.LENGTH_LONG)
						.show();
			}

			FetchRegisteredEvents events = new FetchRegisteredEvents();
			events.execute("Execute");

		}

	}

	class SaveRegistrationId extends AsyncTask<String, String, String> {

		int success;

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected String doInBackground(String... args) {
			JSONParser jsonParser = new JSONParser();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("GCMRegisterId", gcmRegId));
			params.add(new BasicNameValuePair("MobileNumber", UserDetails
					.getSingletonInstance().getPhoneNumber()));
			JSONObject json = jsonParser
					.makeHttpRequest(
							"http://aggniapp.aggni.org/aggniappscripts/RegisterGCMKey.php",
							"POST", params);

			// Log.i("Vaibhavs",json.toString());
			Gson gson = new Gson();

			try {
				success = json.getInt("success");

				return json.getString("message");
			} catch (Exception e) {
				Log.i("Vaibhavs", "catch");
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(String result) {

			if (success == 1 && result != null) {
				//Toast.makeText(EventListActivity.this, result,
					//	Toast.LENGTH_LONG).show();
			}

		}

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
					&& mRegisteredEventListResponse != null) {

				ArrayList<String> registeredEvents = new ArrayList<String>();
				mProgressDialog.dismiss();
				for (int i = 0; i < mRegisteredEventListResponse.length(); i++) {
					try {
						registeredEvents.add(mRegisteredEventListResponse
								.getString(i));

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				RegisteredEventsList.getSingletonInstance().setEventId(
						registeredEvents);

			}

			else

			{
				mProgressDialog.dismiss();
				// Toast.makeText(EventListActivity.this,
				// "Fetching Registered Events Failed, Try Again!",
				// Toast.LENGTH_LONG)
				// .show();
			}

			RegisteredEventsList.getSingletonInstance().getEventId();
		}

	}

}
