package com.wipro.cash.transaction.management.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserAccountDetailsRepository {

	private static Logger logger = LogManager.getLogger(UserAccountDetailsRepository.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private QueryProperties queryProperties;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int insertCashTransaction(Integer from_account_number, Integer balace_amount, String transfer_type,
			Integer to_account_number, Integer transfer_amount, String remarks) {
		int i = jdbcTemplate.update(
				"Insert into CashTransaction ( from_account_number, transfer_type,to_account_number,transfer_amount, remarks) "
						+ "Values(?,?,?,?,?)",
				from_account_number, transfer_type, to_account_number, transfer_amount, remarks);
		System.out.println("======================================");
		// Map<String, Object> mpap = this.accountTransactons().get(0);

		// mpap.forEach((K, V)->System.out.println(K+ "..."+V));

		return i;
	}

	/*
	 * public List<TransactionDetails> accountTransactonsForCredit(Integer userId) {
	 * 
	 * String sql = "SELECT * FROM CashTransaction where from_account_number= ?";
	 * List< Map<String, Object>> mpapList= jdbcTemplate.queryForList(sql);
	 * Map<String, Object> mpap = mpapList.get(0);
	 * 
	 * 
	 * mpap.forEach((K, V)->System.out.println(K+ "..."+V));
	 * 
	 * List<TransactionDetails> list = jdbcTemplate.query(sql, new Object[]{userId},
	 * new TransactionDetailRowMapper()); return list; }
	 */

	public List<TransactionDetails> accountTransactonsForDebit(Integer userId) {

		List<TransactionDetails> list = null;
		System.out.println("UserId" + userId);
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue("from_account_number", userId);
			list = namedParameterJdbcTemplate.query(queryProperties.getTransactionHistoryById(), mapSqlParameterSource,
					new TransactionDetailRowMapper());
			System.out.println("Size of list " + list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public List<TransactionDetails> accountTransactonsForCredit(Integer userId) {

		List<TransactionDetails> list = null;
		System.out.println("UserId" + userId);
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue("to_account_number", userId);
			list = namedParameterJdbcTemplate.query(queryProperties.getTransactionHistoryById1(), mapSqlParameterSource,
					new TransactionDetailRowMapper());
			System.out.println("Size of list " + list.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}
}
