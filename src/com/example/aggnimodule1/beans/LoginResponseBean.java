package com.example.aggnimodule1.beans;

public class LoginResponseBean extends BaseGsonBean<LoginResponseBean> {

	@com.google.gson.annotations.SerializedName("UserId")
	private int userId;

	@com.google.gson.annotations.SerializedName("FirstName")
	private String firstName;

	@com.google.gson.annotations.SerializedName("LastName")
	private String lastName;

	@com.google.gson.annotations.SerializedName("EmailId")
	private String emailId;

	@com.google.gson.annotations.SerializedName("PhoneNumber")
	private String phoneNumber;

	@com.google.gson.annotations.SerializedName("Password")
	private String password;

	@com.google.gson.annotations.SerializedName("Role")
	private String role;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
