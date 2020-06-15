package com.wipro.cash.transaction.management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
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
	private int cashlimitOne;

	@Value("${cash.transfer.swift.cashlimit.two}")
	private int cashlimitTwo;

	@Value("${cash.transfer.swift.cashlimit.three}")
	private int cashlimitThree;

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

	/**
	 * @return the cashlimitOne
	 */
	public int getCashlimitOne() {
		return cashlimitOne;
	}

	/**
	 * @param cashlimitOne the cashlimitOne to set
	 */
	public void setCashlimitOne(int cashlimitOne) {
		this.cashlimitOne = cashlimitOne;
	}

	/**
	 * @return the cashlimitTwo
	 */
	public int getCashlimitTwo() {
		return cashlimitTwo;
	}

	/**
	 * @param cashlimitTwo the cashlimitTwo to set
	 */
	public void setCashlimitTwo(int cashlimitTwo) {
		this.cashlimitTwo = cashlimitTwo;
	}

	/**
	 * @return the cashlimitThree
	 */
	public int getCashlimitThree() {
		return cashlimitThree;
	}

	/**
	 * @param cashlimitThree the cashlimitThree to set
	 */
	public void setCashlimitThree(int cashlimitThree) {
		this.cashlimitThree = cashlimitThree;
	}

}
