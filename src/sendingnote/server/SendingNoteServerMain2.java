package sendingnote.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class SendingNoteServerMain2 {
	private ServerSocket ssoc;
	private Socket clientSocket;
	public HashMap<String, MyThread2> clientThreadMap1 = new HashMap<>();
	
	public SendingNoteServerMain2() {
		try {
			ssoc = new ServerSocket(8880);
			System.out.println("Server open");
			
			while(true){
			clientSocket = ssoc.accept();
			System.out.println("A client connected");
			
			MyThread2 thread = new MyThread2(clientSocket, this);
			thread.start();
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	SendingNoteServerMain2 pet = new SendingNoteServerMain2();
	}
}
