package com.wipro.cash.transaction.management.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.cash.transaction.management.config.PropertiesReader;
import com.wipro.cash.transaction.management.constants.Constants;
import com.wipro.cash.transaction.management.entity.TransactionDetails;
import com.wipro.cash.transaction.management.entity.UserAccountDetails;
import com.wipro.cash.transaction.management.repository.CashTransactionManagementDao;
import com.wipro.cash.transaction.management.service.CashTransactionManagementService;
import com.wipro.cash.transaction.management.service.CashTransferServiceImpl;
import com.wipro.cash.transaction.management.repository.UserAccountDetailsRepository;

@Controller
public class CashTransferController {

	private CashTransferServiceImpl cashTransferService;
	private PropertiesReader propertiesReader;
	private CashTransactionManagementDao cashTransactionManagementDao;
	@Autowired
	UserAccountDetailsRepository userAccountDetailsRepository;

	@Autowired
	private CashTransactionManagementService cashTransactionManagementService;

	@Autowired
	public CashTransferController(CashTransferServiceImpl cashTransferService,
			CashTransactionManagementDao cashTransactionManagementDao, PropertiesReader propertiesReader) {
		this.cashTransferService = cashTransferService;
		this.cashTransactionManagementDao = cashTransactionManagementDao;
		this.propertiesReader = propertiesReader;
	}

	@PostMapping(value = "/cash/transfer")
	public String cashtransfer(HttpServletRequest request, Model model,
			@RequestParam(value = "transferType", required = false) String transferType,
			@RequestParam(value = "toAccountNo", required = false) int toAccountNo, @RequestParam("amount") int amount,
			@RequestParam("remarks") String remarks) {

		UserAccountDetails userAccountDetails = (UserAccountDetails) request.getSession().getAttribute("userDetail");
		String userName = userAccountDetails.getUserName();
		String loggedInUserId = userAccountDetails.getLoginId();

		int balace_amount = userAccountDetails.getBalanceAmount();
		UserAccountDetails toUserAccountDetails = cashTransactionManagementDao
				.findUserAccountByAccountNumber(toAccountNo);
		
		boolean isCashToBeTransferedOrNot = cashTransferService.cashTransfer(userAccountDetails, toUserAccountDetails, amount, transferType, model);
		
		if(isCashToBeTransferedOrNot) {
			userAccountDetailsRepository.insertCashTransaction(userAccountDetails.getAccountNo(), balace_amount,
					transferType, toAccountNo, amount, remarks);
			UserAccountDetails updateAccount = cashTransactionManagementDao.findUserAccountByUserId(loggedInUserId);

			List<UserAccountDetails> userAccountDetailsList = new ArrayList<>();
			/*
			 * userAccountDetailsList.add(updateAccount);
			 * request.setAttribute("userDetails", userAccountDetailsList);
			 * model.addAttribute(Constants.USERNAME, userName);
			 */

			userAccountDetails.setBalanceAmount(updateAccount.getBalanceAmount());
			userAccountDetails.setRedeemPoints(updateAccount.getRedeemPoints());

			userAccountDetails = cashTransactionManagementService.findUserAccountByUserId(loggedInUserId);
			userAccountDetailsList.add(userAccountDetails);
			model.addAttribute(Constants.USERNAME, userAccountDetails.getUserName());
			request.setAttribute("userDetails", userAccountDetailsList);
			return Constants.SUCCESS;
		}

		else {
			String page = getCashTranferDetails(request,model);
			return page;
		}
	}

	@GetMapping(value = "/cashTransfer")
	public String getCashTranferDetails(HttpServletRequest request, Model model) {

		UserAccountDetails details = (UserAccountDetails) request.getSession().getAttribute("userDetail");
		// cashTransferService.getUserAccountDetails(request);
		List<UserAccountDetails> userAccountDetailsList = (List<UserAccountDetails>) request.getSession()
				.getAttribute("listAllAccount");
		List<Integer> toAccountNumbers = new ArrayList<>();
		for (UserAccountDetails accountNumber : userAccountDetailsList) {
			toAccountNumbers.add(accountNumber.getAccountNo());
		}

		String loggedInUserId = details.getLoginId();

		if (Objects.nonNull(loggedInUserId)) {
			UserAccountDetails userAccountDetails = cashTransactionManagementDao
					.findUserAccountByUserId(loggedInUserId);

			List<String> listTransferType = getTransferType(userAccountDetails.getPremiumUser());

			if (Objects.nonNull(userAccountDetails)) {
				model.addAttribute("loggedInUserId", loggedInUserId);
				model.addAttribute("accountNo", userAccountDetails.getAccountNo());
				model.addAttribute("balanceAmount", userAccountDetails.getBalanceAmount());
				model.addAttribute("toAccountNumbers", toAccountNumbers);
				model.addAttribute("listTransferType", listTransferType);

			}
		}

		return "cashTransfer";
	}

	//
	//
	public List<String> getTransferType(String isPremiumUser) {
		List<String> transferTypes = new ArrayList<String>();
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

		cashTransferService.getUserAccountDetails(request);
		List<UserAccountDetails> userAccountDetailsList = (List<UserAccountDetails>) request.getSession()
				.getAttribute("listAllAccount");
		List<UserAccountDetails> listAllAccount = cashTransactionManagementService.getUserAccountDetails();

		request.getSession().setAttribute("userDetails", listAllAccount);

		UserAccountDetails details = (UserAccountDetails) request.getSession().getAttribute("userDetail");
		Integer userId = 0;

		String loggedInUserId = details.getLoginId();
		if (Objects.nonNull(loggedInUserId)) {
			UserAccountDetails userAccountDetails = cashTransactionManagementDao
					.findUserAccountByUserId(loggedInUserId);
			userId = userAccountDetails.getAccountNo();
			model.addAttribute("accountNo", userAccountDetails.getAccountNo());
			model.addAttribute("balanceAmount", userAccountDetails.getBalanceAmount());
		}
		List<TransactionDetails> tranactionDetails = userAccountDetailsRepository.accountTransactonsForDebit(userId);
		List<TransactionDetails> tranactionDetails1 = userAccountDetailsRepository.accountTransactonsForCredit(userId);

		request.getSession().setAttribute("tranactionDetails", tranactionDetails);
		request.getSession().setAttribute("tranactionDetails1", tranactionDetails1);
		return "transactionHistory";
	}

}
