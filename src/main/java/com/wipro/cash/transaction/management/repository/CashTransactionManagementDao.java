
package com.wipro.cash.transaction.management.repository;

import java.util.List;

import com.wipro.cash.transaction.management.entity.UserAccountDetails;

/**
 * @author Bhaskar
 *
 */
public interface CashTransactionManagementDao {

	List<UserAccountDetails> getUserAccountDetails();
	
	UserAccountDetails findUserAccountByUserId(String userId);
	
	void updateRedeemPoints(String userId, Integer balanceAmount, Integer redeemPoints);
}
