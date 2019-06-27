package br.com.reips.sockets.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

public abstract class Client extends Thread {

    private String ip;
    private int port;

    public Client(final String ip, final int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        final IoConnector connector = getIoConnector();
        connector.getSessionConfig().setReadBufferSize(2048);

        connector.getFilterChain().addLast("text-codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        connector.setHandler(new ClientHandler());
        final ConnectFuture future = connector.connect(new InetSocketAddress(ip, port));
        future.awaitUninterruptibly();

        if (!future.isConnected()) {
            return;
        }
        setName("Client " + connector.getTransportMetadata().getName());
        final IoSession session = future.getSession();
        while (session.isConnected()) {
            try {
                session.write("Message " + session.getWrittenMessages());
                sleep(TimeUnit.SECONDS.toMillis(new Random().nextInt(10)));
            } catch (final Exception e) {
                System.out.println("Problema no cliente: " + e.getMessage());
            }
        }
    }

    public abstract IoConnector getIoConnector();

    public String getIp() {
        return ip;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

}
