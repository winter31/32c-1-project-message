package sendingnote.vo;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2077151493349305858L;
	private String id;
	private String name;
	private String log;
	
	public User() {
	}
	
	public User(String id, String name, String log) {
		super();
		this.id = id;
		this.name = name;
		this.log =log;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	@Override
	public String toString() {
		return "아이디 : " + id + ", 이름 : " + name + "-----"+log;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String [] toArray() {
		String[] result = {this.id+"", this.name};
		return result;
	}
}
