package br.com.reips.sockets.model;

public class InitClient {

    private int clients = 1;
    private String ip = "localhost";
    private int port = 1234;
    private Type type = Type.TCP;

    public int getClients() {
        return clients;
    }

    public void setClients(final int clients) {
        this.clients = clients;
    }

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

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public enum Type {
        TCP,
        UDP
    }

}
