package com.transfermoneyapp.exception;

/**
 * @author sachin.sharma 
 * Custom exception class
 */
public class CustomMoneyTransferException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomMoneyTransferException(String msg) {
		super(msg);
	}

	public CustomMoneyTransferException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
