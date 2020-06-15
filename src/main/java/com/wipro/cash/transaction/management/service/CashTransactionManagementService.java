package com.wipro.cash.transaction.management.service;

import java.util.List;

import com.wipro.cash.transaction.management.entity.UserAccountDetails;

/**
 * @author Bhaskar
 *
 */
public interface CashTransactionManagementService {

	List<UserAccountDetails> getUserAccountDetails();

	UserAccountDetails findUserAccountByUserId(String userId);
	
	void updateRedeemPoints(String userId, Integer balanceAmount, Integer redeemPoints);

	void updateRedeemPoints(String userId, Integer redeemPoints);

}
