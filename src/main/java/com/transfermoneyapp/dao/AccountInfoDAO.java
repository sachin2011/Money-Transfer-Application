package com.transfermoneyapp.dao;

import java.util.List;

import com.transfermoneyapp.exception.CustomMoneyTransferException;
import com.transfermoneyapp.model.AccountInfo;

/**
 * @author sachin.sharma 
 * Interface for performing Account related operations
 *
 */
public interface AccountInfoDAO {

	List<AccountInfo> getAllAccountsDetails() throws CustomMoneyTransferException;

	AccountInfo getAccountDetailById(long accountId) throws CustomMoneyTransferException;

	void addAccount(AccountInfo account) throws CustomMoneyTransferException;

	AccountInfo getAccountDetailByEmailId(String emailId) throws CustomMoneyTransferException;

	AccountInfo getAccountDetailByTelephoneNo(String telephoneNo) throws CustomMoneyTransferException;
}
