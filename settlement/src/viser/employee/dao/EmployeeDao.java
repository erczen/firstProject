package viser.employee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import viser.employee.model.Employee;

public class EmployeeDao {

	public List<Employee> select(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from employee");
			rs = pstmt.executeQuery();
			
			List<Employee> employeeList = new ArrayList<>();
			while(rs.next()){
				employeeList.add(convertEmployee(rs));
			}
			return employeeList;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public List<Employee> select(Connection conn, int departmentNo, int positionNo, String name) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from employee where department_no = ? and position_no = ? and name = ?");
			pstmt.setInt(1, departmentNo);
			pstmt.setInt(2, positionNo);
			pstmt.setString(3, name);
			rs = pstmt.executeQuery();
			
			List<Employee> employeeList = new ArrayList<>();
			while(rs.next()){
				employeeList.add(convertEmployee(rs));
			}
			return employeeList;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Employee selectByNo(Connection conn, int employeeNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from employee where employee_no = ?");
			pstmt.setInt(1, employeeNo);
			rs = pstmt.executeQuery();
			
			Employee employee = null;
			if(rs.next()){
				employee = convertEmployee(rs);
			}
			return employee;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, Employee employee) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("insert into employee(department_no, position_no, name, birthday) values(?, ?, ?, ?)");
			pstmt.setInt(1, employee.getDepartmentNo());
			pstmt.setInt(2, employee.getPositionNo());
			pstmt.setString(3, employee.getName());
			pstmt.setDate(4, Date.valueOf(employee.getBirthday().toString()));
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public void delete(Connection conn, Employee employee) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from employee where employee_no = ?");
			pstmt.setInt(1, employee.getEmployeeNo());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	} 
	
	public Employee convertEmployee(ResultSet rs) throws SQLException{
		return new Employee(rs.getInt("employee_no"), rs.getInt("department_no"), rs.getInt("position_no"), rs.getString("name"), rs.getDate("birthday"));
	}
}
