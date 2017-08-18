package salabatepapo.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatMember implements Runnable {

	private Socket conexao;

	public ChatMember(Socket conexao) {
		this.conexao = conexao;
	}

	@Override
	public void run() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(conexao.getInputStream());
			
			String nickname = scanner.nextLine();
			Participantes.registrar(nickname, conexao);
		
			String linha = "";
			while (!(linha = scanner.nextLine()).toLowerCase().equals("sair")) {
				String mensagem = nickname + ": " + linha + "\n";
				System.out.println(mensagem);
				conexao.getOutputStream().write(mensagem.getBytes());
			}
			
			conexao.getOutputStream().write("Bye bye!".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

}
