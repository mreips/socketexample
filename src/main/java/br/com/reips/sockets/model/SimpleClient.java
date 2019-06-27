package br.com.reips.sockets.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class SimpleClient {

	private String ip;
	private int port;
	private List<Message> messages;

	public SimpleClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
		this.messages = new LinkedList<>();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void receiveMessage(String message) {
		messages.add(new Message(new Date(), message));
	}

}
