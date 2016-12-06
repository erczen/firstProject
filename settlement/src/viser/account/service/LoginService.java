package viser.account.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import viser.account.dao.AccountDao;
import viser.account.model.Account;
import viser.department.dao.DepartmentDao;
import viser.employee.dao.EmployeeDao;
import viser.employee.model.Employee;
import viser.position.dao.PositionDao;

public class LoginService {

	private AccountDao accountDao = new AccountDao();
	private EmployeeDao employeeDao = new EmployeeDao();
	private DepartmentDao departmentDao = new DepartmentDao();
	private PositionDao positionDao = new PositionDao();
	
	public User login(String employeeId, String password) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Account account = accountDao.selectById(conn, employeeId);
			if (account == null) {
				throw new LoginFailException();
			}
			if (!account.matchPassword(password)) {
				throw new LoginFailException();
			}
			
			Employee employee = employeeDao.selectByNo(conn, account.getEmployeeNo());
			User user = new User();
			user.setId(account.getEmployeeId());
			user.setEmployeeNo(employee.getEmployeeNo());
			user.setName(employee.getName());
			user.setDepartmentName(departmentDao.selectByNo(conn, employee.getDepartmentNo()).getDepartmentName());
			user.setPositionName(positionDao.selectByNo(conn, employee.getPositionNo()).getPositionName());
			
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
