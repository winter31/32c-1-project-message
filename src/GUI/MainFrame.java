package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sendingnote.client.ClientManager;
import sendingnote.vo.Note;
import sendingnote.vo.User;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;

public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	public ClientManager manager;
	public String userId;
	private String name;
	public CardLayout cardLayout = new CardLayout();

	public ArrayList<User> serverUserList = null;
	public User me;
	public JButton mailexit;
	public JButton btdeleteM;
	public 	JButton btnewM;
	public 	JList list;
	public 	MainFrame main;
	public 	User selected;
	public 	JTextField tftoid;
	public 	UnreadMailUI uo;
	public 	JTextField tftitle;
	public 	JLabel itsme;
	public DefaultTableModel dm1;
	private JTable table;
	private JTextField textField;
	public User n12 = null;
	public 	ArrayList<String> name123;
	public 	DefaultTableModel mmm;
	public 	JPanel Newmessage;
	public 	JPanel Newmessage0;
	public 	JPanel Newmessage2;
	public JLabel Newreceiver;
	public JTextField Newtftoid;
	public 	JLabel Newsender;
	public 	JTextField Newtffromid;
	public 	JLabel Newdate;
	public 	JTextField Newtfdate;
	public JLabel Newtitle;
	public JTextField Newtftitle;
	public JLabel Newmessage1;
	public 	JLabel Newnullspace;
	public 	JTextArea Newtamessage;
	public JOptionPane aa;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	public JTable table2;
	public petAnimalGUI guu;
	
	public MainFrame(String userId, ClientManager manager, String name, petAnimalGUI guu) {
		this.main = this;
		this.manager = manager;
		this.userId = userId;
		this.name = name;
		this.guu = guu;
		setSize(new Dimension(600, 578));
		setBounds(970, 100, 550, 680);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		itsme = new JLabel("접속된 아이디");
		itsme.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 12));
		panel.add(itsme);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(20);
		textField.setText(userId + "  ---   이름: " + name);
		textField.setEnabled(false);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnewM = new JButton("안읽은 쪽지");
		btnewM.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 12));
		panel_1.add(btnewM);
		btnewM.addActionListener(this);
		btdeleteM = new JButton("쪽지모두 지우기");
		btdeleteM.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 12));
		panel_1.add(btdeleteM);
		btdeleteM.addActionListener(this);
		mailexit = new JButton("나가기");
		mailexit.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 12));
		panel_1.add(mailexit);
		mailexit.addActionListener(this);
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 1, 0, 0));

		String[] s = { "제목", "내용", "발신자" };
		dm1 = new DefaultTableModel(s, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(dm1);
		JScrollPane jf = new JScrollPane(table);
		jf.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"\uBCF4\uB0B8 \uBA54\uC138\uC9C0", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(jf);
		manager.getRequestSender().allnotMessage(userId);
		
		//------------------------------------------------------------------------------------------------
		String[] aaaaaa1 = { "제목", "내용", "수신자" };

		mmm = new DefaultTableModel(aaaaaa1, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	
		table2 = new JTable(mmm);

		scrollPane = new JScrollPane(table2);
		scrollPane.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"\uBC1B\uC740 \uBA54\uC138\uC9C0", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(scrollPane);
		manager.getRequestSender().allMessage(userId);
		//    =========================================================================================================
		
		list = new JList();
		panel_2.add(list);
		list.setBorder(new TitledBorder(null, "\uC811\uC18D\uC790 \uB9AC\uC2A4\uD2B8", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {  //------------- 바꿈
				if (e.getClickCount() == 2) {
					selected = (User) list.getSelectedValue();
					JPanel message = new JPanel(new GridLayout(2, 1));
					JPanel message0 = new JPanel(new GridLayout(5, 2)); 
					JPanel message2 =new JPanel(new GridLayout(1, 1));
					
					JLabel receiver = new JLabel("수신자");
					tftoid = new JTextField(20);
					JLabel sender = new JLabel("발신자");
					JTextField tffromid = new JTextField(20);
					JLabel date = new JLabel("날짜");
					JTextField tfdate = new JTextField(20);
					JLabel title = new JLabel("제목");
					tftitle = new JTextField(20);
					JLabel message1 = new JLabel("내용");
					JLabel nullspace = new JLabel("");
					JTextArea tamessage = new JTextArea();
					message0.add(receiver);
					message0.add(tftoid);
					message0.add(sender);
					message0.add(tffromid);
					message0.add(date);
					message0.add(tfdate);
					message0.add(title);
					message0.add(tftitle);
					message0.add(message1);
					message0.add(nullspace);
					message2.add(tamessage);
					
					message.add(message0); message.add(message2);
					String[] aa = { "보내기", "취소" };

					Date d = new Date();
					String dattt = DateFormat.getInstance().format(d);
					tftoid.setText(selected.getId());
					tftoid.setEnabled(false);
					tfdate.setText(dattt);
					tffromid.setText(userId);
					tffromid.setEnabled(false);
					tfdate.setEnabled(false);

					int x1 = JOptionPane.showOptionDialog(null, message, "메세지보내기", JOptionPane.YES_NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, aa, null);

					if (x1 == JOptionPane.YES_OPTION) {
						Note note = new Note(userId, tftitle.getText(), tamessage.getText(), null, userId,
								tftoid.getText(), null);

						manager.getRequestSender().insertNote(note);
						manager.getRequestSender().allnotMessage(userId);
						
					} else if (x1 == JOptionPane.NO_OPTION) {
						tftitle.setText("");
						tamessage.setText("");
					}
				} else if (e.getClickCount() == 1) {
					name123 = new ArrayList<>();
					serverUserList = (ArrayList<User>) list.getSelectedValuesList();
					for (User uu : serverUserList) {
						name123.add(uu.getId());
					}
				}
				super.mouseClicked(e);
			}
		});
		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { //------------- 바꿈
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					JPanel message = new JPanel(new GridLayout(2, 1));
					JPanel message0 = new JPanel(new GridLayout(5, 2)); 
					JPanel message2 =new JPanel(new GridLayout(1, 1));

					JLabel receiver = new JLabel("수신자");
					tftoid = new JTextField(20);
					JLabel sender = new JLabel("발신자");
					JTextField tffromid = new JTextField(20);
					JLabel date = new JLabel("날짜");
					JTextField tfdate = new JTextField(20);
					JLabel title = new JLabel("제목");
					tftitle = new JTextField(20);
					JLabel message1 = new JLabel("내용");
					JTextArea tamessage = new JTextArea();
					JLabel lbl_image3 =new JLabel("");
//					ImageIcon icon = new ImageIcon("src\\독수리\\%B9%E8%B0%E6_%B8%E9-%C6_%BA%C5%DA.jpg");
		
					message0.add(receiver);
					message0.add(tftoid);
					message0.add(sender);
					message0.add(tffromid);
					message0.add(date);
					message0.add(tfdate);
					message0.add(title);
					message0.add(tftitle);
					message0.add(message1);
					message0.add(lbl_image3);
					message2.add(tamessage);
					message.add(message0);message.add(message2);
					
					String[] aa = { "보내기", "취소" };

					Date d = new Date();
					String dattt = DateFormat.getInstance().format(d);
					tftoid.setText(name123.toString());
					tftoid.setEnabled(false);
					tfdate.setText(dattt);
					tffromid.setText(userId);
					tffromid.setEnabled(false);
					tfdate.setEnabled(false);

					int x1 = JOptionPane.showOptionDialog(null, message, "메세지보내기", JOptionPane.YES_NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, aa, null);

					if (x1 == JOptionPane.YES_OPTION) {
						for (String string1 : name123) {
							Note note = new Note(userId, tftitle.getText(), tamessage.getText(), null, userId, string1,
									null);
							manager.getRequestSender().insertNote(note);
						}
					} else if (x1 == JOptionPane.NO_OPTION) {
						tftitle.setText("");
						tamessage.setText("");
					}

				}

				super.keyReleased(e);
			}
		});

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //------------- 바꿈
				if (e.getClickCount() == 2) {
					int x = table.getSelectedRow();
					String title1 = table.getValueAt(x, 0).toString();
					String conta1 = table.getValueAt(x, 1).toString();
					String name1 = table.getValueAt(x, 2).toString();

					JPanel message = new JPanel(new GridLayout(2, 1));
					JPanel message0 = new JPanel(new GridLayout(3, 2));
					JPanel message2 = new JPanel(new GridLayout(1, 1));

					JLabel sender = new JLabel("발신자");
					JTextField tffromid = new JTextField(20);
					JLabel title = new JLabel("제목");
					JTextField tftitle1 = new JTextField(20);
					JLabel message1 = new JLabel("내용");
					JTextArea tamessage = new JTextArea();
					message0.add(sender);
					message0.add(tffromid);
					message0.add(title);
					message0.add(tftitle1);
					message0.add(message1);
					message2.add(tamessage);
					message.add(message0);message.add(message2);
					
					
					tffromid.setText(name1);
					tftitle1.setText(title1);
					tamessage.setText(conta1);
					String[] aa = { "확인 " };
					int xx = JOptionPane.showOptionDialog(null, message, "메세지보기", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, aa, "확인");
					Note note = new Note(userId, tftitle1.getText(), tamessage.getText(), null, userId,
							tffromid.getText(), "t");
					manager.getRequestSender().readAndUnread(note);
				}
				super.mouseClicked(e);
			}
		});
		
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //------------- 바꿈
				if (e.getClickCount() == 2) {
					int x = table2.getSelectedRow();
					String title1 = table2.getValueAt(x, 0).toString();
					String conta1 = table2.getValueAt(x, 1).toString();
					String name1 = table2.getValueAt(x, 2).toString();

					JPanel message = new JPanel(new GridLayout(2, 1));
					JPanel message0 = new JPanel(new GridLayout(3, 2));
					JPanel message2 = new JPanel(new GridLayout(1, 1));

					JLabel sender = new JLabel("발신자");
					JTextField tffromid = new JTextField(20);
					JLabel title = new JLabel("제목");
					JTextField tftitle1 = new JTextField(20);
					JLabel message1 = new JLabel("내용");
					JTextArea tamessage = new JTextArea();
					message0.add(sender);
					message0.add(tffromid);
					message0.add(title);
					message0.add(tftitle1);
					message0.add(message1);
					message2.add(tamessage);
					message.add(message0);message.add(message2);
					
					tffromid.setText(name1);
					tftitle1.setText(title1);
					tamessage.setText(conta1);
					String[] aa = { "확인 " };
					int xx = JOptionPane.showOptionDialog(null, message, "메세지보기", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, aa, "확인");
					Note note = new Note(userId, tftitle1.getText(), tamessage.getText(), null, userId,
							tffromid.getText(), "t");
					manager.getRequestSender().readAndUnread(note);
				}
				super.mouseClicked(e);
			}
		});
		basicsett();
		manager.setMainFrame(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mailexit)) {
			me = new User(userId, name, null);
			manager.getRequestSender().logOut(me);
			manager.getRequestSender().userList();
			dispose();
			guu.setVisible(false);
			
		} else if (e.getSource().equals(btnewM)) {
			uo = new UnreadMailUI(manager);
			uo.setVisible(true);
			manager.getRequestSender().unreadMessage(userId);
		} else if (e.getSource().equals(btdeleteM)) {
			manager.getRequestSender().deleteMessage(userId);
			boolean result = manager.getResponseRecevier().result;
			if (result == true) {
				JOptionPane.showMessageDialog(null, "삭제성공");
				while (dm1.getRowCount() != 0) {
					dm1.removeRow(0);
				}
				while (mmm.getRowCount() != 0) {
					mmm.removeRow(0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "삭제실패");
			}
		}
	}
	public void basicsett(){
		manager.getRequestSender().userList();
	}
}
