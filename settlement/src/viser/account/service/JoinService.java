package viser.account.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import viser.account.dao.AccountDao;
import viser.account.model.Account;

public class JoinService {
	
	private AccountDao accountDao = new AccountDao();
	
	public void join(JoinRequest joinRequest){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Account account = accountDao.selectById(conn, joinRequest.getId());
			if(account != null){
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			
			accountDao.insert(conn, new Account(joinRequest.getId(), joinRequest.getEmployeeNo(), joinRequest.getPassword(), new Date()));
			 
			conn.commit();
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
