package com.trasfermoneyapp.constants;

/**
 * @author sachin.sharma 
 * App Constants that are used across the application
 */
public class AppConstants {
	public static final String FETCH_ACC_BY_ID_QUERY = "SELECT * FROM Account WHERE AccountId = ? ";
	public static final String LOCK_ACC_BY_ID_QUERY = "SELECT * FROM Account WHERE AccountId = ? FOR UPDATE";
	public static final String ADD_ACC_QUERY = "INSERT INTO Account (UserName,EmailId,TelephoneNo,Address,AccountBalance,Currency) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_ACC_BAL_QUERY = "UPDATE Account SET AccountBalance = ? WHERE AccountId = ? ";
	public static final String FETCH_ALL_ACCOUNT_QUERY = "SELECT * FROM Account";
	public static final String FETCH_USER_BY_EMAIL_QUERY = "SELECT * FROM Account WHERE UserName = ? ";
	public static final String FETCH_ACC_BY_EMAIL_ID_QUERY = "SELECT * FROM Account WHERE EmailId = ? ";
	public static final String FETCH_ACC_BY_TELEPHONE_NO_QUERY = "SELECT * FROM Account WHERE TelephoneNo = ? ";
	public static final String SQL_EXECUTION_ERROR = "Error occurred during Query Execution";
	public static final String DB_DRIVER = "org.h2.Driver";
	public static final String DB_CONNECTION_URL = "jdbc:h2:mem:moneyapp;DB_CLOSE_DELAY=-1";
	public static final String DB_USERNAME = "sa";
	public static final String DB_PASSWORD = "sa";
}
