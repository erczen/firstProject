package viser.document.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import viser.document.service.DeleteDocumentService;
import viser.document.service.DocumentNotFoundException;

public class DeleteDocumentHandler implements CommandHandler {
	
	private static final String LIST_VIEW = "/WEB-INF/view/settlementMain.jsp";
	private static final String SUCCESS_VIEW = "/WEB-INF/view/deleteDocumentSuccess.jsp";
	
	private DeleteDocumentService deleteDocumentService = new DeleteDocumentService(); 
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try{
			deleteDocumentService.delete(Integer.parseInt(req.getParameter("documentNo")));
			
			return SUCCESS_VIEW;
		}catch(DocumentNotFoundException e){
			req.getServletContext().log("no document", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return LIST_VIEW;
		}
	}
	
}
