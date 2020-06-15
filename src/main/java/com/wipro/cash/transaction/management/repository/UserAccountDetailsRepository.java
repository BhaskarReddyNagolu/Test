package com.wipro.cash.transaction.management.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.cash.transaction.management.config.QueryProperties;
import com.wipro.cash.transaction.management.entity.TransactionDetailRowMapper;
import com.wipro.cash.transaction.management.entity.TransactionDetails;

@Repository
@Transactional
public class UserAccountDetailsRepository implements UserAccountDetailsRepositoryI {

	private static final Logger LOGGER = LogManager.getLogger(UserAccountDetailsRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private QueryProperties queryProperties;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Integer insertCashTransaction(Integer fromAccountNumber, Integer balaceAmount, String transferType,
			Integer toAccountNumber, Integer transferAmount, String remarks) {
		return jdbcTemplate.update(
				"Insert into CashTransaction ( from_account_number, transfer_type,to_account_number,transfer_amount, remarks) "
						+ "Values(?,?,?,?,?)",
				fromAccountNumber, transferType, toAccountNumber, transferAmount, remarks);
	}

	@Override
	public List<TransactionDetails> accountTransactonsForDebit(Integer userId) {
		List<TransactionDetails> transactionsList = null;
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue("from_account_number", userId);
			transactionsList = namedParameterJdbcTemplate.query(
					queryProperties.getUserAccountTransactionDetailsFromAccountNumber(), mapSqlParameterSource,
					new TransactionDetailRowMapper());
			LOGGER.debug("AccountTransactonsForDebit Response Size {} ", transactionsList.size());
		} catch (DataAccessException dataAccessException) {
			LOGGER.error("accountTransactonsForDebit By UserId  - {} and an Exception - {} ", userId,
					dataAccessException);
		}
		return transactionsList;
	}

	@Override
	public List<TransactionDetails> accountTransactonsForCredit(Integer userId) {
		List<TransactionDetails> transactionsList = null;
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue("to_account_number", userId);
			transactionsList = namedParameterJdbcTemplate.query(
					queryProperties.getUserAccountTransactionDetailsToAccountNumber(), mapSqlParameterSource,
					new TransactionDetailRowMapper());
			LOGGER.debug("AccountTransactonsForCredit Response Size {} ", transactionsList.size());
		} catch (DataAccessException dataAccessException) {
			LOGGER.error("AccountTransactonsForCredit By UserId  - {} and an Exception - {} ", userId,
					dataAccessException);
		}
		return transactionsList;
	}
}
