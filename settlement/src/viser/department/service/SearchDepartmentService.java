package viser.department.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import viser.department.dao.DepartmentDao;
import viser.department.model.Department;

public class SearchDepartmentService {

	private DepartmentDao departmentDao = new DepartmentDao();
	
	public Department search(int departmentNo){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Department department = departmentDao.selectbyNo(conn, departmentNo);
			
			conn.commit();
			
			return department;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public Department search(String departmentName){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Department department = departmentDao.selectbyName(conn, departmentName);
			
			conn.commit();
			
			return department;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
