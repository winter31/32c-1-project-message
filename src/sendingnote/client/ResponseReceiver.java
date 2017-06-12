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

public class ResponseReceiver extends Thread {
	private ObjectInputStream ois;
	private MainFrame mainFrame;
	public MyProtocol fromServer;
	public boolean result = false;
	public ArrayList<Note> myNotYetReceivedNotes;
	public Note note;
	public Protector protector;
	public ArrayList<Protector> prolist;
	private petAnimalGUI petgui;

	public ResponseReceiver(ObjectInputStream ois) {
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
				case MyConstants.PROTOCOL_COMMAND_USER_INSERT:
					System.out.println(MyConstants.PROTOCOL_COMMAND_USER_INSERT);
					result = fromServer.getResult();
					break;
				case MyConstants.PROTOCOL_COMMAND_NOTE_INSRT:
					System.out.println(MyConstants.PROTOCOL_COMMAND_NOTE_INSRT);
					result = fromServer.getResult();
					break;
				case MyConstants.PROTOCOL_COMMAND_LOG_IN:
					System.out.println(MyConstants.PROTOCOL_COMMAND_LOG_IN);
					result = fromServer.getResult();
					break;
				case MyConstants.PROTOCOL_COMMAND_CHECK_USER_LIST:
					System.out.println(MyConstants.PROTOCOL_COMMAND_CHECK_USER_LIST);
					fromServer.getToUsers();
					mainFrame.list.setListData(fromServer.getToUsers().toArray());
					break;
				case MyConstants.PROTOCOL_COMMAND_DELETE_MESSAGE:
					System.out.println(MyConstants.PROTOCOL_COMMAND_DELETE_MESSAGE);
					result = fromServer.getResult();
					break;
				case MyConstants.PROTOCOL_COMMAND_UNREAD_MESSAGE:
					System.out.println(MyConstants.PROTOCOL_COMMAND_UNREAD_MESSAGE);
					myNotYetReceivedNotes = fromServer.getNotes();
					while (mainFrame.uo.dm.getRowCount() != 0) {
						mainFrame.uo.dm.removeRow(0);
					}
					for (Note note2 : myNotYetReceivedNotes) {
						Object[] rowdata = { note2.getToUserId(), note2.getTitle() };
						mainFrame.uo.dm.addRow(rowdata);
					}
					break;
				case MyConstants.PROTOCOL_COMMAND_LOG_OUT:
					System.out.println(MyConstants.PROTOCOL_COMMAND_LOG_OUT);
					result = fromServer.getResult();
					break;
				case MyConstants.PROTOCOL_COMMAND_ALL_MESSAGE:
					System.out.println(MyConstants.PROTOCOL_COMMAND_ALL_MESSAGE);
					myNotYetReceivedNotes = fromServer.getNotes();

					while (mainFrame.mmm.getRowCount() != 0) {
						mainFrame.mmm.removeRow(0);
					}
					for (Note note2 : myNotYetReceivedNotes) {
						Object[] rowdata = { note2.getTitle(), note2.getContent(), note2.getFromUserId() };
						mainFrame.mmm.addRow(rowdata);
					}
					break;

				case "내가보낸글":
					System.out.println("내가보낸글");
					myNotYetReceivedNotes = fromServer.getNotes();
					for (Note note : myReceivedNotes) {
						System.out.println(note);
					}
					while (mainFrame.dm1.getRowCount() != 0) {
						mainFrame.dm1.removeRow(0);
					}
					for (Note note2 : myNotYetReceivedNotes) {
						Object[] rowdata = { note2.getTitle(), note2.getContent(), note2.getId() };
						mainFrame.dm1.addRow(rowdata);
					}
					break;

				case MyConstants.PROTOCOL_COMMAND_SELECTED_MESSAGE:
					System.out.println(MyConstants.PROTOCOL_COMMAND_SELECTED_MESSAGE); //------------- 바꿈
					note = fromServer.getNote();
					mainFrame.Newmessage = new JPanel(new GridLayout(2, 1));
					mainFrame.Newmessage0 = new JPanel(new GridLayout(5, 2));
					mainFrame.Newmessage2 = new JPanel(new GridLayout(1, 1));
					
					mainFrame.Newreceiver = new JLabel("수신자");
					mainFrame.Newtftoid = new JTextField(20);
					mainFrame.Newsender = new JLabel("발신자");
					mainFrame.Newtffromid = new JTextField(20);
					mainFrame.Newdate = new JLabel("날짜");
					mainFrame.Newtfdate = new JTextField(20);
					mainFrame.Newtitle = new JLabel("제목");
					mainFrame.Newtftitle = new JTextField(20);
					mainFrame.Newmessage1 = new JLabel("내용");
					mainFrame.Newnullspace = new JLabel("");
					mainFrame.Newtamessage = new JTextArea();
					
					mainFrame.Newmessage.add(mainFrame.Newmessage0);
					mainFrame.Newmessage.add(mainFrame.Newmessage2);
					
					mainFrame.Newmessage0.add(mainFrame.Newreceiver);
					mainFrame.Newmessage0.add(mainFrame.Newtftoid);
					mainFrame.Newmessage0.add(mainFrame.Newsender);
					mainFrame.Newmessage0.add(mainFrame.Newtffromid);
					mainFrame.Newmessage0.add(mainFrame.Newdate);
					mainFrame.Newmessage0.add(mainFrame.Newtfdate);
					mainFrame.Newmessage0.add(mainFrame.Newtitle);
					mainFrame.Newmessage0.add(mainFrame.Newtftitle);
					mainFrame.Newmessage0.add(mainFrame.Newmessage1);
					mainFrame.Newmessage0.add(mainFrame.Newnullspace);
					mainFrame.Newmessage2.add(mainFrame.Newtamessage);

					mainFrame.Newtftoid.setText(note.getToUserId());
					mainFrame.Newtffromid.setText(note.getId());
					mainFrame.Newtfdate.setText(note.getStringDate());
					mainFrame.Newtftitle.setText(note.getTitle());
					mainFrame.Newtamessage.setText(note.getContent());

					mainFrame.Newtftoid.setEnabled(false);
					mainFrame.Newtffromid.setEnabled(false);
					mainFrame.Newtfdate.setEnabled(false);
					mainFrame.Newtftitle.setEnabled(false);
					mainFrame.Newtamessage.setEnabled(false);

					String[] a = { "확인" };
					int x = mainFrame.aa.showOptionDialog(null, mainFrame.Newmessage, "메세지보기", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, a, null);
					if (x == JOptionPane.YES_OPTION) {
						note.setRead("t");
						mainFrame.manager.getRequestSender().readAndUnread(note);
						mainFrame.manager.getRequestSender().allMessage(note.getToUserId());
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

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	public void setPetgui(petAnimalGUI petgui) {
		this.petgui =petgui;
	}
}
