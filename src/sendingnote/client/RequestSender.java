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

public class RequestSender {
	private ObjectOutputStream oos;
	private MainFrame mainFrame;
	String userId;
	private petAnimalGUI pet;



	public RequestSender(ObjectOutputStream oos, String userId) throws IOException {
		this.userId = userId;
		this.oos = oos;
		oos.writeObject(userId);
	}

	public void insertUser(User user) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_USER_INSERT);
		p.setFromUser(user);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertNote(Note note) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_NOTE_INSRT);
		p.setNote(note);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logIn(User userId) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_LOG_IN);
		p.setFromUser(userId);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logOut(User userId) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_LOG_OUT);
		p.setFromUser(userId);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void userList() {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_CHECK_USER_LIST);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteMessage(String id) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_DELETE_MESSAGE);
		p.setName(id);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void unreadMessage(String id) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_UNREAD_MESSAGE);
		p.setName(id);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void allMessage(String id) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_ALL_MESSAGE);
		p.setName(id);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void allnotMessage(String id) {
		MyProtocol p = new MyProtocol();
		p.setCommand("내가보낸글");
		p.setName(id);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readAndUnread(Note note) {
		MyProtocol p = new MyProtocol();
		p.setCommand(MyConstants.PROTOCOL_COMMAND_ALL_READ_UNREAD);
		p.setNote(note);
		try {
			oos.writeObject(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// -----------------------------펫

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	


	public void close() {
		try {
			mainFrame.me.setId(mainFrame.userId);
			this.logOut(mainFrame.me);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
