package com.wipro.cash.transaction.management.exception;

/**
 * @author Bhaskar
 *
 */
public class CashTransactionManagementException extends RuntimeException {

	private static final long serialVersionUID = 7350314032241024215L;

	public CashTransactionManagementException() {
		super();
	}

	public CashTransactionManagementException(Throwable cause) {
		super(cause);
	}

	public CashTransactionManagementException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public CashTransactionManagementException(String exceptionMessage, Throwable exceptionCause) {
		super(exceptionMessage, exceptionCause);
	}

}