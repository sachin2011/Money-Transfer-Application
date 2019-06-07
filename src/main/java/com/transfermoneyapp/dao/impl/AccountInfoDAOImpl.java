package com.transfermoneyapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.transfermoneyapp.dao.AccountInfoDAO;
import com.transfermoneyapp.exception.CustomMoneyTransferException;
import com.transfermoneyapp.model.AccountInfo;
import com.trasfermoneyapp.constants.AppConstants;

/**
 * @author sachin.sharma
 * Implementation class for account Info interface
 *         Contains method for performing account related operations
 */
public class AccountInfoDAOImpl implements AccountInfoDAO {

	private static Logger log = Logger.getLogger(AccountInfoDAOImpl.class);

	/* Get all accounts available in database */

	public List<AccountInfo> getAllAccountsDetails() throws CustomMoneyTransferException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<AccountInfo> allAccountsInfo = new ArrayList<AccountInfo>();
		try {
			connection = DBFactoryImpl.getConnection();
			statement = connection.prepareStatement(AppConstants.FETCH_ALL_ACCOUNT_QUERY);
			log.debug("DB query for getting all account details" + statement);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				AccountInfo accInfo = new AccountInfo(resultSet.getLong("AccountId"), resultSet.getString("UserName"),
						resultSet.getString("EmailId"), resultSet.getString("TelephoneNo"),
						resultSet.getString("Address"), resultSet.getBigDecimal("AccountBalance"),
						resultSet.getString("Currency"));
				allAccountsInfo.add(accInfo);
			}
			return allAccountsInfo;
		} catch (SQLException e) {
			log.debug("Error occured during query execution" + e.getMessage());
			throw new CustomMoneyTransferException(AppConstants.SQL_EXECUTION_ERROR, e);
		} finally {
			log.debug("Closing connection");
			DbUtils.closeQuietly(connection, statement, resultSet);
		}
	}

	/**
	 * Get account detail based on account ID
	 */
	public AccountInfo getAccountDetailById(long accountId) throws CustomMoneyTransferException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		AccountInfo accInfo = null;
		try {
			connection = DBFactoryImpl.getConnection();
			statement = connection.prepareStatement(AppConstants.FETCH_ACC_BY_ID_QUERY);
			log.debug("DB query for getting account detail by account ID" + statement);
			statement.setLong(1, accountId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				accInfo = new AccountInfo(resultSet.getLong("AccountId"), resultSet.getString("UserName"),
						resultSet.getString("EmailId"), resultSet.getString("TelephoneNo"),
						resultSet.getString("Address"), resultSet.getBigDecimal("AccountBalance"),
						resultSet.getString("Currency"));
			}
			return accInfo;
		} catch (SQLException e) {
			log.debug("Error occured during query execution" + e.getMessage());
			throw new CustomMoneyTransferException(AppConstants.SQL_EXECUTION_ERROR, e);
		} finally {
			log.debug("Closing connection");
			DbUtils.closeQuietly(connection, statement, resultSet);
		}

	}

	/*
	 * Get account details based on email Id of user
	 */
	public AccountInfo getAccountDetailByEmailId(String emailId) throws CustomMoneyTransferException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		AccountInfo accInfo = null;
		try {
			connection = DBFactoryImpl.getConnection();
			statement = connection.prepareStatement(AppConstants.FETCH_ACC_BY_EMAIL_ID_QUERY);
			log.debug("DB query for getting account detail by email ID" + statement);
			statement.setString(1, emailId);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				accInfo = new AccountInfo(resultSet.getLong("AccountId"), resultSet.getString("UserName"),
						resultSet.getString("EmailId"), resultSet.getString("TelephoneNo"),
						resultSet.getString("Address"), resultSet.getBigDecimal("AccountBalance"),
						resultSet.getString("Currency"));
			}
			return accInfo;
		} catch (SQLException e) {
			log.debug("Error occured during query execution" + e.getMessage());
			throw new CustomMoneyTransferException(AppConstants.SQL_EXECUTION_ERROR, e);
		} finally {
			log.debug("Closing connection");
			DbUtils.closeQuietly(connection, statement, resultSet);
		}

	}

	/*
	 * Get Account details based on the telephone number
	 */
	public AccountInfo getAccountDetailByTelephoneNo(String telephoneNo) throws CustomMoneyTransferException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		AccountInfo accInfo = null;
		try {
			connection = DBFactoryImpl.getConnection();
			statement = connection.prepareStatement(AppConstants.FETCH_ACC_BY_TELEPHONE_NO_QUERY);
			log.debug("DB query for getting account detail by telephonr Number" + statement);
			statement.setString(1, telephoneNo);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				accInfo = new AccountInfo(resultSet.getLong("AccountId"), resultSet.getString("UserName"),
						resultSet.getString("EmailId"), resultSet.getString("TelephoneNo"),
						resultSet.getString("Address"), resultSet.getBigDecimal("AccountBalance"),
						resultSet.getString("Currency"));
			}
			return accInfo;
		} catch (SQLException e) {
			log.debug("Error occured during query execution" + e.getMessage());
			throw new CustomMoneyTransferException(AppConstants.SQL_EXECUTION_ERROR, e);
		} finally {
			log.debug("Closing connection");
			DbUtils.closeQuietly(connection, statement, resultSet);
		}

	}

	/*
	 * Create a new Account
	 */
	public void addAccount(AccountInfo account) throws CustomMoneyTransferException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DBFactoryImpl.getConnection();
			statement = connection.prepareStatement(AppConstants.ADD_ACC_QUERY);
			log.debug("DB query for adding account" + statement);
			statement.setString(1, account.getUserName());
			statement.setString(2, account.getEmailId());
			statement.setString(3, account.getTelphoneNo());
			statement.setString(4, account.getAddress());
			statement.setBigDecimal(5, account.getAccountBalance());
			statement.setString(6, account.getCurrency());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.debug("Error occured during query execution" + e.getMessage());
			throw new CustomMoneyTransferException(AppConstants.SQL_EXECUTION_ERROR + account, e);
		} finally {
			log.debug("Closing connection");
			DbUtils.closeQuietly(connection, statement, resultSet);
		}
	}
}
