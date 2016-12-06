package viser.document.service;

public class DocumentPage {
	private PageForm cautionPage;
	private PageForm checkWaitPage;
	private PageForm checkPage;
	private PageForm submitPage;
	
	public DocumentPage() {}
	
	public DocumentPage(PageForm cautionPage, PageForm checkWaitPage, PageForm checkPage, PageForm submitPage){
		this.cautionPage = cautionPage;
		this.checkWaitPage = checkWaitPage;
		this.checkPage = checkPage;
		this.submitPage = submitPage;
	}

	public PageForm getCautionPage() {
		return cautionPage;
	}

	public void setCautionPage(PageForm cautionPage) {
		this.cautionPage = cautionPage;
	}

	public PageForm getCheckWaitPage() {
		return checkWaitPage;
	}

	public void setCheckWaitPage(PageForm checkWaitPage) {
		this.checkWaitPage = checkWaitPage;
	}

	public PageForm getCheckPage() {
		return checkPage;
	}

	public void setCheckPage(PageForm checkPage) {
		this.checkPage = checkPage;
	}

	public PageForm getSubmitPage() {
		return submitPage;
	}

	public void setSubmitPage(PageForm submitPage) {
		this.submitPage = submitPage;
	}
	
}
