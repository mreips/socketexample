package br.com.reips.sockets;

import javax.annotation.PostConstruct;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.reips.sockets.javacustom.TCPCustomServer;
import br.com.reips.sockets.javacustom.UDPCustomServer;
import br.com.reips.sockets.javanative.TCPNativeServer;
import br.com.reips.sockets.javanative.UDPNativeServer;

@SpringBootApplication
public class Main {


	public static final PrettyTime PRETTY = new PrettyTime();

	public static final UDPNativeServer UDP_NATIVE_SERVER = new UDPNativeServer(1234);
	public static final TCPNativeServer TCP_NATIVE_SERVER = new TCPNativeServer(1234);
	
	public static final UDPCustomServer UDP_CUSTOM_SERVER = new UDPCustomServer(1235);
	public static final TCPCustomServer TCP_CUSTOM_SERVER = new TCPCustomServer(1235);

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@PostConstruct
	public void init() {
		TCP_NATIVE_SERVER.start();
		UDP_NATIVE_SERVER.start();
		
		// Using Apache MINA
		TCP_CUSTOM_SERVER.start();
		UDP_CUSTOM_SERVER.start();
	}

}
