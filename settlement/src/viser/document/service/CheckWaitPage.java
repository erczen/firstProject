package viser.document.service;

import java.util.List;

import viser.document.model.Document;

public class CheckWaitPage extends PageForm{
	
	public CheckWaitPage() {
		super();
	}
	
	public CheckWaitPage(int total, int currentPage, int size, List<Document> content){
		super(total, currentPage, size, content);
	}
}
