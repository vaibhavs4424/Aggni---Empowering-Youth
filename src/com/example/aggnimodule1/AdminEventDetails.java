package com.example.aggnimodule1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.VO.EventDetailsAdmin;
import com.example.aggnimodule1.VO.EventList;
import com.example.aggnimodule1.VO.RegisteredEventsList;
import com.example.aggnimodule1.VO.UserDetails;
import com.example.aggnimodule1.beans.EventDetailsAdminResponseBean;
import com.example.aggnimodule1.beans.EventsListResponse;
import com.example.aggnimodule1.utilities.JSONParser;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminEventDetails extends Activity {

	private TextView eventName;
	private TextView eventDescription;
	private TextView eventDate;
	private TextView totalMembers;
	private ListView mMembersList;
	private int mEventPosition;
	private ProgressDialog mProgressDialog;
	private EventDetailsAdminResponseBean mEventDetailsAdminResponseBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_admin_event_detail);
		Bundle b = getIntent().getExtras();
		if (b != null) {
			mEventPosition = b.getInt("EventPosition");
		}
		mProgressDialog = new ProgressDialog(this);
		eventName = (TextView) findViewById(R.id.admin_event_name);
		eventDescription = (TextView) findViewById(R.id.admin_event_desc);
		eventDate = (TextView) findViewById(R.id.admin_event_date);
		totalMembers = (TextView) findViewById(R.id.admin_event_total_participants);
		mMembersList = (ListView) findViewById(R.id.member_list);

		eventName.setText(EventList.getSingletonInstance().getEventDetailList()
				.get(mEventPosition).getmEventName());
		eventDate.setText(EventList.getSingletonInstance().getEventDetailList()
				.get(mEventPosition).getmEventDate());
		eventDescription.setText(EventList.getSingletonInstance()
				.getEventDetailList().get(mEventPosition)
				.getmEventDescription());

		FetchEventDetailAdmin fetch = new FetchEventDetailAdmin();
		fetch.execute("Execute");

	}

	class FetchEventDetailAdmin extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			mProgressDialog.setMessage("Fetching Registered Events...");
			mProgressDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			JSONParser jsonParser = new JSONParser();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("EventId", EventList
					.getSingletonInstance().getEventDetailList()
					.get(mEventPosition).getmEventID()));

			JSONObject json = jsonParser
					.makeHttpRequest(
							"http://aggniapp.aggni.org/aggniappscripts/GetRegisteredEventDetail.php",
							"POST", params);

			// Log.i("Vaibhavs",json.toString());
			Gson gson = new Gson();

			try {
				mEventDetailsAdminResponseBean = gson.fromJson(json.toString(),
						EventDetailsAdminResponseBean.class);

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
					&& mEventDetailsAdminResponseBean != null && mEventDetailsAdminResponseBean.getmUsers()!=null) {
				EventDetailsAdmin.getSingletonInstance().setmUserId(
						mEventDetailsAdminResponseBean.getmUserId());
				EventDetailsAdmin.getSingletonInstance().setmStatus(
						mEventDetailsAdminResponseBean.getmStatus());
				EventDetailsAdmin.getSingletonInstance().setmTotalCount(
						mEventDetailsAdminResponseBean.getmTotalCount());
				EventDetailsAdmin.getSingletonInstance().setmUsers(
						mEventDetailsAdminResponseBean.getmUsers());

				totalMembers.setText(String.valueOf(EventDetailsAdmin.getSingletonInstance()
						.getmTotalCount()));
				EventMembersListAdapter memberList = new EventMembersListAdapter(
						AdminEventDetails.this);
				mMembersList.setAdapter(memberList);
				mProgressDialog.dismiss();
			}

			else

			{
				mProgressDialog.dismiss();
				Toast.makeText(AdminEventDetails.this,
						"Fetching Events Failed, Try Again!", Toast.LENGTH_LONG)
						.show();
			}

		}

	}

}
