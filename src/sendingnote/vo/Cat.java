package sendingnote.vo;

public class Cat extends Protector{

	private String catName;
	private int catAge;
	private String catType;
	
	public Cat(String protector, int age, String jumin, String catName, int catAge, String catType) {
		super(protector, age, jumin);
		this.catName = catName;
		this.catAge = catAge;
		this.catType = catType;
	}

	public Cat(String protector, int age, String jumin) {
		super(protector, age, jumin);
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getCatAge() {
		return catAge;
	}

	public void setCatAge(int catAge) {
		this.catAge = catAge;
	}

	public String getCatType() {
		return catType;
	}

	public void setCatType(String catType) {
		this.catType = catType;
	}

	@Override
	public String toString() {
		return super.toString()+" Æê ÀÌ¸§: " + catName + ", Æê ³ªÀÌ: " + catAge + ", Æê Á¾: " + catType;
	}
	
	
}
