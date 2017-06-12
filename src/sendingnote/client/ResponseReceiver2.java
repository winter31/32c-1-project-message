package sendingnote.client;

import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import GUI.MainFrame;
import GUI.petAnimalGUI;
import sendingnote.common.MyConstants;
import sendingnote.common.MyProtocol;
import sendingnote.vo.Cat;
import sendingnote.vo.Dog;
import sendingnote.vo.Mouse;
import sendingnote.vo.Note;
import sendingnote.vo.Protector;
import sendingnote.vo.User;

public class ResponseReceiver2 extends Thread {
	private ObjectInputStream ois;
	private MainFrame mainFrame;
	MyProtocol fromServer;
	boolean result = false;
	ArrayList<Note> myNotYetReceivedNotes;
	Note note;
	Protector protector;
	ArrayList<Protector> prolist;
	private petAnimalGUI petgui;

	public ResponseReceiver2(ObjectInputStream ois) {
		this.ois = ois;
	}

	private void receiveResponse() {
		try {
			while (true) {
				fromServer = (MyProtocol) ois.readObject();
				String command = fromServer.getCommand();
				ArrayList<User> allUsers = new ArrayList<>();
				ArrayList<Note> myReceivedNotes = new ArrayList<>();
				note = null;
				myNotYetReceivedNotes = new ArrayList<>();
				switch (command) {
				
				// ======================= pet

				case MyConstants.PROTOCOL_COMMAND_PET_INSERT:
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_INSERT);
					boolean result_1 = fromServer.getResult();

					if (result_1 == true) {
						petgui.mess.showMessageDialog(null, " 등록 결과 : " + petgui.temp);
					} else {
						petgui.mess.showMessageDialog(null, "등록 결과 : 실패. \n 이미 등록되었습니다.");
					}
					break;
				case MyConstants.PROTOCOL_COMMAND_PET_SEARCH:
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_SEARCH);
					protector = fromServer.getProtector();
					if (protector != null) {
						petgui.mess.showMessageDialog(null, "검색 결과 : " + protector);
					} else {
						petgui.mess.showConfirmDialog(null, "검색 결과 : 없습니다.");
					}
					
					break;
					
				case MyConstants.PROTOCOL_COMMAND_PET_UPDATE:
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_UPDATE);
					protector = fromServer.getProtector();

					if (protector == null) {
						petgui.mess.showMessageDialog(null, "수정 결과 : 성공");
					} else {
						petgui.mess.showMessageDialog(null, "수정 결과 : 실패");
					}
					
					break;
				case MyConstants.PROTOCOL_COMMAND_PET_DELETE:
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_DELETE);
					boolean result_2 = fromServer.getResult();
					
					if (result_2 == true) {
						petgui.mess.showMessageDialog(null, "삭제 결과 : " + petgui.temp);
					} else {
						petgui.mess.showMessageDialog(null, "삭제 결과 : 삭제 할 목록이 없습니다.");
					}
					break;
				case MyConstants.PROTOCOL_COMMAND_PET_GETALL:
					System.out.println(MyConstants.PROTOCOL_COMMAND_PET_GETALL);
					prolist = fromServer.getPet();
					petgui.listModel.removeAllElements();
					for (int i = 0; i < prolist.size(); i++) {
						petgui.listModel.addElement(prolist.get(i));
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.receiveResponse();
		this.close();
	}

	public petAnimalGUI getPetgui() {
		return petgui;
	}

	public void setPetgui(petAnimalGUI petgui) {
		this.petgui = petgui;
	}

}
