
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
import org.springframework.stereotype.Repository;

import com.wipro.cash.transaction.management.config.QueryProperties;
import com.wipro.cash.transaction.management.constants.Constants;
import com.wipro.cash.transaction.management.entity.UserAccountDetails;
import com.wipro.cash.transaction.management.exception.CashTransactionManagementException;

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
			userAccountDetails.setBalanceAmount(rs.getInt(Constants.BALACE_AMOUNT));
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

}
