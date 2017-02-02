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
import android.support.v4.view.ViewPager;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.VO.EventList;
import com.example.aggnimodule1.VO.RegisteredEventsList;
import com.example.aggnimodule1.VO.UserDetails;
import com.example.aggnimodule1.utilities.JSONParser;
import com.example.aggnimodule1.widgets.CirclePageIndicator;
import com.google.gson.Gson;

public class EventDetailActivity extends Activity {

	int mEventPosition;
	private ViewPager mImageViewPager;
	private CirclePageIndicator mCircleIndicator;
	private TextView mEventName, mEventDate, mEventDescription;
	private Button mRegister;
	JSONObject json;
	int success;
	ProgressDialog mProgressDialog;
	private JSONArray mRegisteredEventListResponse;
	private String registrationCount;
	private TextView mFacebookLink;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		Bundle b = getIntent().getExtras();
		if (b != null) {
			mEventPosition = b.getInt("EventPosition");
		}
		mProgressDialog = new ProgressDialog(this);
		Log.i("Vaibhavs", "" + mEventPosition);
		setContentView(R.layout.activity_event_detail);
		mEventName = (TextView) findViewById(R.id.event_name);
		mEventDate = (TextView) findViewById(R.id.event_date);
		mEventDescription = (TextView) findViewById(R.id.event_desc);
		mRegister = (Button) findViewById(R.id.register_event);
		mImageViewPager = (ViewPager) findViewById(R.id.image_viewpager);
		mCircleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
		mFacebookLink = (TextView) findViewById(R.id.event_facebook_link);
		
		
		if (RegisteredEventsList
				.getSingletonInstance()
				.getEventId()
				.contains(
						EventList.getSingletonInstance().getEventDetailList()
								.get(mEventPosition).getmEventID())) {
			mRegister.setText("Event already registered");
			mRegister.setEnabled(false);
		}
		EventImagesAdapter imageAdapter = new EventImagesAdapter(this,
				mEventPosition);
		mImageViewPager.setAdapter(imageAdapter);
		mCircleIndicator.setViewPager(mImageViewPager);

		mEventName.setText(EventList.getSingletonInstance()
				.getEventDetailList().get(mEventPosition).getmEventName());
		mEventDate.setText(EventList.getSingletonInstance()
				.getEventDetailList().get(mEventPosition).getmEventDate());
		mEventDescription.setText(EventList.getSingletonInstance()
				.getEventDetailList().get(mEventPosition)
				.getmEventDescription());
		mFacebookLink.setText(EventList.getSingletonInstance().getEventDetailList().get(mEventPosition).getFacebookLink());
		

		mRegister.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				
				final Dialog dialog = new Dialog(EventDetailActivity.this);
				dialog.setContentView(R.layout.event_count_register);
				final EditText registrationCounttxt = (EditText) dialog
						.findViewById(R.id.registration_count);
				((Button) dialog.findViewById(R.id.confirm))
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								if (registrationCounttxt.getText() != null
										&& !registrationCounttxt.getText().toString()
												.equals("") && !registrationCounttxt.getText().toString()
												.equals(" ")) {
									registrationCount = registrationCounttxt.getText()
											.toString();
									EventRegister event = new EventRegister();
									event.execute("execute");
									dialog.dismiss();
								} else {
									Toast.makeText(EventDetailActivity.this,
											"Please enter a valid count",
											Toast.LENGTH_LONG).show();
								}
							}
						});

				((Button) dialog.findViewById(R.id.cancel))
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								dialog.dismiss();
							}
						});
				
				dialog.show();
				
			}
		});



	}

	class EventRegister extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			mProgressDialog.setMessage("Registering User, Please wait");
			mProgressDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			JSONParser jsonParser = new JSONParser();

			String userId = String.valueOf(UserDetails.getSingletonInstance()
					.getUserId());
			String eventId = EventList.getSingletonInstance()
					.getEventDetailList().get(mEventPosition).getmEventID();
			String registrationCountvalue = registrationCount;
			String registrationStatus = "1";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("UserId", userId));
			params.add(new BasicNameValuePair("EventId", eventId));
			params.add(new BasicNameValuePair("RegistrationCount",
					registrationCountvalue));
			params.add(new BasicNameValuePair("RegistrationStatus",
					registrationStatus));

			json = jsonParser
					.makeHttpRequest(
							"http://aggniapp.aggni.org/aggniappscripts/EventRegister.php",
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

			// mProgressDialog.dismiss();
			// Toast.makeText(SignUpActivity.this,
			// result+" Please login to continue.", Toast.LENGTH_LONG)
			// .show();
			if (result != null && success == 1) {
				try {
					Toast.makeText(EventDetailActivity.this,
							json.getString("message"), Toast.LENGTH_LONG)
							.show();
					mRegister.setText("Event already registered");
					mRegister.setEnabled(false);
					FetchRegisteredEvents events = new FetchRegisteredEvents();
					events.execute("Execute");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				try {
					if (result != null)
						Toast.makeText(EventDetailActivity.this,
								json.getString("message"), Toast.LENGTH_LONG)
								.show();

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			FetchRegisteredEvents events = new FetchRegisteredEvents();
			events.execute("Execute");
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
				Toast.makeText(EventDetailActivity.this,
						"Fetching Events Failed, Try Again!", Toast.LENGTH_LONG)
						.show();
			}

		}

	}

}
