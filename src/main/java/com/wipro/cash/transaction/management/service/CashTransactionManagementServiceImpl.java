
package com.wipro.cash.transaction.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.cash.transaction.management.entity.UserAccountDetails;
import com.wipro.cash.transaction.management.repository.CashTransactionManagementDao;

/**
 * @author Bhaskar
 *
 */
@Service
public class CashTransactionManagementServiceImpl implements CashTransactionManagementService {

	@Autowired
	private CashTransactionManagementDao cashTransactionManagementDao;

	@Override
	public List<UserAccountDetails> getUserAccountDetails() {
		return cashTransactionManagementDao.getUserAccountDetails();
	}

	@Override
	public UserAccountDetails findUserAccountByUserId(String userId) {
		return cashTransactionManagementDao.findUserAccountByUserId(userId);
	}

	@Override
	public void updateRedeemPoints(String userId, Integer balanceAmount, Integer redeemPoints) {
		cashTransactionManagementDao.updateRedeemPoints(userId, balanceAmount, redeemPoints);

	}

	@Override
	public void updateRedeemPoints(String userId, Integer redeemPoints) {
		cashTransactionManagementDao.updateRedeemPoints(userId, redeemPoints);

	}

}
