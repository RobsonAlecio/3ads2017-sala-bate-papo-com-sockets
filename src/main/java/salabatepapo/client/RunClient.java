package salabatepapo.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RunClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 7030);
		Scanner leitorConsole = new Scanner(System.in);
		
		socket.getOutputStream().write((leitorConsole.nextLine() + "\n").getBytes());
		
		Scanner leitorSocket;
		String retorno;
		do {
			socket.getOutputStream().write((leitorConsole.nextLine() + "\n").getBytes());
			leitorSocket = new Scanner(socket.getInputStream());
			retorno = leitorSocket.nextLine();
			System.out.println(retorno);
			
		} while (!"Bye bye!".equals(retorno));
		
		leitorConsole.close();
		leitorSocket.close();
		socket.close();
	}

}
