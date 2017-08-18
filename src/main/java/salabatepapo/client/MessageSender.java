package salabatepapo.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MessageSender implements Runnable {

	private Socket socket;

	public MessageSender(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		Scanner leitorConsole;
		try {
			do {
				leitorConsole = new Scanner(System.in);
			
				socket.getOutputStream().write((leitorConsole.nextLine() + "\n").getBytes());
			} while (socket.isConnected());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
