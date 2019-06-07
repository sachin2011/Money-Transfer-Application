package com.transfermoneyapp.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sachin.sharma 
 * POJO class for Account related details
 */
public class AccountInfo {

	@JsonIgnore
	private long accountId;

	@JsonProperty(required = true)
	private String userName;

	@JsonProperty(required = true)
	private String emailId;

	@JsonProperty(required = true)
	private String telphoneNo;

	@JsonProperty(required = true)
	private String address;

	@JsonProperty(required = true)
	private BigDecimal accountBalance;

	@JsonProperty(required = true)
	private String currency;

	public AccountInfo() {
	}

	public AccountInfo(String userName, String emailId, String telephoneNo, String address, BigDecimal accountBalance,
			String currency) {
		this.userName = userName;
		this.accountBalance = accountBalance;
		this.currency = currency;
		this.telphoneNo = telephoneNo;
		this.address = address;
		this.emailId = emailId;
	}

	public AccountInfo(long accountId, String userName, String emailId, String telephoneNo, String address,
			BigDecimal accountBalance, String currency) {
		this.accountId = accountId;
		this.userName = userName;
		this.accountBalance = accountBalance;
		this.currency = currency;
		this.telphoneNo = telephoneNo;
		this.address = address;
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getTelphoneNo() {
		return telphoneNo;
	}

	public String getAddress() {
		return address;
	}

	public long getAccountId() {
		return accountId;
	}

	public String getUserName() {
		return userName;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public String getCurrency() {
		return currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((telphoneNo == null) ? 0 : telphoneNo.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountInfo other = (AccountInfo) obj;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (accountId != other.accountId)
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (telphoneNo == null) {
			if (other.telphoneNo != null)
				return false;
		} else if (!telphoneNo.equals(other.telphoneNo))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
