package com.example.aggnimodule1.VO;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Joydeep on 18-03-2015.
 */
public class EventDetailVO {

	private String mEventID;

	private String mEventName;

	private String mEventDescription;

	private String mEventDate;

	private ArrayList<String> mImageList = new ArrayList<String>();

	private int maxNoOfPeople;

	private int seatsAvailable;

	private int seatsWaiting;
	
	private String facebookLink;

	
	
	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getmEventName() {
		return mEventName;
	}

	public void setmEventName(String mEventName) {
		this.mEventName = mEventName;
	}

	public String getmEventDescription() {
		return mEventDescription;
	}

	public void setmEventDescription(String mEventDescription) {
		this.mEventDescription = mEventDescription;
	}

	public String getmEventDate() {
		return mEventDate;
	}

	public void setmEventDate(String mEventDate) {
		this.mEventDate = mEventDate;
	}

	public ArrayList<String> getmImageList() {
		return mImageList;
	}

	public void setmImageList(ArrayList<String> mImageList) {
		this.mImageList = mImageList;
	}

	public EventDetailVO() {

	}

	public String getmEventID() {
		return mEventID;
	}

	public void setmEventID(String mEventID) {
		this.mEventID = mEventID;
	}

	public int getMaxNoOfPeople() {
		return maxNoOfPeople;
	}

	public void setMaxNoOfPeople(int maxNoOfPeople) {
		this.maxNoOfPeople = maxNoOfPeople;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public int getSeatsWaiting() {
		return seatsWaiting;
	}

	public void setSeatsWaiting(int seatsWaiting) {
		this.seatsWaiting = seatsWaiting;
	}

}
