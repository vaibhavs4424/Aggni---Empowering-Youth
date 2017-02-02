package com.example.aggnimodule1.beans;

import java.util.ArrayList;

/**
 * The login response Gson bean to hold the login response. The data is copied
 * from this bean to the bean which derives from the Parcelable which will be
 * used throughout the application
 * 
 * @author Melvin Lobo
 * 
 */
public class EventDetailsAdminResponseBean extends
		BaseGsonBean<EventDetailsAdminResponseBean> {

	/**
	 * The Data
	 */
	@com.google.gson.annotations.SerializedName("Users")
	private ArrayList<Users> mUsers;

	/**
	 * The Status node
	 */
	@com.google.gson.annotations.SerializedName("success")
	private int mStatus;

	@com.google.gson.annotations.SerializedName("totalCount")
	private int mTotalCount;

	@com.google.gson.annotations.SerializedName("userId")
	private int mUserId;


	
	
	public ArrayList<Users> getmUsers() {
		return mUsers;
	}




	public void setmUsers(ArrayList<Users> mUsers) {
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




	public static class Users {
		// //////////////////////////////// CLASS MEMBERS
		// /////////////////////////////////////

		/**
		 * The user aID
		 */
		@com.google.gson.annotations.SerializedName("RegistrationCount")
		private int RegistrationCount;

		/**
		 * The user e-mail
		 */
		@com.google.gson.annotations.SerializedName("FirstName")
		private String FirstName = null;

		/**
		 * User first name
		 */
		@com.google.gson.annotations.SerializedName("LastName")
		private String LastName = null;

		/**
		 * User Lastname
		 */
		@com.google.gson.annotations.SerializedName("EmailId")
		private String EmailId = null;

		/**
		 * User telephone number
		 */
		@com.google.gson.annotations.SerializedName("PhoneNumber")
		private String PhoneNumber = null;

		public int getRegistrationCount() {
			return RegistrationCount;
		}

		public void setRegistrationCount(int registrationCount) {
			RegistrationCount = registrationCount;
		}

		public String getFirstName() {
			return FirstName;
		}

		public void setFirstName(String firstName) {
			FirstName = firstName;
		}

		public String getLastName() {
			return LastName;
		}

		public void setLastName(String lastName) {
			LastName = lastName;
		}

		public String getEmailId() {
			return EmailId;
		}

		public void setEmailId(String emailId) {
			EmailId = emailId;
		}

		public String getPhoneNumber() {
			return PhoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			PhoneNumber = phoneNumber;
		}

	}

}
