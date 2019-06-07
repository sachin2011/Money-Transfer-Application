package com.transfermoneyapp.dao;

import com.transfermoneyapp.exception.CustomMoneyTransferException;
import com.transfermoneyapp.model.MoneyTransactionInfo;

/**
 * @author sachin.sharma
 * Interface for Money transfer data layer
 */
public interface MoneyTransferDAO {

	void transferMoney(MoneyTransactionInfo userTransaction) throws CustomMoneyTransferException;
}
