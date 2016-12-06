package viser.document.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import viser.document.service.CheckDocumentService;
import viser.document.service.RejectDocumentService;

public class RejectDocumentHandler implements CommandHandler {

	private static final String SUCCESS_VIEW = "/WEB-INF/view/updateDocumentSuccess.jsp";
	private static final String READ_VIEW = "/WEB-INF/view/readDocumentForm.jsp";
	
	private RejectDocumentService rejectDocumentService = new RejectDocumentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String documentNoVal = req.getParameter("documentNo");
		int documentNo = Integer.parseInt(documentNoVal);
		
		try{
			rejectDocumentService.reject(documentNo);
			req.setAttribute("documentNo", documentNo);
			return SUCCESS_VIEW;
		}catch(RuntimeException e){
			return READ_VIEW;
		}
	}
}
