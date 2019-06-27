package br.com.reips.sockets.javacustom;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

import br.com.reips.sockets.model.SimpleClient;

public abstract class SimpleServer {
	private final ServerHandler handler = new ServerHandler();
	
	private final int port;

	public SimpleServer(int port) {
		this.port = port;
	}

	public void start() {
		try {
			IoAcceptor acceptor = getAcceptor();
	        acceptor.getFilterChain().addLast("text-codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));    
			acceptor.setHandler(getHandler());        
	        acceptor.bind(new InetSocketAddress(getPort()));
		} catch(Exception e) {
			System.out.println("Problema no server custom: " + e.toString());
		}
	}
	
	public abstract IoAcceptor getAcceptor();
	
	public int getPort() {
		return port;
	}
	
	public ServerHandler getHandler() {
		return handler;
	}
	
	public Map<String, SimpleClient> getClients() {
		return handler.getClients();
	}
	
	public int getMessagesCount() {
		return getClients().values()
			.stream()
			.map(x -> x.getMessages().size())
			.reduce(0, Integer::sum);
	}
	
}
