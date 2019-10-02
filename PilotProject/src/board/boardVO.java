package board;

import java.sql.Date;

public class boardVO{
	
	private int article_no;
	private String article_pw;
	private String writer_id;
	private String writer_name;
	private String title;
	private String content;
	private Date regdate;
	private Date moddate;
	private int read_cnt;
	
	public boardVO() {
		System.out.println("MmberVO 생성자 호출");
	}
	
	public boardVO(int article_no, String writer_id, String writer_name, String title, String content, Date regdate, Date moddate, int read_cnt) {
		this.article_no = article_no;
		this.writer_id = writer_id;
		this.writer_name = writer_name;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.moddate = moddate;
		this.read_cnt = read_cnt;
	}

	public void setArticle_no(int article_no) {this.article_no = article_no;}
	public void setWriter_id(String writer_id) {this.writer_id = writer_id;}
	public void setWriter_name(String writer_name) {this.writer_name = writer_name;}
	public void setTitle(String title) {this.title = title;}
	public void setRegdate(Date regdate) {this.regdate = regdate;}
	public void setModdate(Date moddate) {this.moddate = moddate;}
	public void setRead_cnt(int read_cnt) {this.read_cnt = read_cnt;}

	public int getArticle_no() {return article_no;}
	public String getWriter_id() {return writer_id;}
	public String getWriter_name() {return writer_name;}
	public String getTitle() {return title;}
	public Date getRegdate() {return regdate;}
	public Date getModdate() {return moddate;}
	public int getRead_cnt() {return read_cnt;}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getArticle_pw() {
		return article_pw;
	}

	public void setArticle_pw(String article_pw) {
		this.article_pw = article_pw;
	}
	
	
}

