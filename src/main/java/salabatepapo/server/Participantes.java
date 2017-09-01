package salabatepapo.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Participantes {

	private static Map<String, Socket> participantes = new HashMap<>();
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	private static List<String> historico = new ArrayList<>();
	
	public static synchronized void registrar(String nickname, Socket conexao) {
		if (!participantes.containsKey(nickname)) {
			participantes.put(nickname, conexao);
			enviarHistorico(nickname, conexao);
		}
	}

	private static void enviarHistorico(String nickname, Socket conexao) {
		for (String mensagemAntiga : historico) {
			sendBroadcastMessage(mensagemAntiga, nickname, conexao);
		}
	}

	public static synchronized void enviarMensagem(String mensagem) {
		historico.add(mensagem);
		
		Set<Entry<String, Socket>> entrySet = participantes.entrySet();
		for (Entry<String, Socket> registro : entrySet) {
			sendBroadcastMessage(mensagem, registro.getKey(), registro.getValue());
		}
	}

	private static void sendBroadcastMessage(String mensagem, String nickname, Socket conexao) {
		executor.submit(new BroadcastMessage(mensagem, nickname, conexao));
	}

	public static void sair(String nickname) {
		participantes.remove(nickname);
	}

}
