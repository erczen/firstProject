package viser.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import viser.account.model.Account;

public class AccountDao {
	
	public List<Account> select(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from account");
			rs = pstmt.executeQuery();
			
			List<Account> accountList = new ArrayList<>();
			while(rs.next()){
				accountList.add(convertAccount(rs));
			}
			return accountList;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Account selectById(Connection conn, String employeeId) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from account where employee_id = ?");
			pstmt.setString(1, employeeId);
			rs = pstmt.executeQuery();
			
			Account account = null;
			if(rs.next()){
				account = convertAccount(rs);
			}
			return account;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, Account account) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("insert into account(employee_id, employee_no, password, regdate) values(?, ?, ?, ?)");
			pstmt.setString(1, account.getEmployeeId());
			pstmt.setInt(2, account.getEmployeeNo());
			pstmt.setString(3, account.getPassword());
			pstmt.setTimestamp(4, new Timestamp(account.getRegdate().getTime()));
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public void update(Connection conn, Account account) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("update account set employee_no = ?, password = ?, regdate = ? where employee_id = ?");
			pstmt.setInt(1, account.getEmployeeNo());
			pstmt.setString(2, account.getPassword());
			pstmt.setTimestamp(3, new Timestamp(account.getRegdate().getTime()));
			pstmt.setString(4, account.getEmployeeId());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public void delete(Connection conn, Account account) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from account where employee_id = ?");
			pstmt.setString(1, account.getEmployeeId());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	private Date toDate(Timestamp date){
		return date == null ? null : new Date(date.getTime());
	}
	
	private Account convertAccount(ResultSet rs) throws SQLException{
		return new Account(rs.getString("employee_id"), rs.getInt("employee_no"), rs.getString("password"), rs.getDate("regdate"));
	}
}
