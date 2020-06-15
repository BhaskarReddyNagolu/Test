package com.wipro.cash.transaction.management.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.cash.transaction.management.constants.Constants;
import com.wipro.cash.transaction.management.entity.UserAccountDetails;
import com.wipro.cash.transaction.management.service.CashTransactionManagementService;

/**
 * @author Bhaskar
 *
 */

@Controller
public class LoginController {

	@Autowired
	private CashTransactionManagementService cashTransactionManagementService;

	// Java 8 LocalDate
	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
	LocalDate date = LocalDate.now();

	@GetMapping(value = "/")
	public String login(Model model) {
		model.addAttribute(Constants.DATE, "Today is -  " + date.format(formatter));
		return Constants.LOGIN;
	}

	@GetMapping(value = "/home")
	public String homePage(HttpServletRequest request, Model model) {
		UserAccountDetails userAccountDetailsSession = (UserAccountDetails) request.getSession()
				.getAttribute(Constants.USER_DETAIL);
		this.setUserDetailsInSession(model, request, userAccountDetailsSession);
		return Constants.SUCCESS;
	}

	@PostMapping(value = "/login")
	public String login(HttpServletRequest request, Model model, @RequestParam("userName") String userName,
			@RequestParam("password") String password) {
		model.addAttribute(Constants.DATE, "Today is - " + date.format(formatter));
		if (Objects.nonNull(userName) && Objects.nonNull(password)) {
			model.addAttribute(Constants.USERNAME, userName);
			model.addAttribute(Constants.PASSWORD, password);
			UserAccountDetails userAccountDetails = cashTransactionManagementService.findUserAccountByUserId(userName);
			if (Objects.nonNull(userAccountDetails) && userName.equals(userAccountDetails.getLoginId())
					&& password.equals(userAccountDetails.getPassword())) {
				this.setUserDetailsInSession(model, request, userAccountDetails);
				 List<UserAccountDetails> listAllAccount = cashTransactionManagementService.getUserAccountDetails();
                 listAllAccount.remove(userAccountDetails);
                 request.getSession().setAttribute("listAllAccount", listAllAccount);
				return Constants.SUCCESS;
			} else {
				model.addAttribute(Constants.ERROR, "Invalid Credentials");
				return Constants.LOGIN;
			}
		} else {
			model.addAttribute(Constants.ERROR, "Please Enter Your Login Details");
			return Constants.LOGIN;
		}
	}

	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constants.USERNAME);
		session.invalidate();
		return Constants.LOGIN;
	}

	private void setUserDetailsInSession(Model model, HttpServletRequest request,
			UserAccountDetails userAccountDetails) {
		List<UserAccountDetails> userAccountDetailsList = new ArrayList<>();
		userAccountDetailsList.add(userAccountDetails);
		model.addAttribute(Constants.USERNAME, userAccountDetails.getUserName());
		request.getSession().setAttribute(Constants.USER_DETAILS, userAccountDetailsList);
		request.getSession().setAttribute(Constants.USER_DETAIL, userAccountDetails);
	}
}
