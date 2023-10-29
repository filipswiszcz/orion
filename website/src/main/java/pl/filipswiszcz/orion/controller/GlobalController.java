package pl.filipswiszcz.orion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GlobalController {

    String world = "Osiris";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("world", world);
        return "hello";
    }
    
}
