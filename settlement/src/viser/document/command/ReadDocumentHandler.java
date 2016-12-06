package viser.document.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import viser.document.service.DocumentContentNotFoundException;
import viser.document.service.DocumentNotFoundException;
import viser.document.service.ListDocumentForm;
import viser.document.service.ReadDocumentService;

public class ReadDocumentHandler implements CommandHandler {

	private ReadDocumentService readDocumentService = new ReadDocumentService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String documentNoVal = req.getParameter("documentNo");
		int documentNo = Integer.parseInt(documentNoVal);
		try {
			ListDocumentForm documentForm = readDocumentService.getDocument(documentNo);
			int writerNo = readDocumentService.getWriterNo(documentNo);
			req.setAttribute("documentForm", documentForm);
			req.setAttribute("writerNo", writerNo);
			return "/WEB-INF/view/readDocument.jsp";
		} catch (DocumentNotFoundException | DocumentContentNotFoundException e) {
			req.getServletContext().log("no document", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
