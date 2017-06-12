package sendingnote.vo;

import java.io.Serializable;

public class Protector implements Serializable{

	private String protector;
	private int age;
	private String jumin;
	
	public Protector(String protector, int age, String jumin) {
		super();
		this.protector = protector;
		this.age = age;
		this.jumin = jumin;
	}

	public Protector() {
		super();
	}

	public String getProtector() {
		return protector;
	}

	public void setProtector(String protector) {
		this.protector = protector;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getJumin() {
		return jumin;
	}

	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	@Override
	public String toString() {
		return " 이름: " + protector + ", 나이: " + age + ", 주번: " + jumin;
	}
	
	
	
}