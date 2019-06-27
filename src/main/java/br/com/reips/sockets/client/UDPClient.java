package br.com.reips.sockets.client;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

public class UDPClient extends Client {

    public UDPClient(final String ip, final int port) {
        super(ip, port);
    }

    @Override
    public IoConnector getIoConnector() {
        return new NioDatagramConnector();
    }

}
