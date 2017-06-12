package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sendingnote.client.ClientManager;
import sendingnote.vo.Note;
import java.awt.Color;

public class UnreadMailUI extends JFrame {

	private JPanel contentPane;
	public DefaultTableModel dm;
	public JTable jt;
	private JButton button;
	public UnreadMailUI ui;
	private ClientManager manager;
	private JLabel lbl_image;
	public UnreadMailUI(ClientManager manager) { //------------- 바꿈
		this.ui = this;
		this.manager = manager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		String[] columnNames ={"수신자", "제목"};
		dm = new DefaultTableModel(columnNames, 0){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		contentPane.setLayout(null);
		jt = new JTable(dm);
		JScrollPane scrollPane = new JScrollPane(jt);
		scrollPane.setBounds(12, 10, 410, 215);
		contentPane.add(scrollPane);
		
		jt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //------------- 바꿈
				if(e.getClickCount() == 2){
					int x = jt.getSelectedRow();
					ArrayList<Note> list = new ArrayList<>();
					
					JPanel message = new JPanel(new GridLayout(2, 1));
					JPanel message0 = new JPanel(new GridLayout(4, 2));
					JPanel message2 = new JPanel(new GridLayout(1, 1));
					JLabel sender = new JLabel("발신자");
					JTextField tffromid = new JTextField(20);
					JLabel date = new JLabel("날짜");
					JTextField tfdate = new JTextField(20);
					JLabel title = new JLabel("제목");
					JTextField tftitle1 = new JTextField(20);
					JLabel message1 = new JLabel("내용");
					JTextArea tamessage = new JTextArea();
					
					message0.add(sender);message0.add(tffromid);
					message0.add(date);message0.add(tfdate);
					message0.add(title);message0.add(tftitle1);
					
					message0.add(message1);message2.add(tamessage);
					message.add(message0);message.add(message2);
					
					list = manager.getResponseRecevier().myNotYetReceivedNotes;
					for (Note note : list) {
						tffromid.setText(note.getId());
						tfdate.setText(note.getStringDate().toString());
						tftitle1.setText(note.getTitle());
						tamessage.setText(note.getContent());
					}
					tffromid.setEnabled(false);
					tfdate.setEnabled(false);
					tftitle1.setEnabled(false);
					tamessage.setEnabled(false);
					String[] aa = {"확인"};
					JOptionPane.showOptionDialog(null, message, "메세지보기", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, aa, "확인");
					
					String sender12 = jt.getValueAt(x, 0).toString();
					String title12 = jt.getValueAt(x, 1).toString();
					
					Note n = new Note(sender12, title12, null, null, sender12, sender12, "t");
					manager.getRequestSender().readAndUnread(n);
					
					dm.removeRow(x);
				}
				super.mouseClicked(e);
			}
		});
		button = new JButton("나가기");
		button.setBounds(5, 234, 424, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(button);
		
		lbl_image = new JLabel("");
		lbl_image.setBounds(0, 0, 434, 257);
		lbl_image.setIcon(new ImageIcon("src\\독수리\\%B9%E8%B0%E6_%B8%E9-%C6_%BA%C5%DA.jpg"));
		contentPane.add(lbl_image);
	}
}
