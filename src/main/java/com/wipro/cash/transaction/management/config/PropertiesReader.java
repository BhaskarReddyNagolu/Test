package com.wipro.cash.transaction.management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesReader {

	@Value("${cash.transfer.type.wiretransfer}")
	private String wiretransfer;

	@Value("${cash.transfer.type.savingaccount}")
	private String savingaccount;

	@Value("${cash.transfer.type.swiftpayment}")
	private String swiftpayment;

	@Value("${cash.transfer.type.redeem.percentage}")
	private int percentage;

	@Value("${cash.transfer.swift.cashlimit.one}")
	private int cashlimit1;

	@Value("${cash.transfer.swift.cashlimit.two}")
	private int cashlimit2;

	@Value("${cash.transfer.swift.cashlimit.three}")
	private int cashlimit3;
    
	
	@Value("${cash.transfer.type.wiretransfer}")
	public String getWiretransfer() {
		return wiretransfer;
	}

	public void setWiretransfer(String wiretransfer) {
		this.wiretransfer = wiretransfer;
	}

	public String getSavingaccount() {
		return savingaccount;
	}

	public void setSavingaccount(String savingaccount) {
		this.savingaccount = savingaccount;
	}

	public String getSwiftpayment() {
		return swiftpayment;
	}

	public void setSwiftpayment(String swiftpayment) {
		this.swiftpayment = swiftpayment;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getCashlimit1() {
		return cashlimit1;
	}

	public void setCashlimit1(int cashlimit1) {
		this.cashlimit1 = cashlimit1;
	}

	public int getCashlimit2() {
		return cashlimit2;
	}

	public void setCashlimit2(int cashlimit2) {
		this.cashlimit2 = cashlimit2;
	}

	public int getCashlimit3() {
		return cashlimit3;
	}

	public void setCashlimit3(int cashlimit3) {
		this.cashlimit3 = cashlimit3;
	}

}
