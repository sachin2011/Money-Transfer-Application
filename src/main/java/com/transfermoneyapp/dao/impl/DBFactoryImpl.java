package com.transfermoneyapp.dao.impl;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.h2.tools.RunScript;

import com.transfermoneyapp.dao.AccountInfoDAO;
import com.transfermoneyapp.dao.DBFactory;
import com.transfermoneyapp.dao.MoneyTransferDAO;
import com.trasfermoneyapp.constants.AppConstants;

/**
 * @author sachin.sharma
 * Implementation class for db factory
 */
public class DBFactoryImpl extends DBFactory {
	private static Logger log = Logger.getLogger(DBFactoryImpl.class);

	private final AccountInfoDAOImpl AccountInfoDAO = new AccountInfoDAOImpl();
	private final MoneyTransferDAOImpl MoneyTransferDAO = new MoneyTransferDAOImpl();

	public DBFactoryImpl() {
		DbUtils.loadDriver(AppConstants.DB_DRIVER);
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(AppConstants.DB_CONNECTION_URL, AppConstants.DB_USERNAME,
				AppConstants.DB_PASSWORD);
	}

	public AccountInfoDAO getAccountInfoDAO() {
		return AccountInfoDAO;
	}

	public MoneyTransferDAO getMoneyTransferDAO() {
		return MoneyTransferDAO;
	}

	/*
	 * (non-Javadoc) Method to populate table and some dummy data based on
	 * dummyData.sql file
	 */
	@Override
	public void populateDummyData() {
		Connection dbConnection = null;
		try {
			dbConnection = DBFactoryImpl.getConnection();
			RunScript.execute(dbConnection, new FileReader("src/main/resources/dummyData.sql"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			log.debug("closing connection");
			DbUtils.closeQuietly(dbConnection);
		}
	}
}
