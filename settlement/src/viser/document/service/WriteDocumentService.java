package viser.document.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import viser.account.dao.AccountDao;
import viser.account.service.AccountNotFoundException;
import viser.department.dao.DepartmentDao;
import viser.doctype.dao.DoctypeDao;
import viser.document.dao.DocumentDao;
import viser.document.model.Document;
import viser.employee.dao.EmployeeDao;
import viser.employee.model.Employee;
import viser.position.dao.PositionDao;

public class WriteDocumentService {
	private DocumentDao documentDao = new DocumentDao();
	private DoctypeDao doctypeDao = new DoctypeDao();
	private EmployeeDao employeeDao = new EmployeeDao();
	private DepartmentDao departmentDao = new DepartmentDao();
	private PositionDao positionDao = new PositionDao();
	private AccountDao accountDao = new AccountDao();
	
	public Integer write(WriteRequest writeRequest){
		Connection conn = null;
		try{
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Employee officer = new Employee();
			officer.setDepartmentNo(departmentDao.selectByName(conn, writeRequest.getOfficerDepartment()).getDepartmentNo());
			officer.setPositionNo(positionDao.selectByName(conn, writeRequest.getOfficerPosition()).getPositionNo());
			officer.setName(writeRequest.getOfficerName());
			
			List<Employee> officerList = employeeDao.select(conn, officer.getDepartmentNo(), officer.getPositionNo(), officer.getName());
			if(officerList != null){
				officer = officerList.get(0);
			}else{
				JdbcUtil.rollback(conn);
				throw new AccountNotFoundException();
			}
			
			Document document = new Document();
			document.setEmployeeNo(accountDao.selectById(conn, writeRequest.getUser().getId()).getEmployeeNo());
			document.setDoctypeNo(doctypeDao.selectByName(conn, writeRequest.getDoctypeName()).getDoctypeNo());
			document.setTitle(writeRequest.getTitle());
			document.setContent(writeRequest.getContent());
			document.setOfficerNo(officer.getEmployeeNo());
			document.setOfficerCheck(false);
			document.setRegDate(new Date());
			
			int documentNo = documentDao.insert(conn, document);
			
			conn.commit();
			return documentNo;
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
