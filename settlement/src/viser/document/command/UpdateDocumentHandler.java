package viser.document.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import viser.account.service.AccountNotFoundException;
import viser.account.service.User;
import viser.document.service.DocumentContentNotFoundException;
import viser.document.service.DocumentNotFoundException;
import viser.document.service.ListDocumentForm;
import viser.document.service.ReadDocumentService;
import viser.document.service.UpdateDocumentService;
import viser.document.service.WriteRequest;

public class UpdateDocumentHandler implements CommandHandler {
	
	private static final String LIST_VIEW = "/WEB-INF/view/settlementMain.jsp";
	private static final String SUCCESS_VIEW = "/WEB-INF/view/updateDocumentSuccess.jsp";
	private static final String WRITE_VIEW = "/WEB-INF/view/writeDocumentForm.jsp";
	
	private ReadDocumentService readDocumentService = new ReadDocumentService();
	private UpdateDocumentService UpdateDocumentService = new UpdateDocumentService();
	
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
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String documentNoVal = req.getParameter("documentNo");
		int documentNo = Integer.parseInt(documentNoVal);
		try {
			ListDocumentForm documentForm = readDocumentService.getDocument(documentNo);
			int writerNo = readDocumentService.getWriterNo(documentNo);
			req.setAttribute("documentForm", documentForm);
			req.setAttribute("writerNo", writerNo);
			return WRITE_VIEW;
		} catch (DocumentNotFoundException | DocumentContentNotFoundException e) {
			req.getServletContext().log("no document", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return LIST_VIEW;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return WRITE_VIEW;
		}
		
		try{
			int documentNo = UpdateDocumentService.update(writeReq);
			req.setAttribute("documentNo", documentNo);
			
			return SUCCESS_VIEW;
		}catch(AccountNotFoundException e){
			errors.put("notFoundOfficer", Boolean.TRUE);
			return WRITE_VIEW;
		}
		
	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(user, req.getParameter("doctypeName"), req.getParameter("officerDepartment")
				, req.getParameter("officerPosition"), req.getParameter("officerName"), req.getParameter("title")
				, req.getParameter("content"));
	}

}
