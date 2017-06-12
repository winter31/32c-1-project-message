package sendingnote.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2551821583224593850L;
	private String id;
	private String title;
	private String content;
	private Timestamp dateCreated;
	private String fromUserId;
	private String toUserId;
	private String read;
	
	public Note() {
	
	}

	public Note(String id, String title, String content, Timestamp dateCreated, String fromUserId, String toUserId, String read) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.dateCreated = dateCreated;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.read = read;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public String getStringDate() {
		return dateCreated.toString();
	}
	
	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	@Override
	public String toString() {
		return " 아이디 : " + id + " 제목 : " + title + " 제목 : " + content + " 시간 : " + this.getStringDate()
				+ " 발신자 : " + fromUserId + " 수신자 : " + toUserId +","+ read;
	}

	public String[] toArray() {
		String[] result = {this.id+"", this.title, this.getStringDate()};
		return result;
	}
}
