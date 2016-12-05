package viser.document.service;

import java.util.List;

import viser.document.model.Document;

public class CautionPage extends PageForm{
	
	public CautionPage(){
		super();
	}
	
	public CautionPage(int total, int currentPage, int size, List<Document> content){
		super(total, currentPage, size, content);
	}
}
