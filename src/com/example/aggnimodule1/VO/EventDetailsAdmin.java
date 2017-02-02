package com.example.aggnimodule1.VO;

import java.util.ArrayList;

import com.example.aggnimodule1.beans.EventDetailsAdminResponseBean;

/**
 * The login response Gson bean to hold the login response. The data is copied
 * from this bean to the bean which derives from the Parcelable which will be
 * used throughout the application
 * 
 * @author Melvin Lobo
 * 
 */
public class EventDetailsAdmin {

	/**
	 * The Data
	 */
	@com.google.gson.annotations.SerializedName("Users")
	private ArrayList<EventDetailsAdminResponseBean.Users> mUsers;

	/**
	 * The Status node
	 */
	@com.google.gson.annotations.SerializedName("success")
	private int mStatus;

	@com.google.gson.annotations.SerializedName("totalCount")
	private int mTotalCount;

	@com.google.gson.annotations.SerializedName("userId")
	private int mUserId;

	public ArrayList<EventDetailsAdminResponseBean.Users> getmUsers() {
		return mUsers;
	}

	public void setmUsers(ArrayList<EventDetailsAdminResponseBean.Users> mUsers) {
		this.mUsers = mUsers;
	}

	public int getmStatus() {
		return mStatus;
	}

	public void setmStatus(int mStatus) {
		this.mStatus = mStatus;
	}

	public int getmTotalCount() {
		return mTotalCount;
	}

	public void setmTotalCount(int mTotalCount) {
		this.mTotalCount = mTotalCount;
	}

	public int getmUserId() {
		return mUserId;
	}

	public void setmUserId(int mUserId) {
		this.mUserId = mUserId;
	}

	private static EventDetailsAdmin mEventDetailsAdmin;

	private EventDetailsAdmin() {

	}

	public synchronized static EventDetailsAdmin getSingletonInstance() {
		if (mEventDetailsAdmin == null) {

			mEventDetailsAdmin = new EventDetailsAdmin();

		}

		return mEventDetailsAdmin;

	}

}
