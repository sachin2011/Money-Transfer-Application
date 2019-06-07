package com.transfermoneyapp.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.transfermoneyapp.dao.DBFactory;
import com.transfermoneyapp.exception.CustomMoneyTransferException;
import com.transfermoneyapp.model.AccountInfo;

/**
 * @author sachin.sharma 
 * Rest services for add/get/check account
 */
@Path("/account-management")
@Produces(MediaType.APPLICATION_JSON)
public class AccountManagementService {

	private final DBFactory dbFactory = DBFactory.getDBFactory();

	/**
	 * To get all account detaila present in application
	 * 
	 * @return list of accounts
	 * @throws CustomMoneyTransferException
	 */
	@GET
	@Path("/accounts")
	public List<AccountInfo> getAllAccountsDetails() throws CustomMoneyTransferException {
		return dbFactory.getAccountInfoDAO().getAllAccountsDetails();
	}

	/**
	 * To get the balance of account that is present in data base
	 * 
	 * @param accountId
	 * @return account balance
	 * @throws CustomMoneyTransferException
	 */
	@GET
	@Path("/{accountId}/balance")
	public BigDecimal getAccountBalance(@PathParam("accountId") long accountId) throws CustomMoneyTransferException {
		AccountInfo account = dbFactory.getAccountInfoDAO().getAccountDetailById(accountId);

		if (account == null) {
			throw new WebApplicationException("Provided Account Info not available", Response.Status.NO_CONTENT);
		}
		return account.getAccountBalance();
	}

	/**
	 * To add/create an account
	 * 
	 * @param account
	 * @return HTTP response 201 when created successfully
	 * @throws CustomMoneyTransferException
	 */
	@POST
	@Path("/add")
	public Response addAccount(AccountInfo account) throws CustomMoneyTransferException {
		if (dbFactory.getAccountInfoDAO().getAccountDetailByEmailId(account.getEmailId()) != null) {
			throw new WebApplicationException("User email id already present", Response.Status.CONFLICT);
		}
		if (dbFactory.getAccountInfoDAO().getAccountDetailByTelephoneNo(account.getTelphoneNo()) != null) {
			throw new WebApplicationException("User telephone No already present", Response.Status.CONFLICT);
		}
		dbFactory.getAccountInfoDAO().addAccount(account);
		return Response.status(Response.Status.CREATED).build();
	}
}
