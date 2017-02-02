package com.example.aggnimodule1.VO;

import java.util.ArrayList;

public class RegisteredEventsList {

	private ArrayList<String> eventId =new ArrayList<String>();



	private static RegisteredEventsList mRegisteredEventsList;

	public static RegisteredEventsList getSingletonInstance() {
		if (mRegisteredEventsList == null)
			mRegisteredEventsList = new RegisteredEventsList();

		return mRegisteredEventsList;
	}

	private RegisteredEventsList() {
		super();
	}

	public ArrayList<String> getEventId() {
		return eventId;
	}

	public void setEventId(ArrayList<String> userId) {
		this.eventId = userId;
	}


}
