package dao;

import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.transfermoneyapp.dao.DBFactory;
import com.transfermoneyapp.exception.CustomMoneyTransferException;
import com.transfermoneyapp.model.AccountInfo;

public class TestAccountInfoDAO {

	private static final DBFactory dbFactory = DBFactory.getDBFactory();

	@BeforeClass
	public static void setup() {
		dbFactory.populateDummyData();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetAllAccounts() throws CustomMoneyTransferException {
		List<AccountInfo> allAccounts = dbFactory.getAccountInfoDAO().getAllAccountsDetails();
		assertTrue(allAccounts.size() > 1);
	}

	@Test
	public void testGetAccountById() throws CustomMoneyTransferException {
		AccountInfo account = dbFactory.getAccountInfoDAO().getAccountDetailById(1L);
		assertTrue(account.getUserName().equals("sachin"));
	}

	@Test
	public void testGetNonExistingAccById() throws CustomMoneyTransferException {
		AccountInfo account = dbFactory.getAccountInfoDAO().getAccountDetailById(100L);
		assertTrue(account == null);
	}

	@Test
	public void testCreateAccount() throws CustomMoneyTransferException {
		BigDecimal balance = new BigDecimal(100).setScale(4, RoundingMode.HALF_EVEN);
		AccountInfo acc = new AccountInfo("test2", "test2@gmail.com", "9013005011", "nehru vihar", balance, "EUR");
		dbFactory.getAccountInfoDAO().addAccount(acc);
		AccountInfo afterCreation = dbFactory.getAccountInfoDAO().getAccountDetailByEmailId("test2@gmail.com");
		assertTrue(afterCreation.getUserName().equals("test2"));
		assertTrue(afterCreation.getCurrency().equals("EUR"));
	}
}