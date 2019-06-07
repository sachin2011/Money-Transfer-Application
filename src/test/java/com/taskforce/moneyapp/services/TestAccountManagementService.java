package com.taskforce.moneyapp.services;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.transfermoneyapp.model.AccountInfo;

public class TestAccountManagementService extends TestMoneyTransferAppService {

	@Test
	public void testGetAllAccounts() throws IOException, URISyntaxException {
		URI uri = builder.setPath("/account-management/accounts").build();
		HttpGet request = new HttpGet(uri);
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
		String jsonString = EntityUtils.toString(response.getEntity());
		AccountInfo[] accounts = mapper.readValue(jsonString, AccountInfo[].class);
		assertTrue(accounts.length > 0);
	}

	@Test
	public void testGetAccountBalance() throws IOException, URISyntaxException {
		URI uri = builder.setPath("/account-management/1/balance").build();
		HttpGet request = new HttpGet(uri);
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
		String balance = EntityUtils.toString(response.getEntity());
		BigDecimal res = new BigDecimal(balance).setScale(4, RoundingMode.HALF_EVEN);
		BigDecimal db = new BigDecimal(800).setScale(4, RoundingMode.HALF_EVEN);
		assertTrue(res.equals(db));
	}

	@Test
	public void testCreateAccount() throws IOException, URISyntaxException {
		URI uri = builder.setPath("/account-management/add").build();
		BigDecimal balance = new BigDecimal(100).setScale(4, RoundingMode.HALF_EVEN);
		AccountInfo acc = new AccountInfo("abc", "abc@gmail.com", "123456", "nehru vihar", balance, "EUR");
		String jsonInString = mapper.writeValueAsString(acc);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 201);
	}

	@Test
	public void testCreateExistingUserEmailAccount() throws IOException, URISyntaxException {
		URI uri = builder.setPath("/account-management/add").build();
		BigDecimal balance = new BigDecimal(10).setScale(4, RoundingMode.HALF_EVEN);
		AccountInfo acc = new AccountInfo("test", "sac@gmail.com", "9013004011", "nehru vihar", balance, "EUR");
		String jsonInString = mapper.writeValueAsString(acc);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 409);

	}

	@Test
	public void testCreateExistingUserTelephoneAccount() throws IOException, URISyntaxException {
		URI uri = builder.setPath("/account-management/add").build();
		BigDecimal balance = new BigDecimal(10).setScale(4, RoundingMode.HALF_EVEN);
		AccountInfo acc = new AccountInfo("test", "xyz@gmail.com", "9013004011", "nehru vihar", balance, "EUR");
		String jsonInString = mapper.writeValueAsString(acc);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 409);
	}
}
