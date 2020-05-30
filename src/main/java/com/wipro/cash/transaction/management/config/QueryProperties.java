
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

	@Value("${all.user.account.details}")
	private String allUserAccountDetails;

	@Value("${account.details.by.userid}")
	private String accountDetailsByUserId;

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
	 * @return the accountDetailsByUserId
	 */
	public String getAccountDetailsByUserId() {
		return accountDetailsByUserId;
	}

	/**
	 * @param accountDetailsByUserId the accountDetailsByUserId to set
	 */
	public void setAccountDetailsByUserId(String accountDetailsByUserId) {
		this.accountDetailsByUserId = accountDetailsByUserId;
	}

}
