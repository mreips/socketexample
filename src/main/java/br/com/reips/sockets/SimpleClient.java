package br.com.reips.sockets;

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

	public class Message {
		private Date date;
		private String message;

		public Message(Date date, String message) {
			this.date = date;
			this.message = message;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

}
