package com.wipro.cash.transaction.management.service;

import org.springframework.ui.Model;

import com.wipro.cash.transaction.management.entity.UserAccountDetails;

public interface CashTransferService {

	boolean cashTransfer(UserAccountDetails fromUserAccountDetails, UserAccountDetails toUserAccountDetails,
			Integer amount, String transferType, Model model);
}
