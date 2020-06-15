/**
 *  Harsha
 */
package com.wipro.cash.transaction.management.repository;

import java.util.List;

import com.wipro.cash.transaction.management.entity.TransactionDetails;

/**
 * @author Bhaskar
 *
 */
public interface UserAccountDetailsRepositoryI {

	Integer insertCashTransaction(Integer fromAccountNumber, Integer balaceAmount, String transferType,
			Integer toAccountNumber, Integer transferAmount, String remarks);

	List<TransactionDetails> accountTransactonsForDebit(Integer userId);

	List<TransactionDetails> accountTransactonsForCredit(Integer userId);

}
