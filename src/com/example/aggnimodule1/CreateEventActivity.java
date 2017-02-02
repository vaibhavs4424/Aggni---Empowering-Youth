package com.example.aggnimodule1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.utilities.JSONParser;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateEventActivity extends Activity {

	private final int TAKE_GALLERY = 4;
	private EditText mEventName, mEventDesc, mEventFacebookLink,
			mEventImageList;
	private Button mSelectPicture, mSelectDate, mCreateEvent;
	private ImageView mSelectedImage;
	private int todayYear, todayMonth, todayDay;
	String imageFilePath = "";
	Bitmap bmp;
	private ProgressDialog mPRogressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.create_event);
		mPRogressDialog = new ProgressDialog(this);
		mEventName = (EditText) findViewById(R.id.event_name);
		mEventDesc = (EditText) findViewById(R.id.event_desc);
		mEventFacebookLink = (EditText) findViewById(R.id.event_facebook_link);
		mEventImageList = (EditText) findViewById(R.id.event_img_urls);
		mSelectPicture = (Button) findViewById(R.id.select_image);
		mSelectDate = (Button) findViewById(R.id.event_date);
		mSelectedImage = (ImageView) findViewById(R.id.selected_image);

		mCreateEvent = (Button) findViewById(R.id.create_event);

		mCreateEvent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (mEventName.getText() != null
						&& !mEventName.getText().equals("") && mEventDesc.getText() != null
								&& !mEventDesc.getText().equals("") && mEventImageList.getText() != null
										&& !mEventImageList.getText().equals("") && mSelectDate.getText() != null
												&& !mSelectDate.getText().equals("select Date"))

				{
					CreateEvent event = new CreateEvent();
					event.execute("Execute");
				}
				else	
				{
					Toast.makeText(CreateEventActivity.this, "Please enter all the details.", Toast.LENGTH_LONG).show();
				}
				
			}
		});

		mSelectDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				CallenderCall();

			}
		});

		mSelectPicture.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, TAKE_GALLERY);

			}
		});

	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		// onDateSet method
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			String dateOfBirth = String.valueOf(dayOfMonth) + "/"
					+ String.valueOf(monthOfYear + 1) + "/"
					+ String.valueOf(year);
			mSelectDate.setText(dateOfBirth);

		}

	};

	public void CallenderCall() {
		// Creates new instance of calendar, get current Year, Month and Day and
		// use it as default values for the Date Picker Dialog.
		final Calendar c = Calendar.getInstance();

		todayYear = c.get(Calendar.YEAR);
		todayMonth = c.get(Calendar.MONTH);
		todayDay = c.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog dpdFromDate = new DatePickerDialog(this,
				mDateSetListener, todayYear, todayMonth, todayDay);
		dpdFromDate.show();
	}

	private Bitmap decodeFile(File f) {
		try {
			if (f.exists()) {
				BitmapFactory.Options o = new BitmapFactory.Options();
				o.inJustDecodeBounds = true;
				BitmapFactory.decodeStream(new FileInputStream(f), null, o);

				// The new size we want to scale to
				final int REQUIRED_SIZE = 500;

				int width_tmp = o.outWidth, height_tmp = o.outHeight;
				int scale = 1;
				while (true) {
					if (width_tmp / 2 < REQUIRED_SIZE
							|| height_tmp / 2 < REQUIRED_SIZE)
						break;
					width_tmp /= 2;
					height_tmp /= 2;
					scale *= 2;
				}

				BitmapFactory.Options o2 = new BitmapFactory.Options();
				o2.inSampleSize = scale;
				return BitmapFactory.decodeStream(new FileInputStream(f), null,
						o2);
			} else {
				// Log.d("Account", " *************** File Missing!");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {

		case TAKE_GALLERY:
			if (resultCode == RESULT_OK) {
				System.gc();
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				String filename = "";
				String filePath = "";
				if (cursor != null && cursor.moveToFirst()) {
					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
					filePath = cursor.getString(columnIndex);
					int pos = filePath.lastIndexOf("/");
					filename = filePath.substring(pos + 1).trim();
					// Log.d("Account() on activity result", " Filename is: "
					// + filename);
					cursor.deactivate();
					cursor.close();
				} else {
					Toast.makeText(this, "Unable to retrieve selected image",
							Toast.LENGTH_LONG).show();
					return;
				}

				// file processing

				imageFilePath = filePath;
				File file = new File(filePath);
				Bitmap bm = null;
				File f = null;
				try {

					bm = decodeFile(file);
					OutputStream fOut = null;
					File directory = new File(
							Environment.getExternalStorageDirectory()
									+ File.separator
									+ getResources().getString(
											R.string.app_name));
					directory.mkdirs();
					File newwfile = new File(directory,
							System.currentTimeMillis()
									+ "_cmp_"
									+ getResources().getString(
											R.string.app_name) + ".jpg");
					newwfile.createNewFile();
					fOut = new FileOutputStream(newwfile);

					ExifInterface exif = new ExifInterface(filePath);
					int orientation = exif.getAttributeInt(
							ExifInterface.TAG_ORIENTATION, 1);
					if (orientation == 6)// portrait
					{
						Matrix matrix = new Matrix();
						matrix.postRotate(90);
						bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
								bm.getHeight(), matrix, true);
					}
					bm.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
					// imgDisplay.setImageBitmap(bm);
					// uploadImage(bm);
					bmp = bm;
					mSelectedImage.setImageBitmap(bmp);
					fOut.flush();
					fOut.close();

				} catch (Exception e) {
					e.printStackTrace();
				}

				break;
			}
			break;

		}
	}

	class CreateEvent extends AsyncTask<String, String, String> {

		JSONObject json;
		int success;

		@Override
		protected void onPreExecute() {
			mPRogressDialog.setMessage("Creating Event, Please wait");
			mPRogressDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			JSONParser jsonParser = new JSONParser();

			String eventName = mEventName.getText().toString();
			String eventDesc = mEventDesc.getText().toString();
			String eventDate = mSelectDate.getText().toString();
			String mFacebookLink = mEventFacebookLink.getText().toString();
			String eventImageURls = mEventImageList.getText().toString();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("eventName", eventName));
			params.add(new BasicNameValuePair("eventDesc", eventDesc));
			params.add(new BasicNameValuePair("eventDate", eventDate));
			params.add(new BasicNameValuePair("eventFacebookLink",
					mFacebookLink));
			params.add(new BasicNameValuePair("eventImageURLs", eventImageURls));
			json = jsonParser
					.makeHttpRequest(
							"http://aggniapp.aggni.org/aggniappscripts/CreateEventTemp.php",
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

			mPRogressDialog.dismiss();
			// Toast.makeText(SignUpActivity.this,
			// result+" Please login to continue.", Toast.LENGTH_LONG)
			// .show();
			try 
			{
			if (result != null && success == 1) {

				Toast.makeText(CreateEventActivity.this,
						json.getString("message"), Toast.LENGTH_LONG)
						.show();
				Intent intent = new Intent(CreateEventActivity.this,EventListActivity.class);
				startActivity(intent);
				
			} else {
				
				Toast.makeText(CreateEventActivity.this,
						json.getString("message"), Toast.LENGTH_LONG)
						.show();
						

			}
			}
			catch(Exception e)
			{
				
			}
		}

	}

	public class uploadTimetableTask extends AsyncTask<String, String, String> {

		ProgressDialog dialog = null;
		HttpResponse response;
		String responseString = null;

		@Override
		protected String doInBackground(String... uri) {
			// TODO Auto-generated method stub
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.JPEG, 100, bao);
			byte[] ba = bao.toByteArray();

			InputStream is;
			try {

				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://aggniapp.aggni.org/aggniappscripts/CreateEventNew.php");
				ByteArrayBody bab = new ByteArrayBody(ba,
						System.currentTimeMillis() + ".jpg");
				MultipartEntity reqEntity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);
				reqEntity.addPart("photo", bab);
				reqEntity.addPart("eventName", new StringBody(mEventName
						.getText().toString()));
				reqEntity.addPart("eventDesc", new StringBody(mEventDesc
						.getText().toString()));
				reqEntity.addPart("eventDate", new StringBody(mSelectDate
						.getText().toString()));
				reqEntity.addPart("eventFacebookLink", new StringBody(
						mEventFacebookLink.getText().toString()));

				httppost.setEntity(reqEntity);

				response = httpclient.execute(httppost);
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					response.getEntity().writeTo(out);
					out.close();
					responseString = out.toString();
				} else {
					// Closes the connection.
					response.getEntity().getContent().close();
					throw new IOException(statusLine.getReasonPhrase());
				}

			} catch (ClientProtocolException e) {
				// writing exception to log
				e.printStackTrace();
			} catch (IOException e) {
				// writing exception to log
				e.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(CreateEventActivity.this,
						"Error uploading image.", Toast.LENGTH_SHORT).show();
			}

			return responseString;
		}

		@Override
		protected void onPostExecute(String result) {

			mPRogressDialog.cancel();
			Log.d("PANKAJ", "upload response" + result);
			super.onPostExecute(result);
			try {
				JSONObject obj = new JSONObject(result);
				int Success = obj.getInt("success");
				if (Success == 0)
					Toast.makeText(CreateEventActivity.this,
							"Error uploading timetable......",
							Toast.LENGTH_SHORT).show();
				else {
					Toast.makeText(CreateEventActivity.this,
							"Timetable uploaded......", Toast.LENGTH_SHORT)
							.show();
					setResult(RESULT_OK);

					// finish();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
