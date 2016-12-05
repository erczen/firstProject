package viser.document.service;

import java.util.List;

import viser.document.model.Document;

public class PageForm {
	protected int total;
	protected int currentPage;
	protected List<ListDocumentForm> content;
	protected int totalPages;
	protected int startPage;
	protected int endPage;
	
	public PageForm(){}
	
	public PageForm(int total, int currentPage, int size, List<ListDocumentForm> content){
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if(total == 0){
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		}else{
			totalPages = total / size;
			if(total % size > 0){
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if(modVal == 0){
				startPage -= 5;
			}
			
			endPage = startPage + 4;
			if(endPage > totalPages){
				endPage = totalPages;
			}
		}
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<ListDocumentForm> getContent() {
		return content;
	}

	public void setContent(List<ListDocumentForm> content) {
		this.content = content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public boolean hasNoDocuments() {
		return total == 0;
	}

	public boolean hasDocuments() {
		return total > 0;
	}
}
