package sendingnote.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import GUI.MainFrame;
import GUI.petAnimalGUI;
import sendingnote.vo.User;

public class ClientManager {
	private Socket socket;
	private Socket socket1;
	private RequestSender requestSender;
	private ResponseReceiver responseRecevier;
	private RequestSender2 requestSender2;
	private ResponseReceiver2 responseRecevier2;
	private MainFrame mainFrame;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	ObjectOutputStream oos1;
	ObjectInputStream ois1;
	String userId;
	Thread thread;
	petAnimalGUI pet;
	
	public ClientManager(String serverIp, String userId) throws UnknownHostException, IOException {
		this.userId = userId;
		socket = new Socket(serverIp, 8889);
		socket1 = new Socket(serverIp, 8880);
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		oos1 = new ObjectOutputStream(socket1.getOutputStream());
		ois1 = new ObjectInputStream(socket1.getInputStream());
		requestSender = new RequestSender(oos, userId);
		responseRecevier = new ResponseReceiver(ois);
		requestSender2 = new RequestSender2(oos1, userId);
		responseRecevier2 = new ResponseReceiver2(ois1);
	}
	public ClientManager() {
		
	}

	public RequestSender getRequestSender() {
		return requestSender;
	}

	public ResponseReceiver getResponseRecevier() {
		return responseRecevier;
	}
	
	public RequestSender2 getRequestSender2() {
		return requestSender2;
	}

	public ResponseReceiver2 getResponseRecevier2() {
		return responseRecevier2;
	}

	
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		requestSender.setMainFrame(mainFrame);
		responseRecevier.setMainFrame(mainFrame);
		responseRecevier.start();
		
	}
	
	public petAnimalGUI getMainFrame2() {
		return pet;
	}

	public void setMainFrame2(petAnimalGUI pet) {
		this.pet = pet;
		requestSender2.setMainPet(pet);
		responseRecevier2.setPetgui(pet);
		responseRecevier2.start();
		
	}
	
	public void finishThread(){
		responseRecevier.stop();
	}
	
}
