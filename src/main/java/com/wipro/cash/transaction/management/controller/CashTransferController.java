package com.wipro.cash.transaction.management.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.cash.transaction.management.constants.Constants;
import com.wipro.cash.transaction.management.entity.TransactionDetails;
import com.wipro.cash.transaction.management.entity.UserAccountDetails;
import com.wipro.cash.transaction.management.repository.CashTransactionManagementDao;
import com.wipro.cash.transaction.management.repository.UserAccountDetailsRepository;
import com.wipro.cash.transaction.management.service.CashTransactionManagementService;
import com.wipro.cash.transaction.management.service.CashTransferService;

@Controller
public class CashTransferController {

	@Autowired
	private CashTransferService cashTransferService;

	@Autowired
	private CashTransactionManagementDao cashTransactionManagementDao;

	@Autowired
	UserAccountDetailsRepository userAccountDetailsRepository;

	@Autowired
	private CashTransactionManagementService cashTransactionManagementService;

	@PostMapping(value = "/cash/transfer")
	public String cashtransfer(HttpServletRequest request, Model model,
			@RequestParam(value = "transferType", required = false) String transferType,
			@RequestParam(value = "toAccountNo", required = false) int toAccountNo, @RequestParam("amount") int amount,
			@RequestParam("remarks") String remarks) {

		UserAccountDetails userAccountDetails = (UserAccountDetails) request.getSession()
				.getAttribute(Constants.USER_DETAIL);
		String loggedInUserId = userAccountDetails.getLoginId();

		int balaceAmount = userAccountDetails.getBalanceAmount();
		UserAccountDetails toUserAccountDetails = cashTransactionManagementDao
				.findUserAccountByAccountNumber(toAccountNo);

		boolean isCashToBeTransferedOrNot = cashTransferService.cashTransfer(userAccountDetails, toUserAccountDetails,
				amount, transferType, model);

		if (isCashToBeTransferedOrNot) {
			userAccountDetailsRepository.insertCashTransaction(userAccountDetails.getAccountNo(), balaceAmount,
					transferType, toAccountNo, amount, remarks);

			UserAccountDetails updateAccount = cashTransactionManagementDao.findUserAccountByUserId(loggedInUserId);

			List<UserAccountDetails> userAccountDetailsList = new ArrayList<>();

			userAccountDetails.setBalanceAmount(updateAccount.getBalanceAmount());
			userAccountDetails.setRedeemPoints(updateAccount.getRedeemPoints());

			userAccountDetails = cashTransactionManagementService.findUserAccountByUserId(loggedInUserId);
			userAccountDetailsList.add(userAccountDetails);
			model.addAttribute(Constants.USERNAME, userAccountDetails.getUserName());
			request.setAttribute(Constants.USER_DETAILS, userAccountDetailsList);
			return Constants.SUCCESS;
		}

		else {
			return getCashTranferDetails(request, model);
		}
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/cashTransfer")
	public String getCashTranferDetails(HttpServletRequest request, Model model) {

		UserAccountDetails details = (UserAccountDetails) request.getSession().getAttribute(Constants.USER_DETAIL);
		List<UserAccountDetails> userAccountDetailsList = (List<UserAccountDetails>) request.getSession()
				.getAttribute(Constants.USER_DETAILS);

		List<Integer> toAccountNumbers = new ArrayList<>();
		for (UserAccountDetails accountNumber : userAccountDetailsList) {
			toAccountNumbers.add(accountNumber.getAccountNo());
		}

		String loggedInUserId = details.getLoginId();
		if (Objects.nonNull(loggedInUserId)) {
			UserAccountDetails userAccountDetails = cashTransactionManagementDao
					.findUserAccountByUserId(loggedInUserId);

			List<String> listTransferType = getTransferType(userAccountDetails.getPremiumUser());

			model.addAttribute("loggedInUserId", loggedInUserId);
			model.addAttribute("accountNo", userAccountDetails.getAccountNo());
			model.addAttribute("balanceAmount", userAccountDetails.getBalanceAmount());
			model.addAttribute("toAccountNumbers", toAccountNumbers);
			model.addAttribute("listTransferType", listTransferType);
		}

		return Constants.CASHTRANSFER;
	}

	public List<String> getTransferType(String isPremiumUser) {
		List<String> transferTypes = new ArrayList<>();
		if ("Yes".equalsIgnoreCase(isPremiumUser)) {
			transferTypes.add("Wire Transfer");
			transferTypes.add("Saving Account");
			transferTypes.add("Swift Payment");
		} else {
			transferTypes.add("Saving Account");
			transferTypes.add("Swift Payment");
		}
		return transferTypes;
	}

	@GetMapping(value = "/transactionHistory")
	public String getCashTransactionDetails(HttpServletRequest request, Model model) {
		List<UserAccountDetails> listAllAccount = cashTransactionManagementService.getUserAccountDetails();
		request.getSession().setAttribute(Constants.USER_DETAILS, listAllAccount);

		UserAccountDetails details = (UserAccountDetails) request.getSession().getAttribute(Constants.USER_DETAIL);
		Integer userId = 0;

		String loggedInUserId = details.getLoginId();
		if (Objects.nonNull(loggedInUserId)) {
			UserAccountDetails userAccountDetails = cashTransactionManagementDao
					.findUserAccountByUserId(loggedInUserId);
			userId = userAccountDetails.getAccountNo();
			model.addAttribute("accountNo", userAccountDetails.getAccountNo());
			model.addAttribute("balanceAmount", userAccountDetails.getBalanceAmount());
		}

		List<TransactionDetails> accountTransactonsForDebit = userAccountDetailsRepository
				.accountTransactonsForDebit(userId);
		List<TransactionDetails> accountTransactonsForCredit = userAccountDetailsRepository
				.accountTransactonsForCredit(userId);

		request.getSession().setAttribute("tranactionDetails", accountTransactonsForDebit);
		request.getSession().setAttribute("tranactionDetails1", accountTransactonsForCredit);

		return Constants.TRANSACTION_HISTORY;
	}

}
