
package com.wipro.cash.transaction.management.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.wipro.cash.transaction.management.config.QueryProperties;
import com.wipro.cash.transaction.management.constants.Constants;
import com.wipro.cash.transaction.management.entity.UserAccountDetails;
import com.wipro.cash.transaction.management.exception.CashTransactionManagementException;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * @author Bhaskar
 *
 */
@Repository
@Transactional
public class CashTransactionManagementDaoImpl implements CashTransactionManagementDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(CashTransactionManagementDaoImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private QueryProperties queryProperties;

	class UserAccountDetailsMapper implements RowMapper<UserAccountDetails> {
		@Override
		public UserAccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserAccountDetails userAccountDetails = new UserAccountDetails();
			userAccountDetails.setAccountNo(rs.getInt(Constants.ACCOUNT_NUMBER));
			userAccountDetails.setUserName(rs.getString(Constants.USER_NAME));
			userAccountDetails.setLoginId(rs.getString(Constants.LOGIN_ID));
			userAccountDetails.setPassword(rs.getString(Constants.PASSWORD));
			userAccountDetails.setPremiumUser(rs.getString(Constants.PREMIUM_USER));
			userAccountDetails.setBalanceAmount(rs.getInt(Constants.BALANCE_AMOUNT));
			userAccountDetails.setRedeemPoints(rs.getInt(Constants.REDEEM_POINTS));

			return userAccountDetails;
		}
	}

	@Override
	public List<UserAccountDetails> getUserAccountDetails() {
		List<UserAccountDetails> userAccountDetails = new ArrayList<>();
		try {
			LOGGER.debug("Get All UserAccountDetails Sql Query - {} ", queryProperties.getAllUserAccountDetails());
			userAccountDetails = namedParameterJdbcTemplate.query(queryProperties.getAllUserAccountDetails(),
					new UserAccountDetailsMapper());
		} catch (DataAccessException dataAccessException) {
			throw new CashTransactionManagementException("Get UserAccountDetails Exception", dataAccessException);
		}
		LOGGER.debug("UserAccountDetails Response Count  - {} ", userAccountDetails.size());
		return userAccountDetails;
	}

	@Override
	public UserAccountDetails findUserAccountByUserId(String userId) {
		UserAccountDetails userAccountDetails = null;
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.USER_ID, userId);
			LOGGER.debug("FindUserAccountBy UserId {} and Sql Query - {} ", userId,
					queryProperties.getAccountDetailsByUserId());
			userAccountDetails = namedParameterJdbcTemplate.queryForObject(queryProperties.getAccountDetailsByUserId(),
					mapSqlParameterSource, new UserAccountDetailsMapper());
		} catch (DataAccessException dataAccessException) {
			LOGGER.error("UserAccountDetails By UserId  - {} and an Exception - {} ", userId, dataAccessException);
		}
		LOGGER.debug("UserAccountDetails by UserId Response   - {} ", userAccountDetails);
		return userAccountDetails;
	}

	@Override

	public void updateRedeemPoints(String userId, Integer balanceAmount, Integer redeemPoints) {
		UserAccountDetails userAccountDetails = null;
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.USER_ID, userId);
			mapSqlParameterSource.addValue(Constants.BALANCE_AMOUNT, balanceAmount);
			mapSqlParameterSource.addValue("redeem_points", redeemPoints);
			final String UPDATE_QUERY = "update UserAccountDetails set redeem_points = :redeem_points,balance_amount= :balance_amount where login_id = :userId";

			int status = namedParameterJdbcTemplate.update(UPDATE_QUERY, mapSqlParameterSource);

			if (status != 0) {
				System.out.println("Updated");
			} else {
				System.out.println("NOt updated");
			}

		} catch (DataAccessException dataAccessException) {
			LOGGER.error("UserAccountDetails By UserId  - {} and an Exception - {} ", userId, dataAccessException);
		}
		LOGGER.debug("UserAccountDetails by UserId Response   - {} ", userAccountDetails);

	}

	@Override
	public UserAccountDetails findUserAccountByAccountNumber(Integer accountnumber) {
		UserAccountDetails userAccountDetails = null;
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.ACCOUNT_NUMBER, accountnumber);

			LOGGER.debug("FindUserAccountBy accountnumber {} and Sql Query - {} ", accountnumber,
					queryProperties.getAccountDetailsByAccountNumber());

			userAccountDetails = namedParameterJdbcTemplate.queryForObject(
					queryProperties.getAccountDetailsByAccountNumber(), mapSqlParameterSource,
					new UserAccountDetailsMapper());
		} catch (DataAccessException dataAccessException) {
			LOGGER.error("UserAccountDetails By accountnumber  - {} and an Exception - {} ", accountnumber,
					dataAccessException);
		}
		LOGGER.debug("UserAccountDetails by UserId Response   - {} ", userAccountDetails);
		return userAccountDetails;
	}

	public void updateBalanceAmount(Integer account_number, Integer balanceAmount) {
		UserAccountDetails userAccountDetails = null;

		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.ACCOUNT_NUMBER, account_number);
			mapSqlParameterSource.addValue(Constants.BALANCE_AMOUNT, balanceAmount);
			final String UPDATE_QUERY = "update UserAccountDetails set balance_amount= :balance_amount where account_number = :account_number";

			int status = namedParameterJdbcTemplate.update(UPDATE_QUERY, mapSqlParameterSource);

			if (status != 0) {
				System.out.println("Updated");
			} else {
				System.out.println("NOt updated");
			}
			String query = "SELECT * FROM CashTransaction";

		} catch (DataAccessException dataAccessException) {
			LOGGER.error("UserAccountDetails By UserId  - {} and an Exception - {} ", account_number,
					dataAccessException);
		}

	}

	@Override
	public void updateRedeemPoints(String userId, Integer redeemPoints) {
		UserAccountDetails userAccountDetails = null;
		try {
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
			mapSqlParameterSource.addValue(Constants.USER_ID, userId);
			mapSqlParameterSource.addValue("redeem_points", redeemPoints);
			final String UPDATE_QUERY = "update UserAccountDetails set redeem_points = :redeem_points  where login_id = :userId";

			int status = namedParameterJdbcTemplate.update(UPDATE_QUERY, mapSqlParameterSource);

			if (status != 0) {
				System.out.println("ùpdate reedem points for cashtransfer");
			} else {
				System.out.println("reedem points not updated for cashtransfer");
			}

		} catch (DataAccessException dataAccessException) {
			LOGGER.error("UserAccountDetails By UserId  - {} and an Exception - {} ", userId, dataAccessException);
		}
		LOGGER.debug("UserAccountDetails by UserId Response   - {} ", userAccountDetails);

	}

}
