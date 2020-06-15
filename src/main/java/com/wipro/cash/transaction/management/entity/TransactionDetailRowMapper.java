package com.wipro.cash.transaction.management.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TransactionDetailRowMapper  implements RowMapper<TransactionDetails> {

	@Override
	public TransactionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {


		TransactionDetails tranactionDetails = new TransactionDetails();
		
		
		
		System.out.println("tType"+rs.getString("transfer_type"));
	tranactionDetails.setTransferType(rs.getString("transfer_type"));
	System.out.println("2e"+rs.getInt("to_account_number"));
	tranactionDetails.setFromAccountNumber(rs.getInt("from_account_number"));
	tranactionDetails.setToAccountNumber(rs.getInt("to_account_number"));
	tranactionDetails.setBalaceAmount(rs.getInt("transfer_amount"));
	tranactionDetails.setRemarks(rs.getString("remarks"));
		return tranactionDetails;
	}

}
