package br.com.reips.sockets.service;

import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import br.com.reips.sockets.client.TCPClient;
import br.com.reips.sockets.client.UDPClient;
import br.com.reips.sockets.model.InitClient;

@Service
public class ClientService {

    public void startClient(final InitClient clients) {
        IntStream.of(clients.getClients()).forEach(i -> {
            if (InitClient.Type.TCP.equals(clients.getType())) {
                new TCPClient(clients.getIp(), clients.getPort()).start();
            } else {
                new UDPClient(clients.getIp(), clients.getPort()).start();
            }
        });
    }

}
