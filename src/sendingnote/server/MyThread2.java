package sendingnote.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import sendingnote.common.MyConstants;
import sendingnote.common.MyProtocol;
import sendingnote.dao.PetAnimalSeverdao;
import sendingnote.dao.SendingNoteDao;
import sendingnote.vo.Note;
import sendingnote.vo.Protector;
import sendingnote.vo.User;

public class MyThread2 extends Thread {
	private SendingNoteServerMain2 serverMain;
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

	private static PetAnimalSeverdao pdao = new PetAnimalSeverdao();

	public MyThread2(Socket clientSocket, SendingNoteServerMain2 serverMain) {
		this.clientSocket = clientSocket;
		this.serverMain = serverMain;
		this.openStream();
	}
	private void openStream() {
		try {
			oos = new ObjectOutputStream(this.clientSocket.getOutputStream());
			ois = new ObjectInputStream(this.clientSocket.getInputStream());
			this.userId = (String) ois.readObject();
			this.serverMain.clientThreadMap1.put(userId, this);

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
				// ---------------펫
				case MyConstants.PROTOCOL_COMMAND_PET_INSERT:
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_INSERT);
					boolean result_1 = pdao.insertProtector(fromClient.getProtector());
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_PET_INSERT);
					toClient.setResult(result_1);
					userConnection();
					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_PET_SEARCH:
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_SEARCH);
					protector = pdao.searchProtector(fromClient.getJumin());
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_PET_SEARCH);
					toClient.setProtector(protector);
					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_PET_UPDATE: // nuul 오류
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_UPDATE);
					pdao.updateProtector(fromClient.getProtector());
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_PET_UPDATE);
					toClient.setProtector(null);
					userConnection();
					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_PET_DELETE:
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_DELETE);
					boolean result_2 = pdao.deleteProtector(fromClient.getJumin());
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_PET_DELETE);
					toClient.setResult(result_2);
					userConnection();
					oos.writeObject(toClient);
					break;
				case MyConstants.PROTOCOL_COMMAND_PET_GETALL:
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_GETALL);
					prolist = new ArrayList<>();
					prolist = pdao.getAll();
					toClient.setCommand(MyConstants.PROTOCOL_COMMAND_PET_GETALL);
					toClient.setPet(prolist);
					oos.writeObject(toClient);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void userConnection() throws SQLException {

		for (MyThread2 toUserThread : this.serverMain.clientThreadMap1.values()) {
			if (toUserThread != null) {
				MyProtocol toClient = new MyProtocol();
				toClient.setCommand(MyConstants.PROTOCOL_COMMAND_PET_GETALL);
				toClient.setPet(pdao.getAll());
				try {
					toUserThread.oos.writeObject(toClient);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void close() {
		serverMain.clientThreadMap1.remove(this.getUserName());
		try {
			ois.close();
			oos.close();
			clientSocket.close();
			new SendingNoteServerMain2();
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
