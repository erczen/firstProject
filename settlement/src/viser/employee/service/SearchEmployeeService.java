package viser.employee.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import viser.employee.dao.EmployeeDao;
import viser.employee.model.Employee;

public class SearchEmployeeService {
	private EmployeeDao employeeDao = new EmployeeDao();
	
	public List<Employee> search(SearchEmployeeRequest searchEmployeeRequest){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			List<Employee> employeeList = employeeDao.select(conn, searchEmployeeRequest.getDepartmentNo(), searchEmployeeRequest.getPositionNo(), searchEmployeeRequest.getName());
			
			conn.commit();
			
			return employeeList;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
