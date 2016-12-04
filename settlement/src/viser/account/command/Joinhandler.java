package viser.account.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mvc.command.CommandHandler;
import viser.account.service.JoinRequest;
import viser.account.service.JoinService;
import viser.department.service.SearchDepartmentService;
import viser.employee.model.Employee;
import viser.employee.service.SearchEmployeeRequest;
import viser.employee.service.SearchEmployeeService;
import viser.position.service.SearchPositionService;

public class Joinhandler implements CommandHandler{
	
	private static final String NONE_FORM_VIEW = "/WEB-INF/view/checkEmployeeForm.jsp";
	private static final String MAKE_FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private JoinService joinService = new JoinService();
	private SearchEmployeeService searchEmployeeService = new SearchEmployeeService();
	private SearchDepartmentService searchDepartmentService = new SearchDepartmentService();
	private SearchPositionService searchPositionService = new SearchPositionService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return NONE_FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		SearchEmployeeRequest searchEmployeeRequest = new SearchEmployeeRequest();
		String departmentName = req.getParameter("departmentName");
		String positionName = req.getParameter("positionName");
		String name = req.getParameter("name");
		
		searchEmployeeRequest.setDepartmentNo(searchDepartmentService.search(departmentName).getDepartmentNo());
		searchEmployeeRequest.setPositionNo(searchPositionService.search(positionName).getPositionNo());
		searchEmployeeRequest.setName(name);
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		searchEmployeeRequest.validate(errors);
		
		if(!errors.isEmpty()){
			return NONE_FORM_VIEW;
		}
		
		List<Employee> employeeList = searchEmployeeService.search(searchEmployeeRequest);
		if(employeeList.size() == 0){
			errors.put("beEmployee", Boolean.FALSE);
			return NONE_FORM_VIEW;
		}else if(employeeList.size() == 1){
			req.setAttribute("employeeNo", employeeList.get(0).getEmployeeNo());
			return MAKE_FORM_VIEW;
		}else{
			// 일단 부서 직책 이름이 중복되는 사람이 없다고 생각하고 나중에 만듦
			return NONE_FORM_VIEW;
		}
		
	}
}
