package com.example.aggnimodule1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.utilities.JSONParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

/**
 * Created by Joydeep on 10-03-2015.
 */
public class SignUpActivity extends Activity {

	JSONObject json;
	private Button mSignin;
	private EditText mFirstName;
	private EditText mLastName;
	private EditText mEmailId;
	private EditText mPassword;
	private EditText mConfirmPassword;
	private EditText mMobileNumber;

	private int success = -1;
	private ProgressDialog mProgressDialog;
	private Button mSignUp;
	
	private ArrayList<ContactDetails> mPhoneContactNumbers = new ArrayList<SignUpActivity.ContactDetails>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_signup);
		mProgressDialog = new ProgressDialog(this);
		mSignUp = (Button) findViewById(R.id.submit_sign_up);
		mSignin = (Button) findViewById(R.id.sign_in_sign_up);
		mFirstName = (EditText) findViewById(R.id.first_name_sign_up);
		mLastName = (EditText) findViewById(R.id.last_name_sign_up);
		mEmailId = (EditText) findViewById(R.id.email_sign_up);
		mPassword = (EditText) findViewById(R.id.password_sign_up);
		mConfirmPassword = (EditText) findViewById(R.id.confirm_password_sign_up);
		mMobileNumber = (EditText) findViewById(R.id.mobile_no_sign_up);
		FetchContacts fetchContacts = new FetchContacts();
		fetchContacts.execute("execute");
		mSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (isvalid()) {
					SIgnUpTask sIgnUpTask = new SIgnUpTask();
					sIgnUpTask.execute("execute");
				}
			}
		});

		mSignin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SignUpActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
		});
	}

	
	private class FetchContacts extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			fetchContacts();
			return "success";
		}

		@Override
		protected void onPostExecute(String result) {

			//Toast.makeText(SignUpActivity.this, "Contacts Loaded", Toast.LENGTH_LONG).show();
			
			

		        JSONObject jResult = new JSONObject();// main object
		        JSONArray jArray = new JSONArray();// /ItemDetail jsonArray

		        for (int i = 0; i < mPhoneContactNumbers.size(); i++) {
		            JSONObject jGroup = new JSONObject();// /sub Object

		            try {
		                jGroup.put("ContactName",mPhoneContactNumbers.get(i).getmContactName());
		                jGroup.put("ContactNumber", mPhoneContactNumbers.get(i).getmContactPhone());
		                jArray.put(jGroup);

		                // /itemDetail Name is JsonArray Name
		                
		               
		            } catch (JSONException e) {
		                e.printStackTrace();
		            }
		        }
		        try {
					jResult.put("ContactList", jArray);
					Log.i("ContactList", jResult.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }


		@Override
		protected void onPreExecute() {
		}

	}

	
	public void fetchContacts() {

		ContactDetails contact;
		String contactName;
		String contactNumber;
		String contactEmail;
		String phoneNumber = null;
		String email = null;

		Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
		String _ID = ContactsContract.Contacts._ID;
		String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
		String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

		Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
		String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

		Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
		String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
		String DATA = ContactsContract.CommonDataKinds.Email.DATA;

		StringBuffer output = new StringBuffer();

		ContentResolver contentResolver = getContentResolver();

		Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null,
				null);

		// Loop for every contact in the phone
		if (cursor.getCount() > 0) {

			while (cursor.moveToNext()) {

				
				String contact_id = cursor
						.getString(cursor.getColumnIndex(_ID));
				String name = cursor.getString(cursor
						.getColumnIndex(DISPLAY_NAME));

				int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor
						.getColumnIndex(HAS_PHONE_NUMBER)));

				if (hasPhoneNumber > 0) {
					
					output.append("\n First Name:" + name);

					// Query and loop for every phone number of the contact
					Cursor phoneCursor = contentResolver.query(
							PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?",
							new String[] { contact_id }, null);

					while (phoneCursor.moveToNext()) {
						contact = new ContactDetails();
						
						phoneNumber = phoneCursor.getString(phoneCursor
								.getColumnIndex(NUMBER));
						
						contact.setmContactName(name);
						contact.setmContactPhone(phoneNumber);
						mPhoneContactNumbers.add(contact);
						output.append("\n Phone number:" + phoneNumber);

					}

					phoneCursor.close();

					/*
					 * // Query and loop for every email of the contact Cursor
					 * emailCursor = contentResolver.query(EmailCONTENT_URI,
					 * null, EmailCONTACT_ID + " = ?", new String[] { contact_id
					 * }, null);
					 * 
					 * while (emailCursor.moveToNext()) {
					 * 
					 * email =
					 * emailCursor.getString(emailCursor.getColumnIndex(DATA));
					 * contact.setContactEmail(email); output.append("\nEmail:"
					 * + email);
					 * 
					 * }
					 * 
					 * emailCursor.close();
					 */
				}

				// Contacts.getSingleInstance().getmContactsList().add(contact);
				output.append("\n");
			
			
			}

		}
	
		Log.i("Contact",mPhoneContactNumbers.toString()+"");
	Log.i("sad", "asd");
	}
	
	
		
		class ContactDetails
		{
			private String mContactName;
			private String mContactPhone;
			public String getmContactName() {
				return mContactName;
			}
			public void setmContactName(String mContactName) {
				this.mContactName = mContactName;
			}
			public String getmContactPhone() {
				return mContactPhone;
			}
			public void setmContactPhone(String mContactPhone) {
				this.mContactPhone = mContactPhone;
			}
			
			
			
		}
	
	
	public boolean isvalid() {

		if (mFirstName != null && !mFirstName.getText().toString().equals("")
				&& mLastName != null
				&& !mLastName.getText().toString().equals("")
				&& mEmailId != null
				&& !mEmailId.getText().toString().equals("")
				&& mPassword != null
				&& !mPassword.getText().toString().equals("")
				&& mConfirmPassword != null
				&& !mConfirmPassword.getText().toString().equals("")
				&& mMobileNumber != null
				&& !mMobileNumber.getText().toString().equals("")) {

			if (mEmailId.getText().toString().contains("@")) {
				if (mPassword.getText().toString()
						.equals(mConfirmPassword.getText().toString())) {
					if (mMobileNumber.length() == 10) {
						return true;
					} else {
						Toast.makeText(getApplicationContext(),
								"Please enter a valid mobile number",
								Toast.LENGTH_LONG).show();
					return false;
					}
					

				} else {
					Toast.makeText(getApplicationContext(),
							"Password and Confirm Password do not match.",
							Toast.LENGTH_LONG).show();

					return false;
				}

			} else {
				Toast.makeText(getApplicationContext(),
						"Email-id entered is invalid.", Toast.LENGTH_LONG)
						.show();

				return false;
			}

		} else {
			Toast.makeText(getApplicationContext(),
					"Please fill up all the fields.", Toast.LENGTH_LONG).show();

			return false;
		}
	}

	class SIgnUpTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			mProgressDialog.setMessage("Registering User, Please wait");
			mProgressDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			JSONParser jsonParser = new JSONParser();

			String firstName = mFirstName.getText().toString();
			String lastName = mLastName.getText().toString();
			String email = mEmailId.getText().toString();
			String password = mPassword.getText().toString();
			String mobileNumber = mMobileNumber.getText().toString();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("FirstName", firstName));
			params.add(new BasicNameValuePair("LastName", lastName));
			params.add(new BasicNameValuePair("EmailId", email));
			params.add(new BasicNameValuePair("Password", password));
			params.add(new BasicNameValuePair("MobileNumber", mobileNumber));

			json = jsonParser
					.makeHttpRequest(
							"http://aggniapp.aggni.org/aggniappscripts/RegisterUser.php",
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

			mProgressDialog.dismiss();
//			Toast.makeText(SignUpActivity.this, result+" Please login to continue.", Toast.LENGTH_LONG)
	//				.show();
			if (result != null && success == 1) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						SignUpActivity.this);
				builder.setTitle("Registeration Successful!");
				builder.setMessage(result + " Please login with the same credentials to continue.")
						.setCancelable(false)
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

										finish();
										Intent intent = new Intent(
												SignUpActivity.this,
												LoginActivity.class);
										startActivity(intent);

									}
								});
				builder.show();

			} else {
				try {
					if (result != null)
						Toast.makeText(SignUpActivity.this,
								json.getString("message"), Toast.LENGTH_LONG)
								.show();

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/*
	 * public String validate() { if(mFirstName!=null && !mFirstName.equals("")
	 * && !mFirstName.equals(" ") ] )
	 * 
	 * }
	 */

}
