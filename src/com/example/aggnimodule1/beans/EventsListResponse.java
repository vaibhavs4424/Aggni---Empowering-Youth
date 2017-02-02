package com.example.aggnimodule1.beans;

import java.util.ArrayList;


/**
 * The login response Gson bean to hold the login response. The data is copied from this bean to the bean which derives from the Parcelable which will
 * be used throughout the application
 * 
 * @author Melvin Lobo
 * 
 */
public class EventsListResponse extends BaseGsonBean<EventsListResponse> {

	/**
	 * The Data
	 */
	@com.google.gson.annotations.SerializedName("Events")
	private ArrayList<Event> mEvents;
	
	/**
	 * The Status node
	 */
	@com.google.gson.annotations.SerializedName("success")
	private int mStatus;
	

	
	
	
	public ArrayList<Event> getmEvents() {
		return mEvents;
	}





	public void setmEvents(ArrayList<Event> mEvents) {
		this.mEvents = mEvents;
	}





	public int getmStatus() {
		return mStatus;
	}





	public void setmStatus(int mStatus) {
		this.mStatus = mStatus;
	}





	public static class Event {
		// //////////////////////////////// CLASS MEMBERS /////////////////////////////////////
	
		/**
		 * The user aID
		 */
		@com.google.gson.annotations.SerializedName("EventId")
		private String	eventId				= null;
	
		/**
		 * The user e-mail
		 */
		@com.google.gson.annotations.SerializedName("EventName")
		private String	eventName			= null;
	
		/**
		 * User first name
		 */
		@com.google.gson.annotations.SerializedName("EventDescription")
		private String	eventDescription		= null;
	
		/**
		 * User Lastname
		 */
		@com.google.gson.annotations.SerializedName("EventPhotos")
		private String	eventPhotos		= null;
	
		/**
		 * User telephone number
		 */
		@com.google.gson.annotations.SerializedName("EventDate")
		private String	eventDate		= null;
	
		/**
		 * The Store Id
		 */
		@com.google.gson.annotations.SerializedName("EventMaxNoPeople")
		private int	eventMaxNoPeople			= 0;
	
		/**
		 * The manager Id
		 */
		@com.google.gson.annotations.SerializedName("SeatsAvailable")
		private int	seatsAvailable		= 0;
	
		/**
		 * The location type
		 */
		@com.google.gson.annotations.SerializedName("SeatsWaiting")
		private int	seatsWaiting	= 0;
		
		@com.google.gson.annotations.SerializedName("facebook_link")
		private String	facebook_link;
		
		
		

		public String getFacebook_link() {
			return facebook_link;
		}

		public void setFacebook_link(String facebook_link) {
			this.facebook_link = facebook_link;
		}

		public String getEventId() {
			return eventId;
		}

		public void setEventId(String eventId) {
			this.eventId = eventId;
		}

		public String getEventName() {
			return eventName;
		}

		public void setEventName(String eventName) {
			this.eventName = eventName;
		}

		public String getEventDescription() {
			return eventDescription;
		}

		public void setEventDescription(String eventDescription) {
			this.eventDescription = eventDescription;
		}

		public String getEventPhotos() {
			return eventPhotos;
		}

		public void setEventPhotos(String eventPhotos) {
			this.eventPhotos = eventPhotos;
		}

		public String getEventDate() {
			return eventDate;
		}

		public void setEventDate(String eventDate) {
			this.eventDate = eventDate;
		}

		public int getEventMaxNoPeople() {
			return eventMaxNoPeople;
		}

		public void setEventMaxNoPeople(int eventMaxNoPeople) {
			this.eventMaxNoPeople = eventMaxNoPeople;
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

}
