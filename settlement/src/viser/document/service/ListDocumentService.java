package viser.document.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import viser.account.service.User;
import viser.department.dao.DepartmentDao;
import viser.doctype.dao.DoctypeDao;
import viser.document.dao.DocumentDao;
import viser.document.model.Document;
import viser.employee.dao.EmployeeDao;
import viser.employee.model.Employee;
import viser.position.dao.PositionDao;

public class ListDocumentService {

	private DocumentDao documentDao = new DocumentDao();
	private EmployeeDao employeeDao = new EmployeeDao();
	private DepartmentDao departmentDao = new DepartmentDao();
	private PositionDao positionDao = new PositionDao();
	private DoctypeDao doctypeDao = new DoctypeDao();
	private int size = 5;

	public PageForm getDocumentPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = documentDao.selectCount(conn);
			List<Document> content = documentDao.select(
					conn, (pageNum - 1) * size, size);
			List<ListDocumentForm> convertContent = new ArrayList<>();
			if(content != null){
				for(Document document : content){
					convertContent.add(convertListDocumentForm(conn, document));
				}
			}else{
				convertContent = null;
			}
		
			return new PageForm(total, pageNum, size, convertContent);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PageForm getDocumentPage(int pageNum, int doctypeNo) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = documentDao.selectCount(conn, doctypeNo);
			List<Document> content = documentDao.select(
					conn, (pageNum - 1) * size, size, doctypeNo);
			List<ListDocumentForm> convertContent = new ArrayList<>();
			if(content != null){
				for(Document document : content){
					convertContent.add(convertListDocumentForm(conn, document));
				}
			}else{
				convertContent = null;
			}
		
			return new PageForm(total, pageNum, size, convertContent);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PageForm getDocumentPage(int pageNum, User user) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = documentDao.selectEmployeeDocumentCount(conn, user.getEmployeeNo());
			List<Document> content = documentDao.selectSubmitDocument(
					conn, (pageNum - 1) * size, size, user.getEmployeeNo());
			List<ListDocumentForm> convertContent = new ArrayList<>();
			if(content != null){
				for(Document document : content){
					convertContent.add(convertListDocumentForm(conn, document));
				}
			}else{
				convertContent = null;
			}
		
			return new PageForm(total, pageNum, size, convertContent);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PageForm getDocumentPage(int pageNum, boolean officerCheck, User user) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = documentDao.selectCount(conn, officerCheck, user.getEmployeeNo());
			List<Document> content = documentDao.select(
					conn, (pageNum - 1) * size, size, officerCheck, user.getEmployeeNo());
			List<ListDocumentForm> convertContent = new ArrayList<>();
			if(content != null){
				for(Document document : content){
					convertContent.add(convertListDocumentForm(conn, document));
				}
			}else{
				convertContent = null;
			}
		
			return new PageForm(total, pageNum, size, convertContent);
		} catch (SQLException e) {
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
