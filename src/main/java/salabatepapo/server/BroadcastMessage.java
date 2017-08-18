package salabatepapo.server;

import java.io.IOException;
import java.net.Socket;

public class BroadcastMessage implements Runnable {

	private String mensagem;
	private String nickname;
	private Socket conexao;

	public BroadcastMessage(String mensagem, String nickname, Socket conexao) {
		this.mensagem = mensagem;
		this.nickname = nickname;
		this.conexao = conexao;
	}

	@Override
	public void run() {
		System.out.printf("Enviando mensagem '%s' para %s\n", mensagem, nickname);
		
		try {
			conexao.getOutputStream().write((mensagem + '\n').getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
