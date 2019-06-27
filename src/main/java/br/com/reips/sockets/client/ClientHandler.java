package br.com.reips.sockets.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(final IoSession session, final Object received) throws Exception {
        final String message = received.toString();
        System.out.println(session.toString() + ": " + message);
    }

}