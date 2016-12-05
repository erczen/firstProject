package viser.document.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import viser.account.service.AccountNotFoundException;
import viser.account.service.User;
import viser.document.service.WriteDocumentService;
import viser.document.service.WriteRequest;

public class WriteDocumentHandler implements CommandHandler{
	private static final String FORM_VIEW = "/WEB-INF/view/writeDocumentForm.jsp";
	
	private WriteDocumentService writeDocumentService = new WriteDocumentService();
	
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
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if (!errors.isEmpty()) {
			System.out.println("여기인가");
			return FORM_VIEW;
		}
		
		try{
			int documentNo = writeDocumentService.write(writeReq);
			req.setAttribute("documentNo", documentNo);
			
			return "/WEB-INF/view/writeDocumentSuccess.jsp";
		}catch(AccountNotFoundException e){
			errors.put("notFoundOfficer", Boolean.TRUE);
			System.out.println("우리는");
			return FORM_VIEW;
		}
		
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(user, req.getParameter("doctypeName"), req.getParameter("officerDepartment")
				, req.getParameter("officerPosition"), req.getParameter("officerName"), req.getParameter("title")
				, req.getParameter("content"));
	}
}
