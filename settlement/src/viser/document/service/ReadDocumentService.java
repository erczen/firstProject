package viser.document.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import viser.department.dao.DepartmentDao;
import viser.doctype.dao.DoctypeDao;
import viser.document.dao.DocumentDao;
import viser.document.model.Document;
import viser.employee.dao.EmployeeDao;
import viser.employee.model.Employee;
import viser.position.dao.PositionDao;

public class ReadDocumentService {
	
	private DocumentDao documentDao = new DocumentDao();
	private EmployeeDao employeeDao = new EmployeeDao();
	private DepartmentDao departmentDao = new DepartmentDao();
	private PositionDao positionDao = new PositionDao();
	private DoctypeDao doctypeDao = new DoctypeDao();
	
	public ListDocumentForm getDocument(int documentNo){
		try(Connection conn = ConnectionProvider.getConnection()){
			Document document = documentDao.selectByNo(conn, documentNo);
			return convertListDocumentForm(conn, document);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public int getWriterNo(int documentNo){
		try(Connection conn = ConnectionProvider.getConnection()){
			Document document = documentDao.selectByNo(conn, documentNo);
			return document.getEmployeeNo();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public int getOfficerNo(int documentNo){
		try(Connection conn = ConnectionProvider.getConnection()){
			Document document = documentDao.selectByNo(conn, documentNo);
			return document.getOfficerNo();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	private ListDocumentForm convertListDocumentForm(Connection conn, Document document) throws SQLException{
		Employee employee = employeeDao.selectByNo(conn, document.getEmployeeNo());
		Employee officer = employeeDao.selectByNo(conn, document.getOfficerNo());
		ListDocumentForm listDocumentForm = new ListDocumentForm();
		
		listDocumentForm.setDocumentNo(document.getDocumentNo());
		listDocumentForm.setDepartmentName(departmentDao.selectByNo(conn, employee.getDepartmentNo()).getDepartmentName());
		listDocumentForm.setPositionName(positionDao.selectByNo(conn, employee.getPositionNo()).getPositionName());
		listDocumentForm.setName(employee.getName());
		listDocumentForm.setDoctypeName(doctypeDao.selectByNo(conn, document.getDoctypeNo()).getDoctypeName());
		listDocumentForm.setTitle(document.getTitle());
		listDocumentForm.setContent(document.getContent());
		listDocumentForm.setOfficerDepartment(departmentDao.selectByNo(conn, officer.getDepartmentNo()).getDepartmentName());
		listDocumentForm.setOfficerPosition(positionDao.selectByNo(conn, officer.getPositionNo()).getPositionName());
		listDocumentForm.setOfficerName(officer.getName());
		listDocumentForm.setOfficerCheck(document.isOfficerCheck());
		listDocumentForm.setRegDate(document.getRegDate());
		
		return listDocumentForm;
	}
}
