package br.com.reips.sockets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.reips.sockets.Main;

@Controller
public class SocketsController {

	@GetMapping("/tcp/native")
	public String tcpNative(Model model) {
		model.addAttribute("server", Main.TCP_NATIVE_SERVER);
		model.addAttribute("type", "TCP Native");
		return "socket";
	}
	
	@GetMapping("/udp/native")
	public String udpNative(Model model) {
		model.addAttribute("server", Main.UDP_NATIVE_SERVER);
		model.addAttribute("type", "UDP Native");
		return "socket";
	}
	
	@GetMapping("/tcp/custom")
	public String tcpCustom(Model model) {
		model.addAttribute("server", Main.TCP_CUSTOM_SERVER);
		model.addAttribute("type", "TCP Custom");
		return "socket";
	}
	
	@GetMapping("/udp/custom")
	public String udpCustom(Model model) {
		model.addAttribute("server", Main.UDP_CUSTOM_SERVER);
		model.addAttribute("type", "UDP Custom");
		return "socket";
	}
	
}
