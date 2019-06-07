package com.transfermoneyapp.dao;

import com.transfermoneyapp.dao.impl.DBFactoryImpl;

/**
 * @author sachin.sharma 
 * Factory class to create instances of other classes in
 *         factory design pattern
 *
 */
public abstract class DBFactory {

	public abstract AccountInfoDAO getAccountInfoDAO();

	public abstract MoneyTransferDAO getMoneyTransferDAO();

	public abstract void populateDummyData();

	public static DBFactory getDBFactory() {
		return new DBFactoryImpl();
	}
}
