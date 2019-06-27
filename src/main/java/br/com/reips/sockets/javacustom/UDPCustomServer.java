package br.com.reips.sockets.javacustom;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

public class UDPCustomServer extends SimpleServer {

    public UDPCustomServer(final int port) {
        super(port);
    }

    @Override
    public IoAcceptor getAcceptor() {
        return new NioDatagramAcceptor();
    }

}
