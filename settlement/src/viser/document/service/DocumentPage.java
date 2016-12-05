package viser.document.service;

public class DocumentPage {
	private CautionPage cautionPage;
	private CheckWaitPage checkWaitPage;
	private CheckPage checkPage;
	
	public DocumentPage() {}
	
	public DocumentPage(CautionPage cautionPage, CheckWaitPage checkWaitPage, CheckPage checkPage){
		this.cautionPage = cautionPage;
		this.checkWaitPage = checkWaitPage;
		this.checkPage = checkPage;
	}

	public CautionPage getCautionPage() {
		return cautionPage;
	}

	public void setCautionPage(CautionPage cautionPage) {
		this.cautionPage = cautionPage;
	}

	public CheckWaitPage getCheckWaitPage() {
		return checkWaitPage;
	}

	public void setCheckWaitPage(CheckWaitPage checkWaitPage) {
		this.checkWaitPage = checkWaitPage;
	}

	public CheckPage getCheckPage() {
		return checkPage;
	}

	public void setCheckPage(CheckPage checkPage) {
		this.checkPage = checkPage;
	}
}
