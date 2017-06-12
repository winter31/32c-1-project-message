package sendingnote.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class SendingNoteServerMain {
	private ServerSocket ssoc1;
	private Socket clientSocket;
	public HashMap<String, MyThread> clientThreadMap = new HashMap<>();

	public SendingNoteServerMain() {
		try {
			ssoc1 = new ServerSocket(8889);
			System.out.println("Server open");

			while (true) {
				clientSocket = ssoc1.accept();
				System.out.println("A client connected");

				MyThread thread = new MyThread(clientSocket, this);
				thread.start();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SendingNoteServerMain message = new SendingNoteServerMain();
	}
}
