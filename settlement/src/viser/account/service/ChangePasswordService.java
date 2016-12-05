package viser.account.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

import viser.account.dao.AccountDao;
import viser.account.model.Account;

public class ChangePasswordService {

	private AccountDao accountDao = new AccountDao();
	
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Account account = accountDao.selectById(conn, userId);
			if (account == null) {
				throw new AccountNotFoundException();
			}
			if (!account.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			account.changePassword(newPwd);
			accountDao.update(conn, account);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
