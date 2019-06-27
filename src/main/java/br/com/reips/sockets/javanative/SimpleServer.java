package br.com.reips.sockets.javanative;

import java.util.HashMap;
import java.util.Map;

import br.com.reips.sockets.model.SimpleClient;

public abstract class SimpleServer extends Thread {
	
	private final Map<String, SimpleClient> clients = new HashMap<>();
	private int port;
	
	public SimpleServer(int port) {
		this.port = port;
	}

	public int getMessagesCount() {
		return getClients().values()
			.stream()
			.map(x -> x.getMessages().size())
			.reduce(0, Integer::sum);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Map<String, SimpleClient> getClients() {
		return clients;
	}

}
