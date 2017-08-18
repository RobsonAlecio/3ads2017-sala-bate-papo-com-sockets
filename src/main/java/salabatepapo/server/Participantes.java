package salabatepapo.server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Participantes {

	private static Map<String, Socket> participantes = new HashMap<>();
	private static ExecutorService executor = Executors.newFixedThreadPool(10);
	
	public static synchronized void registrar(String nickname, Socket conexao) {
		if (!participantes.containsKey(nickname))
			participantes.put(nickname, conexao);
	}

	public static synchronized void enviarMensagem(String mensagem) {
		Set<Entry<String, Socket>> entrySet = participantes.entrySet();
		for (Entry<String, Socket> registro : entrySet) {
			executor.submit(new BroadcastMessage(mensagem, registro.getKey(), registro.getValue()));
		}
	}

	public static void sair(String nickname) {
		participantes.remove(nickname);
	}

}
