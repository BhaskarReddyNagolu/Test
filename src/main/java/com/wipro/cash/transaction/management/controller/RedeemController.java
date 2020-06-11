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

		UserAccountDetails details = (UserAccountDetails) request.getSession().getAttribute("loggedInUserDetails");
		String loggedInUserId = details.getLoginId();

		if (Objects.nonNull(loggedInUserId)) {
			UserAccountDetails userAccountDetails = cashTransactionManagementService
					.findUserAccountByUserId(loggedInUserId);

			if (Objects.nonNull(userAccountDetails) && userAccountDetails.getPremiumUser().equalsIgnoreCase("Yes")) {

				model.addAttribute("accountNo", userAccountDetails.getAccountNo());
				model.addAttribute("balanceAmount", userAccountDetails.getBalanceAmount());
				model.addAttribute("redeemPoints", userAccountDetails.getRedeemPoints());
			}
		}

		return "redeemDetails";
	}

	@PostMapping(value = "/postredeemDetails")
	public String postRedeemDetails(HttpServletRequest request, Model model) {

		UserAccountDetails details = (UserAccountDetails) request.getSession().getAttribute("loggedInUserDetails");
		String loggedInUserId = details.getLoginId();
		System.out.println("Post mehtod");

		if (Objects.nonNull(loggedInUserId)) {
			UserAccountDetails userAccountDetails = cashTransactionManagementService
					.findUserAccountByUserId(loggedInUserId);
			// Update
			Integer balanceAmount = userAccountDetails.getBalanceAmount() + userAccountDetails.getRedeemPoints();
			System.out.println("Before balanceAmount = " + balanceAmount);
			System.out.println("Before redeemPoints = " + userAccountDetails.getRedeemPoints());
			Integer redeemPoints = 0;

			cashTransactionManagementService.updateRedeemPoints(loggedInUserId, balanceAmount, redeemPoints);
			userAccountDetails.setBalanceAmount(balanceAmount);
			userAccountDetails.setRedeemPoints(redeemPoints);

			userAccountDetails = cashTransactionManagementService.findUserAccountByUserId(loggedInUserId);
			List<UserAccountDetails> userAccountDetailsList = new ArrayList<>();
			userAccountDetailsList.add(userAccountDetails);
			model.addAttribute(Constants.USERNAME, userAccountDetails.getUserName());
			request.setAttribute("userDetails", userAccountDetailsList);

		}

		return Constants.SUCCESS;
	}

}
