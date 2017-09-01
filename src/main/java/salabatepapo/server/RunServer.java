package salabatepapo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import salabatepapo.Port;

public class RunServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(Port.NUMBER);
		
		while (true) {
			Socket conexao = server.accept();
			new Thread(new ChatMember(conexao)).start();
		}
	}

}
