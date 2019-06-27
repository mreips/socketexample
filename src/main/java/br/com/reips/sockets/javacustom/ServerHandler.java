package br.com.reips.sockets.javacustom;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import br.com.reips.sockets.model.SimpleClient;

public class ServerHandler extends IoHandlerAdapter {
	
	private final Map<String, SimpleClient> clients = new HashMap<>();
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		InetSocketAddress socketAddress = (InetSocketAddress) session.getRemoteAddress();
		InetAddress inetAddress = socketAddress.getAddress();
		clients.put(session.getRemoteAddress().toString(), new SimpleClient(inetAddress.getHostAddress(), socketAddress.getPort()));
	}
 
    @Override
    public void messageReceived(IoSession session, Object received) throws Exception {
        String message = received.toString();
        System.out.println(session.toString() + ": " + message);
        receiveMessage(session, message);
        session.write(message.toUpperCase());
    }
    
	private void receiveMessage(IoSession session, String message) {
		clients.get(session.getRemoteAddress().toString()).receiveMessage(message);
    }
	
	public Map<String, SimpleClient> getClients() {
		return clients;
	}
}