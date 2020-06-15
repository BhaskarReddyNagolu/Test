
package com.wipro.cash.transaction.management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Bhaskar
 *
 */
@Configuration
@PropertySource(value = { "classpath:/sql/queries.properties" })
public class QueryProperties {

	@Value("${get.all.user.account.details}")
	private String allUserAccountDetails;

	@Value("${get.user.account.details.by.userid}")
	private String userAccountDetailsByUserId;

	@Value("${get.user.account.details.by.account.number}")
	private String userAccountDetailsByAccountNumber;

	@Value("${get.all.user.account.transaction.details.from.account.number}")
	private String userAccountTransactionDetailsFromAccountNumber;

	@Value("${get.all.user.account.transaction.details.to.account.number}")
	private String userAccountTransactionDetailsToAccountNumber;

	@Value("${update.user.account.balacne.by.account.number}")
	private String updateUserAccountDetailsByAccountNumber;

	@Value("${update.user.account.details.by.userid}")
	private String updateUserAccountDetailsByUserId;

	@Value("${update.user.account.balacne.by.account.number}")
	private String userAccountBalacneByAccountNumber;

	@Value("${update.user.account.redeem.points.by.userid}")
	private String userAccountRedeemPointsByUserId;

	/**
	 * @return the allUserAccountDetails
	 */
	public String getAllUserAccountDetails() {
		return allUserAccountDetails;
	}

	/**
	 * @param allUserAccountDetails the allUserAccountDetails to set
	 */
	public void setAllUserAccountDetails(String allUserAccountDetails) {
		this.allUserAccountDetails = allUserAccountDetails;
	}

	/**
	 * @return the userAccountDetailsByUserId
	 */
	public String getUserAccountDetailsByUserId() {
		return userAccountDetailsByUserId;
	}

	/**
	 * @param userAccountDetailsByUserId the userAccountDetailsByUserId to set
	 */
	public void setUserAccountDetailsByUserId(String userAccountDetailsByUserId) {
		this.userAccountDetailsByUserId = userAccountDetailsByUserId;
	}

	/**
	 * @return the userAccountDetailsByAccountNumber
	 */
	public String getUserAccountDetailsByAccountNumber() {
		return userAccountDetailsByAccountNumber;
	}

	/**
	 * @param userAccountDetailsByAccountNumber the
	 *                                          userAccountDetailsByAccountNumber to
	 *                                          set
	 */
	public void setUserAccountDetailsByAccountNumber(String userAccountDetailsByAccountNumber) {
		this.userAccountDetailsByAccountNumber = userAccountDetailsByAccountNumber;
	}

	/**
	 * @return the userAccountTransactionDetailsFromAccountNumber
	 */
	public String getUserAccountTransactionDetailsFromAccountNumber() {
		return userAccountTransactionDetailsFromAccountNumber;
	}

	/**
	 * @param userAccountTransactionDetailsFromAccountNumber the
	 *                                                       userAccountTransactionDetailsFromAccountNumber
	 *                                                       to set
	 */
	public void setUserAccountTransactionDetailsFromAccountNumber(
			String userAccountTransactionDetailsFromAccountNumber) {
		this.userAccountTransactionDetailsFromAccountNumber = userAccountTransactionDetailsFromAccountNumber;
	}

	/**
	 * @return the userAccountTransactionDetailsToAccountNumber
	 */
	public String getUserAccountTransactionDetailsToAccountNumber() {
		return userAccountTransactionDetailsToAccountNumber;
	}

	/**
	 * @param userAccountTransactionDetailsToAccountNumber the
	 *                                                     userAccountTransactionDetailsToAccountNumber
	 *                                                     to set
	 */
	public void setUserAccountTransactionDetailsToAccountNumber(String userAccountTransactionDetailsToAccountNumber) {
		this.userAccountTransactionDetailsToAccountNumber = userAccountTransactionDetailsToAccountNumber;
	}

	/**
	 * @return the updateUserAccountDetailsByAccountNumber
	 */
	public String getUpdateUserAccountDetailsByAccountNumber() {
		return updateUserAccountDetailsByAccountNumber;
	}

	/**
	 * @param updateUserAccountDetailsByAccountNumber the
	 *                                                updateUserAccountDetailsByAccountNumber
	 *                                                to set
	 */
	public void setUpdateUserAccountDetailsByAccountNumber(String updateUserAccountDetailsByAccountNumber) {
		this.updateUserAccountDetailsByAccountNumber = updateUserAccountDetailsByAccountNumber;
	}

	/**
	 * @return the updateUserAccountDetailsByUserId
	 */
	public String getUpdateUserAccountDetailsByUserId() {
		return updateUserAccountDetailsByUserId;
	}

	/**
	 * @param updateUserAccountDetailsByUserId the updateUserAccountDetailsByUserId
	 *                                         to set
	 */
	public void setUpdateUserAccountDetailsByUserId(String updateUserAccountDetailsByUserId) {
		this.updateUserAccountDetailsByUserId = updateUserAccountDetailsByUserId;
	}

	/**
	 * @return the userAccountBalacneByAccountNumber
	 */
	public String getUserAccountBalacneByAccountNumber() {
		return userAccountBalacneByAccountNumber;
	}

	/**
	 * @param userAccountBalacneByAccountNumber the
	 *                                          userAccountBalacneByAccountNumber to
	 *                                          set
	 */
	public void setUserAccountBalacneByAccountNumber(String userAccountBalacneByAccountNumber) {
		this.userAccountBalacneByAccountNumber = userAccountBalacneByAccountNumber;
	}

	/**
	 * @return the userAccountRedeemPointsByUserId
	 */
	public String getUserAccountRedeemPointsByUserId() {
		return userAccountRedeemPointsByUserId;
	}

	/**
	 * @param userAccountRedeemPointsByUserId the userAccountRedeemPointsByUserId to
	 *                                        set
	 */
	public void setUserAccountRedeemPointsByUserId(String userAccountRedeemPointsByUserId) {
		this.userAccountRedeemPointsByUserId = userAccountRedeemPointsByUserId;
	}

}
