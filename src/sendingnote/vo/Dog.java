package sendingnote.vo;

public class Dog extends Protector{

	private String dogName;
	private int dogAge;
	private String dogType;
	
	public Dog(String protector, int age, String jumin, String dogName, int dogAge, String dogType) {
		super(protector, age, jumin);
		this.dogName = dogName;
		this.dogAge = dogAge;
		this.dogType = dogType;
	}

	public Dog(String protector, int age, String jumin) {
		super(protector, age, jumin);
	}

	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	public int getDogAge() {
		return dogAge;
	}

	public void setDogAge(int dogAge) {
		this.dogAge = dogAge;
	}

	public String getDogType() {
		return dogType;
	}

	public void setDogType(String dogType) {
		this.dogType = dogType;
	}

	@Override
	public String toString() {
		return super.toString()+" Æê ÀÌ¸§: " + dogName + ", Æê ³ªÀÌ: " + dogAge + ", Æê Á¾: " + dogType;
	}
	
	
}
