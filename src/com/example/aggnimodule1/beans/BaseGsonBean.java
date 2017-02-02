package com.example.aggnimodule1.beans;



/**
 * The base class for all beans using the Volley Request queue. All child classes have to implement
 * the abstract function for response handling
 *  
 * @author Melvin Lobo
 *
 */
public class BaseGsonBean<T> {
	
	//////////////////////////////// CLASS MEMBERS //////////////////////////////////////
	/**
	 * The response callback hook to the activity waiting for the bean
	 *//*
	protected ResponseHook mResponseHook = null;
	
	*//**
	 * The request identifier
	 *//*
	protected int mRequestType = 0;
	
	//////////////////////////////// CLASS METHODS //////////////////////////////////////
	*//**
	 * Set the response hook 
	 * 
	 * @author Melvin Lobo
	 *//*
	public void setResponseHook(int requestType, ResponseHook responseHook) {
		mResponseHook = responseHook;
		mRequestType = requestType;
	}
	
	
	*//**
	 * On Response handled by the child class. This is an abstract function as the response for each
	 * JSON request will be different and subjective.
	 * 
	 * @author Melvin Lobo
	 *//*
	public void onResponse(T Response) {
		ResponseEntity rE = new ResponseEntity();
		rE.setRequestType(mRequestType);
//		rE.setBaseBean(Response);
		mResponseHook.receiveResponse(rE);
	}
	*/
}
