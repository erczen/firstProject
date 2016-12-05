package viser.department.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import viser.department.model.Department;

public class DepartmentDao {

	public List<Department> select(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from department");
			rs = pstmt.executeQuery();
			
			List<Department> accountList = new ArrayList<>();
			while(rs.next()){
				accountList.add(convertDepartment(rs));
			}
			return accountList;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Department selectByName(Connection conn, String departmentName) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from department where department_name = ?");
			pstmt.setString(1, departmentName);
			rs = pstmt.executeQuery();
			
			Department department = null;
			if(rs.next()){
				department = convertDepartment(rs);
			}
			return department;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Department selectByNo(Connection conn, int departmentNo) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt = conn.prepareStatement("select * from department where department_no = ?");
			pstmt.setInt(1, departmentNo);
			rs = pstmt.executeQuery();
			
			Department department = null;
			if(rs.next()){
				department = convertDepartment(rs);
			}
			return department;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void insert(Connection conn, Department department) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("insert into department(department_name) values(?)");
			pstmt.setString(1, department.getDepartmentName());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
	public void delete(Connection conn, Department department) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement("delete from department where department_no = ?");
			pstmt.setInt(1, department.getDepartmentNo());
			pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	} 
	
	private Department convertDepartment(ResultSet rs) throws SQLException{
		return new Department(rs.getInt("department_no"), rs.getString("department_name"));
	}
}
