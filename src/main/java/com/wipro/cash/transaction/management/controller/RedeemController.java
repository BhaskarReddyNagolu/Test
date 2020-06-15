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

import com.wipro.cash.transaction.management.constants.Constants;
import com.wipro.cash.transaction.management.entity.UserAccountDetails;
import com.wipro.cash.transaction.management.service.CashTransactionManagementService;

/**
 * @author Naveen
 *
 */

@Controller
public class RedeemController {

	@Autowired
	private CashTransactionManagementService cashTransactionManagementService;

	@GetMapping(value = "/redeemDetails")
	public String getRedeemDetails(HttpServletRequest request, Model model) {
		UserAccountDetails userAccountDetails = (UserAccountDetails) request.getSession()
				.getAttribute(Constants.USER_DETAIL);
		String loggedInUserId = userAccountDetails.getLoginId();
		if (Objects.nonNull(loggedInUserId)) {
			userAccountDetails = cashTransactionManagementService.findUserAccountByUserId(loggedInUserId);
			if (Objects.nonNull(userAccountDetails) && userAccountDetails.getPremiumUser().equalsIgnoreCase("Yes")) {
				model.addAttribute("accountNo", userAccountDetails.getAccountNo());
				model.addAttribute("balanceAmount", userAccountDetails.getBalanceAmount());
				model.addAttribute("redeemPoints", userAccountDetails.getRedeemPoints());
			}
		}
		return Constants.REDEEM_DETAILS;
	}

	@PostMapping(value = "/postredeemDetails")
	public String postRedeemDetails(HttpServletRequest request, Model model) {
		UserAccountDetails details = (UserAccountDetails) request.getSession().getAttribute(Constants.USER_DETAIL);
		String loggedInUserId = details.getLoginId();
		if (Objects.nonNull(loggedInUserId)) {
			UserAccountDetails userAccountDetails = cashTransactionManagementService
					.findUserAccountByUserId(loggedInUserId);
			Integer balanceAmount = userAccountDetails.getBalanceAmount() + userAccountDetails.getRedeemPoints();
			cashTransactionManagementService.updateRedeemPoints(loggedInUserId, balanceAmount, 0);
			userAccountDetails = cashTransactionManagementService.findUserAccountByUserId(loggedInUserId);
			List<UserAccountDetails> userAccountDetailsList = new ArrayList<>();
			userAccountDetailsList.add(userAccountDetails);
			model.addAttribute(Constants.USERNAME, userAccountDetails.getUserName());
			request.getSession().setAttribute(Constants.USER_DETAILS, userAccountDetailsList);
			request.getSession().setAttribute(Constants.USER_DETAIL, userAccountDetails);
		}
		return Constants.SUCCESS;
	}
}
