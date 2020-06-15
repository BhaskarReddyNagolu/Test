
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
	
	
	@Value("${account.details.by.accountnumber}")
    private String accountDetailsByAccountNumber;
    
    
    @Value("${all.user.account.transaction.details}")
    private String transactionHistoryById;
    
    @Value("${all.user.account.transaction.details1}")
    private String transactionHistoryById1;

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

	public String getAccountDetailsByAccountNumber() {
		return accountDetailsByAccountNumber;
	}

	public void setAccountDetailsByAccountNumber(String accountDetailsByAccountNumber) {
		this.accountDetailsByAccountNumber = accountDetailsByAccountNumber;
	}

	public String getTransactionHistoryById() {
		return transactionHistoryById;
	}

	public void setTransactionHistoryById(String transactionHistoryById) {
		this.transactionHistoryById = transactionHistoryById;
	}

	public String getTransactionHistoryById1() {
		return transactionHistoryById1;
	}

	public void setTransactionHistoryById1(String transactionHistoryById1) {
		this.transactionHistoryById1 = transactionHistoryById1;
	}
	
	

}
