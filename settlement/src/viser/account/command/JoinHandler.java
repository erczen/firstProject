package viser.account.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mvc.command.CommandHandler;
import viser.account.service.DuplicateIdException;
import viser.account.service.JoinRequest;
import viser.account.service.JoinService;

public class JoinHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private static final String SUCCESS_VIEW = "/WEB-INF/view/joinSuccess.jsp";
	private JoinService joinService = new JoinService();
	
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
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {	
		JoinRequest joinRequest = new JoinRequest();
		joinRequest.setId(req.getParameter("id"));
		joinRequest.setEmployeeNo(Integer.parseInt(req.getParameter("employeeNo")));
		joinRequest.setPassword(req.getParameter("password"));
		joinRequest.setConfirmPassword(req.getParameter("confirmPassword"));
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		joinRequest.validate(errors);
		
		if(!errors.isEmpty()){
			req.setAttribute("employeeNo", joinRequest.getEmployeeNo());
			return FORM_VIEW;
		}
		
		try{
			joinService.join(joinRequest);
			return SUCCESS_VIEW;
		}catch(DuplicateIdException e){
			errors.put("duplicateId", Boolean.TRUE);
			req.setAttribute("employeeNo", joinRequest.getEmployeeNo());
			return FORM_VIEW;
		}
	}
}
