package com.taskforce.moneyapp.services;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.transfermoneyapp.model.MoneyTransactionInfo;

public class TestMoneyTransferService extends TestMoneyTransferAppService {

	@Test
	public void testSuccessfulTransaction() throws IOException, URISyntaxException {
		URI uri = builder.setPath("/money-transfer/transfer").build();
		BigDecimal amount = new BigDecimal(10).setScale(4, RoundingMode.HALF_EVEN);
		MoneyTransactionInfo transaction = new MoneyTransactionInfo("EUR", amount, 1L, 2L);
		String jsonInString = mapper.writeValueAsString(transaction);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
	}

	@Test
	public void testFailedTransactionDueToUnsufficientFund() throws IOException, URISyntaxException {
		URI uri = builder.setPath("/money-transfer/transfer").build();
		BigDecimal amount = new BigDecimal(100000).setScale(4, RoundingMode.HALF_EVEN);
		MoneyTransactionInfo transaction = new MoneyTransactionInfo("EUR", amount, 1L, 2L);
		String jsonInString = mapper.writeValueAsString(transaction);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 500);
	}

	@Test
	public void testFailedTransactionDueToDiffrentCurrencyCode() throws IOException, URISyntaxException {
		URI uri = builder.setPath("/money-transfer/transfer").build();
		BigDecimal amount = new BigDecimal(100).setScale(4, RoundingMode.HALF_EVEN);
		MoneyTransactionInfo transaction = new MoneyTransactionInfo("USD", amount, 3L, 4L);
		String jsonInString = mapper.writeValueAsString(transaction);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 500);
	}
}
