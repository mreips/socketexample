package br.com.reips.sockets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.reips.sockets.Main;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("tcpNative", Main.TCP_NATIVE_SERVER);
		model.addAttribute("udpNative", Main.UDP_NATIVE_SERVER);
		model.addAttribute("tcpCustom", Main.TCP_CUSTOM_SERVER);
		model.addAttribute("udpCustom", Main.UDP_CUSTOM_SERVER);
		return "home";
	}
}
