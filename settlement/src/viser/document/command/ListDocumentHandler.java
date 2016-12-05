package viser.document.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import viser.document.service.DocumentPage;
import viser.document.service.ListDocumentService;
import viser.document.service.PageForm;

public class ListDocumentHandler implements CommandHandler {

	private static final String LIST_VIEW = "/WEB-INF/view/settlementMain.jsp";
	
	private ListDocumentService listDocumentService = new ListDocumentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String cautionPageNoVal = req.getParameter("cautionPageNo");
		String checkWaitPageNoVal = req.getParameter("checkWaitPageNo");
		String checkPageNoVal = req.getParameter("checkPageNo");
		
		int cautionPageNo = 1;
		int checkWaitPageNo = 1;
		int checkPageNo = 1;
		if(cautionPageNoVal != null){
			cautionPageNo = Integer.parseInt(cautionPageNoVal);
		}
		if(checkWaitPageNoVal != null){
			checkWaitPageNo = Integer.parseInt(checkWaitPageNoVal);
		}
		if(checkPageNoVal != null){
			checkPageNo = Integer.parseInt(checkPageNoVal);
		}
		
		PageForm cautionPage = listDocumentService.getDocumentPage(cautionPageNo);
		PageForm checkWaitPage = listDocumentService.getDocumentPage(checkWaitPageNo);
		PageForm checkPage = listDocumentService.getDocumentPage(checkPageNo);
		DocumentPage documentPage = new DocumentPage(cautionPage, checkWaitPage, checkPage);
		
		req.setAttribute("documentPage", documentPage);
		
		return LIST_VIEW;
	}
	
}
