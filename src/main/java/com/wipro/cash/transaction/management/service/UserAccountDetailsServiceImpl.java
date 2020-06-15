
package com.wipro.cash.transaction.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.cash.transaction.management.entity.TransactionDetails;
import com.wipro.cash.transaction.management.repository.UserAccountDetailsRepositoryI;

/**
 * @author Bhaskar
 *
 */
@Service
public class UserAccountDetailsServiceImpl implements UserAccountDetailsService {

	@Autowired
	private UserAccountDetailsRepositoryI userAccountDetailsRepository;

	@Override
	public int insertCashTransaction(Integer fromAccountNumber, Integer balaceAmount, String transferType,
			Integer toAccountNumber, Integer transferAmount, String remarks) {
		return userAccountDetailsRepository.insertCashTransaction(fromAccountNumber, balaceAmount, transferType,
				toAccountNumber, transferAmount, remarks);
	}

	@Override
	public List<TransactionDetails> accountTransactonsForDebit(Integer userId) {
		return userAccountDetailsRepository.accountTransactonsForDebit(userId);
	}

	@Override
	public List<TransactionDetails> accountTransactonsForCredit(Integer userId) {
		return userAccountDetailsRepository.accountTransactonsForCredit(userId);
	}

}
