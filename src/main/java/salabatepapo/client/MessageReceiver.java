package salabatepapo.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MessageReceiver implements Runnable {

	private Socket socket;

	public MessageReceiver(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		Scanner leitorSocket = null;
		String retorno;
		try {
			leitorSocket = new Scanner(socket.getInputStream());
			do {
				retorno = leitorSocket.nextLine();
				System.out.println(retorno);
			} while (!"Bye bye!".equals(retorno));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			leitorSocket.close();
		}
	}

}
