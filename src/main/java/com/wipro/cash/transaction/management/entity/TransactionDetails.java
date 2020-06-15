package com.wipro.cash.transaction.management.entity;

import java.io.Serializable;

public class TransactionDetails implements Serializable {

	private static final long serialVersionUID = -5091926068296601978L;

	private Integer fromAccountNumber;
	private Integer balaceAmount;
	private String transferType;
	private Integer toAccountNumber;
	private Integer transferAmount;
	private String remarks;

	public Integer getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setFromAccountNumber(Integer fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public Integer getBalaceAmount() {
		return balaceAmount;
	}

	public void setBalaceAmount(Integer balaceAmount) {
		this.balaceAmount = balaceAmount;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public Integer getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(Integer toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public Integer getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(Integer transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
