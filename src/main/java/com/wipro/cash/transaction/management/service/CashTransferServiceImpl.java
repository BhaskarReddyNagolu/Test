package com.wipro.cash.transaction.management.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.wipro.cash.transaction.management.config.PropertiesReader;
import com.wipro.cash.transaction.management.constants.Constants;
import com.wipro.cash.transaction.management.entity.UserAccountDetails;
import com.wipro.cash.transaction.management.repository.CashTransactionManagementDao;
import com.wipro.cash.transaction.management.repository.UserAccountDetailsRepository;

@Service
public class CashTransferServiceImpl {

	private UserAccountDetailsRepository userAccountDetailsRepository;
	private PropertiesReader propertiesReader;

	@Autowired
	CashTransactionManagementDao CashTransactionDao;

	@Autowired
	CashTransactionManagementService cashTransactionManagementService;

	@Autowired
	public CashTransferServiceImpl(UserAccountDetailsRepository userAccountDetailsRepository,
			PropertiesReader propertiesReader) {
		this.userAccountDetailsRepository = userAccountDetailsRepository;
		this.propertiesReader = propertiesReader;
	}

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
		Integer account_number = userAccountDetails.getAccountNo();
		String loggedInUserId = userAccountDetails.getLoginId();
		if (amount <= 0) {
			model.addAttribute(Constants.ERROR, "Enter Amount more than Zero.");
			return false;
		}

		if (transferType.equalsIgnoreCase(propertiesReader.getWiretransfer())) {
			int redeemPoints =  ((amount) * (propertiesReader.getPercentage())) / (100);
			cashTransactionManagementService.updateRedeemPoints(loggedInUserId, redeemPoints);

			if (amount > balanceAmount) {
				model.addAttribute(Constants.ERROR, "Insufficient balance.");
			} else {
				balanceAmount = balanceAmount - amount;
				CashTransactionDao.updateBalanceAmount(account_number, balanceAmount);
				return true;
			}

			int fee = getFee(amount);
			amount += fee;
			if (amount <= 0) {
				model.addAttribute(Constants.ERROR, "Amount should be more than Zero.");
				return false;
			}
			else if (amount > balanceAmount) {
				model.addAttribute(Constants.ERROR, "Insufficient balance.");
				return false;
			}
			else {
				balanceAmount = balanceAmount - amount;
				CashTransactionDao.updateBalanceAmount(account_number, balanceAmount);
				return true;
			}
		} else {
			if (amount > balanceAmount) {
				model.addAttribute(Constants.ERROR, "Insufficient balance.");
				return false;
			} else {
				balanceAmount = balanceAmount - amount;
				CashTransactionDao.updateBalanceAmount(account_number, balanceAmount);
				return true;
			}

		}
		//return false;

	}

	public void deposit(UserAccountDetails userAccountDetails, Integer amount, Model model) {
		Integer account_number = userAccountDetails.getAccountNo();
		if (amount <= 0) {
			model.addAttribute(Constants.ERROR, "Amount should be more than Zero.");
			return;
		} else {
			Integer balanceAmount = userAccountDetails.getBalanceAmount();
			Integer accountNo = userAccountDetails.getAccountNo();
			balanceAmount = balanceAmount + amount;
			CashTransactionDao.updateBalanceAmount(account_number, balanceAmount);
		}
	}

	public int getFee(Integer amount) {
		int fee = 0;
		if (amount > propertiesReader.getCashlimit1() && amount <= propertiesReader.getCashlimit2())
			fee = 10;
		else if (amount > propertiesReader.getCashlimit2() && amount <= propertiesReader.getCashlimit3())
			fee = 15;
		else if (amount > propertiesReader.getCashlimit3())
			fee = 20;
		return fee;

	}

	public void getUserAccountDetails(HttpServletRequest request) {
		List<UserAccountDetails> userAccountDetailsList = (List<UserAccountDetails>) request.getSession()
				.getAttribute("listAllAccount");
		for (UserAccountDetails user : userAccountDetailsList) {
			System.out.println(user);
		}

	}

}
