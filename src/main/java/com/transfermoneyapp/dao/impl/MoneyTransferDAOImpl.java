package com.transfermoneyapp.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.transfermoneyapp.dao.MoneyTransferDAO;
import com.transfermoneyapp.exception.CustomMoneyTransferException;
import com.transfermoneyapp.model.AccountInfo;
import com.transfermoneyapp.model.MoneyTransactionInfo;
import com.trasfermoneyapp.constants.AppConstants;

/**
 * @author sachin.sharma 
 * Implementation class for money transfer interface. It
 *         handles the logic for money transfer between accounts
 */
public class MoneyTransferDAOImpl implements MoneyTransferDAO {
	private static Logger log = Logger.getLogger(MoneyTransferDAOImpl.class);

	/*
	 * Method for transferring money between two accounts based upon the transaction
	 * JSON
	 */
	public void transferMoney(MoneyTransactionInfo userTransaction) throws CustomMoneyTransferException {
		Connection connection = null;
		PreparedStatement lockStatementForSourceAccount = null;
		PreparedStatement lockStatementForDestinationAccount = null;
		PreparedStatement updateStatement = null;
		ResultSet sourceResultSet = null;
		ResultSet desrinationResultSet = null;
		AccountInfo sourceAccount = null;
		AccountInfo destinationAccount = null;
		try {
			connection = DBFactoryImpl.getConnection();
			connection.setAutoCommit(false);
			lockStatementForSourceAccount = connection.prepareStatement(AppConstants.LOCK_ACC_BY_ID_QUERY);
			lockStatementForSourceAccount.setLong(1, userTransaction.getSourceAccountId());
			// locking a row in accounts table for update purpose
			sourceResultSet = lockStatementForSourceAccount.executeQuery();
			if (sourceResultSet.next()) {
				sourceAccount = new AccountInfo(sourceResultSet.getLong("AccountId"),
						sourceResultSet.getString("UserName"), sourceResultSet.getString("EmailId"),
						sourceResultSet.getString("TelephoneNo"), sourceResultSet.getString("Address"),
						sourceResultSet.getBigDecimal("AccountBalance"), sourceResultSet.getString("Currency"));
			}
			lockStatementForDestinationAccount = connection.prepareStatement(AppConstants.LOCK_ACC_BY_ID_QUERY);
			lockStatementForDestinationAccount.setLong(1, userTransaction.getDestinationAccountId());
			desrinationResultSet = lockStatementForDestinationAccount.executeQuery();
			if (desrinationResultSet.next()) {
				destinationAccount = new AccountInfo(desrinationResultSet.getLong("AccountId"),
						desrinationResultSet.getString("UserName"), desrinationResultSet.getString("EmailId"),
						desrinationResultSet.getString("TelephoneNo"), desrinationResultSet.getString("Address"),
						desrinationResultSet.getBigDecimal("AccountBalance"),
						desrinationResultSet.getString("Currency"));
			}
			// Provided account details are not available in Data base
			if (sourceAccount == null || destinationAccount == null) {
				throw new CustomMoneyTransferException("Provided Account Deatils are not available.");
			}
			// source account currency code and transaction currency mismatch
			if (!sourceAccount.getCurrency().equals(userTransaction.getCurrency())) {
				throw new CustomMoneyTransferException(
						"Error Occured in money transfer due to difference in currecy code in source account and transaction");
			}
			// Destination account currency code and transaction currency mismatch
			if (!destinationAccount.getCurrency().equals(userTransaction.getCurrency())) {
				throw new CustomMoneyTransferException(
						"Error Occured in money transfer due to difference in currecy code in destination account and transaction");
			}
			// Source and destination account currency code mismatch
			if (!sourceAccount.getCurrency().equals(destinationAccount.getCurrency())) {
				throw new CustomMoneyTransferException(
						"Error Occured in money transfer due to difference in currecy code in source/destination account");
			}

			BigDecimal fromAccountLeftOver = sourceAccount.getAccountBalance()
					.subtract(userTransaction.getTransferredAmount());
			// Account balance is sufficient for transaction or not
			if (fromAccountLeftOver.compareTo(BigDecimal.valueOf(0)) < 0) {
				throw new CustomMoneyTransferException("Insufficient Account balance!");
			}
			updateStatement = connection.prepareStatement(AppConstants.UPDATE_ACC_BAL_QUERY);
			updateStatement.setBigDecimal(1, fromAccountLeftOver);
			updateStatement.setLong(2, userTransaction.getSourceAccountId());
			updateStatement.addBatch();
			updateStatement.setBigDecimal(1,
					destinationAccount.getAccountBalance().add(userTransaction.getTransferredAmount()));
			updateStatement.setLong(2, userTransaction.getDestinationAccountId());
			updateStatement.addBatch();
			updateStatement.executeBatch();
			connection.commit();
			log.debug("Money has been transferred successfully");
		} catch (SQLException se) {
			try {
				if (connection != null) {
					connection.rollback();
					log.debug("Money transferred got failed, transaction rolled back successfully");
				}
			} catch (SQLException re) {
				throw new CustomMoneyTransferException("Transaction Roll back failed", re);
			}
		} finally {
			log.debug("Cloding connections");
			DbUtils.closeQuietly(connection);
			DbUtils.closeQuietly(sourceResultSet);
			DbUtils.closeQuietly(desrinationResultSet);
			DbUtils.closeQuietly(lockStatementForSourceAccount);
			DbUtils.closeQuietly(lockStatementForDestinationAccount);
			DbUtils.closeQuietly(updateStatement);
		}
	}
}
