package com.transfermoneyapp.service;

import java.util.Currency;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.transfermoneyapp.dao.DBFactory;
import com.transfermoneyapp.dao.impl.AccountInfoDAOImpl;
import com.transfermoneyapp.exception.CustomMoneyTransferException;
import com.transfermoneyapp.model.MoneyTransactionInfo;

/**
 * @author sachin.sharma 
 * Class handling rest service for money transfer
 */
@Path("/money-transfer")
@Produces(MediaType.APPLICATION_JSON)
public class MoneyTransferService {

	private DBFactory dbFactory = DBFactory.getDBFactory();
	private static Logger log = Logger.getLogger(AccountInfoDAOImpl.class);

	/**
	 * Rest method for transfering funds
	 * 
	 * @param transaction
	 * @return HTTP response 200 when transferred successfully, otherwise 500
	 * @throws CustomMoneyTransferException
	 */
	@POST
	@Path("/transfer")
	public Response transferFund(MoneyTransactionInfo transaction) throws CustomMoneyTransferException {
		if (transaction.getTransferredAmount().signum() <= 0) {
			log.debug("Amount enetered as zero or negative number");
			throw new WebApplicationException("Please provide amount as non-zero postive number",
					Response.Status.BAD_REQUEST);
		}
		String currency = transaction.getCurrency();
		Currency instance = Currency.getInstance(currency);
		// To check if the provided currency code is valid or not
		if (instance.getCurrencyCode().equals(currency)) {
			dbFactory.getMoneyTransferDAO().transferMoney(transaction);
			return Response.status(Response.Status.OK).build();
		} else {
			throw new WebApplicationException("Provided currency Code is not valid, please enter valid code",
					Response.Status.BAD_REQUEST);
		}
	}
}
