package board;

import java.util.List;

public class Viewinfo {
	private List<boardVO> printList;
	private int totnum;
	private int currentPageNumber;
	private int pageTotalCount;
	private int firstRow;
	private int endRow;

	public Viewinfo(List<boardVO> printList, int totnum, int currentPageNumber, int pageTotalCount, int firstRow,
			int endRow) {
		this.printList = printList;
		this.totnum = totnum;
		this.currentPageNumber = currentPageNumber;
		this.pageTotalCount = pageTotalCount;
		this.firstRow = firstRow;
		this.endRow = endRow;
	}

	public List<boardVO> getPrintList() {
		return this.printList;
	}

	public void setPrintList(List<boardVO> printList) {
		this.printList = printList;
	}

	public int getTotnum() {
		return this.totnum;
	}

	public void setTotnum(int totnum) {
		this.totnum = totnum;
	}

	public int getCurrentPageNumber() {
		return this.currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getPageTotalCount() {
		return this.pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getFirstRow() {
		return this.firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getEndRow() {
		return this.endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
}