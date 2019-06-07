package com.transfermoneyapp.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sachin.sharma 
 * POJO class for transaction related details
 */
public class MoneyTransactionInfo {

	@JsonProperty(required = true)
	private String currency;

	@JsonProperty(required = true)
	private BigDecimal transferredAmount;

	@JsonProperty(required = true)
	private Long sourceAccountId;

	@JsonProperty(required = true)
	private Long destinationAccountId;

	public MoneyTransactionInfo() {
	}

	public MoneyTransactionInfo(String currency, BigDecimal transferredAmount, Long sourceAccountId,
			Long destinationAccountId) {
		this.currency = currency;
		this.transferredAmount = transferredAmount;
		this.sourceAccountId = sourceAccountId;
		this.destinationAccountId = destinationAccountId;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getTransferredAmount() {
		return transferredAmount;
	}

	public Long getSourceAccountId() {
		return sourceAccountId;
	}

	public Long getDestinationAccountId() {
		return destinationAccountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transferredAmount == null) ? 0 : transferredAmount.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((destinationAccountId == null) ? 0 : destinationAccountId.hashCode());
		result = prime * result + ((sourceAccountId == null) ? 0 : sourceAccountId.hashCode());
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
		MoneyTransactionInfo other = (MoneyTransactionInfo) obj;
		if (transferredAmount == null) {
			if (other.transferredAmount != null)
				return false;
		} else if (!transferredAmount.equals(other.transferredAmount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (destinationAccountId == null) {
			if (other.destinationAccountId != null)
				return false;
		} else if (!destinationAccountId.equals(other.destinationAccountId))
			return false;
		if (sourceAccountId == null) {
			if (other.sourceAccountId != null)
				return false;
		} else if (!sourceAccountId.equals(other.sourceAccountId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MoneyTransactionInfo [currency=" + currency + ", transferredAmount=" + transferredAmount
				+ ", sourceAccountId=" + sourceAccountId + ", destinationAccountId=" + destinationAccountId + "]";
	}
}
