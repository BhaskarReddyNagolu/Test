package com.wipro.cash.transaction.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.wipro.cash.transaction.management.config.PropertiesReader;
import com.wipro.cash.transaction.management.constants.Constants;
import com.wipro.cash.transaction.management.entity.UserAccountDetails;
import com.wipro.cash.transaction.management.repository.CashTransactionManagementDao;

@Service
public class CashTransferServiceImpl {

	@Autowired
	private PropertiesReader propertiesReader;

	@Autowired
	private CashTransactionManagementDao cashTransactionDao;

	@Autowired
	CashTransactionManagementService cashTransactionManagementService;

	public boolean cashTransfer(UserAccountDetails fromUserAccountDetails, UserAccountDetails toUserAccountDetails,
			int amount, String transferType, Model model) {
		boolean isCashToBeTransfered = false;
		boolean istransfer = withdraw(fromUserAccountDetails, amount, transferType, model);
		if (istransfer) {
			deposit(toUserAccountDetails, amount, model);
			model.addAttribute(Constants.MESSAGE, "Transaction completed.");
			isCashToBeTransfered = true;
		}
		return isCashToBeTransfered;
	}

	public boolean withdraw(UserAccountDetails userAccountDetails, int amount, String transferType, Model model) {
		int balanceAmount = userAccountDetails.getBalanceAmount();
		Integer accountNumber = userAccountDetails.getAccountNo();
		String loggedInUserId = userAccountDetails.getLoginId();
		if (amount <= 0) {
			model.addAttribute(Constants.ERROR, "Enter Amount more than Zero.");
			return false;
		}

		if (transferType.equalsIgnoreCase(propertiesReader.getWiretransfer())) {
			int redeemPoints = ((amount) * (propertiesReader.getPercentage())) / (100);
			cashTransactionManagementService.updateRedeemPoints(loggedInUserId, redeemPoints);

			if (amount > balanceAmount) {
				model.addAttribute(Constants.ERROR, Constants.INSUFFICIENT_BALANCE);
			} else {
				balanceAmount = balanceAmount - amount;
				cashTransactionDao.updateBalanceAmount(accountNumber, balanceAmount);
				return true;
			}

			int fee = getFee(amount);
			amount += fee;
			if (amount <= 0) {
				model.addAttribute(Constants.ERROR, "Amount should be more than Zero.");
				return false;
			} else if (amount > balanceAmount) {
				model.addAttribute(Constants.ERROR, Constants.INSUFFICIENT_BALANCE);
				return false;
			} else {
				balanceAmount = balanceAmount - amount;
				cashTransactionDao.updateBalanceAmount(accountNumber, balanceAmount);
				return true;
			}
		} else {
			if (amount > balanceAmount) {
				model.addAttribute(Constants.ERROR, Constants.INSUFFICIENT_BALANCE);
				return false;
			} else {
				balanceAmount = balanceAmount - amount;
				cashTransactionDao.updateBalanceAmount(accountNumber, balanceAmount);
				return true;
			}

		}
	}

	public void deposit(UserAccountDetails userAccountDetails, Integer amount, Model model) {
		Integer accountNo = userAccountDetails.getAccountNo();
		if (amount <= 0) {
			model.addAttribute(Constants.ERROR, "Amount should be more than Zero.");
		} else {
			Integer balanceAmount = userAccountDetails.getBalanceAmount();
			balanceAmount = balanceAmount + amount;
			cashTransactionDao.updateBalanceAmount(accountNo, balanceAmount);
		}
	}

	public int getFee(Integer amount) {
		int fee = 0;
		if (amount > propertiesReader.getCashlimitOne() && amount <= propertiesReader.getCashlimitTwo())
			fee = 10;
		else if (amount > propertiesReader.getCashlimitTwo() && amount <= propertiesReader.getCashlimitThree())
			fee = 15;
		else if (amount > propertiesReader.getCashlimitThree())
			fee = 20;
		return fee;

	}

}
