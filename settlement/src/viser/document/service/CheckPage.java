package viser.document.service;

import java.util.List;

import viser.document.model.Document;

public class CheckPage extends PageForm{
	
	public CheckPage(){
		super();
	}
	
	public CheckPage(int total, int currentPage, int size, List<Document> content){
		super(total, currentPage, size, content);
	}
}
