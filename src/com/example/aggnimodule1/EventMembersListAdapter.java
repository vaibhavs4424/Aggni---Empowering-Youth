package com.example.aggnimodule1;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.VO.EventDetailsAdmin;
import com.example.aggnimodule1.beans.EventDetailsAdminResponseBean;

/**
 * Created by Joydeep on 18-03-2015.
 */
public class EventMembersListAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<EventDetailsAdminResponseBean.Users> mEventDetailsList = new ArrayList<EventDetailsAdminResponseBean.Users>();

	public EventMembersListAdapter(Context mContext) {
		this.mContext = mContext;
		mEventDetailsList = EventDetailsAdmin.getSingletonInstance()
				.getmUsers();

	}

	@Override
	public int getCount() {
		return mEventDetailsList.size();
	}

	@Override
	public Object getItem(int position) {
		return mEventDetailsList.get((getCount() - position - 1));
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater
				.inflate(R.layout.member_list_row_item, null, false);
		TextView memberFirstName = (TextView) view
				.findViewById(R.id.members_first_name);
		TextView mLastName = (TextView) view
				.findViewById(R.id.members_last_name);
		TextView mEmail = (TextView) view.findViewById(R.id.members_email_id);
		TextView phone = (TextView) view.findViewById(R.id.members_contact_no);
		TextView totalCount = (TextView) view.findViewById(R.id.ticket_count);

		ImageView call = (ImageView) view.findViewById(R.id.call_contact);

		memberFirstName.setText(mEventDetailsList.get(position).getFirstName());
		mLastName.setText(mEventDetailsList.get(position).getLastName());
		mEmail.setText(mEventDetailsList.get(position).getEmailId());
		phone.setText(mEventDetailsList.get(position).getPhoneNumber());
		totalCount.setText(String.valueOf(mEventDetailsList.get(position)
			.getRegistrationCount()));
		
		call.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", mEventDetailsList.get(position).getPhoneNumber(), null));
				mContext.startActivity(intent);
				
			}
		});
		
		return view;

	}
}
