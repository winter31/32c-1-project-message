package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import music.MusicThread;
import music.MusicThread2;
import sendingnote.client.ClientManager;
import sendingnote.vo.Cat;
import sendingnote.vo.Dog;
import sendingnote.vo.Mouse;
import sendingnote.vo.Protector;

public class petAnimalGUI extends JFrame implements ActionListener {
	public MusicThread th;
	JButton btnext;
	public MusicThread2 th1;
	public JTextField txtName;
	public JTextField txtJumin;
	public JTextField txtAge;
	public JTextField txtPetName;
	public JTextField txtPetAge;
	public JTextField txtPetType;
	public JList<Protector> list;
	public DefaultListModel<Protector> listModel;
	public JComboBox<String> cb;
	public String name, jumin = "";
	public int age = 0;
	public Protector temp = null;
	public JButton btnInsert, btnSearch, btnUpdate, btnDelete, btnMessage, btnOk, btnCancel;
	public JScrollPane scrollPane;
	public JLabel lblNewLabel;
	public JLabel lblImage;
	public JLabel lblImage2;
	public JButton btmusicStart;
	public String userId;
	public String logname;
	public ClientManager manager;
	public petAnimalGUI GGui;
	public MainFrame messageMain;
	public JOptionPane mess;
	public MainFrame fff;
	private JButton btmusicStop;
	

	public petAnimalGUI(String userId, ClientManager manager, String logname) {
		this.userId = userId;
		this.logname = logname;
		this.manager = manager;
		this.GGui = this;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 680);
		getContentPane().setLayout(null);

		btmusicStart = new JButton("▶");
		btmusicStart.addActionListener(this);

		btmusicStart.setBackground(Color.PINK);
		btmusicStart.setBounds(51, 32, 65, 23);
		getContentPane().add(btmusicStart);

		btmusicStop = new JButton("■");
		btmusicStop.setBackground(Color.PINK);
		btmusicStop.setVerticalAlignment(SwingConstants.CENTER);
		btmusicStop.setBounds(117, 32, 65, 23);
		getContentPane().add(btmusicStop);
		btmusicStop.addActionListener(this);

