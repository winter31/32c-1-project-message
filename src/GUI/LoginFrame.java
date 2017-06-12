package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sendingnote.client.ClientManager;
import sendingnote.vo.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Font;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfUserId;
	private JTextField tfServerIp;
	private JTextField tfuserName;
	private String serverIp;
	public String userId;
	public String name;
	public ClientManager manager;
	public 	MainFrame messageMain;
	public 	petAnimalGUI pet;
	
	public ImageIcon f = new ImageIcon("src\\독수리\\untitled.png");


	public LoginFrame() {  //------------- 바꿈
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 218, 143);
		setSize(new Dimension(437, 392));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
								
										JButton btnLogin = new JButton("Login");
										btnLogin.setBounds(149, 311, 105, 33);
										contentPane.add(btnLogin);
										btnLogin.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 18));
										
												tfServerIp = new JTextField();
												tfServerIp.setBounds(196, 280, 116, 21);
												contentPane.add(tfServerIp);
												tfServerIp.setColumns(10);
												
														JLabel lblNewLabel = new JLabel("Server IP :");
														lblNewLabel.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 18));
														lblNewLabel.setBounds(79, 274, 105, 31);
														contentPane.add(lblNewLabel);
												
														tfUserId = new JTextField();
														tfUserId.setBounds(196, 217, 116, 21);
														contentPane.add(tfUserId);
														tfUserId.setText("");
														tfUserId.setColumns(10);
														
																JLabel lblId = new JLabel("ID :");
																lblId.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 18));
																lblId.setBounds(145, 217, 43, 27);
																contentPane.add(lblId);
																
																		tfuserName = new JTextField(10);
																		tfuserName.setBounds(196, 249, 116, 21);
																		contentPane.add(tfuserName);
																		tfuserName.setText("");
																		
																		JLabel lname = new JLabel("\uC774\uB984 :");
																		lname.setFont(new Font("한컴 바겐세일 M", Font.BOLD, 18));
																		lname.setBounds(137, 242, 51, 33);
																		contentPane.add(lname);
																		
																		JLabel lbl_image2 = new JLabel("");
																		lbl_image2.setBounds(64, 36, 293, 171);
																		ImageIcon icon = new ImageIcon("src\\독수리\\\uCE74\uC640\uC774.png");
																	      java.awt.Image change = icon.getImage().getScaledInstance(lbl_image2.getWidth(), lbl_image2.getHeight(),
																	            java.awt.Image.SCALE_SMOOTH);
																	      lbl_image2.setIcon(new ImageIcon(change));
																		contentPane.add(lbl_image2);
																		
																		JLabel lbl_image = new JLabel("");
																		lbl_image.setBounds(0, 0, 421, 354);
																		lbl_image.setIcon(new ImageIcon("src\\독수리\\%B9%E8%B0%E6_%B8%E9-%C6_%BA%C5%DA.jpg"));
																		contentPane.add(lbl_image);
										btnLogin.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												serverIp = tfServerIp.getText();
												userId = tfUserId.getText();
												name = tfuserName.getText();
												if (userId.equals("")) {
													JOptionPane.showMessageDialog(null, "Input ID");
													return;
												}
												try {
													manager = new ClientManager(serverIp, userId);
													User user = new User(userId, tfuserName.getText(), null);
													manager.getRequestSender().insertUser(user);
													manager.getRequestSender().logIn(user);
													pet = new petAnimalGUI(userId, manager, name);
													pet.setVisible(true);
													messageMain = new MainFrame(userId, manager, name, pet);
													messageMain.setVisible(true);
													setVisible(false);

												} catch (UnknownHostException uhe) {
													JOptionPane.showMessageDialog(null, "아이피를 체크하십시오.");
												} catch (Exception e) {
													JOptionPane.showMessageDialog(null, e.getMessage());
												}
											}
										});
	}

}
