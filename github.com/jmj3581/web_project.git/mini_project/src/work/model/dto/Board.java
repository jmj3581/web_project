package work.model.dto;

import java.io.Serializable;

public class Board implements Serializable{

	/** 게시글 번호 */
	private int boardNo;
	
	/** 게시글 제목 */
	private String title;
	
	/** 작성자 */
	private String userId;
	
	/** 작성일 */
	private String regDate;
	
	/** 내용 */
	private String contents;
	
	/** 조회수 */
	private int count;

	
	/** 기본 생성자 */
	public Board() {}
	
	public Board(String title, String userId, String contents) {
		this.title = title;
		this.userId = userId;
		this.contents = contents;
	}
	
	public Board(int boardNo, String title, String contents) {
		this.boardNo = boardNo;
		this.title = title;
		this.contents = contents;
	}
	
	public Board(int boardNo, String title, String userId, String regDate, String contents, int count) {
		this.boardNo = boardNo;
		this.title = title;
		this.userId = userId;
		this.regDate = regDate;
		this.contents = contents;
		this.count = count;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (boardNo != other.boardNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(boardNo);
		builder.append(", ");
		builder.append(title);
		builder.append(", ");
		builder.append(userId);
		builder.append(", ");
		builder.append(regDate);
		builder.append(", ");
		builder.append(contents);
		builder.append(", ");
		builder.append(count);
		return builder.toString();
	}
	
}
