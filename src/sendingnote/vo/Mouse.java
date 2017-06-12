package sendingnote.vo;

public class Mouse extends Protector{
	
	private String MouseName;
	private int MouseAge;
	private String MouseType;
	
	public Mouse(String protector, int age, String jumin, String mouseName, int mouseAge, String mouseType) {
		super(protector, age, jumin);
		MouseName = mouseName;
		MouseAge = mouseAge;
		MouseType = mouseType;
	}

	public Mouse(String protector, int age, String jumin) {
		super(protector, age, jumin);
	}

	public String getMouseName() {
		return MouseName;
	}

	public void setMouseName(String mouseName) {
		MouseName = mouseName;
	}

	public int getMouseAge() {
		return MouseAge;
	}

	public void setMouseAge(int mouseAge) {
		MouseAge = mouseAge;
	}

	public String getMouseType() {
		return MouseType;
	}

	public void setMouseType(String mouseType) {
		MouseType = mouseType;
	}

	@Override
	public String toString() {
		return super.toString()+" Æê ÀÌ¸§: " + MouseName + ", Æê ³ªÀÌ: " + MouseAge + ", Æê Á¾: " + MouseType;
	}
	
	
}
