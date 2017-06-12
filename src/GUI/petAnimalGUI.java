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

		btmusicStart = new JButton("��");
		btmusicStart.addActionListener(this);

		btmusicStart.setBackground(Color.PINK);
		btmusicStart.setBounds(51, 32, 65, 23);
		getContentPane().add(btmusicStart);

		btmusicStop = new JButton("��");
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
		lblTitle.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 30));
		// ===============================================================
		JPanel panelWest = new JPanel();
		panelWest.setBackground(Color.PINK);
		panelWest.setBackground(new Color(255, 0, 0, 0));
		panelWest.setBounds(74, 288, 261, 309);
		getContentPane().add(panelWest);
		panelWest.setLayout(null);

		JLabel lblName = new JLabel("��ȣ�� : ");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("���� ���� M", Font.BOLD, 18));
		lblName.setBounds(12, 26, 97, 32);
		panelWest.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(116, 26, 123, 29);
		panelWest.add(txtName);
		txtName.setColumns(10);

		JLabel lblAge = new JLabel("���� : ");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 18));
		lblAge.setBounds(22, 68, 88, 29);
		panelWest.add(lblAge);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(116, 70, 123, 27);
		panelWest.add(txtAge);

		JLabel lblJumin = new JLabel("�ֹι�ȣ : ");
		lblJumin.setHorizontalAlignment(SwingConstants.CENTER);
		lblJumin.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 18));
		lblJumin.setBounds(12, 111, 97, 29);
		panelWest.add(lblJumin);

		txtJumin = new JTextField();
		txtJumin.setColumns(10);
		txtJumin.setBounds(116, 114, 123, 27);
		panelWest.add(txtJumin);

		JLabel lblPetName = new JLabel("�� �̸� :");
		lblPetName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetName.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 18));
		lblPetName.setBounds(12, 163, 97, 29);
		panelWest.add(lblPetName);

		txtPetName = new JTextField();
		txtPetName.setColumns(10);
		txtPetName.setBounds(116, 164, 123, 27);
		panelWest.add(txtPetName);

		JLabel lblPetAge = new JLabel("�� ���� : ");
		lblPetAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetAge.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 18));
		lblPetAge.setBounds(12, 213, 97, 29);
		panelWest.add(lblPetAge);

		txtPetAge = new JTextField();
		txtPetAge.setColumns(10);
		txtPetAge.setBounds(116, 208, 123, 27);
		panelWest.add(txtPetAge);

		JLabel lblPetType = new JLabel("�� ���� : ");
		lblPetType.setHorizontalAlignment(SwingConstants.CENTER);
		lblPetType.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 18));
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

		JLabel label = new JLabel("�� ��� ���� ��");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("���� ���� M", Font.PLAIN, 18));
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
		cb.addItem("������");
		cb.addItem("�����");
		cb.addItem("�ܽ���");
		panelSouth.add(cb);
		cb.addActionListener(this);

		btnInsert = new JButton("���");
		btnInsert.setBackground(Color.PINK);
		btnInsert.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 17));
		btnInsert.setBounds(106, 4, 69, 33);
		btnInsert.addActionListener(this);
		panelSouth.add(btnInsert);

		btnSearch = new JButton("�˻�");
		btnSearch.setBackground(Color.PINK);
		btnSearch.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 17));
		btnSearch.setBounds(202, 4, 77, 33);
		panelSouth.add(btnSearch);
		btnSearch.addActionListener(this);

		btnUpdate = new JButton("����");
		btnUpdate.setBackground(Color.PINK);
		btnUpdate.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 17));
		btnUpdate.setBounds(303, 4, 77, 33);
		panelSouth.add(btnUpdate);
		btnUpdate.addActionListener(this);

		btnDelete = new JButton("����");
		btnDelete.setBackground(Color.PINK);
		btnDelete.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 17));
		btnDelete.setBounds(407, 4, 77, 33);
		panelSouth.add(btnDelete);
		btnDelete.addActionListener(this);

		btnOk = new JButton("Ȯ��");
		btnOk.setBackground(Color.PINK);
		btnOk.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 17));
		btnOk.setBounds(511, 4, 77, 33);
		panelSouth.add(btnOk);
		btnOk.addActionListener(this);

		btnCancel = new JButton("���");
		btnCancel.setBackground(Color.PINK);
		btnCancel.setFont(new Font("���� �ٰռ��� M", Font.BOLD, 17));
		btnCancel.setBounds(611, 4, 77, 33);
		panelSouth.add(btnCancel);

		lblImage2 = new JLabel("");
		lblImage2.setHorizontalAlignment(SwingConstants.LEFT);
		lblImage2.setBounds(142, 10, 566, 309);
		ImageIcon icon = new ImageIcon("src\\������\\\uCE74\uC640\uC774.png");
		java.awt.Image change = icon.getImage().getScaledInstance(lblImage2.getWidth(), lblImage2.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		lblImage2.setIcon(new ImageIcon(change));
		getContentPane().add(lblImage2);

		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon("src\\������\\%B9%E8%B0%E6_%B8%E9-%C6_%BA%C5%DA.jpg"));
		lblImage.setBounds(0, 0, 862, 642);
		getContentPane().add(lblImage);

		ImageIcon icon2 = new ImageIcon("src\\������\\welcom.jpg");

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
			case "���":
				insertProtector();
				break;
			case "�˻�":
				jumin = JOptionPane.showInputDialog("�˻��� �ֹι�ȣ�� �Է��ϼ���.");
				manager.getRequestSender2().searchProtector(jumin);
				clearText();
				setItem();
				break;
			case "����":
				temp = list.getSelectedValue();

				if (temp != null) {
					int yesOrNo = JOptionPane.showConfirmDialog(null, "������ ����� �����Ͻðڽ��ϱ�?");

					if (yesOrNo == 0) {
						jumin = temp.getJumin();
						updateProtector(jumin);
					}
				} else {
					jumin = JOptionPane.showInputDialog("������ �ֹι�ȣ�� �Է��ϼ���.");
					updateProtector(jumin);
				}
				setItem();
				clearText();

				break;
			case "����":

				if (temp != null) {
					int yesOrNo = JOptionPane.showConfirmDialog(null, "������ ����� �����Ͻðڽ��ϱ�?");

					if (yesOrNo == 0) {
						jumin = temp.getJumin();
						deleteProtector(jumin);
					}
				} else {
					jumin = JOptionPane.showInputDialog("���� �� �ֹι�ȣ�� �Է��ϼ���.");
					deleteProtector(jumin);
				}
				clearText();
				break;

			case "Ȯ��":
				temp = list.getSelectedValue();
				clearText();

				if (temp != null) {
					String info = " ���õ� ����� ���� �� \n";
					info += "�̸� :               " + temp.getProtector() + "\n";
					info += "���� :               " + temp.getAge() + "\n";
					info += "�ֹι�ȣ :       " + temp.getJumin() + "\n";

					txtName.setText(temp.getJumin());
					txtAge.setText("" + temp.getAge());
					txtJumin.setText(temp.getJumin());

					if (temp instanceof Dog) {
						info += "�� �̸� :          " + ((Dog) temp).getDogName() + "\n";
						info += "�� ���� :          " + ((Dog) temp).getDogAge() + "\n";
						info += "�� ���� :          " + ((Dog) temp).getDogType() + "\n";
						txtPetName.setText(((Dog) temp).getDogName());
						txtPetAge.setText("" + ((Dog) temp).getDogAge());
						txtPetType.setText(((Dog) temp).getDogType());
					} else if (temp instanceof Cat) {
						info += "�� �̸� :          " + ((Cat) temp).getCatName() + "\n";
						info += "�� ���� :          " + ((Cat) temp).getCatAge() + "\n";
						info += "�� ���� :          " + ((Cat) temp).getCatType() + "\n";
						txtPetName.setText(((Cat) temp).getCatName());
						txtPetAge.setText("" + ((Cat) temp).getCatAge());
						txtPetType.setText(((Cat) temp).getCatType());
					} else if (temp instanceof Mouse) {
						info += "�� �̸� :          " + ((Mouse) temp).getMouseName() + "\n";
						info += "�� ���� :          " + ((Mouse) temp).getMouseAge() + "\n";
						info += "�� ���� :          " + ((Mouse) temp).getMouseType() + "\n";
						txtPetName.setText(((Mouse) temp).getMouseName());
						txtPetAge.setText("" + ((Mouse) temp).getMouseAge());
						txtPetType.setText(((Mouse) temp).getMouseType());
					}
					JOptionPane.showMessageDialog(null, info);
				}
				break;
			case "���":
				setItem();
				clearText();
				closeText();
				break;

			case "��":
				th = new MusicThread();
				th.start();
				break;
			case "��":
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
			case "������":
				openText();
				break;
			case "�����":
				openText();
				break;
			case "�ܽ���":
				openText();
				break;
			}
		}
	}

	public void insertProtector() {

		String type = (String) cb.getSelectedItem();

		if (txtName.getText().length() == 0 || txtAge.getText().length() == 0 || txtJumin.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "������ �������� �Է��ϼ���.");
			return;
		}
		name = txtName.getText();
		try {
			age = Integer.parseInt(txtAge.getText());
		} catch (Exception e) {
			age = Integer.parseInt(JOptionPane.showInputDialog("���̴� ���ڷθ� �Է����ּ���."));
			return;
		}
		jumin = txtJumin.getText();

		switch (type) {
		case "������":
			String dogName = txtPetName.getText();
			int dogAge = Integer.parseInt(txtPetAge.getText());
			String dogType = txtPetType.getText();
			temp = new Dog(name, age, jumin, dogName, dogAge, dogType);
			break;
		case "�����":
			String catName = txtPetName.getText();
			int catAge = Integer.parseInt(txtPetAge.getText());
			String catType = txtPetType.getText();
			temp = new Cat(name, age, jumin, catName, catAge, catType);
			break;
		case "�ܽ���":
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
			JOptionPane.showMessageDialog(null, "���� ��� : ���� �� ����� �����ϴ�.");
			return;
		}

		name = JOptionPane.showInputDialog("���� �� �̸��� �Է����ּ���.");
		age = Integer.parseInt(JOptionPane.showInputDialog("���� �� ���̸� �Է����ּ���."));

		if (temp instanceof Dog) {
			String dogName = JOptionPane.showInputDialog("���� �� �� �̸��� �Է����ּ���.");
			int dogAge = Integer.parseInt(JOptionPane.showInputDialog("���� �� �� ���̸� �Է����ּ���."));
			String dogType = JOptionPane.showInputDialog("���� �� �� ���� �Է����ּ���.");
			temp = new Dog(name, age, jumin, dogName, dogAge, dogType);
		} else if (temp instanceof Cat) {
			String catName = JOptionPane.showInputDialog("���� �� �� �̸��� �Է����ּ���.");
			int catAge = Integer.parseInt(JOptionPane.showInputDialog("���� �� �� ���̸� �Է����ּ���."));
			String catType = JOptionPane.showInputDialog("���� �� �� ���� �Է����ּ���.");
			temp = new Cat(name, age, jumin, catName, catAge, catType);
		} else if (temp instanceof Mouse) {
			String mouseName = JOptionPane.showInputDialog("���� �� �� �̸��� �Է����ּ���.");
			int mouseAge = Integer.parseInt(JOptionPane.showInputDialog("���� �� �� ���̸� �Է����ּ���."));
			String mouseType = JOptionPane.showInputDialog("���� �� �� ���� �Է����ּ���.");
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