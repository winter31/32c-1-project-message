package sendingnote.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import sendingnote.common.MyConstants;
import sendingnote.common.MyProtocol;
import sendingnote.dao.PetAnimalSeverdao;
import sendingnote.dao.SendingNoteDao;
import sendingnote.vo.Note;
import sendingnote.vo.Protector;
import sendingnote.vo.User;

public class MyThread extends Thread {
	private SendingNoteServerMain serverMain;
	public Socket clientSocket;
	public ObjectOutputStream oos;
	public ObjectInputStream ois;
	public String userName;
	public Note from;
	public Note to;
	public MyProtocol toClient;
	public MyProtocol fromClient;
	String userId;
	Protector protector;
	ArrayList<Protector> prolist;

	private static SendingNoteDao dao = new SendingNoteDao();

	public MyThread(Socket clientSocket, SendingNoteServerMain serverMain) {
		this.clientSocket = clientSocket;
		this.serverMain = serverMain;
		this.openStream();
	}

	private void openStream() {
		try {
			oos = new ObjectOutputStream(this.clientSocket.getOutputStream());
			ois = new ObjectInputStream(this.clientSocket.getInputStream());
			this.userId = (String) ois.readObject();
			this.serverMain.clientThreadMap.put(userId, this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void communicate() {
		try {
			while (this.clientSocket.isConnected()) {
				fromClient = (MyProtocol) ois.readObject();
				String command = fromClient.getCommand();
				toClient = new MyProtocol();
				ArrayList<User> result3 = new ArrayList<>();
				switch (command) {
				case MyConstants.PROTOCOL_COMMAND_USER_INSERT:
					System.out.println(MyConstants.PROTOCOL_COMMAND_USER_INSERT);
					boolean result = dao.insertUser(fromClient.getFromUser());
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_USER_INSERT);
					toClient.setResult(result);
					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_NOTE_INSRT:
					System.out.println(MyConstants.PROTOCOL_COMMAND_NOTE_INSRT);
					dao.insertNote(fromClient.getNote());
					Note result1 = dao.selectedNote(fromClient.getNote().getToUserId());
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_NOTE_INSRT);
					toClient.setNote(result1);
					MessageBroad(toClient.getNote().getToUserId(), null, toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_LOG_IN:
					System.out.println(MyConstants.PROTOCOL_COMMAND_LOG_IN);
					boolean result2 = dao.insertLogInOut(fromClient.getFromUser());
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_LOG_IN);
					toClient.setResult(result2);
					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_CHECK_USER_LIST:
					System.out.println(MyConstants.PROTOCOL_COMMAND_CHECK_USER_LIST);

					result3 = dao.selectAllUsers();

					userConnection(userId, result3, fromClient);

					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_CHECK_USER_LIST);
					toClient.setToUsers(result3);
					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_DELETE_MESSAGE:
					boolean result4 = dao.deleteAllMessager(fromClient.getName());
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_DELETE_MESSAGE);
					toClient.setResult(result4);
					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_UNREAD_MESSAGE:
					System.out.println(MyConstants.PROTOCOL_COMMAND_UNREAD_MESSAGE);
					ArrayList<Note> result5 = new ArrayList<>();
					result5 = dao.checkMyMessage(fromClient.getName());
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_UNREAD_MESSAGE);
					toClient.setNotes(result5);
					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_LOG_OUT:
					System.out.println(MyConstants.PROTOCOL_COMMAND_LOG_OUT);
					toClient.setResult(dao.logoutChecking(fromClient.getFromUser()));

					userConnection(userId, result3, fromClient);

					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_ALL_MESSAGE:
					System.out.println(MyConstants.PROTOCOL_COMMAND_ALL_MESSAGE);
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_ALL_MESSAGE);
					System.out.println(fromClient.getName());
					toClient.setNotes(dao.allNote(fromClient.getName()));
					oos.writeObject(toClient);
					break;

				case "내가보낸글":
					System.out.println("내가보낸글");
					toClient.setCommand("내가보낸글");
					toClient.setNotes(dao.allnjotNote(fromClient.getName()));
					oos.writeObject(toClient);
					break;

				case MyConstants.PROTOCOL_COMMAND_ALL_READ_UNREAD:
					System.out.println(MyConstants.PROTOCOL_COMMAND_ALL_READ_UNREAD);
					dao.readAndUnread(fromClient.getNote());
					break;
				// ---------------펫

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void userConnection(String userId, ArrayList<User> toUsers, MyProtocol tocol) throws SQLException {
		ArrayList<User> toUsers1 = toUsers;
		for (User toUser : toUsers1) {

			MyThread toUserThread = (MyThread) this.serverMain.clientThreadMap.get(toUser.getId());

			if (toUserThread != null) {
				MyProtocol toClient = new MyProtocol();
				toClient.setCommand(MyConstants.PROTOCOL_COMMAND_CHECK_USER_LIST);
				toClient.setName(userId);
				toClient.setToUsers(toUsers);
				toClient.setNote(tocol.getNote());

				try {
					toUserThread.oos.writeObject(toClient);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void MessageBroad(String touserId, ArrayList<User> toUsers, MyProtocol tocol) throws SQLException {

		MyThread toUserThread = (MyThread) this.serverMain.clientThreadMap.get(touserId);

		if (toUserThread != null) {
			MyProtocol toClient = new MyProtocol();
			toClient.setCommand(MyConstants.PROTOCOL_COMMAND_SELECTED_MESSAGE);
			toClient.setName(userId);
			toClient.setToUsers(null);
			toClient.setNote(tocol.getNote());

			try {
				toUserThread.oos.writeObject(toClient);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		serverMain.clientThreadMap.remove(this.getUserName());
		try {
			ois.close();
			oos.close();
			clientSocket.close();
			
		} catch (Exception e) {
			System.out.println("클라이언트 접속 종료");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.communicate();
		this.close();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
