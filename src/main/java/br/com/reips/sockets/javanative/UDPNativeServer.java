package br.com.reips.sockets.javanative;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import br.com.reips.sockets.model.SimpleClient;

public class UDPNativeServer extends SimpleServer {

    private DatagramSocket server;

    public UDPNativeServer(final int port) {
        super(port);
    }

    @Override
    public void run() {
        try {
            server = new DatagramSocket(getPort());
            setName("UDP Native Listener");
            while (!server.isClosed()) {
                final byte[] receiveData = new byte[128];

                final DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
                server.receive(packet);

                final InetAddress address = packet.getAddress();

                final SimpleClient client = getClients().getOrDefault(packet.getSocketAddress().toString(), new SimpleClient(address.getHostName(), packet.getPort()));
                getClients().putIfAbsent(packet.getSocketAddress().toString(), client);

                final String message = new String(packet.getData()).trim();
                client.receiveMessage(message);

                System.out.println(packet.getSocketAddress() + ": " + message);

                server.send(new DatagramPacket(message.toUpperCase().getBytes(), message.toUpperCase().getBytes().length, address, packet.getPort()));
            }
        } catch (final Exception e) {
            System.out.println("Problema no server TCP nativo: " + e.toString());
        }
    }

}