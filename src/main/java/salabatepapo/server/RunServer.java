package salabatepapo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RunServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(7030);
		
		while (true) {
			Socket conexao = server.accept();
			new Thread(new ChatMember(conexao)).start();
		}
	}

}
