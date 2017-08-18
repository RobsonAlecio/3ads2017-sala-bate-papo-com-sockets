package salabatepapo.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RunClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7030);
		socket.getOutputStream().write((new Scanner(System.in).nextLine() + "\n").getBytes());
		
		new Thread(new MessageReceiver(socket)).start();
		new Thread(new MessageSender(socket)).start();
	}

}
