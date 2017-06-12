package sendingnote.common;

import java.io.Serializable;
import java.util.ArrayList;

import sendingnote.vo.Cat;
import sendingnote.vo.Dog;
import sendingnote.vo.Mouse;
import sendingnote.vo.Note;
import sendingnote.vo.Protector;
import sendingnote.vo.User;

public class MyProtocol implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -979993247016034676L;
	private String command;
	private User fromUser;
	private ArrayList<User> list = new ArrayList<>();
	private Note note;
	private ArrayList<Note> notes = new ArrayList<>();
	private User to;
	private boolean result;
	private String name;
	private Protector protector;
	private Dog dog;
	private Cat cat;
	private Mouse mouse;
	private String jumin;
	private ArrayList<Protector> pet = new ArrayList<>();
	
	public ArrayList<Protector> getPet() {
		return pet;
	}
	public void setPet(ArrayList<Protector> pet) {
		this.pet = pet;
	}
	public Protector getProtector() {
		return protector;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public void setProtector(Protector protector) {
		this.protector = protector;
	}
	public Dog getDog() {
		return dog;
	}
	public void setDog(Dog dog) {
		this.dog = dog;
	}
	public Cat getCat() {
		return cat;
	}
	public void setCat(Cat cat) {
		this.cat = cat;
	}
	public Mouse getMouse() {
		return mouse;
	}
	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public ArrayList<User> getToUsers() {
		return list;
	}
	public void setToUsers(ArrayList<User> toUsers) {
		this.list = toUsers;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public ArrayList<Note> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public boolean getResult(){
		return this.result;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
