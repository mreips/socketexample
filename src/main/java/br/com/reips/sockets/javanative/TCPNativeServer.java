package br.com.reips.sockets.javanative;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.reips.sockets.model.SimpleClient;

public class TCPNativeServer extends SimpleServer {

    private ServerSocket server;

    public TCPNativeServer(final int port) {
        super(port);
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(getPort());
            setName("TCP Native Listener");
            while (!server.isClosed()) {
                final Socket socket = server.accept();
                new ClientReader(socket).start();
            }
        } catch (final Exception e) {
            System.out.println("Problema no server TCP nativo: " + e.toString());
        }
    }

    public class ClientReader extends Thread {

        private final Socket socket;
        private BufferedReader reader;
        private DataOutputStream writer;

        private final String ip;
        private final SimpleClient me;

        public ClientReader(final Socket socket) throws IOException {
            this.socket = socket;
            this.ip = socket.getInetAddress().getHostAddress();
            this.me = new SimpleClient(ip, socket.getPort());
            getClients().putIfAbsent(ip + ":" + socket.getPort(), me);
        }

        @Override
        public void run() {
            try {
                init();
                while (!socket.isClosed()) {
                    final String message = reader.readLine();
                    me.receiveMessage(message);

                    System.out.println(socket.toString() + ": " + message);

                    writer.writeBytes(message.toUpperCase());
                }
            } catch (final Exception e) {
                System.out.println("Problema cliente TCP nativo: " + e);
            } finally {
                getClients().remove(ip);
            }
        }

        private void init() throws IOException {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new DataOutputStream(socket.getOutputStream());
            setName(ip + socket.getPort() + "-" + getClients().size());
        }
    }
}
