package sendingnote.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import GUI.MainFrame;
import GUI.petAnimalGUI;
import sendingnote.common.MyConstants;
import sendingnote.common.MyProtocol;
import sendingnote.vo.Note;
import sendingnote.vo.Protector;
import sendingnote.vo.User;

public class RequestSender2 {
	private ObjectOutputStream oos;
	private MainFrame mainFrame;
	String userId;
	private petAnimalGUI pet;



	public RequestSender2(ObjectOutputStream oos, String userId) throws IOException {
		this.userId = userId;
		this.oos = oos;
		oos.writeObject(userId);
	}

	public void insertProtector(Protector p1) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_PET_INSERT);
		p.setProtector(p1);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchProtector(String jumin) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_PET_SEARCH);
		p.setJumin(jumin);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateProtector(Protector p1) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_PET_UPDATE);
		p.setProtector(p1);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteProtector(String jumin) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_PET_DELETE);
		p.setJumin(jumin);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getAll() {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_PET_GETALL);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public petAnimalGUI getMainPet() {
		return pet;
	}
	public void setMainPet(petAnimalGUI pet) {
		this.pet = pet;
	}


	public void close() {
		try {
			mainFrame.me.setId(mainFrame.userId);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
