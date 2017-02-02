package com.example.aggnimodule1;

import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.VO.EventDetailVO;
import com.example.aggnimodule1.VO.EventList;
import com.example.aggnimodule1.VO.RegisteredEventsList;
import com.example.aggnimodule1.utilities.ImageLoader;

/**
 * Created by Joydeep on 18-03-2015.
 */
public class EventListAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<EventDetailVO> mEventDetailsList = new ArrayList<EventDetailVO>();

	public EventListAdapter(Context mContext, String fromTag) {
		this.mContext = mContext;

		if (fromTag.equals("AllEvents"))
		{
			mEventDetailsList = EventList.getSingletonInstance()
					.getEventDetailList();
			Collections.reverse(mEventDetailsList);
		}
		
			else {
			for (int i = 0; i < EventList.getSingletonInstance()
					.getEventDetailList().size(); i++) {
				if (RegisteredEventsList
						.getSingletonInstance()
						.getEventId()
						.contains(
								EventList.getSingletonInstance()
										.getEventDetailList().get(i)
										.getmEventID())) {
					mEventDetailsList.add(EventList.getSingletonInstance()
							.getEventDetailList().get(i));

				}

			}

		}
		
		
		
	}

	@Override
	public int getCount() {
		return mEventDetailsList.size();
	}

	@Override
	public Object getItem(int position) {
		return mEventDetailsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View view = inflater.inflate(R.layout.event_list_row_item, null, false);
		TextView mEventTitle = (TextView) view.findViewById(R.id.event_title);
		TextView mEventDescription = (TextView) view
				.findViewById(R.id.event_description);
		TextView mEventDate = (TextView) view.findViewById(R.id.event_date);
		ImageView mEventImage = (ImageView) view.findViewById(R.id.event_image);
		mEventTitle.setText(mEventDetailsList.get(position).getmEventName());
		mEventDescription.setText(mEventDetailsList.get(position)
				.getmEventDescription());
		mEventDate.setText(mEventDetailsList.get(position).getmEventDate()
				.toString());
		String image_url = "";
		if (mEventDetailsList.get(position).getmImageList().size() != 0)
			image_url = mEventDetailsList.get(position).getmImageList().get(0);

		int loader = R.drawable.app_logo;
		ImageLoader imgLoader = new ImageLoader(mContext);
		// whenever you want to load an image from url
		// call DisplayImage function
		// url - image url to load
		// loader - loader image, will be displayed before getting image
		// image - ImageView
		imgLoader.DisplayImage(image_url, loader, mEventImage);

		return view;

	}
}
