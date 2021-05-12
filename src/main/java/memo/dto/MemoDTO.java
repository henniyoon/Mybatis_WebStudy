package memo.dto;

public class MemoDTO {
	private int idx;
	private String writer;
	private String memo;
	private String post_date;
	
	// Getter, Setter
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	
	// 생성자
	public MemoDTO() {
		
	}
	public MemoDTO(String writer, String memo) {
		super();
		this.writer = writer;
		this.memo = memo;
	}
}
