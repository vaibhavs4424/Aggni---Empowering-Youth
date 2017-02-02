package com.example.aggnimodule1.VO;

import java.util.ArrayList;

/**
 * Created by Joydeep on 18-03-2015.
 */
public class EventList {

	private static ArrayList<EventDetailVO> mEventDetailList = new ArrayList<EventDetailVO>();
	private static EventList mEventList;

	private EventList() {
	}

	public synchronized static EventList getSingletonInstance() {
		if (mEventList == null) {
			mEventList = new EventList();
		}
		return mEventList;
	}

	public ArrayList<EventDetailVO> getEventDetailList() {
		return mEventDetailList;
	}

	public void setEventDetailList(ArrayList<EventDetailVO> mEventDetailList) {
		this.mEventDetailList = mEventDetailList;
	}
}
