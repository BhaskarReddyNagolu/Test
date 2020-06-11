
package com.wipro.cash.transaction.management.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bhaskar
 *
 */
public class UserAccountDetails implements Serializable {

	private static final long serialVersionUID = 6516313342739861805L;

	private Integer accountNo;

	@NotNull(message = "Please enter your user name.")
	@Size(min = 8, max = 30, message = "Your username must less than 30 characters")
	private String userName;

	private String loginId;

	@NotNull(message = "Please enter your password.")
	@Size(min = 8, max = 30, message = "Your password must between 8 and 30 characters")
	private String password;

	private String premiumUser;

	private Integer balanceAmount;

	private Integer redeemPoints;

	private String transferType;

	/**
	 * @return the accountNo
	 */
	public Integer getAccountNo() {
		return accountNo;
	}

	/**
	 * @param accountNo
	 *            the accountNo to set
	 */
	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the premiumUser
	 */
	public String getPremiumUser() {
		return premiumUser;
	}

	/**
	 * @param premiumUser
	 *            the premiumUser to set
	 */
	public void setPremiumUser(String premiumUser) {
		this.premiumUser = premiumUser;
	}

	/**
	 * @return the balanceAmount
	 */
	public Integer getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * @param balanceAmount
	 *            the balanceAmount to set
	 */
	public void setBalanceAmount(Integer balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Integer getRedeemPoints() {
		return redeemPoints;
	}

	public void setRedeemPoints(Integer redeemPoints) {
		this.redeemPoints = redeemPoints;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	@Override
	public String toString() {
		return "UserAccountDetails [accountNo=" + accountNo + ", userName=" + userName + ", loginId=" + loginId
				+ ", password=" + password + ", premiumUser=" + premiumUser + ", balanceAmount=" + balanceAmount +  ", redeemPoints=" + redeemPoints +  ", transferType=" + transferType + "]";
	}

}
