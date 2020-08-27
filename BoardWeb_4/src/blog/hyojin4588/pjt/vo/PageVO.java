package blog.hyojin4588.pjt.vo;

public class PageVO extends BoardVO {
	
	private int page;
	private int recordCnt;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecordCnt() {
		return recordCnt;
	}
	public void setRecordCnt(int recordCnt) {
		this.recordCnt = recordCnt;
	}

}
