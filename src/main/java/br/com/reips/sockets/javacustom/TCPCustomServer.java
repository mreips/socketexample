package br.com.reips.sockets.javacustom;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class TCPCustomServer extends SimpleServer {

	public TCPCustomServer(final int port) {
		super(port);
	}

	public IoAcceptor getAcceptor() {
		return new NioSocketAcceptor();
	}

}
