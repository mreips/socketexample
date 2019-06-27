package br.com.reips.sockets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.reips.sockets.Main;
import br.com.reips.sockets.model.InitClient;
import br.com.reips.sockets.service.ClientService;

@Controller
public class HomeController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/socket")
    public String home(final Model model) {
        model.addAttribute("tcpNative", Main.TCP_NATIVE_SERVER);
        model.addAttribute("udpNative", Main.UDP_NATIVE_SERVER);
        model.addAttribute("tcpCustom", Main.TCP_CUSTOM_SERVER);
        model.addAttribute("udpCustom", Main.UDP_CUSTOM_SERVER);
        model.addAttribute("clients", new InitClient());
        return "home";
    }

    @PostMapping("/start")
    public String startTcp(@ModelAttribute final InitClient clients) {
        clientService.startClient(clients);
        return "redirect:socket";
    }
}