		btnext = new JButton(">>");
		btnext.setToolTipText("");
		btnext.setBounds(183, 32, 65, 23);
		getContentPane().add(btnext);
		btnext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnext)) {
					// th.stop();
					th1 = new MusicThread2();
					th1.start();
				}

			}
		});

		JLabel lblTitle = new JLabel(" A n i m a l H o s p i t a l");
		lblTitle.setBounds(74, 192, 711, 45);
		getContentPane().add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 30));
		// ===============================================================
		JPanel panelWest = new JPanel();
		panelWest.setBackground(Color.PINK);
		panelWest.setBackground(new Color(255, 0, 0, 0));
		panelWest.setBounds(74, 288, 261, 309);
		getContentPane().add(panelWest);
		panelWest.setLayout(null);

		JLabel lblName = new JLabel("보호자 : ");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("한컴 백제 M", Font.BOLD, 18));
		lblName.setBounds(12, 26, 97, 32);
		panelWest.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(116, 26, 123, 29);
		panelWest.add(txtName);
		txtName.setColumns(10);

		JLabel lblAge = new JLabel("나이 : ");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 18));
		lblAge.setBounds(22, 68, 88, 29);
		panelWest.add(lblAge);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(116, 70, 123, 27);
		panelWest.add(txtAge);

		JLabel lblJumin = new JLabel("주민번호 : ");
		lblJumin.setHorizontalAlignment(SwingConstants.CENTER);
		lblJumin.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 18));
		lblJumin.setBounds(12, 111, 97, 29);
		panelWest.add(lblJumin);

		txtJumin = new JTextField();
		txtJumin.setColumns(10);
		txtJumin.setBounds(116, 114, 123, 27);
		panelWest.add(txtJumin);

		JLabel lblPetName = new JLabel("펫 이름 :");
		lblPetName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetName.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 18));
		lblPetName.setBounds(12, 163, 97, 29);
		panelWest.add(lblPetName);

		txtPetName = new JTextField();
		txtPetName.setColumns(10);
		txtPetName.setBounds(116, 164, 123, 27);
		panelWest.add(txtPetName);

		JLabel lblPetAge = new JLabel("펫 나이 : ");
		lblPetAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetAge.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 18));
		lblPetAge.setBounds(12, 213, 97, 29);
		panelWest.add(lblPetAge);

		txtPetAge = new JTextField();
		txtPetAge.setColumns(10);
		txtPetAge.setBounds(116, 208, 123, 27);
		panelWest.add(txtPetAge);

		JLabel lblPetType = new JLabel("펫 종류 : ");
		lblPetType.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetType.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 18));
		lblPetType.setBounds(12, 252, 97, 29);
		panelWest.add(lblPetType);

		txtPetType = new JTextField();
		txtPetType.setColumns(10);
		txtPetType.setBounds(116, 254, 123, 27);
		panelWest.add(txtPetType);
		// ===============================================================
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(SystemColor.activeCaption);
		panelCenter.setBackground(new Color(255, 0, 0, 0));
		panelCenter.setBounds(336, 288, 449, 309);
		getContentPane().add(panelCenter);
		panelCenter.setLayout(null);

		JLabel label = new JLabel("◀ 등록 정보 ▶");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("한컴 솔잎 M", Font.PLAIN, 18));
		label.setBounds(0, 0, 449, 31);
		panelCenter.add(label);

		listModel = new DefaultListModel<>();

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 427, 270);
		panelCenter.add(scrollPane);

		list = new JList();
		scrollPane.setViewportView(list);
		list.setBackground(SystemColor.menu);

		list.setModel(listModel);

		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(SystemColor.inactiveCaptionText);
		panelSouth.setBackground(new Color(255, 0, 0, 0));
		panelSouth.setBounds(74, 599, 711, 43);
		getContentPane().add(panelSouth);
		panelSouth.setLayout(null);

		cb = new JComboBox();
		cb.setBounds(12, 7, 71, 28);
		cb.addItem("===");
		cb.addItem("강아지");
		cb.addItem("고양이");
		cb.addItem("햄스터");
		panelSouth.add(cb);
		cb.addActionListener(this);

		btnInsert = new JButton("등록");
		btnInsert.setBackground(Color.PINK);
		btnInsert.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 17));
		btnInsert.setBounds(106, 4, 69, 33);
		btnInsert.addActionListener(this);
		panelSouth.add(btnInsert);

		btnSearch = new JButton("검색");
		btnSearch.setBackground(Color.PINK);
		btnSearch.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 17));
		btnSearch.setBounds(202, 4, 77, 33);
		panelSouth.add(btnSearch);
		btnSearch.addActionListener(this);

		btnUpdate = new JButton("수정");
		btnUpdate.setBackground(Color.PINK);
		btnUpdate.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 17));
		btnUpdate.setBounds(303, 4, 77, 33);
		panelSouth.add(btnUpdate);
		btnUpdate.addActionListener(this);

		btnDelete = new JButton("삭제");
		btnDelete.setBackground(Color.PINK);
		btnDelete.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 17));
		btnDelete.setBounds(407, 4, 77, 33);
		panelSouth.add(btnDelete);
		btnDelete.addActionListener(this);

		btnOk = new JButton("확인");
		btnOk.setBackground(Color.PINK);
		btnOk.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 17));
		btnOk.setBounds(511, 4, 77, 33);
		panelSouth.add(btnOk);
		btnOk.addActionListener(this);

		btnCancel = new JButton("취소");
		btnCancel.setBackground(Color.PINK);
		btnCancel.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 17));
		btnCancel.setBounds(611, 4, 77, 33);
		panelSouth.add(btnCancel);

		lblImage2 = new JLabel("");
		lblImage2.setHorizontalAlignment(SwingConstants.LEFT);
		lblImage2.setBounds(142, 10, 566, 309);
		ImageIcon icon = new ImageIcon("src\\독수리\\\uCE74\uC640\uC774.png");
		java.awt.Image change = icon.getImage().getScaledInstance(lblImage2.getWidth(), lblImage2.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		lblImage2.setIcon(new ImageIcon(change));
		getContentPane().add(lblImage2);

		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("src\\독수리\\%B9%E8%B0%E6_%B8%E9-%C6_%BA%C5%DA.jpg"));
		lblImage.setBounds(0, 0, 862, 642);
		getContentPane().add(lblImage);

		ImageIcon icon2 = new ImageIcon("src\\독수리\\welcom.jpg");

		btnCancel.addActionListener(this);
		list.addMouseListener(new MouseLister());
		setItem();
		closeText();
		manager.setMainFrame2(this);
		setVisible(true);
	}

	public void setItem() {
		listModel.clear();
		manager.getRequestSender2().getAll();
	}

	public class MouseLister extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Protector temp = list.getSelectedValue();
			clearText();

			if (temp != null) {
				openText();
				txtName.setText(temp.getJumin());
				txtAge.setText("" + temp.getAge());
				txtJumin.setText(temp.getJumin());
				if (temp instanceof Dog) {
					txtPetName.setText(((Dog) temp).getDogName());
					txtPetAge.setText("" + ((Dog) temp).getDogAge());
					txtPetType.setText(((Dog) temp).getDogType());
				} else if (temp instanceof Cat) {
					txtPetName.setText(((Cat) temp).getCatName());
					txtPetAge.setText("" + ((Cat) temp).getCatAge());
					txtPetType.setText(((Cat) temp).getCatType());
				} else if (temp instanceof Mouse) {
					txtPetName.setText(((Mouse) temp).getMouseName());
					txtPetAge.setText("" + ((Mouse) temp).getMouseAge());
					txtPetType.setText(((Mouse) temp).getMouseType());
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JButton jb = new JButton();

		if (obj instanceof JButton) {
			jb = (JButton) obj;

			switch (jb.getActionCommand()) {
			case "등록":
				insertProtector();
				break;
			case "검색":
				jumin = JOptionPane.showInputDialog("검색할 주민번호를 입력하세요.");
				manager.getRequestSender2().searchProtector(jumin);
				clearText();
				setItem();
				break;
			case "수정":
				temp = list.getSelectedValue();

				if (temp != null) {
					int yesOrNo = JOptionPane.showConfirmDialog(null, "선택한 목록을 수정하시겠습니까?");

					if (yesOrNo == 0) {
						jumin = temp.getJumin();
						updateProtector(jumin);
					}
				} else {
					jumin = JOptionPane.showInputDialog("수정할 주민번호를 입력하세요.");
					updateProtector(jumin);
				}
				setItem();
				clearText();

				break;
			case "삭제":

				if (temp != null) {
					int yesOrNo = JOptionPane.showConfirmDialog(null, "선택한 목록을 삭제하시겠습니까?");

					if (yesOrNo == 0) {
						jumin = temp.getJumin();
						deleteProtector(jumin);
					}
				} else {
					jumin = JOptionPane.showInputDialog("삭제 할 주민번호를 입력하세요.");
					deleteProtector(jumin);
				}
				clearText();
				break;

			case "확인":
				temp = list.getSelectedValue();
				clearText();

				if (temp != null) {
					String info = " 선택된 사람의 정보 ▶ \n";
					info += "이름 :               " + temp.getProtector() + "\n";
					info += "나이 :               " + temp.getAge() + "\n";
					info += "주민번호 :       " + temp.getJumin() + "\n";

					txtName.setText(temp.getJumin());
					txtAge.setText("" + temp.getAge());
					txtJumin.setText(temp.getJumin());

					if (temp instanceof Dog) {
						info += "펫 이름 :          " + ((Dog) temp).getDogName() + "\n";
						info += "펫 나이 :          " + ((Dog) temp).getDogAge() + "\n";
						info += "펫 종류 :          " + ((Dog) temp).getDogType() + "\n";
						txtPetName.setText(((Dog) temp).getDogName());
						txtPetAge.setText("" + ((Dog) temp).getDogAge());
						txtPetType.setText(((Dog) temp).getDogType());
					} else if (temp instanceof Cat) {
						info += "펫 이름 :          " + ((Cat) temp).getCatName() + "\n";
						info += "펫 나이 :          " + ((Cat) temp).getCatAge() + "\n";
						info += "펫 종류 :          " + ((Cat) temp).getCatType() + "\n";
						txtPetName.setText(((Cat) temp).getCatName());
						txtPetAge.setText("" + ((Cat) temp).getCatAge());
						txtPetType.setText(((Cat) temp).getCatType());
					} else if (temp instanceof Mouse) {
						info += "펫 이름 :          " + ((Mouse) temp).getMouseName() + "\n";
						info += "펫 나이 :          " + ((Mouse) temp).getMouseAge() + "\n";
						info += "펫 종류 :          " + ((Mouse) temp).getMouseType() + "\n";
						txtPetName.setText(((Mouse) temp).getMouseName());
						txtPetAge.setText("" + ((Mouse) temp).getMouseAge());
						txtPetType.setText(((Mouse) temp).getMouseType());
					}
					JOptionPane.showMessageDialog(null, info);
				}
				break;
			case "취소":
				setItem();
				clearText();
				closeText();
				break;

			case "▶":
				th = new MusicThread();
				th.start();
				break;
			case "■":
				if (th.isAlive()){
					th.stop();}
				
				else if(th1.isAlive()) {	th1.stop();
				}
				// th.closee();
				break;
			}
		} else if (obj instanceof JComboBox) {
			cb = (JComboBox<String>) obj;

			String item = (String) cb.getSelectedItem();
			clearText();

			switch (item) {
			case "===":
				closeText();
				break;
			case "강아지":
				openText();
				break;
			case "고양이":
				openText();
				break;
			case "햄스터":
				openText();
				break;
			}
		}
	}

	public void insertProtector() {

		String type = (String) cb.getSelectedItem();

		if (txtName.getText().length() == 0 || txtAge.getText().length() == 0 || txtJumin.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "정보를 빠짐없이 입력하세요.");
			return;
		}
		name = txtName.getText();
		try {
			age = Integer.parseInt(txtAge.getText());
		} catch (Exception e) {
			age = Integer.parseInt(JOptionPane.showInputDialog("나이는 숫자로만 입력해주세요."));
			return;
		}
		jumin = txtJumin.getText();

		switch (type) {
		case "강아지":
			String dogName = txtPetName.getText();
			int dogAge = Integer.parseInt(txtPetAge.getText());
			String dogType = txtPetType.getText();
			temp = new Dog(name, age, jumin, dogName, dogAge, dogType);
			break;
		case "고양이":
			String catName = txtPetName.getText();
			int catAge = Integer.parseInt(txtPetAge.getText());
			String catType = txtPetType.getText();
			temp = new Cat(name, age, jumin, catName, catAge, catType);
			break;
		case "햄스터":
			String mouseName = txtPetName.getText();
			int mouseAge = Integer.parseInt(txtPetAge.getText());
			String mouseType = txtPetType.getText();
			temp = new Mouse(name, age, jumin, mouseName, mouseAge, mouseType);
			break;
		}
		manager.getRequestSender2().insertProtector(temp);
		setItem();
		clearText();
	}

	public void updateProtector(String jumin) {

		if (temp == null) {
			JOptionPane.showMessageDialog(null, "수정 결과 : 수정 할 목록이 없습니다.");
			return;
		}

		name = JOptionPane.showInputDialog("수정 할 이름을 입력해주세요.");
		age = Integer.parseInt(JOptionPane.showInputDialog("수정 할 나이를 입력해주세요."));

		if (temp instanceof Dog) {
			String dogName = JOptionPane.showInputDialog("수정 할 펫 이름을 입력해주세요.");
			int dogAge = Integer.parseInt(JOptionPane.showInputDialog("수정 할 펫 나이를 입력해주세요."));
			String dogType = JOptionPane.showInputDialog("수정 할 펫 종을 입력해주세요.");
			temp = new Dog(name, age, jumin, dogName, dogAge, dogType);
		} else if (temp instanceof Cat) {
			String catName = JOptionPane.showInputDialog("수정 할 펫 이름을 입력해주세요.");
			int catAge = Integer.parseInt(JOptionPane.showInputDialog("수정 할 펫 나이를 입력해주세요."));
			String catType = JOptionPane.showInputDialog("수정 할 펫 종을 입력해주세요.");
			temp = new Cat(name, age, jumin, catName, catAge, catType);
		} else if (temp instanceof Mouse) {
			String mouseName = JOptionPane.showInputDialog("수정 할 펫 이름을 입력해주세요.");
			int mouseAge = Integer.parseInt(JOptionPane.showInputDialog("수정 할 펫 나이를 입력해주세요."));
			String mouseType = JOptionPane.showInputDialog("수정 할 펫 종을 입력해주세요.");
			temp = new Mouse(name, age, jumin, mouseName, mouseAge, mouseType);
		}
		manager.getRequestSender2().updateProtector(temp);
	}

	public void deleteProtector(String jumin) {
		// manager.getRequestSender2().searchProtector(jumin);
		manager.getRequestSender2().deleteProtector(jumin);
		// setItem();
		clearText();
	}

	public void clearText() {
		txtName.setText("");
		txtAge.setText("");
		txtName.setText("");
		txtJumin.setText("");
		txtPetName.setText("");
		txtPetAge.setText("");
		txtPetType.setText("");
	}

	public void openText() {
		txtName.setEditable(true);
		txtAge.setEditable(true);
		txtJumin.setEditable(true);
		txtPetName.setEditable(true);
		txtPetAge.setEditable(true);
		txtPetType.setEditable(true);
	}

	public void closeText() {
		txtName.setEditable(false);
		txtAge.setEditable(false);
		txtJumin.setEditable(false);
		txtPetName.setEditable(false);
		txtPetAge.setEditable(false);
		txtPetType.setEditable(false);
	}
}