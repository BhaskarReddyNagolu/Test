DROP TABLE IF EXISTS UserAccountDetails;
CREATE TABLE UserAccountDetails (
  account_number INTEGER(10) NOT NULL,
  user_name VARCHAR(50) NOT NULL,
  login_id varchar(50)  NOT NULL,
  password varchar(20)  NOT NULL,
  premiumUser varchar(5),
  balance_amount BIGINT,
  redeem_points BIGINT,
  transfer_type varchar(50)
);
