package board;

import java.sql.Date;

//데이터를 주고 받기 위한 그릇
public class BoardDTO {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private int viewCount;
	private Date postTime;
	
	public BoardDTO() {
		super();
	}

	public BoardDTO(int boardNo, String title, String content, String writer, int viewCount, Date postTime) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.viewCount = viewCount;
		this.postTime = postTime;
	}
	
	public BoardDTO(String writer, String title, String content) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
}